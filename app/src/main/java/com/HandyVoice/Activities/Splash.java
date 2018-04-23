package com.HandyVoice.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;
import com.HandyVoice.R;



public class Splash extends Activity {

    final static int NUMBER_OF_CITATIONS = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        runTimer();
    }

    void init() {
        setContentView(R.layout.activity_splash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Random rand = new Random();
        int n = rand.nextInt(NUMBER_OF_CITATIONS)+1;
        this.makeCitation(n);
    }

    void makeCitation (int n){
        TextView citation = (TextView) findViewById(R.id.citation);
        if (n==1){
            citation.setText("My eye is my ear, my hand is my mouth, and I will live my life");
        } else if (n==2){
            citation.setText("Kindness is a language that the deaf can hear, and the blind can see");
        } else if (n==3){
            citation.setText("Only dumb hearing people think that deaf people are dumb");
        }else if (n==4){
            citation.setText("Everyone has their own obstacles that they struggle to surpass, deafness can just be one of it");
        }


    }

    void runTimer() {

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    finish();
                    //startActivity(new Intent(Splash.this, LoginSignup.class));
                    Intent i = new Intent(Splash.this, FirstHomeActivity.class);
                    startActivity(i);
                }
            }
        };
        timer.start();
    }
}
