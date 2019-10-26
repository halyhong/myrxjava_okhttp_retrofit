package com.example.myapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.IPresenter.IrxAdapter;
import com.example.myapplication.R;
import com.example.myapplication.bean.ImageInfo;
import com.example.myapplication.widget.bitmapWidget.GlideAsyncImageView;

import java.util.ArrayList;
import java.util.List;

public class RxPagerAdapter  extends RecyclerView.Adapter implements IrxAdapter{
//    public static RequestOptions ro = new RequestOptions().placeholder(R.drawable.ic_launcher_background).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
    private Context mContext;
    private List<ImageInfo> mList;

    public RxPagerAdapter(Context ctx, List<ImageInfo> l) {
        this.mContext = ctx;

        if (null == l) {
            l = new ArrayList<>();
        }
        this.mList = l;
    }

    public void updateImageList(List<ImageInfo> l) {
        this.mList.addAll(l);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RxPagerAdapter.RxHolder myHolder = new RxPagerAdapter.RxHolder(mContext, LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.list_item_new, null));
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        String url = mList.get(i).getUrl();
        RxHolder holder = (RxHolder)viewHolder;

        holder.onBind(url);
    }

    @Override
    public int getItemCount() {
        if (null != mList) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public List<ImageInfo> getImageList() {
        return this.mList;
    }

    public static class RxHolder extends RecyclerView.ViewHolder {
        public GlideAsyncImageView im1;
        private Context mContext;


        public RxHolder(Context ctx, View itemView) {
            super(itemView);

            mContext = ctx;
            im1 = itemView.findViewById(R.id.te1);
        }

        public void onBind(String url) {
            im1.build(url);
        }
    }
}
