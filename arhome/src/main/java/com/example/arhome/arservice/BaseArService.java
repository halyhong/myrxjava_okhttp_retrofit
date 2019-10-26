package com.example.arhome.arservice;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;

public interface BaseArService extends IProvider {
    String myTestString(String s);
}
