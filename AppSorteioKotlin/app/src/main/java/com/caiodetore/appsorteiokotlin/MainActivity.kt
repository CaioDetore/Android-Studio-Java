package com.caiodetore.appsorteiokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCalcular = findViewById<Button>(R.id.button2)
        btnCalcular.setOnClickListener({
            calcularPreco()
        })
    }

    fun calcularPreco(){
        val precoAlcool = edtAlcool.text.toString()
        val precoGasolina = edtGasolina.text.toString()

        val validarCampos = validarCampos(precoAlcool, precoGasolina)
    }

    fun calcularMelhorPreco(precoAlcool: String, precoGasolina: String){
        val valorAlcool = precoAlcool.toDouble()
        val valorGasolina = precoGasolina.toDouble()

        val resultadoPreco = valorAlcool/valorGasolina

        if ( resultadoPreco >= 0.7 ) {
            txtResult.text = "Gasolina >"
        } else {
            txtResult.text = "Alcool >"
        }
    }

    fun validarCampos(precoAlcool: String, precoGasolina: String) : Boolean {

        var camposValidados: Boolean = true;
        if( precoAlcool == null || precoAlcool.equals("") ) {
            camposValidados = false
        } else if ( precoGasolina == null || precoGasolina.equals("") ){
            camposValidados = false
        }

        return camposValidados
    }

    fun sortearNumero(view: View){
        var texto = findViewById<TextView>(R.id.textView)
        var numSort = Random().nextInt(11)
        texto.text = "Número sorteado: " + numSort
    }

}