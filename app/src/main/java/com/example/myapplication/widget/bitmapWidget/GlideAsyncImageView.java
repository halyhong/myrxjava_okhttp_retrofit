package com.example.myapplication.widget.bitmapWidget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.example.myapplication.MyApplication;
import com.example.myapplication.utils.DimenSdkUtils;

public class GlideAsyncImageView extends AppCompatImageView {
    //    public static final String DEFAULT_URL = "http://img.store.ksmobile.net/cmnow/20160716/4/2267_31bf153c_146866762728_315_315.png";
    private String mUrl;

    public GlideAsyncImageView(Context context) {
        super(context);
    }

    public GlideAsyncImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GlideAsyncImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private RequestOptions mRequestOptions;
    private synchronized RequestOptions getBaseRequestOptions(){
        if(null == mRequestOptions){
//            mRequestOptions = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(true);
            mRequestOptions = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        }
        return mRequestOptions;
    }

    public void build(String url) {
        mUrl = url;
        if (!canLoadImg()) {
            return;
        }
//        Glide.with(getContext()).load(url).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(this);

        RequestOptions options = getBaseRequestOptions();
        Glide.with(MyApplication.getApp()).load(url).apply(options).into(this);
    }

    public void build(String url, Drawable placeHolder) {
        mUrl = url;
        if (!canLoadImg()) {
            return;
        }

        RequestOptions options = getBaseRequestOptions()
                .placeholder(placeHolder);

//        Glide.with(getContext()).load(url).placeholder(placeHolder).diskCacheStrategy(DiskCacheStrategy.SOURCE).crossFade().into(this);
        Glide.with(MyApplication.getApp()).load(url).apply(options).transition(new DrawableTransitionOptions().crossFade()).into(this);
    }


    public void buildCenterCrop(String url, final Drawable placeHolder, int width, int height) {
        mUrl = url;
        if (!canLoadImg()) {
            return;
        }
//        Glide.with(getContext()).load(url).asBitmap().centerCrop().placeholder(placeHolder).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(new RequestListener<String, Bitmap>() {
//            @Override
//            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
//                newsrepublic_image.doReport(1, e != null ? e.getMessage() : "", "");
//                return false;
//            }
//
//            @Override
//            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                return false;
//            }
//        }).into(this);

        RequestOptions options = getBaseRequestOptions()
                .placeholder(placeHolder)
                .centerCrop()
                .override(width, height);
        Glide.with(MyApplication.getApp()).asBitmap().load(url).apply(options).transition(new BitmapTransitionOptions().crossFade()).listener(new RequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        }).into(this);
    }

    //    public void buildCenterCrop(String url, final Drawable placeHolder, RequestListener<String, Bitmap> listener) {
    public void buildCenterCrop(String url, final Drawable placeHolder, RequestListener<Bitmap> listener) {
        mUrl = url;
        if (!canLoadImg()) {
            return;
        }
//        Glide.with(getContext())
//                .load(url).asBitmap()
//                .centerCrop()
//                .placeholder(placeHolder)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .listener(listener).into(this);

        RequestOptions options = getBaseRequestOptions()
                .placeholder(placeHolder)
                .centerCrop();
        Glide.with(MyApplication.getApp()).asBitmap().load(url).listener(listener).into(this);
    }

    public void buildCallBack(String url, final RequestListener listener ) {
        mUrl = url;
        if (!canLoadImg()) {
            return;
        }
//        Glide.with(getContext()).load(url).asBitmap().centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(new RequestListener<String, Bitmap>() {
//            @Override
//            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
//                newsrepublic_image.doReport(1, e != null ? e.getMessage() : "", "");
//                if (listener!=null){
//                    listener.onException(e,model,target,isFirstResource);
//                }
//                return false;
//            }
//
//            @Override
//            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                if (listener!=null){
//                    listener.onResourceReady(resource,model,target,isFromMemoryCache,isFirstResource);
//                }
//                return false;
//            }
//        }).into(this);

        RequestOptions options = getBaseRequestOptions()
                .centerCrop();
        Glide.with(MyApplication.getApp()).asBitmap().load(url).apply(options).listener(new RequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                if (listener!=null){
                    listener.onResourceReady(resource,model,target,dataSource,isFirstResource);
                }
                return false;
            }
        }).into(this);
    }


