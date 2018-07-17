package com.example.bertogonz3000.songtest;

import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sdsmdg.harjot.crollerTest.Croller;
import com.sdsmdg.harjot.crollerTest.OnCrollerChangeListener;
public class SeekArcActivity extends AppCompatActivity {
    private AudioManager audioManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_arc);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);


        Croller croller = (Croller) findViewById(R.id.croller);
        croller.setIndicatorWidth(10);
        croller.setBackCircleColor(Color.parseColor("#EDEDED"));
        croller.setMainCircleColor(Color.WHITE);
        //croller.setMax(50);
        croller.setStartOffset(45);
        croller.setIsContinuous(false);
        croller.setLabel("Surround");
        croller.setLabelColor(Color.BLACK);
        croller.setProgressPrimaryColor(Color.parseColor("#0B3C49"));
        croller.setIndicatorColor(Color.parseColor("#0B3C49"));
        croller.setProgressSecondaryColor(Color.parseColor("#EEEEEE"));

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        croller.setMax(audioManager
                .getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        croller.setProgress(audioManager
                .getStreamVolume(AudioManager.STREAM_MUSIC));

        croller.setOnCrollerChangeListener(new OnCrollerChangeListener() {
            @Override
            public void onProgressChanged(Croller croller, int progress) {
                // use the progress
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                        progress, 0);
            }

            @Override
            public void onStartTrackingTouch(Croller croller) {
                // tracking started
            }

            @Override
            public void onStopTrackingTouch(Croller croller) {
                // tracking stopped
            }
        });
    }
}
