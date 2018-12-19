package com.sheygam.masa_2018_g1_18_12_18;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MyFragment.Callback {

    private Button addBtn, replaceBtn, removeBtn, detachBtn;
    private float scale = 1.0F;
    private int count = 0;
    private boolean isAttached = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addBtn = findViewById(R.id.add_btn);
        replaceBtn = findViewById(R.id.replace_btn);
        removeBtn = findViewById(R.id.remove_btn);
        detachBtn = findViewById(R.id.detach_btn);
        detachBtn.setOnClickListener(this);
        removeBtn.setOnClickListener(this);
        replaceBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);
//        MyFragment myFragment = new MyFragment();
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.add(R.id.my_container, myFragment);
//        transaction.commit();
//        myFragment.setTitle("My Super title");


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.add_btn){
            Random rnd = new Random();
            int r = rnd.nextInt(256);
            int g = rnd.nextInt(256);
            int b = rnd.nextInt(256);
            int color = Color.rgb(r,g,b);
            MyFragment fragment = new MyFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("COLOR",color);
            bundle.putFloat("SCALE",scale);
            fragment.setArguments(bundle);
            fragment.setCallback(this);
            if(count == 2){
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.my_container,fragment,"MY_TAG")
                        .addToBackStack(null)
                        .commit();
            }else {
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.my_container, fragment)
                        .addToBackStack(null)
                        .commit();
            }

            scale -= 0.1;
            count++;

        }else if(v.getId() == R.id.replace_btn){
            MyFragment fragment = new MyFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("COLOR",Color.rgb(0,255,0));
            bundle.putFloat("SCALE",scale);
            fragment.setArguments(bundle);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.my_container,fragment)
                    .commit();
            scale -= 0.1;
        }else if(v.getId() == R.id.remove_btn){
            MyFragment myFragment = (MyFragment) getSupportFragmentManager().findFragmentByTag("MY_TAG");
            Bundle bundle = myFragment.getArguments();
            Log.d("MY_TAG", "onClick: " + bundle.getInt("COLOR") + " " + bundle.getFloat("SCALE"));
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(myFragment)
                    .commit();
        }else if(v.getId() == R.id.detach_btn){
            MyFragment fragment = (MyFragment) getSupportFragmentManager().findFragmentByTag("MY_TAG");
            if(isAttached){
                getSupportFragmentManager()
                        .beginTransaction()
                        .detach(fragment)
                        .commit();
                detachBtn.setText("Attach");

            }else{
                getSupportFragmentManager()
                        .beginTransaction()
                        .attach(fragment)
                        .commit();
                detachBtn.setText("Detach");
            }
            isAttached = !isAttached;
        }
    }

    @Override
    public void onButtonClick(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
