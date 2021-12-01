package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton ibPlay;
    private ImageButton ibPause;
    private ImageButton ibStop;
    private SeekBar sbVol;
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ibPlay = findViewById(R.id.ibPlay);
        ibPause = findViewById(R.id.ibPause);
        ibStop = findViewById(R.id.ibStop);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.bach);

        ibPlay.setOnClickListener( v -> executarSom() );
        ibPause.setOnClickListener( v -> pausarSom() );
        ibStop.setOnClickListener( v -> pararSom() );
        inicializarSeekbar();
    }

    public void inicializarSeekbar(){
        sbVol = findViewById(R.id.sbVol);

        //Configura o AudioManage
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //Recupera volume máximo e o volume atual
        int volumeMaximo = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int volumeAtual = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        //Configura o valor máximo e atual para o Seekbar
        sbVol.setMax( volumeMaximo );
        sbVol.setProgress( volumeAtual );

        sbVol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0  );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void executarSom(){
        if(mediaPlayer != null) {
            Toast.makeText(getApplicationContext(), "Iniciando Som", Toast.LENGTH_SHORT).show();
            mediaPlayer.start();
        }
    }

    public void pausarSom(){
        if( mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    public void pararSom(){
        if( mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.bach);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if ( mediaPlayer != null && mediaPlayer.isPlaying() ){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        pausarSom();
    }
}