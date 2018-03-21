package com.vivi.artwork.activity;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.baoyz.actionsheet.ActionSheet;
import com.chenyuwei.basematerial.activity.BaseActivity;
import com.chenyuwei.basematerial.util.Tool;
import com.chenyuwei.basematerial.view.dialog.WaitDialog;
import com.chenyuwei.loadimageview.LoadImageView;
import com.vivi.artwork.R;
import com.vivi.artwork.http.ConnectPHPToUpLoadFile;
import com.vivi.artwork.http.RequestMaker;
import com.vivi.artwork.http.ServiceFactory;
import com.vivi.artwork.model.User;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

import static com.vivi.artwork.MyApplication.PERMISSION_STORAGE;

public class RegisterActivity extends BaseActivity {

    private LoadImageView ivAvatar;
    private EditText etName;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etBirth;
    private RadioButton rbFemale;

    private static final int REQUEST_PICTURE =0;
    private static final int REQUEST_CAMERA= 1;
    private static final int REQUEST_CROP = 2;

    private Bitmap photo;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMddHHmmss", Locale.CHINA);
    SimpleDateFormat birthFormat = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
    private String timeString;
    private String photoPath;

    @Override
    protected int onBindView() {
        return R.layout.activity_register;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(R.id.toolbar);
        setDisplayHomeAsUpEnabled(true);
        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etBirth = (EditText) findViewById(R.id.etBirth);
        ivAvatar = (LoadImageView) findViewById(R.id.ivAvatar);
        rbFemale = (RadioButton) findViewById(R.id.rbFemale);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.ivAvatar:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    activity.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, PERMISSION_STORAGE);
                }
                else showSheet();
                break;
            case R.id.etBirth:
                new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        etBirth.setText(year+"年"+(monthOfYear+1)+"月"+dayOfMonth+"日");
                    }
                },1997, 6, 15).show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                File temp = new File(Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/" + timeString + ".jpg");
                startPhotoZoom(Uri.fromFile(temp));
            } else if (requestCode == REQUEST_PICTURE) {
                startPhotoZoom(data.getData());
            } else if (requestCode == REQUEST_CROP) {
                if (data != null) {
                    setPicToView(data);
                }
            }
        }
    }

    private void showSheet(){
        ActionSheet.createBuilder(activity,getSupportFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles("相册", "拍照")
                .setCancelableOnTouchOutside(true)
                .setListener(new ActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {
                    }
                    @Override
                    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
                        switch (index) {
                            case 0:
                                Intent picture = new Intent(Intent.ACTION_PICK, null);
                                picture.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                                startActivityForResult(picture, REQUEST_PICTURE);
                                break;
                            case 1:
                                Date date = new Date(System.currentTimeMillis());
                                timeString = dateFormat.format(date);
                                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                camera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera", timeString + ".jpg")));
                                startActivityForResult(camera, REQUEST_CAMERA);
                                break;
                        }
                    }
                }).show();
    }

    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        if (extras != null) {
            photo = extras.getParcelable("data");
            photoPath = saveImage(photo);
            new Thread(new ConnectPHPToUpLoadFile(photoPath, upLoadFileHandler, "http://139.199.32.74/files/up_file.php")).start();
        }
    }

    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, REQUEST_CROP);
    }
    private String saveImage(Bitmap bitmap){
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bStream);
        String time = String.valueOf(System.currentTimeMillis());
        try {
            FileOutputStream outputStream = openFileOutput(time+".jpg", Activity.MODE_WORLD_READABLE);
            outputStream.write(bStream.toByteArray());
            Log.e("output","saveImage() success");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e("output","saveImage() error FileNotFoundException");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("output","saveImage() error IOException");
        }
        return getFilesDir() + "/" + time+".jpg";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_done:
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String birth = etBirth.getText().toString();
                String avatar = photoPath.substring(photoPath.lastIndexOf("/") + 1);
                int sex = 0;
                if (rbFemale.isChecked()) sex = 1;
                if (TextUtils.isEmpty(avatar)){
                    toast("请上传头像");
                }
                else if (TextUtils.isEmpty(name)){
                    toast("请填写姓名");
                }
                else if (TextUtils.isEmpty(email)){
                    toast("请填写邮箱");
                }
                else if (Tool.isEmail(email)){
                    toast("邮箱格式错误");
                }
                else if (TextUtils.isEmpty("密码")){
                    toast("请填写密码");
                }
                else if (password.length() < 6){
                    toast("密码不能小于6位");
                }
                else if (TextUtils.isEmpty(birth)){
                    toast("请填写邮箱");
                }
                else {
                    long birthStamp = 0;
                    try {
                        birthStamp = birthFormat.parse(birth).getTime();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    final WaitDialog dialog = new WaitDialog(activity);
                    dialog.show();
                    new RequestMaker<User>(activity, ServiceFactory.getUserService().register(email,password,name,avatar,birthStamp,sex)){

                        @Override
                        protected void onSuccess(final User user) {
                            Observable.timer(1, TimeUnit.SECONDS)
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Consumer<Long>() {
                                        @Override
                                        public void accept(Long aLong) throws Exception {
                                            SharedPreferences.Editor editor = preferences.edit();
                                            editor.putLong("uid",user.getData().getUser().getId());
                                            editor.putString("name",user.getData().getUser().getName());
                                            editor.putString("avatar",user.getData().getUser().getAvatar());
                                            editor.apply();
                                            dialog.dismiss();
                                            startActivity(MainActivity.class);
                                            setResult(WelcomeActivity.RESULT_DESTROY);
                                            finish();
                                        }
                                    }) ;
                        }

                        @Override
                        protected void onFail(int code, String msg) {
                            super.onFail(code, msg);
                            dialog.dismiss();
                        }
                    };
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private Handler upLoadFileHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    ivAvatar.load(photo);
                    break;
                case 1:Toast.makeText(activity, "文件上传失败",Toast.LENGTH_SHORT).show();break;
            }
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showSheet();
            } else {
                toast("请给予存储权限！");
            }
        }
    }
}
