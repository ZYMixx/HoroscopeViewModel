package com.example.horoscopeviewmodel.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.horoscopeviewmodel.databinding.HoroscopeScreenBinding
import com.example.horoscopeviewmodel.domain.HoroscopeDate
import com.example.horoscopeviewmodel.domain.ZodiacSigns
import com.example.horoscopeviewmodel.presentation.viewmodel.HoroscopeScreenViewModel
import com.squareup.picasso.Picasso

class HoroscopeScreen : AppCompatActivity() {

    private lateinit var viewModel: HoroscopeScreenViewModel
    private lateinit var sing: ZodiacSigns
    private lateinit var date: HoroscopeDate
    private val binding by lazy {
        HoroscopeScreenBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        parsParams()
        setUpViewModel()
        setUpButtons()
    }

    fun parsParams() {
        sing = intent.extras?.get(SING_KEY) as ZodiacSigns
        date = intent.extras?.get(DATE_KEY) as HoroscopeDate
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this)[HoroscopeScreenViewModel::class.java]
        viewModel.horoscopeLD.observe(this) {
            binding.horoscopeTv.text = it.horoscope
            Picasso.get().load(it.image).into(binding.horoscopeImage)
            binding.scrollView.visibility = View.VISIBLE
            binding.horoscopeCardView.visibility = View.VISIBLE
            binding.progressBar.visibility = View.INVISIBLE
        }
        viewModel.getHoroscope(sing, date)
    }

    private fun setUpButtons() {
        binding.historyButton.setOnClickListener {
            startActivity(HistoryHoroscopeScreen.newIntent(this))
        }
    }

    companion object {
        const val SING_KEY = "sing"
        const val DATE_KEY = "date"

        fun newIntent(context: Context, sing: ZodiacSigns, time: HoroscopeDate): Intent {
            return Intent(context, HoroscopeScreen::class.java).apply {
                putExtra(SING_KEY, sing)
                putExtra(DATE_KEY, time)
            }
        }
    }

}