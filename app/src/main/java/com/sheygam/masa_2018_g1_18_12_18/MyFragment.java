package com.sheygam.masa_2018_g1_18_12_18;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MyFragment extends Fragment implements View.OnClickListener {
    private TextView titleTxt;
    private Button clickBtn;
    private int color;
    private float scale;
    private Callback callback;

//    public static MyFragment newInstance(int color, float scale){
//        MyFragment fragment = new MyFragment();
//        fragment.color = color;
//        fragment.scale = scale;
//        return fragment;
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MY_TAG", "onCreate: ");
        Bundle arguments = getArguments();
        if (arguments != null){
            color = arguments.getInt("COLOR");
            scale = arguments.getFloat("SCALE");
        }else{
            color = Color.rgb(0,0,255);
            scale = 1.0F;
        }
    }

    public void setCallback(Callback callback){
        this.callback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my,container,false);
        titleTxt = view.findViewById(R.id.textView);
        clickBtn = view.findViewById(R.id.click_btn);
        clickBtn.setOnClickListener(this);
        view.setBackgroundColor(color);
        view.setScaleX(scale);
        view.setScaleY(scale);
        Log.d("MY_TAG", "onCreateView: ");
        return view;
    }

    public void setTitle(String title){
        if(titleTxt != null) {
            titleTxt.setText(title);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("MY_TAG", "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MY_TAG", "onDestroy: ");
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.click_btn){
            if(callback!=null){
                callback.onButtonClick("Hello world");
            }
        }
    }

    public interface Callback{
        void onButtonClick(String message);
    }
}
