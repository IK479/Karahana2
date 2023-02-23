//package com.example.karahana.Activities;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import com.example.karahana.R;
//import com.example.karahana.managers.Models.YouTubeConf;
//import com.google.android.youtube.player.YouTubeBaseActivity;
//import com.google.android.youtube.player.YouTubeInitializationResult;
//import com.google.android.youtube.player.YouTubePlayer;
//import com.google.android.youtube.player.YouTubePlayerView;
//
//public class YouTubeTest extends YouTubeBaseActivity {
//
//    YouTubePlayerView youTubePlayerView;
//    Button btnPlay;
//    YouTubePlayer.OnInitializedListener onInitializedListener;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_you_tube_test2);
//        btnPlay = (Button)findViewById(R.id.btnPlay);
//        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.viewYoutube);
//
//
//        YouTubePlayer.OnInitializedListener onInitializedListener = new YouTubePlayer.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//                youTubePlayer.loadVideo("SadeVEVO"); // Cue the video
//                youTubePlayer.setPlaybackEventListener(new YouTubePlayer.PlaybackEventListener() {
//                    @Override
//                    public void onPlaying() {
//                        // Audio is now playing
//                    }
//
//                    @Override
//                    public void onPaused() {
//                        // Audio is now paused
//                    }
//
//                    @Override
//                    public void onStopped() {
//
//                    }
//
//                    @Override
//                    public void onBuffering(boolean b) {
//
//                    }
//
//                    @Override
//                    public void onSeekTo(int i) {
//
//                    }
//                });
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//                // Handle initialization failure
//            }
//        };
//        btnPlay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                youTubePlayerView.initialize("AIzaSyBpfv5s3NG03RSQPbtjtR3vXfOSFiW4bWk", onInitializedListener);
//            }
//        });
//
//
//
//    }
//}