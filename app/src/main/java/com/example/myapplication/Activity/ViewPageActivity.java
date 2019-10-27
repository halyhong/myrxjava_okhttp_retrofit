package com.example.myapplication.Activity;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.myapplication.Fragment.TestFragment;
import com.example.myapplication.Fragment.fragment;
import com.example.myapplication.Fragment.fragment1;
import com.example.myapplication.Fragment.fragment2;
import com.example.myapplication.Fragment.fragment3;
import com.example.myapplication.R;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ViewPageActivity extends FragmentActivity {
    private static final String[] CONTENT = new String[] { "Recent", "Artists", "Albums", "Songs", "Playlists", "Genres" };
    private SparseArray<Fragment> arr = new SparseArray<>();
    private List<Fragment> list;

    private myViewPagerAdapter myAdapter;
    private TabPageIndicator indicator;

    //    @BindView(R.id.myViewPager)
    private ViewPager vper;

    private Fragment f1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pager_activity);
//        ButterKnife.bind(this);

        ARouter.getInstance().inject(this);
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView() {
        vper = this.findViewById(R.id.myViewPager);
        indicator = (TabPageIndicator)findViewById(R.id.indicator);

    }

    @TargetApi(Build.VERSION_CODES.M)
    private void initData() {
        f1 = new fragment1();
        arr.put(0, f1);
        arr.put(1, new fragment2());
        arr.put(2, new fragment3());
//        list = new ArrayList<>();
//        list.add(new fragment1());
//        list.add(new fragment2());

//        vper.setOnPageChangeListener(new MyPagerChangeListener()) ;
        vper.setOnPageChangeListener(new MyPagerChangeListener());
//        myAdapter = new myViewPagerAdapter(this.getSupportFragmentManager(), list);
        myAdapter = new myViewPagerAdapter(this.getSupportFragmentManager(), arr);
        vper.setAdapter(myAdapter);
        vper.setCurrentItem(0);

        vper.setOnDragListener(new MyPagerDragListener());
        vper.setOnTouchListener(new MyPagerTouchListener());
        vper.setOnScrollChangeListener(new MyScrollChangeListener());

        indicator.setViewPager(vper);
    }


    public class myViewPagerAdapter extends FragmentPagerAdapter {
        private SparseArray<Fragment> mArr;
//        private List<Fragment> mArr;

        public myViewPagerAdapter(FragmentManager fm, SparseArray<Fragment> frags) {
            super(fm);
            mArr = frags;
        }

//        public myViewPagerAdapter(FragmentManager fm, List<Fragment> frags) {
//            super(fm);
//            mArr = frags;
//        }

        @Override
        public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            super.setPrimaryItem(container, position, object);
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public Fragment getItem(int i) {
            if (null == mArr) {
                return null;
            }
            return mArr.get(i);
        }

        @Override
        public int getCount() {
            if (null == mArr) {
                return 0;
            }
            return mArr.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
//            return super.getPageTitle(position);
            String s = ((fragment)mArr.get(position)).getTitle().toUpperCase();
            return s;
        }
    }

    public class MyPagerTouchListener implements ViewPager.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Log.e(this.getClass().getSimpleName(), " onTouch, event.getAction() = " + event.getAction());
            return false;
        }
    }

    public class MyPagerDragListener implements ViewPager.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            Log.e(this.getClass().getSimpleName(), " onDrag, event.getAction() = " + event.getAction());
            return false;
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public class MyScrollChangeListener implements ViewPager.OnScrollChangeListener {

        @Override
        public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            Log.e(this.getClass().getSimpleName(), " onScrollChange = ");
        }
    }

    public class MyPagerChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int i, float v, int i1) {
            Log.e(this.getClass().getSimpleName(), " onPageScrolled,  i = " + i + ", v = " + v + ", i1 = " + i1);

        }

        @Override
        public void onPageSelected(int i) {
            Log.e(this.getClass().getSimpleName(), " onPageScrollStateChanged, i = " + i);
        }

        @Override
        public void onPageScrollStateChanged(int i) {
            Log.e(this.getClass().getSimpleName(), " onPageSelected, i = " + i);
        }

    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.pager_activity);
//
//        FragmentPagerAdapter adapter = new GoogleMusicAdapter(getSupportFragmentManager());
//
//        ViewPager pager = (ViewPager)findViewById(R.id.myViewPager);
//        pager.setAdapter(adapter);
//
//        TabPageIndicator indicator = (TabPageIndicator)findViewById(R.id.indicator);
//        indicator.setViewPager(pager);
//    }
//
//    class GoogleMusicAdapter extends FragmentPagerAdapter {
//        public GoogleMusicAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return TestFragment.newInstance(CONTENT[position % CONTENT.length]);
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return CONTENT[position % CONTENT.length].toUpperCase();
//        }
//
//        @Override
//        public int getCount() {
//            return CONTENT.length;
//        }
//    }

    public void startFragment() {
//        Fragment fragment = (Fragment) ARouter.getInstance().build("/app/fragment1").navigation();

//        this.getSupportFragmentManager().beginTransaction().add(f1, fragment1.class.getName()).commit();
    }

    public void setCurrentItem(int item) {
        vper.setCurrentItem(item);
    }
}
