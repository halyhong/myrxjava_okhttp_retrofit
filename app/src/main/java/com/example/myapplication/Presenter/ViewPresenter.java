package com.example.myapplication.Presenter;


import com.example.myapplication.Callbacks.IHttpServiceListener;
import com.example.myapplication.IPresenter.BasePresenter;
import com.example.myapplication.IPresenter.IView;
import com.example.myapplication.MainActivity;
import com.example.myapplication.ServiceLoader.ImageLoader;
import com.example.myapplication.bean.ImageInfo;
import com.example.myapplication.bean.ImagesInfo;

import java.util.List;

public class ViewPresenter implements BasePresenter {
    private IView mRv;

    public ViewPresenter(IView rv) {
        this.mRv = rv;
    }

    @Override
    public void start() {
        String s = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3495792639,2866513297&fm=15&gp=0.jpg";
        this.mRv.showText(s);
    }

    @Override
    public void update(int index, String url) {
        this.mRv.updateDataToList(index, url);
    }

    @Override
    public void get(final List<MainActivity.ImageInfo> list) {
        ImageLoader loader = new ImageLoader();
        loader.getImageInfo(new IHttpServiceListener() {
            @Override
            public void onSuccess(ImageInfo info) {

            }

            @Override
            public void onSuccess(ImagesInfo info) {
                int size = info.d.list.size();
                for (int i=0; i<size; ++i) {
                    list.add(new MainActivity.ImageInfo(size + i, info.d.list.get(i)));
                }

                mRv.fetchList(list);
            }

            @Override
            public void onSuccess(List<String> list) {

            }

            @Override
            public void onFail(Throwable t) {

            }
        });
    }


}
