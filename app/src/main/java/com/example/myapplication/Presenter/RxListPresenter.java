package com.example.myapplication.Presenter;

import com.example.myapplication.Callbacks.IHttpServiceListener;
import com.example.myapplication.IPresenter.IrxListPresenter;
import com.example.myapplication.IPresenter.Irxview;
import com.example.myapplication.ServiceLoader.ImageLoader;
import com.example.myapplication.bean.ImageInfo;
import com.example.myapplication.bean.ImagesInfo;

import java.util.ArrayList;
import java.util.List;

public class RxListPresenter implements IrxListPresenter {
    private Irxview mView;

    public RxListPresenter(Irxview view) {
        mView = view;
    }

    @Override
    public void getImageList() {
        ImageLoader loader = new ImageLoader();
        loader.getImageInfo2(new IHttpServiceListener() {
            @Override
            public void onSuccess(ImageInfo info) {

            }

            @Override
            public void onSuccess(ImagesInfo info) {

            }

            @Override
            public void onSuccess(List<String> list) {
                List<ImageInfo> l1 = mView.getAdapterImageList();
                List<ImageInfo> l = new ArrayList<ImageInfo>(list.size());
                if (l == null) {
                    return ;
                }

                int index = l1.size();
                for (int i = 0; i<list.size(); i++) {
                    l.add(new ImageInfo(index+i, list.get(i)));
                }

                mView.updateImageList(l);
            }

            @Override
            public void onFail(Throwable t) {
                mView.getImageFail();
            }
        });
    }
}
