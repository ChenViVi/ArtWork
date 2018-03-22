package com.chenyuwei.loadimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.ByteArrayOutputStream;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by vivi on 2016/7/23.
 */
public class ImageLoader {
    public ImageLoader() {
    }

    public static void with(Context context, final View view, String url) {
        if (view instanceof ImageView){
            Glide.with(context.getApplicationContext()).load(url).placeholder(Options.IMG_DEFAULT).error(Options.IMG_DEFAULT).into((ImageView)view);
        }
        else{
            Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            Drawable drawable = new BitmapDrawable(resource);
                            view.setBackgroundDrawable(drawable);
                        }
                    });
        }
    }

    public static void with(Context context, final View view, String url, int width, int height) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>(width, height) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Drawable drawable = new BitmapDrawable(resource);
                        view.setBackgroundDrawable(drawable);
                    }
                });
    }

    public static void with(Context context, ImageView imageView, int resourceId) {
        Glide.with(context.getApplicationContext()).load(resourceId).placeholder(Options.IMG_DEFAULT).error(Options.IMG_DEFAULT).into(imageView);
    }

    public static void with(Context context, ImageView imageView, Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        Glide.with(context.getApplicationContext()).load(baos.toByteArray()).error((new Options()).getDefaultSrc()).into(imageView);
    }

    public static void with(Context context, final ImageView imageView, String url, final ImageListener listener) {
        Glide.with(context.getApplicationContext()).load(url).placeholder(Options.IMG_DEFAULT).error(Options.IMG_DEFAULT).into(new GlideDrawableImageViewTarget(imageView) {
            public void onStart() {
                super.onStart();
                imageView.setImageResource(new Options().getDefaultSrc());
                listener.onStart();
            }

            public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                super.onResourceReady(drawable, anim);
                listener.onFinish();
            }

            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);
                listener.onFailed();
            }
        });
    }

    public static void with(Context context, final ImageView imageView, int resourceId, final ImageListener listener) {
        Glide.with(context.getApplicationContext()).load(resourceId).placeholder(Options.IMG_DEFAULT).error(Options.IMG_DEFAULT).into(new GlideDrawableImageViewTarget(imageView) {
            public void onStart() {
                super.onStart();
                listener.onStart();
            }

            public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                super.onResourceReady(drawable, anim);
                listener.onFinish();
            }

            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);
                listener.onFailed();
            }
        });
    }

    public static void with(Context context, final ImageView imageView, Bitmap bitmap, final ImageListener listener) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        Glide.with(context.getApplicationContext()).load(baos.toByteArray()).placeholder(Options.IMG_DEFAULT).error(Options.IMG_DEFAULT).into(new GlideDrawableImageViewTarget(imageView) {
            public void onStart() {
                super.onStart();
                listener.onStart();
            }

            public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                super.onResourceReady(drawable, anim);
                listener.onFinish();
            }

            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);
                listener.onFailed();
            }
        });
    }

    public static void with(Context context, ImageView imageView, String url, Options options) {
        switchShape(context.getApplicationContext(), url, new GlideDrawableImageViewTarget(imageView), options);
    }

    public static void with(Context context, ImageView imageView, int resourceId, Options options) {
        switchShape(context.getApplicationContext(), resourceId, new GlideDrawableImageViewTarget(imageView), options);
    }

    public static void with(Context context, ImageView imageView, Bitmap bitmap, Options options) {
        switchShape(context.getApplicationContext(), bitmap, new GlideDrawableImageViewTarget(imageView), options);
    }

    public static void with(Context context, ImageView imageView, String url, final ImageListener listener, Options options) {
        GlideDrawableImageViewTarget target = new GlideDrawableImageViewTarget(imageView) {
            public void onStart() {
                super.onStart();
                listener.onStart();
            }

            public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                super.onResourceReady(drawable, anim);
                listener.onFinish();
            }

            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);
                listener.onFailed();
            }
        };
        switchShape(context.getApplicationContext(), url, target, options);
    }

    public static void with(Context context, final ImageView imageView, int resourceId, final ImageListener listener, Options options) {
        GlideDrawableImageViewTarget target = new GlideDrawableImageViewTarget(imageView) {
            public void onStart() {
                super.onStart();
                listener.onStart();
            }

            public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                super.onResourceReady(drawable, anim);
                listener.onFinish();
            }

            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);
                listener.onFailed();
            }
        };
        switchShape(context.getApplicationContext(), resourceId, target, options);
    }

    public static void with(Context context, final ImageView imageView, Bitmap bitmap, final ImageListener listener, Options options) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        GlideDrawableImageViewTarget target = new GlideDrawableImageViewTarget(imageView) {
            public void onStart() {
                super.onStart();
                listener.onStart();
            }

            @Override
            public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                super.onResourceReady(drawable, anim);
                listener.onFinish();
            }

            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);
                listener.onFailed();
            }
        };
        switchShape(context.getApplicationContext(), bitmap, target, options);
    }

    private static void switchShape(Context context, String url, ImageViewTarget<GlideDrawable> target, Options options) {
        switch(options.getShape()) {
            case DEFAULT:
                Glide.with(context.getApplicationContext()).load(url).placeholder(options.getDefaultSrc()).error(options.getDefaultSrc()).into(target);
                break;
            case CIRCLE:
                Glide.with(context.getApplicationContext()).load(url).placeholder(options.getDefaultSrc()).error(options.getDefaultSrc()).bitmapTransform(new CropCircleTransformation(context)).into(target);
                break;
            case ROUND:
                Glide.with(context.getApplicationContext()).load(url).placeholder(options.getDefaultSrc()).error(options.getDefaultSrc()).bitmapTransform(new RoundedCornersTransformation(context,30,0, RoundedCornersTransformation.CornerType.ALL)).into(target);
        }

    }

    private static void switchShape(Context context, int resourceId, ImageViewTarget<GlideDrawable> target, Options options) {
        switch(options.getShape()) {
            case DEFAULT:
                Glide.with(context.getApplicationContext()).load(resourceId).placeholder(options.getDefaultSrc()).error(options.getDefaultSrc()).into(target);
                break;
            case CIRCLE:
                Glide.with(context.getApplicationContext()).load(resourceId).placeholder(options.getDefaultSrc()).error(options.getDefaultSrc()).bitmapTransform(new CropCircleTransformation(context)).into(target);
                break;
            case ROUND:
                Glide.with(context.getApplicationContext()).load(resourceId).placeholder(options.getDefaultSrc()).error(options.getDefaultSrc()).bitmapTransform(new RoundedCornersTransformation(context,30,0, RoundedCornersTransformation.CornerType.ALL)).into(target);
        }

    }

    private static void switchShape(Context context, Bitmap bitmap, ImageViewTarget<GlideDrawable> target, Options options) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        switch(options.getShape()) {
            case DEFAULT:
                Glide.with(context.getApplicationContext()).load(baos.toByteArray()).placeholder(options.getDefaultSrc()).error(options.getDefaultSrc()).into(target);
                break;
            case CIRCLE:
                Glide.with(context.getApplicationContext()).load(baos.toByteArray()).placeholder(options.getDefaultSrc()).error(options.getDefaultSrc()).bitmapTransform(new CropCircleTransformation(context)).into(target);
                break;
            case ROUND:
                Glide.with(context.getApplicationContext()).load(baos.toByteArray()).placeholder(options.getDefaultSrc()).error(options.getDefaultSrc()).bitmapTransform(new RoundedCornersTransformation(context,30,0, RoundedCornersTransformation.CornerType.ALL)).into(target);
        }

    }
}