package com.chenyuwei.loadimageview;


/**
 * Created by vivi on 2016/7/23.
 */
public class Options {
    private int defaultSrc;
    private Shape shape;
    public static final int IMG_DEFAULT = R.drawable.img_default;

    public Options() {
        this.defaultSrc = IMG_DEFAULT;
        this.shape = Shape.DEFAULT;
    }

    public Options(Builder builder) {
        this.defaultSrc = R.drawable.img_default;
        this.shape = Shape.DEFAULT;
        this.defaultSrc = builder.defaultSrc;
        this.shape = builder.shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setDefaultSrc(int defaultSrc) {
        this.defaultSrc = defaultSrc;
    }

    public int getDefaultSrc() {
        return this.defaultSrc;
    }

    public Shape getShape() {
        return this.shape;
    }

    public static class Builder {
        private int defaultSrc;
        private Shape shape;

        public Builder() {
            this.defaultSrc = IMG_DEFAULT;
            this.shape = Shape.DEFAULT;
        }

        public Builder setDefaultSrc(int defaultSrc) {
            this.defaultSrc = defaultSrc;
            return this;
        }

        public Builder setShape(Shape shape) {
            this.shape = shape;
            return this;
        }

        public Options build() {
            return new Options(this);
        }
    }

    public enum Shape {
        DEFAULT,
        CIRCLE,
        ROUND
    }
}