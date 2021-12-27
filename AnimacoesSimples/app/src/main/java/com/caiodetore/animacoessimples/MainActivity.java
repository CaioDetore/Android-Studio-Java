package com.caiodetore.animacoessimples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.bhargavms.dotloader.DotLoader;

public class MainActivity extends AppCompatActivity {
    private LinearLayout layoutDotLoader;
    private DotLoader dotLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dotLoader = findViewById(R.id.text_dot_loader);
//        layoutDotLoader = findViewById(R.id.layout_dot_loader);

        dotLoader.postDelayed(() -> dotLoader.setNumberOfDots(3), 100);

    }
}