package com.example.learnwordshelper.presentation.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.horoscopeviewmodel.databinding.HistoryChooseItemBinding
import com.example.horoscopeviewmodel.databinding.HistoryCompleteItemBinding
import com.example.horoscopeviewmodel.domain.HoroscopeDate
import com.example.horoscopeviewmodel.presentation.utils.TimeCreater
import com.example.learnwordshelper.domain.Horoscope

class HoroscopeHistoryListAdapter :
    ListAdapter<Horoscope, HistoryHoroscopeViewHolder>(HoroscopeHistoryDiffCallBack) {

    var onYesClicked: ((Horoscope) -> Unit)? = null
    var onNoClicked: ((Horoscope) -> Unit)? = null
    var onLongClicked: ((Horoscope) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHoroscopeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutBinding = when (viewType) {
            VIEW_TYPE_OPEN -> HistoryChooseItemBinding.inflate(layoutInflater, parent, false)
            VIEW_TYPE_CLOSE -> HistoryCompleteItemBinding.inflate(layoutInflater, parent, false)
            else -> throw RuntimeException("Unknown view type $viewType")
        }
        return HistoryHoroscopeViewHolder(layoutBinding)
    }

    override fun onBindViewHolder(holder: HistoryHoroscopeViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_CLOSE) {
            setUpCloseItem(
                holder.itemBinding as HistoryCompleteItemBinding,
                getItem(position)
            )
        }
        if (getItemViewType(position) == VIEW_TYPE_OPEN) {
            setUpOpenItem(
                holder.itemBinding as HistoryChooseItemBinding,
                getItem(position)
            )
        }
    }

    val TAG = "ADAPTER_TAG"

    private fun setUpOpenItem(binding: HistoryChooseItemBinding, horoscope: Horoscope) {
        binding.historyInfo.text = "${horoscope.zodiacSign} ${horoscope.date}"
        binding.historyHoroscope.text = horoscope.horoscope
        binding.positivButton.setOnClickListener {
            onYesClicked?.invoke(horoscope)
            Log.d(TAG, "onYesClicked: ")
        }
        binding.negativButton.setOnClickListener {
            onNoClicked?.invoke(horoscope)
            Log.d(TAG, "negativButton: ")
        }
    }

    private fun setUpCloseItem(binding: HistoryCompleteItemBinding, horoscope: Horoscope) {
        binding.historyInfo.text = "${horoscope.zodiacSign} ${TimeCreater.getDate(horoscope.date)}"
        binding.historyHoroscope.text = horoscope.horoscope
        binding.root.setOnClickListener {
            onLongClicked?.invoke(horoscope)
        }
        horoscope.horoscopeResult?.let {
            when (it) {
                true -> {
                    binding.constraint.setBackgroundColor(Color.GREEN)
                }
                false -> {
                    binding.constraint.setBackgroundColor(Color.RED)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).horoscopeResult) {
            true -> VIEW_TYPE_CLOSE
            false -> VIEW_TYPE_CLOSE
            null -> VIEW_TYPE_OPEN
        }
        return super.getItemViewType(position)
    }

    companion object {
        const val VIEW_TYPE_CLOSE = 100
        const val VIEW_TYPE_OPEN = 101
    }
}