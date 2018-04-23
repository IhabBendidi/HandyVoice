package com.HandyVoice.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.HandyVoice.R;


public class FirstHomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button deafButton;
    private Button peopleButton;

    private ImageView clickImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_home_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        deafButton = (Button) findViewById(R.id.button_deaf);
        peopleButton = (Button) findViewById(R.id.button_people);


        clickImage = (ImageView) findViewById(R.id.click_image);

        deafButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstHomeActivity.this,Home.class);
                startActivity(i);
            }
        });

        peopleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstHomeActivity.this,NormalPersonHome.class);
                startActivity(i);
            }
        });




        Animation animation = new TranslateAnimation(0, 0, 0, -200);
        animation.setDuration(3000);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation arg0){

            }
            @Override
            public void onAnimationRepeat(Animation arg0){

            }
            @Override
            public void onAnimationEnd(Animation arg0){
                clickImage.setVisibility(View.INVISIBLE);
            }

        });
        clickImage.startAnimation(animation);



    }
}
