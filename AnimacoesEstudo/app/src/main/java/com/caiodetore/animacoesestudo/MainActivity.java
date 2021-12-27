package com.caiodetore.animacoesestudo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Animation animation = AnimationUtils.loadAnimation(getApplicationContext()
//        ,R.anim.fade_in);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Animated Dialog")
                .setMessage("This is a animated dialog")
        .setPositiveButton("Ok", null);

        AlertDialog dialog = builder.create();
        // adiciona animação do dialog
        dialog.getWindow().getAttributes().windowAnimations = R.style.MyDialogAnimation2;

        findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }

    public void open(View view) {
        Intent i = new Intent(this, MainActivity2.class);
//        Bundle b = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
//        startActivity(i, b);
        startActivity(i);
    }

    public void openShared(View view){
        Intent i = new Intent(this, SharedActivity.class);
        Bundle b = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
        startActivity(i, b);
    }

    // n funciona
    public void open2(View view) {
        Intent i = new Intent(this, MainActivity2.class);
//        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.slide, R.anim.slide_exit);
//        ActivityCompat.startActivity(MainActivity.this, i, activityOptions.toBundle() );
        startActivity(i);
    }
}