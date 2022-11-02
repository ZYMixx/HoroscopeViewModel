package com.example.learnwordshelper.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.learnwordshelper.domain.Horoscope

object HoroscopeHistoryDiffCallBack : DiffUtil.ItemCallback<Horoscope>() {

    override fun areItemsTheSame(oldItem: Horoscope, newItem: Horoscope): Boolean {
        return oldItem.horoscope == newItem.horoscope
    }

    override fun areContentsTheSame(oldItem: Horoscope, newItem: Horoscope): Boolean {
        return oldItem == newItem
    }
}