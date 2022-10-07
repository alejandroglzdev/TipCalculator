package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalcular.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val textIntroduced = binding.editTextTextPersonName.text.toString()
        val cost = textIntroduced.toDoubleOrNull()
        if (cost == null) {
            binding.tipAmount.text = ""
            return
        }

        val percentage = when (binding.radioGroup.checkedRadioButtonId) {
            R.id.radioButton20 -> 0.20
            R.id.radioButton18 -> 0.18
            else -> 0.15

        }

        var tip = percentage * cost

        val roundOut = binding.switchRedondear.isChecked

        if (roundOut) {
            tip = ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipAmount.text = getString(R.string.tip_amount, formattedTip)


    }
}