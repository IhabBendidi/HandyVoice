package com.HandyVoice.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.HandyVoice.R;
import com.HandyVoice.Fragments.Fragment_Video;
import com.HandyVoice.Fragments.Fragment_Quiz;
import com.HandyVoice.Fragments.Fragment_Documentation;
import com.HandyVoice.Activities.play_video;

public class CourseOpenerActivity extends AppCompatActivity  {

    public String s;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_opener);
        s = getIntent().getStringExtra("COURSE_TITLE");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(s);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Toast.makeText(getApplicationContext(),"Coming soon",Toast.LENGTH_SHORT).show();
            }
        });



    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            if (s.equals("ASL Language")||s.equals("Understand deaf people")){
                Intent i = new Intent(CourseOpenerActivity.this, NormalPersonHome.class);
                startActivity(i);
            }else{
                Intent i = new Intent(CourseOpenerActivity.this, Home.class);
                startActivity(i);
            }

            // NavUtils.navigateUpFromSameTask(this);
            // finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        if(s.equals("ASL Language")||s.equals("Understand deaf people")){
            adapter.addFragment(new Fragment_Video(s), "Videos");
            //adapter.addFragment(new Fragment_Quiz(), "Quiz");
            //adapter.addFragment(new Fragment_Documentation(), "Documents");
        }else{
            adapter.addFragment(new Fragment_Video(s), "Videos");
            //adapter.addFragment(new Fragment_Quiz(s), "Quiz");
            adapter.addFragment(new Fragment_Documentation(s), "Documents");
        }
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    public void video(View view)
    {
        Intent intent=new Intent(this,play_video.class);
        startActivity(intent);
    }
}