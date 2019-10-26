package com.example.myapplication.IPresenter;



import com.example.myapplication.MainActivity;

import java.util.List;

public interface BasePresenter {
    void start();

    void update(int index, String url);

    void get(List<MainActivity.ImageInfo> list);
}

