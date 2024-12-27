package com.example.gascalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var textInputAlcool: TextInputLayout;
    private lateinit var editAlcool: TextInputEditText;

    private lateinit var textInputGasoline: TextInputLayout;
    private lateinit var editGasoline: TextInputEditText;

    private lateinit var calculateBtn: Button;
    private lateinit var textResult: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents();

        calculateBtn.setOnClickListener {
            calculateBestPrice();
        };
    }

    private fun calculateBestPrice() {

        val alcoolPrice = editAlcool.text.toString();
        val gasolinePrice = editGasoline.text.toString();


        if (validateInput(alcoolPrice, gasolinePrice)) {
            if ((alcoolPrice.toDouble() / gasolinePrice.toDouble()) >= 0.7) {
                textResult.text = "Melhor utilizar Gasolina";
            } else {
                textResult.text = "Melhor utilizar Álcool";

            }
        }


    }

    private fun validateInput(alcoolPrice: String, gasPrice: String): Boolean {
        textInputAlcool.error = null;
        textInputGasoline.error = null;

        if (alcoolPrice.isEmpty()) {
            textInputAlcool.error = "Digite o preço do álcool"
            return false;
        } else if (gasPrice.isEmpty()) {
            textInputGasoline.error = "Digite o preço da gasolina"
            return false;
        }

        return true;
    }

    private fun initComponents() {
        textInputAlcool = findViewById(R.id.textInputAlcool);
        editAlcool = findViewById(R.id.editAlcool);


        textInputGasoline = findViewById(R.id.textInputGasoline);
        editGasoline = findViewById(R.id.editGasoline);

        calculateBtn = findViewById(R.id.calculateButton);
        textResult = findViewById(R.id.textResult);


    }
}