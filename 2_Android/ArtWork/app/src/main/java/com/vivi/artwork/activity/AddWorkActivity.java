package com.vivi.artwork.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
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
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.baoyz.actionsheet.ActionSheet;
import com.chenyuwei.basematerial.activity.BaseActivity;
import com.chenyuwei.basematerial.view.dialog.WaitDialog;
import com.chenyuwei.loadimageview.LoadImageView;
import com.vivi.artwork.R;
import com.vivi.artwork.http.ConnectPHPToUpLoadFile;
import com.vivi.artwork.http.RequestMaker;
import com.vivi.artwork.http.ServiceFactory;
import com.vivi.artwork.model.json.base.BaseModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

import static com.vivi.artwork.MyApplication.PERMISSION_CAMERA;
import static com.vivi.artwork.MyApplication.PERMISSION_STORAGE;

public class AddWorkActivity extends BaseActivity {

    private static final int REQUEST_PICTURE =0;
    private static final int REQUEST_CAMERA= 1;
    private static final int REQUEST_CROP = 2;


    private Bitmap photo;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMddHHmmss", Locale.CHINA);
    private String timeString;
    private String photoPath;

    private LoadImageView ivWork;
    private EditText etType;

    @Override
    protected int onBindView() {
        return R.layout.activity_add_work;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(R.id.toolbar);
        setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.btnDone);
        ivWork = (LoadImageView) findViewById(R.id.ivWork);
        etType = (EditText) findViewById(R.id.etType);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.btnDone:
                String img = photoPath.substring(photoPath.lastIndexOf("/") + 1);
                String type = etType.getText().toString();
                if (TextUtils.isEmpty(img)){
                    toast("请上传作品");
                }
                else if (TextUtils.isEmpty(type)){
                    toast("请填写分类名称");
                }
                else {
                    final WaitDialog dialog = new WaitDialog(activity);
                    dialog.show();
                    new RequestMaker<BaseModel>(activity, ServiceFactory.getWorkService().add(getUid(),type,img)){

                        @Override
                        protected void onSuccess(final BaseModel user) {
                            Observable.timer(1, TimeUnit.SECONDS)
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Consumer<Long>() {
                                        @Override
                                        public void accept(Long aLong) throws Exception {
                                            toast("上传成功");
                                            dialog.dismiss();
                                            finish();
                                        }
                                    }) ;
                        }

                        @Override
                        protected void onFail(int code, String msg) {
                            super.onFail(code, msg);
                            toast("上传失败");
                            dialog.dismiss();
                        }
                    };
                }
                break;
            case R.id.ivWork:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    activity.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    }, PERMISSION_STORAGE);
                }
                else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    activity.requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISSION_CAMERA);
                }
                else showSheet();
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

    private Handler upLoadFileHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    ivWork.load(photo);
                    break;
                case 1:
                    Toast.makeText(activity, "文件上传失败",Toast.LENGTH_SHORT).show();break;
            }
        }
    };
}