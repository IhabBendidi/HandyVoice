package com.HandyVoice.Activities;
import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.HandyVoice.Model.Comment;
import com.HandyVoice.R;
import com.HandyVoice.data.CommentProvider;

import java.util.List;
import java.util.Random;

public class play_video extends AppCompatActivity {
    private VideoView myVideoView;
    private int position = 0;
    private Context acontext;
    private int lastPosition = -1;
    private ProgressDialog progressDialog;
    String s;

    private android.support.v7.widget.Toolbar toolbar;
    private MediaController mediaControls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        s = getIntent().getStringExtra("TITLE_VIDEO");
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rv = (RecyclerView)findViewById(R.id.comment_list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new CommentListAdapter(new CommentProvider().Commentdisplay()));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if (mediaControls == null) {
            mediaControls = new MediaController(this);
        }
        //initialize the VideoView
        myVideoView = (VideoView) findViewById(R.id.videoView);
        // create a progress bar while the video file is loading
        progressDialog = new ProgressDialog(this);

        // set a title for the progress bar
        progressDialog.setTitle(this.s);
        // set a message for the progress bar
        progressDialog.setMessage("Loading...");
        //set the progress bar not cancelable on users' touch
        progressDialog.setCancelable(false);
        // show the progress bar
        progressDialog.show();
        try {
            //set the media controller in the VideoView
            mediaControls.setAnchorView(toolbar);
            myVideoView.setMediaController(mediaControls);


            //set the uri of the video to be played
            setVideo(this.s);
          //  myVideoView.setVideoURI(Uri.parse("http://commonsware.com/misc/test2.3gp"));
        } catch (Exception e) {

            Log.e("Error", e.getMessage());

            e.printStackTrace();

        }



        myVideoView.requestFocus();

        //we also set an setOnPreparedListener in order to know when the video file is ready for playback

        myVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {
                // close the progress bar and play the video
                progressDialog.dismiss();
                //if we have a position on savedInstanceState, the video playback should start from here
                myVideoView.seekTo(position);
                if (position == 0) {
                    myVideoView.start();
                } else {
                    //if we come from a resumed activity, video playback will be paused

                    myVideoView.pause();

                }

            }

        });



    }
    public void setVideo(String videoTitle){
        if (videoTitle.equals("Video 1: 25 Basic ASL Signs Part 1")){
            myVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.asl_part1));
        }else if (videoTitle.equals("Video 2: 25 Basic ASL Signs Part 2")){
            myVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.asl_part2));
        }else if (videoTitle.equals("Video 3: 25 Basic ASL Signs Part 3")){
            myVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.asl_part3));
        }else if (videoTitle.equals("Video 1: A day through a deaf person's eyes")){
            myVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.understand));
        }else if (videoTitle.equals("HTML development for Mute and Deaf Part 1")){
            myVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.html1));
        }else if (videoTitle.equals("HTML development for Mute and Deaf Part 2")){
            myVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.html2));
        }else if (videoTitle.equals("HTML development for Mute and Deaf Part 3")){
            myVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.html3));
        }else if (videoTitle.equals("HTML development for Mute and Deaf Part 4")){
            myVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.html4));
        }else if (videoTitle.equals("HTML development for Mute and Deaf Part 5")){
            myVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.html5));
        } else {
            myVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.demo));
        }
    }

    class CommentListAdapter extends RecyclerView.Adapter<CommentListHolder> {
        private final List<Comment> comments;
        CommentListAdapter(List<Comment> comments) {
            this.comments = comments;
        }
        @Override
        public CommentListHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            final LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            final View v = layoutInflater.inflate(R.layout.comment_card, viewGroup, false);
            return new CommentListHolder(v);
        }
        @Override
        public void onBindViewHolder(CommentListHolder commentlistHolder, int i) {
            commentlistHolder.comment1.setText(comments.get(i).getcomment());
            setAnimation(commentlistHolder.container, i);
        }

        @Override
        public int getItemCount() {
            return comments.size();
        }
    }
    class CommentListHolder extends RecyclerView.ViewHolder{
        TextView comment1;
        LinearLayout container;

        CommentListHolder(View itemView) {
            super(itemView);
            comment1 = (TextView) itemView.findViewById(R.id.comment);
            container = (LinearLayout) itemView.findViewById(R.id.comment_cv);

        }

    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(new Random().nextInt(501));//to make duration random number between [0,501)
            viewToAnimate.startAnimation(anim);
            lastPosition = position;
        }
    }



    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        //we use onSaveInstanceState in order to store the video playback position for orientation change
        savedInstanceState.putInt("Position", myVideoView.getCurrentPosition());
        myVideoView.pause();
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //we use onRestoreInstanceState in order to play the video playback from the stored position
        position = savedInstanceState.getInt("Position");
        myVideoView.seekTo(position);

    }


}

