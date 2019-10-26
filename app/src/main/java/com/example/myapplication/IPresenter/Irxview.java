package com.example.myapplication.IPresenter;



import com.example.myapplication.bean.ImageInfo;

import java.util.List;

public interface Irxview {
    void updateImageList(List<ImageInfo> list);
    List<ImageInfo> getAdapterImageList();
    void getImageFail();
}
