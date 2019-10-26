package com.example.myapplication.Callbacks;

import com.example.myapplication.bean.ImageInfo;
import com.example.myapplication.bean.ImagesInfo;

import java.util.List;

public interface IHttpServiceListener {
    void onSuccess(ImageInfo info);
    void onSuccess(ImagesInfo info);
    void onSuccess(List<String> list);
    void onFail(Throwable t);
}