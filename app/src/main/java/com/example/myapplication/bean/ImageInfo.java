package com.example.myapplication.bean;

import com.google.gson.annotations.SerializedName;



public class ImageInfo {
//        private String id;
//
//        @SerializedName("n")
//        private String userName;
//
//        @SerializedName("p")
//        private String password;
//
//        @SerializedName("s")
//        private String sex;
//
//
//        @Override
//        public String toString() {
//                return "id = '" + id + "'" + ", userName = '" + userName + '\'' + ", password = '" + password + '\'' + ", sex = " + sex;
//        }


        private int num;
        private String url;

        public ImageInfo(int n, String u) {
                this.num = n;
                this.url = u;
        }

        public int getNum() {
                return this.num;
        }

        public String getUrl() {
                return this.url;
        }

        @Override
        public String toString() {
                return "num = '" + num + "'" + ", userName = '" + url + '\'';
        }
}

