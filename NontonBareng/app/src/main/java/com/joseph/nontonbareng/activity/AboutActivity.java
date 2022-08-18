package com.joseph.nontonbareng.activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.joseph.nontonbareng.R;

public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ImageView imageView = (ImageView) findViewById(R.id.imgAbout);
        imageView.setImageResource(R.mipmap.me_round);
    }
}
