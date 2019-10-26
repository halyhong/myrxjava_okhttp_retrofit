package com.example.arhome.arservice;


import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;



@Route(path = "/arhome/ArHomeService",name = "我的测试")
public class ArHomeService implements BaseArService {
    private String name;
    @Override
    public String myTestString(String s) {
        return "ArHomeService call " + s;
    }

    private void initData() {
        name="myTestString";
    }

    @Override
    public void init(Context context) {
        initData();
    }
}