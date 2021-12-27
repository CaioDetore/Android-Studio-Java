package com.caiodetore.animacoesestudo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SharedActivity extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);

        img= findViewById(R.id.imagem);
    }

    public void go(View view) {
        Intent i = new Intent(this, SharedActivity2.class);
        Bundle b = ActivityOptions.makeSceneTransitionAnimation(this, img, "imagem").toBundle();
        startActivity(i, b);
    }
}