package com.HandyVoice.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.HandyVoice.Fragments.About;
import com.HandyVoice.Fragments.Blog;
import com.HandyVoice.Fragments.NewsUpdates;
import com.HandyVoice.Fragments.NormalWelcome;
import com.HandyVoice.Fragments.Welcome;
import com.HandyVoice.R;


public class NormalPersonHome extends AppCompatActivity {
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_person_home_activity);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // display the first navigation drawer view on app launch
        displayView(0);
    }
    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new NormalWelcome();
                title = getString(R.string.title_welcome);
                break;

            default:
                break;
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }
}
