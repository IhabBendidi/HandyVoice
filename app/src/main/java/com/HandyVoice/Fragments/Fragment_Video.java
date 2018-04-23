package com.HandyVoice.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.HandyVoice.Model.CustomList;
import com.HandyVoice.R;
import com.HandyVoice.Activities.play_video;


public class Fragment_Video extends Fragment{
    ListView list;
    public String courseTitle;
    public String[] web = {
            "Video 1: Introduction",
            "Video 2: Getting Started ",
            "Video 3: Index",
            "Video 4:Key Terms And Concepts",
            "Video 5:Practise Set",
            "Video 6:Points To Remember",
            "Video 7: Summing Up"
    } ;
    Integer[] imageId = {
            R.mipmap.data_scientist,
            R.mipmap.data_scientist,
            R.mipmap.data_scientist,
            R.mipmap.data_scientist,
            R.mipmap.data_scientist,
            R.mipmap.data_scientist,
            R.mipmap.data_scientist
    };
    boolean[] animationStates=new boolean[7];

    View view;
    public Fragment_Video() {
        // Required empty public constructor
    }
    public Fragment_Video(String courseTitle) {
        // Required empty public constructor
        this.courseTitle = courseTitle;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_video, container, false);
        setWebTitles(this.courseTitle);
        CustomList adapter = new
                CustomList(getActivity(), web, imageId);
        list=(ListView)view.findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(), "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),play_video.class);
                intent.putExtra("TITLE_VIDEO",web[+position]);
                startActivity(intent);
            }
        });
        return view;
    }


    void setWebTitles(String courseTitle){
        if (courseTitle.equals("ASL Language")){
            int length=3;
            web = new String[length];
            web[0] = "Video 1: 25 Basic ASL Signs Part 1";
            web[1] = "Video 2: 25 Basic ASL Signs Part 2";
            web[2] = "Video 3: 25 Basic ASL Signs Part 3";
        } else if (courseTitle.equals("Understand deaf people")){
            int length=1;
            web = new String[length];
            web[0] = "Video 1: A day through a deaf person's eyes";
        } else if (courseTitle.equals("HTML for Deaf & Mute")){
            int length = 5;
            web = new String[length];
            web[0] = "HTML development for Mute and Deaf Part 1";
            web[1] = "HTML development for Mute and Deaf Part 2";
            web[2] = "HTML development for Mute and Deaf Part 3";
            web[3] = "HTML development for Mute and Deaf Part 4";
            web[4] = "HTML development for Mute and Deaf Part 5";
        }
    }




}