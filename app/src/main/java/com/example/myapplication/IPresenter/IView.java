package com.example.myapplication.IPresenter;


import com.example.myapplication.MainActivity;
import com.example.myapplication.bean.ImagesInfo;

import java.util.List;

public interface IView {
    void showText(String s);
    void updateDataToList(int index, String url);
    void fetchList(List<MainActivity.ImageInfo> list);
}
