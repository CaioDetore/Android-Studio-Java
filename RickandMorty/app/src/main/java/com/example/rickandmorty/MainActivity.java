package com.example.rickandmorty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton ibPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ibPlay = findViewById(R.id.ibPlay);

        ibPlay.setOnClickListener( v -> executarVideo() );

    }

    public void executarVideo(){
        startActivity( new Intent(this, PlayerActivity.class ) );
    }

}