//    public void buildCenterCropReport(String url,final String ctype, final Drawable placeHolder) {
//        mUrl = url;
//        if (!canLoadImg()) {
//            return;
//        }
//        final long startTime = System.currentTimeMillis();
//        Glide.with(getContext()).load(url).asBitmap().centerCrop().placeholder(placeHolder).animate(android.R.anim.fade_in).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(new RequestListener<String, Bitmap>() {
//            @Override
//            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
//                return false;
//            }
//
//            @Override
//            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                if(null != resource && !isFromMemoryCache){
//                    new newsrepublic_ins_picloadingtime().load_time((int)(System.currentTimeMillis() - startTime))
//                            .cytpe(ctype).report();
//                }
//                return false;
//            }
//        }).into(this);
//    }

    public void buildCornorCrop(String url, Drawable placeHolder) {
        mUrl = url;
        if (!canLoadImg()) {
            return;
        }

        RequestOptions options = getBaseRequestOptions()
                .centerCrop();
        Glide.with(MyApplication.getApp()).asBitmap().load(url).apply(options).into(new BitmapImageViewTarget(this) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                circularBitmapDrawable.setCornerRadius(DimenSdkUtils.dp2px(4));
                setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    public void buildCircleImage(String url, Drawable placeHolder) {
        mUrl = url;
        if (!canLoadImg()) {
            return;
        }

        RequestOptions options = getBaseRequestOptions()
                .placeholder(placeHolder)
                .centerCrop()
                .transform(new GlideCircleTransform(getContext()));

        RequestManager requestManager = Glide.with(getContext());
        requestManager.load(mUrl).apply(options).transition(new DrawableTransitionOptions().crossFade()).into(this);
    }

    public void buildRoundImage(String url, Drawable placeHolder, int radiusDp) {
        mUrl = url;
        if (!canLoadImg()) {
            return;
        }

        RequestOptions options = getBaseRequestOptions()
                .placeholder(placeHolder)
                .transform(new GlideRoundTransform(getContext(), radiusDp));

        RequestManager requestManager = Glide.with(MyApplication.getApp());
//        requestManager.load(mUrl).placeholder(placeHolder).diskCacheStrategy(DiskCacheStrategy.SOURCE).crossFade().transform(new GlideRoundTransform(getContext(), radiusDp)).into(this);
        requestManager.load(mUrl).apply(options).transition(new DrawableTransitionOptions().crossFade()).into(this);
    }

//    public void buildBlurImage(String url, Drawable placeHolder) {
//        mUrl = url;
//        if (!canLoadImg()) {
//            return;
//        }
//
//        RequestOptions options = getBaseRequestOptions()
//                .placeholder(placeHolder)
//                .transform(new  BlurTransformation(getContext()));
//
//        RequestManager requestManager = Glide.with(getContext());
////        requestManager.load(mUrl).placeholder(placeHolder).diskCacheStrategy(DiskCacheStrategy.SOURCE).crossFade().transform(new BlurTransformation(getContext())).into(this);
//        requestManager.load(mUrl).apply(options).transition(new DrawableTransitionOptions().crossFade()).into(this);
//    }

    private boolean canLoadImg() {
        if (getContext() instanceof Activity) {
            if (Build.VERSION.SDK_INT >= 17) {
                if (((Activity) getContext()).isFinishing() || ((Activity) getContext()).isDestroyed()) {
                    return false;
                }
            } else {
                if (((Activity) getContext()).isFinishing()) {
                    return false;
                }
            }
        }
        return true;
    }

//    public void buildCenterCropForLock(final String url, int resource) {
//        mUrl = url;
//        if (!canLoadImg()) {
//            return;
//        }
//
//        RequestOptions options = getBaseRequestOptions()
//                .placeholder(resource);
//
////        Glide.with(getContext()).load(mUrl).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE)
////                .animate(android.R.anim.fade_in).placeholder(resource).into(this);
//
//        Glide.with(getContext()).asBitmap().load(mUrl).apply(options)
//                .transition(new BitmapTransitionOptions().transition(android.R.anim.fade_in)).into(this);
//    }

//    public void buildCenterCropForLockReport(final String url,final String ctype, int resource) {
//        mUrl = url;
//        if (!canLoadImg()) {
//            return;
//        }
//        final long startTime = System.currentTimeMillis();
//        Glide.with(getContext()).load(mUrl).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .animate(android.R.anim.fade_in).placeholder(resource).listener(new RequestListener<String, Bitmap>() {
//            @Override
//            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
//                return false;
//            }
//
//            @Override
//            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                if(null != resource && !isFromMemoryCache){
//                    new newsrepublic_ins_picloadingtime().load_time((int)(System.currentTimeMillis() - startTime))
//                            .cytpe(ctype).report();
//                }
//                return false;
//            }
//        }).into(this);
//    }
}