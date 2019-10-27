package com.example.myapplication.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.myapplication.Fragment.fragment1;
import com.example.myapplication.Fragment.fragment2;
import com.example.myapplication.Fragment.fragment3;
import com.example.myapplication.R;

public class ARfragmentActivity extends FragmentActivity {

    private Fragment f1;
    private Fragment f2;
    private Fragment f3;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ar_fragment_layout);

        ARouter.getInstance().inject(this);

        f1 = Fragment.instantiate(this, fragment1.class.getName(), null);
        f2 = Fragment.instantiate(this, fragment2.class.getName(), null);
        f3 = Fragment.instantiate(this, fragment3.class.getName(), null);
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
//        ft.add(R.id.ar_container, f1, fragment1.class.getName());
        ft.add(R.id.ar_container, f2, fragment2.class.getName());
//        ft.detach(f1);
        ft.detach(f2);
        ft.add(R.id.ar_container, f3, fragment3.class.getName()).commit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mHandler.removeCallbacksAndMessages(null);
    }

    public void startFragment() {
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();

        ft.detach(f3);
//        getSupportFragmentManager().beginTransaction().add(R.id.ar_container, f1, fragment1.class.getName());
        final Fragment fragment = (Fragment) ARouter.getInstance().build("/app/fragment1").navigation();
        ft.add(R.id.ar_container,fragment).commit();

        final FragmentTransaction ft2 = this.getSupportFragmentManager().beginTransaction();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ft2.detach(fragment);
                ft2.attach(f3).commit();
            }
        },20000);




//        this.getSupportFragmentManager().beginTransaction().addToBackStack(fragment3.class.getName()).replace(R.id.ar_container, f1, fragment1.class.getName()).commit();
    }
}
