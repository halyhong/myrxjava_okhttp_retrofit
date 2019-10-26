package com.example.myapplication;



import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.Activity.ViewPageActivity;
import com.example.myapplication.Callbacks.IHttpServiceListener;
import com.example.myapplication.IPresenter.IView;
import com.example.myapplication.Presenter.ViewPresenter;
import com.example.myapplication.ServiceLoader.ImageLoader;
import com.example.myapplication.bean.ImageInfo;
import com.example.myapplication.bean.ImagesInfo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IView {
    RequestOptions ro = new RequestOptions().placeholder(R.drawable.ic_launcher_background).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
//    RequestOptions ro = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);


    RecyclerView recyclerView;

    String s_te1[] = {"内容一", "内容一", "内容一"};

    String s_te2[] = {"内容二", "内容二", "内容二"};

    List<ImageInfo> mList = new ArrayList<>();

    MyAdapter myAdapter;

    ImageLoader mImageLoader;

    ViewPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.myrecycleView);

        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        myAdapter = new MyAdapter();

        recyclerView.setAdapter(myAdapter);

//        for(int i=0; i<10; i++) {
//            mList.add(String.valueOf(i));
//        }

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


        mPresenter = new ViewPresenter(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        myAdapter.updateDatas(mList);

//        mImageLoader = new ImageLoader();
//        mImageLoader.getImageInfo(new IHttpServiceListener() {
//            @Override
//            public void onSuccess(com.example.myapplication.bean.ImageInfo info) {
//                String s = info.toString();
//                Log.d("lizhihong", "info = " + s);
//            }
//
//            @Override
//            public void onSuccess(ImagesInfo info) {
//                mList.clear();
//                for (int i=1 ; i <= info.d.list.size(); i++) {
//                    mList.add(new ImageInfo(i, info.d.list.get(i-1)));
//                }
//
//                myAdapter.setDatas(null);
//                myAdapter.updateDatas(mList);
//            }
//
//            @Override
//            public void onFail(Throwable t) {
//
//            }
//        });

        Log.e(this.getClass().getSimpleName(), "mList.size() 1 = " + mList.size());

        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
//                String s = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3495792639,2866513297&fm=15&gp=0.jpg";
//                mPresenter.update(14, s);
                mPresenter.get(mList);
                mPresenter.start();
            }
        }, 5000);


        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                startPagerActivity();
            }
        }, 10000);
    }

    private void startPagerActivity() {
        Intent i = new Intent(this, ViewPageActivity.class);
        ActivityCompat.startActivity(this, i, null);
    }

    @Override
    public void showText(String s) {
//        mList.add(new ImageInfo(14, s));
//        myAdapter.setDatas(null);
//        myAdapter.updateDatas(mList);
        Toast.makeText(this," List update data", 50).show();
    }

    @Override
    public void updateDataToList(int index, String url) {
        mList.add(new ImageInfo(index, url));
        myAdapter.setDatas(null);
        myAdapter.updateDatas(mList);
    }

    @Override
    public void fetchList(List<com.example.myapplication.MainActivity.ImageInfo> list) {
        myAdapter.setDatas(null);
        myAdapter.updateDatas(list);
        Log.e(this.getClass().getSimpleName(), "mList.size() 2 = " + mList.size());
        Log.e(this.getClass().getSimpleName(), "mList.size() 3 = " + list.size());
    }

    class MyAdapter extends RecyclerView.Adapter {
        private List<ImageInfo> mList;

        public void setDatas(List<ImageInfo> l) {
            mList = l;
        }

        public void updateDatas(List<ImageInfo> l) {
            if (null == mList) {
                mList = l;
            } else {
                mList.addAll(l);
            }
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyHolder myHolder = new MyHolder(LayoutInflater.from(MainActivity.this.getApplicationContext()).inflate(R.layout.list_item, null));//引入自定义列表项的资源文件
            return myHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyHolder mm = (MyHolder) holder;

//            将数据映射到控件中
//            mm.te1.setText(mList.get(position));
//            mm.te2.setText(s_te2[position]);

//            mm.im1.setImageResource(R.mipmap.ic_launcher_round);

            mm.onBind(position);
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @Override
        public int getItemCount() {
            if (null != mList) {
                return mList.size();
            }
            return 0;
        }
//        public int getItemCount() {
//            return s_te1.length;
//        }

        class MyHolder extends RecyclerView.ViewHolder {

//            TextView te1, te2;
            ImageView im1;


            public MyHolder(View itemView) {
                super(itemView);

//                实例化子对象，把对象和列表项布局文件中的id绑定
//                te1 = itemView.findViewById(R.id.te1);
//                te2 = itemView.findViewById(R.id.te2);

                im1 = itemView.findViewById(R.id.te1);
            }

            public void onBind(int position) {
                RequestBuilder rq = Glide.with(MainActivity.this.getApplicationContext()).load(mList.get(position).getUrl());
                rq.apply(ro).into(im1);

//                Drawable drawable = MyApplication.getApp().getResources().getDrawable(R.drawable.ic_launcher_foreground);
//                RequestBuilder rq = Glide.with(MainActivity.this.getApplicationContext()).asBitmap().load(drawable);
//                rq.apply(ro).into(im1);
            }


        }

    }


    public static class ImageInfo {
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
    }
}
