package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FinalActivity extends AppCompatActivity {

    @BindView(R.id.view1) TextView mView;
    @BindView(R.id.view2) TextView mMake;
    @BindView(R.id.view3) TextView mView1;
    @BindView(R.id.view4) TextView mMake2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        ButterKnife.bind(this);


        Intent choice = getIntent();
        String name = choice.getStringExtra("name");
        String email = choice.getStringExtra("email");
        String location = choice.getStringExtra("location");
        String type = choice.getStringExtra("type");

        mView.setText("Name : " + name );
        mMake.setText("Email : " + email);
        mView1.setText("Location : " + location);
        mMake2.setText("Type : " +type);
    }
}
