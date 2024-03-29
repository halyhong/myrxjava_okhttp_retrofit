package com.example.myapplication.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.Activity.ARfragmentActivity;
import com.example.myapplication.Activity.PagerSlidingTabActivity;
import com.example.myapplication.Activity.RxListActivity;
import com.example.myapplication.R;

public class fragment2 extends fragment {

    @Override
    public String getTitle() {
        return "fragment2";
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_layout2, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView v = view.findViewById(R.id.iTx2);
        v.setText(this.getClass().getSimpleName());
        v.setTextSize(20);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRxActivity();
            }
        });

        TextView v2 = view.findViewById(R.id.iTx3);
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSlidingActivity();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public boolean getUserVisibleHint() {
        return super.getUserVisibleHint();
    }

    private void startRxActivity() {
        Intent i = new Intent(getActivity(), RxListActivity.class);
        ActivityCompat.startActivity(getActivity(), i, null);
    }

    private void startSlidingActivity() {
        Intent i = new Intent(getActivity(), PagerSlidingTabActivity.class);
        ActivityCompat.startActivity(getActivity(), i, null);
    }
}
