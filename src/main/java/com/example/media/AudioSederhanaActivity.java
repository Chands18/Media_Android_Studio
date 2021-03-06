package com.example.media;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioSederhanaActivity extends AppCompatActivity {

    @BindView(R.id.btnPlay)
    Button btnPlay;
    @BindView(R.id.btnPause)
    Button btnPause;
    @BindView(R.id.btnResume)
    Button btnResume;
    @BindView(R.id.btnStop)
    Button btnStop;
    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_sederhana);
        ButterKnife.bind(this);
        enable(btnPlay);
        disable(btnPause,btnResume,btnStop);


    }


    @OnClick({R.id.btnPlay, R.id.btnPause, R.id.btnResume, R.id.btnStop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnPlay:
                Uri uriMusik = Uri.parse("android.resource://" + getPackageName()+"/"+R.raw.kawasakih2);
                mPlayer = new MediaPlayer();
                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mPlayer.setDataSource(AudioSederhanaActivity.this,uriMusik);
                } catch (IOException e){
                    e.printStackTrace();
                }
                mPlayer.start();
                enable(btnStop,btnPause);
                disable(btnPlay,btnResume);
                break;
            case R.id.btnPause:
                if (mPlayer.isPlaying()){
                    mPlayer.pause();
                    enable(btnResume,btnStop);
                    disable(btnPlay,btnPause);
                }
                break;
            case R.id.btnResume:
                mPlayer.start();
                enable(btnPause,btnStop);
                disable(btnResume,btnPlay);
                break;
            case R.id.btnStop:
                if (mPlayer.isPlaying() && mPlayer !=null){
                    mPlayer.stop();
                    enable(btnPlay);
                    disable(btnResume,btnStop,btnPause);
                }
                break;
        }
    }
    public void enable(View...views){
        for (View mView : views){
            mView.setEnabled(true);
        }
    }
    public void disable(View...views){
        for (View mView : views){
            mView.setEnabled(true);
        }
    }
}