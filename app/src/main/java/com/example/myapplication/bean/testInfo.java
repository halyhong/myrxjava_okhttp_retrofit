package com.example.myapplication.bean;

import com.google.gson.annotations.SerializedName;

public class testInfo {

    @SerializedName("ret")
    public int r;

    @SerializedName("msg")
    public String m;

    @SerializedName("data")
    public DATA d;

    public static class DATA {
        @SerializedName("webview_switch")
        public int wSwitch;

        @SerializedName("app_switch")
        public String aSwitch;
    }

    public String getMsg() {
        return this.m;
    }
}
