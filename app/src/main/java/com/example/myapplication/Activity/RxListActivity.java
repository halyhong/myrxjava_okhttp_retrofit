package com.example.myapplication.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.myapplication.Adapter.RxPagerAdapter;
import com.example.myapplication.IPresenter.Irxview;
import com.example.myapplication.Presenter.RxListPresenter;
import com.example.myapplication.R;
import com.example.myapplication.bean.ImageInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RxListActivity extends AppCompatActivity implements Irxview {
    @BindView(R.id.rxrecycleView)
    RecyclerView recyclerView;

    private RxPagerAdapter mAdapter;
    private List<ImageInfo> mList;
    private RxListPresenter mListPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rx);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        mListPresenter = new RxListPresenter(this);

        initData();

        mListPresenter.getImageList();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void updateImageList(List<ImageInfo> list) {
        mAdapter.updateImageList(list);
    }

    @Override
    public List<ImageInfo> getAdapterImageList() {
        return mAdapter.getImageList();
    }

    @Override
    public void getImageFail() {
        Toast.makeText(this, "get local net image fail", 3000).show();
    }

    private void initData() {
        if (null == mList) {
            mList = new ArrayList<>(100);
        }

        mList.add(new ImageInfo(1, "http://www.33lc.com/uploadfile/2018/0420/20180420042827555.jpg"));
        mList.add(new ImageInfo(2, "http://m.360buyimg.com/pop/jfs/t22906/274/1763657905/20550/870502f7/5b697ab4N94033349.jpg"));
        mList.add(new ImageInfo(3, "http://b-ssl.duitang.com/uploads/item/201708/17/20170817000314_Qd5cj.thumb.700_0.png"));
        mList.add(new ImageInfo(4, "http://cdn.duitang.com/uploads/item/201410/26/20141026191422_yEKyd.thumb.700_0.jpeg"));
        mList.add(new ImageInfo(5, "http://pic.chinaz.com/2018/0425/2018042516153690.jpg"));
        mList.add(new ImageInfo(6, "http://img.99danji.com/uploadfile/2016/0301/20160301101806940.jpg"));
        mList.add(new ImageInfo(7, "http://b-ssl.duitang.com/uploads/blog/201404/08/20140408225935_xnrkd.jpeg"));
        mList.add(new ImageInfo(8, "http://upload.mnw.cn/2017/0822/1503394274182.jpg"));
        mList.add(new ImageInfo(9, "http://www.jz5u.com/Soft/UploadPic/Upload/2017161123512145.jpg"));
        mList.add(new ImageInfo(10, "http://down.52pk.com/uploads/180814/5039_152922_3096.jpg"));
        mList.add(new ImageInfo(11, "http://img2.vipcn.com/img2017/5/22/2017052222184888.jpg"));
        mList.add(new ImageInfo(12, "http://b-ssl.duitang.com/uploads/item/201808/24/20180824093736_zxysd.jpg"));
        mList.add(new ImageInfo(13, "http://img2.3png.com/720f7b22a939834aca195ca984dcd114a6e2.png"));

        mAdapter = new RxPagerAdapter(this, null);
        recyclerView.setAdapter(mAdapter);
//        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
