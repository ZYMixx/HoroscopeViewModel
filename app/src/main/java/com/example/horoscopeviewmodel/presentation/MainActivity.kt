package com.example.horoscopeviewmodel.presentation

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.horoscopeviewmodel.databinding.LoginScreenBinding
import com.example.horoscopeviewmodel.domain.HoroscopeDate
import com.example.horoscopeviewmodel.domain.ZodiacSigns
import com.example.horoscopeviewmodel.presentation.viewmodel.LoginScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: LoginScreenViewModel
    private val binding by lazy {
        LoginScreenBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpViewModel()
        setUpSpinner()
        setUpAcceptButton()

        println("TEST PRINT")

    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this)[LoginScreenViewModel::class.java]
    }

    private fun setUpSpinner() {
        binding.signSpinner.adapter = ArrayAdapter(
            this, android.R.layout.select_dialog_item,
            ZodiacSigns.values()
        )
        binding.dataSpinner.adapter = ArrayAdapter(
            this, android.R.layout.select_dialog_item,
            HoroscopeDate.values()
        )
    }

    private fun setUpAcceptButton() {
        binding.acceptButton.setOnClickListener {
            startActivity(
                HoroscopeScreen.newIntent(
                    this,
                    binding.signSpinner.selectedItem as ZodiacSigns,
                    binding.dataSpinner.selectedItem as HoroscopeDate,
                )
            )
        }
    }
}


