package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalcular.setOnClickListener { calculateTip() }
    }

    fun calculateTip() {
        val textoIntroducido = binding.editTextTextPersonName.text.toString()
        val coste = textoIntroducido.toDouble()
        val selectedRadio = binding.radioGroup.checkedRadioButtonId

        val porcentage = when (selectedRadio) {
            R.id.radioButton20 -> 0.20
            R.id.radioButton18 -> 0.18
            else -> 0.15

        }

        var tip = porcentage * coste

        val redondear = binding.switchRedondear.isChecked

        if (redondear) {
            tip = ceil(tip)
        }

        val formatedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipAmount.text = getString(R.string.tip_amount, formatedTip)


    }
}