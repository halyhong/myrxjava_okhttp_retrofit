package com.example.myapplication.ServiceLoader;

import android.util.Log;

import com.example.myapplication.Callbacks.IHttpServiceListener;
import com.example.myapplication.ServiceInterface.IHttpService;
import com.example.myapplication.ServiceManager.Api;
import com.example.myapplication.ServiceManager.RetrofitManager;
import com.example.myapplication.bean.ImageInfo;
import com.example.myapplication.bean.ImagesInfo;
import com.example.myapplication.bean.testInfo;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageLoader {
    private IHttpService mHttpService;

    public ImageLoader() {
        mHttpService = RetrofitManager.getInstance(Api.BASE_URL).create(IHttpService.class);
    }

    public void getImageInfo(final IHttpServiceListener listener){
//        Call<testInfo> call = mHttpService.getImageInfo();
//        Call<ImageInfo> call = mHttpService.getImageInfo();
        Call<ImagesInfo> call = mHttpService.getImageInfo();

//        call.enqueue(new Callback<ImageInfo>() {
//            @Override
//            public void onResponse(Call<ImageInfo> call, Response<ImageInfo> response) {
//                if (null != listener && null != response) {
//                    ImageInfo body = response.body();
//                    listener.onSuccess(body);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ImageInfo> call, Throwable t) {
//                call.cancel();
//                Log.e(this.getClass().getSimpleName(), "Throwable = " + t.getMessage());
//                if (null != listener) {
//                    listener.onFail(t);
//                }
//            }
//
//
//        });

//        call.enqueue(new Callback<testInfo>() {
//            @Override
//            public void onResponse(Call<testInfo> call, Response<testInfo> response) {
//                if (null != listener && null != response) {
//                    Log.e(this.getClass().getSimpleName(), "response.body() " + response.body());
//                    testInfo body = response.body();
////                    listener.onSuccess(body);
//                    Log.e("lizhihong", "" + body.r + body.m);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<testInfo> call, Throwable t) {
////                call.cancel();
//                Log.e(this.getClass().getSimpleName(), "Throwable = " + t.getMessage());
//                if (null != listener) {
//                    listener.onFail(t);
//                }
//            }
//
//
//        });

        call.enqueue(new Callback<ImagesInfo>() {
            @Override
            public void onResponse(Call<ImagesInfo> call, Response<ImagesInfo> response) {
                if (null != listener && null != response) {
                    Log.e(this.getClass().getSimpleName(), "response.body() " + response.body());
                    ImagesInfo body = response.body();
//                    listener.onSuccess(body);
                    Log.e("lizhihong", "" + body.r + body.m);

                    listener.onSuccess(body);
                }
            }

            @Override
            public void onFailure(Call<ImagesInfo> call, Throwable t) {
//                call.cancel();
                Log.e(this.getClass().getSimpleName(), "Throwable = " + t.getMessage());
                if (null != listener) {
                    listener.onFail(t);
                }
            }


        });
    }


    public void getImageInfo2(final IHttpServiceListener listener) {
        Observable<ImagesInfo> call = mHttpService.getImageInfo2();

        call.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<ImagesInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ImagesInfo imagesInfo) {
                        if (listener != null) {
                            listener.onSuccess(imagesInfo.d.list);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (listener != null) {
                            listener.onFail(e);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
