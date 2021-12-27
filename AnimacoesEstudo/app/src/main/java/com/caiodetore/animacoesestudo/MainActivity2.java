package com.caiodetore.animacoesestudo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    Animation fall;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        fall = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fall_down);

        btn = findViewById(R.id.button);

        btn.setOnClickListener( view -> { btn.startAnimation(fall); });
    }

}