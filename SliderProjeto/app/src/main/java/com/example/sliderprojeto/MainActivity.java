package com.example.sliderprojeto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;

public class MainActivity extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_um)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_dois)
                .build()
        );

        /*
        setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide( new SimpleSlide.Builder()
                                    .title("Título")
                                    .description("Descricao")
                                    .image(R.drawable.um)
                                    .background(android.R.color.holo_orange_light)
                                    .build()
        );
        addSlide( new SimpleSlide.Builder()
                                    .title("Título2")
                                    .description("Descricao2")
                                    .image(R.drawable.dois)
                                    .background(android.R.color.holo_orange_light)
                                    .build()
        );
        addSlide( new SimpleSlide.Builder()
                                    .title("Título3")
                                    .description("Descricao3")
                                    .image(R.drawable.tres)
                                    .background(android.R.color.holo_orange_light)
                                    .build()
        ); */
    }
}