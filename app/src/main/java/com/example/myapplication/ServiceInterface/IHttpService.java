package com.example.myapplication.ServiceInterface;

import com.example.myapplication.bean.ImageInfo;
import com.example.myapplication.bean.ImagesInfo;
import com.example.myapplication.bean.testInfo;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;



public interface IHttpService {

    @GET("v1/config/switch")
    Call<ImagesInfo> getImageInfo();
//    Call<testInfo> getImageInfo();
//    Call<ResponseBody> getImageInfo();


    @GET("v1/config/switch")
    Observable<ImagesInfo> getImageInfo2();

}
