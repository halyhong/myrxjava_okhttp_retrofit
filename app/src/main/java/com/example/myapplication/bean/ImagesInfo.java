package com.example.myapplication.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImagesInfo {
    @SerializedName("ret")
    public int r;

    @SerializedName("msg")
    public String m;

    @SerializedName("data")
    public DATA d;

    public static class DATA {
        @SerializedName("pic")
        public List<String> list;

    }

    public String getMsg() {
        return this.m;
    }
}
