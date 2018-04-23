package com.HandyVoice.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.HandyVoice.Activities.CourseOpenerActivity;
import com.HandyVoice.Adapter.RVAdapter;
import com.HandyVoice.Data;
import com.HandyVoice.R;

import java.util.ArrayList;
import java.util.List;
public class NormalWelcome extends Fragment {

    View view;
    private List<Data> courseList = new ArrayList<>();
    private RecyclerView recyclerView;
    Context context;
    private RVAdapter mAdapter;
    public NormalWelcome() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_normal_welcome, container, false);

        init();

        recycler_view();

        return view;
    }

    void init() {
        recyclerView = (RecyclerView)view.findViewById(R.id.rv);
    }

    void recycler_view() {
        mAdapter = new RVAdapter(courseList,getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareCourseData();
    }

    private void prepareCourseData() {
        String courseName[]={"ASL Language","Understand deaf people"};
        int photoId []={0,0};
        String courseFaculty []={"Signed with heart","Rachel Soudakoff"};

        for (int i=0;i<photoId.length;i++) {
            Data course=new Data(courseName[i],courseFaculty[i],photoId[i]);
            courseList.add(course);
        }
        mAdapter.notifyDataSetChanged();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
