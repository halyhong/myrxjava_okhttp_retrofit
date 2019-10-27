package com.example.myapplication.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.myapplication.Activity.ARfragmentActivity;
import com.example.myapplication.Activity.RxListActivity;
import com.example.myapplication.Activity.ViewPageActivity;
import com.example.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


//public class fragment3 extends fragment implements View.OnClickListener {
public class fragment3 extends fragment {
    @BindView(R.id.bt1)
    Button button1;

    @BindView(R.id.bt2)
    Button button2;

    @BindView(R.id.bt3)
    Button button3;

    @BindView(R.id.bt4)
    Button button4;

    @Override
    public String getTitle() {
        return "fragment3";
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("lizhihong", "fragment3 onAttach ==== ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("lizhihong", "fragment3 onCreate ==== ");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_layout3, container, false);

//        root.findViewById(R.id.bt1).setOnClickListener(this);

        ButterKnife.bind(this, root);
        ARouter.getInstance().inject(this);

        Log.e("lizhihong", "fragment3 onCreateView ==== ");
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.e("lizhihong", "fragment3 onViewCreated ==== ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("lizhihong", "fragment3 onDestroy ==== ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.e("lizhihong", "fragment3 onDestroyView ==== ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("lizhihong", "fragment3 onDetach ==== ");
    }

    private void startRxActivity() {
        Intent i = new Intent(getActivity(), RxListActivity.class);
        ActivityCompat.startActivity(getActivity(), i, null);
    }

//    @Override
//    public void onClick(View v) {
//        switch(v.getId()) {
//            case R.id.bt1:
//                Log.e("lizhihong", "000000000000");
//                ARouter.getInstance().build("/arhome/mainactivity").navigation();
////                        .withLong("key1", 666L)
////                        .withString("key3", "888")
////                        .navigation();
//                break;
//            case R.id.bt2:
//                break;
//            case R.id.bt3:
//                break;
//            case R.id.bt4:
//                break;
//            default:
//                break;
//        }
//    }

    @OnClick({R.id.bt1})
    void bt1Onclick() {
        ARouter.getInstance().build("/arhome/mainactivity").navigation();
//                        .withLong("key1", 666L)
//                        .withString("key3", "888")
//                        .navigation();
    }

    @OnClick({R.id.bt2})
    void bt2Onclick() {
//        Fragment fragment = (Fragment) ARouter.getInstance().build("/app/fragment1").navigation();

//        getActivity().getSupportFragmentManager().beginTransaction().attach(fragment).commit();
//                        .withLong("key1", 666L)
//                        .withString("key3", "888")
//                        .navigation();

        Activity act = getActivity();
        if (act instanceof ViewPageActivity) {
            ((ViewPageActivity)act).setCurrentItem(0);
        } else if (act instanceof ARfragmentActivity) {
            ((ARfragmentActivity)act).startFragment();
        }

    }
}
