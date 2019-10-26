package com.example.myapplication.Activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.ViewGroup;

//import com.astuetz.PagerSlidingTabStrip;
import com.example.myapplication.Fragment.fragment;
import com.example.myapplication.Fragment.fragment1;
import com.example.myapplication.Fragment.fragment2;
import com.example.myapplication.R;
import com.example.myapplication.widget.indicator.PagerSlidingTabStrip;

public class PagerSlidingTabActivity extends AppCompatActivity {
    private SparseArray<Fragment> arr = new SparseArray<>();

    private myViewPagerAdapter myAdapter;
    private PagerSlidingTabStrip indicator;

    private ViewPager vper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sliding_layout);

        initView();
        initData();

        changeColor(0xFF3F9FE0);
    }

    private void initView() {
        vper = this.findViewById(R.id.pager);
        indicator = (PagerSlidingTabStrip)findViewById(R.id.tabs);

    }

    @TargetApi(Build.VERSION_CODES.M)
    private void initData() {
        arr.put(0, new fragment1());
        arr.put(1, new fragment2());
        arr.put(2, new fragment1());

        myAdapter = new myViewPagerAdapter(this.getSupportFragmentManager(), arr);
        vper.setAdapter(myAdapter);
        vper.setCurrentItem(0);

        indicator.setViewPager(vper);
    }

    private void changeColor(int newColor) {

        indicator.setIndicatorColor(newColor);
    }


    public class myViewPagerAdapter extends FragmentPagerAdapter {
        private SparseArray<Fragment> mArr;

        public myViewPagerAdapter(FragmentManager fm, SparseArray<Fragment> frags) {
            super(fm);
            mArr = frags;
        }

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
            String s = ((fragment)mArr.get(position)).getTitle().toUpperCase();
            return s;
        }
    }
}
