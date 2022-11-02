package com.example.horoscopeviewmodel.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.horoscopeviewmodel.databinding.HistoryScreenBinding
import com.example.horoscopeviewmodel.presentation.viewmodel.HistoryHoroscopeScreenViewModel
import com.example.learnwordshelper.presentation.adapter.HistoryHoroscopeViewHolder
import com.example.learnwordshelper.presentation.adapter.HoroscopeHistoryListAdapter

class HistoryHoroscopeScreen : AppCompatActivity() {

    private lateinit var viewModel: HistoryHoroscopeScreenViewModel
    private val binding by lazy {
        HistoryScreenBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpViewModel()
        setUpRV()
    }

    private fun setUpRV() {
        val adapter = HoroscopeHistoryListAdapter()
        binding.historyRv.adapter = adapter
        viewModel.horoscopeListLD.observe(this) {
            adapter.submitList(it)
        }
        adapter.onYesClicked = {
            viewModel.editHoroscopeResults(it, true)
        }
        adapter.onNoClicked = {
            viewModel.editHoroscopeResults(it, false)
        }
        adapter.onLongClicked = {
            viewModel.editHoroscopeResults(it, null)
        }
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this)[HistoryHoroscopeScreenViewModel::class.java]
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, HistoryHoroscopeScreen::class.java)
        }
    }

}