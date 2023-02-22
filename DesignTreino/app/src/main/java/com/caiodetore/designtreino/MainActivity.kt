package com.caiodetore.designtreino

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val outlineText = findViewById<TextInputLayout>(R.id.textInputLayout)
        val textInputEdit = findViewById<TextInputEditText>(R.id.textInputEditText)
        val button = findViewById<Button>(R.id.button)
        val switch = findViewById<Switch>(R.id.switch1)
        val texto = textInputEdit.text

        switch.setOnClickListener {
            if (switch.isChecked) AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
            else AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        }

        button.setOnClickListener{
            outlineText.isHelperTextEnabled = true
            outlineText.helperText = texto.toString()
        }
    }
}