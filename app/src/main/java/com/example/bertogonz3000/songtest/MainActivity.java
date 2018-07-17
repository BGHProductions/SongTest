package com.example.bertogonz3000.songtest;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaFormat;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MediaPlayer song;
    Button button;
    AudioTrack testTrack;
    SoundPool.Builder soundPoolBuilder;
    SoundPool soundPool;
    int loadID;
    AudioFormat format;

    float rightVol, leftVol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Using SoundPool would be great, b/c I have idea how to set left and right volume without it.
        //HOWEVER...it seems we must use builder.
//        soundPoolBuilder = new SoundPool.Builder();
//        soundPoolBuilder.setMaxStreams(3);
//        soundPoolBuilder.setAudioAttributes(new AudioAttributes.Builder()
//                .setUsage(AudioAttributes.USAGE_MEDIA)
//                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
//                .build());
//
//        soundPool = soundPoolBuilder.build();
//
//        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
//            @Override
//            public void onLoadComplete(SoundPool soundPool, int i, int i1) {
//                Toast.makeText(getApplicationContext(), "Song Loaded", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        loadID = soundPool.load(this, R.raw.heyjudy, 1);

        //format = new AudioFormat.Builder();

        rightVol = 1;
        leftVol = 1;

        //TODO - Copy a soundfile into a new directory under "res" and place it here
        //TODO - as the second argument
        song = MediaPlayer.create(MainActivity.this, R.raw.heyjude);

        MediaPlayer.TrackInfo[] trackInfo = song.getTrackInfo();

        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();

        song.setAudioAttributes(attributes);



        Log.e("HELLO!", trackInfo[0].getFormat().getString(Integer.toString(AudioFormat.CHANNEL_OUT_SIDE_LEFT)));
//
//        int outputBufferSize = AudioTrack.getMinBufferSize(16000,
//                AudioFormat.CHANNEL_OUT_7POINT1_SURROUND,
//                AudioFormat.ENCODING_PCM_16BIT);
//
//        testTrack = new AudioTrack(AudioManager.MODE_CURRENT, 16000,
//                AudioFormat.CHANNEL_OUT_7POINT1_SURROUND, AudioFormat.ENCODING_PCM_16BIT,
//                outputBufferSize, AudioTrack.MODE_STREAM);
//
//        int channelCount = testTrack.getChannelCount();
//        int channelConfig = testTrack.getChannelConfiguration();


//        Log.i("Channel Count", Integer.toString(channelCount));
//        Log.i("Channel Config", Integer.toString(channelConfig));



    }

    public void playSong(View view){
        song.setVolume(leftVol,rightVol);
        song.start();
        //soundPool.play(loadID, 1, 1, 1, 0,1);

    }

    public void pauseSong(View view){
        song.pause();
    }

    public void upLeft(View view){
        if (leftVol <= 0.9) {
            leftVol = leftVol + (float) 0.1;
            song.setVolume(leftVol, rightVol);
        }
            Log.i("upLeft", Float.toString(leftVol));

    }

    public void downLeft(View view){
        if (leftVol >= 0.1) {
            leftVol = leftVol - (float) 0.1;
            song.setVolume(leftVol, rightVol);
        }
        Log.i("downLeft", Float.toString(leftVol));
    }

    public void upRight(View view){
        if (rightVol <= 0.9) {
            rightVol = rightVol + (float) 0.1;
            song.setVolume(leftVol, rightVol);
        }
        Log.i("upRight", Float.toString(rightVol));
    }

    public void downRight(View view){
        if (rightVol >= 0.1) {
            rightVol = rightVol - (float) 0.1;
            song.setVolume(leftVol, rightVol);
        }
        Log.i("downRight", Float.toString(rightVol));
    }

    public void equalize(View view){
        rightVol = 1;
        leftVol = 1;
        song.setVolume(leftVol, rightVol);
        Log.i("Equalize", "right and left @ 100%");
    }

    public void allLeft(View view){
        rightVol = 0;
        leftVol = 1;
        song.setVolume(leftVol,rightVol);
        Log.i("allLeft", "Left = 1, Right = 0");
    }

    public void allRight(View view){
        rightVol = 1;
        leftVol = 0;
        song.setVolume(leftVol,rightVol);
        Log.i("allRight", "Right = 1, Left = 0");
    }

    @Override
    protected void onPause(){
        super.onPause();
        song.release();
        song = null;
    }

    @Override
    protected void onStop(){
        super.onStop();
        song.release();
        song = null;
    }

    @Override
    protected void onResume(){
        super.onResume();
        song = MediaPlayer.create(MainActivity.this, R.raw.heyjude);
    }
}
