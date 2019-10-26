package com.example.commonlib.base.mvp_no_dagger;



public class BaseModel<T> {

    private T t;

    public BaseModel(T t) {
        this.t = t;
    }

    public T getService() {
        return t;
    }

}
