package com.chenyuwei.loadimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by vivi on 2016/7/23.
 */
public class LoadImageView extends ImageView implements ImageListener {
    private Context context;
    private String url;
    private Options options = new Options();

    public LoadImageView(Context context) {
        super(context);
        this.context = context;
    }

    public LoadImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.LoadImageView);
        options.setDefaultSrc(a.getResourceId(R.styleable.LoadImageView_failed_src,R.drawable.img_default));
        switch (a.getInt(R.styleable.LoadImageView_shape,-1)){
            case 0:
                options.setShape(Options.Shape.DEFAULT);
                break;
            case 1:
                options.setShape(Options.Shape.CIRCLE);
                break;
            case 2:
                options.setShape(Options.Shape.ROUND);
                break;
        }
        if (a.getString(R.styleable.LoadImageView_load_src) != null){
            load(a.getString(R.styleable.LoadImageView_load_src));
        }
        a.recycle();
    }

    public LoadImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void load(String url) {
        this.url = url;
        ImageLoader.with(this.context, this, url, this, options);
    }

    public void load(int resourceId) {
        ImageLoader.with(this.context, this, resourceId, this, options);
    }

    public void load(Bitmap bitmap) {
        ImageLoader.with(this.context, this, bitmap, this, options);
    }

    public LoadImageView setShaple(Options.Shape shape) {
        this.options.setShape(shape);
        return this;
    }

    public LoadImageView setDefaultSrc(int resourceId) {
        this.options.setDefaultSrc(resourceId);
        return this;
    }

    public void onStart() {
    }

    public void onFinish() {
    }

    public void onFailed() {
        Log.e("LoadImageViewFailed", "url= " + this.url);
    }
}
