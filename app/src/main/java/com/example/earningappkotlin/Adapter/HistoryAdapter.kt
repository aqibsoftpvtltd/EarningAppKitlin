package com.example.earningappkotlin.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.earningappkotlin.Models.HistoryModel
import com.example.earningappkotlin.databinding.HistoryItemBinding

class HistoryAdapter (var listHistory : ArrayList<HistoryModel>) :RecyclerView.Adapter<HistoryAdapter.HistoryCoinViewHolder>() {

    class HistoryCoinViewHolder (var binding : HistoryItemBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryCoinViewHolder {
        return  HistoryCoinViewHolder(HistoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount() = listHistory.size

    override fun onBindViewHolder(holder: HistoryCoinViewHolder, position: Int) {
        holder.binding.timeDate.text = listHistory[position].timeDate
        holder.binding.coin.text =listHistory[position].coin
    }
}