package com.example.earningappkotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.earningappkotlin.models.HistoryModel
import com.example.earningappkotlin.databinding.HistoryItemBinding
import java.sql.Timestamp
import java.util.Date

class HistoryAdapter (private var listHistory : ArrayList<HistoryModel>) :RecyclerView.Adapter<HistoryAdapter.HistoryCoinViewHolder>() {

    class HistoryCoinViewHolder (var binding : HistoryItemBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryCoinViewHolder {
        return  HistoryCoinViewHolder(HistoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount() = listHistory.size

    override fun onBindViewHolder(holder: HistoryCoinViewHolder, position: Int) {

        val timeStamp = Timestamp(listHistory[position].timeDate.toLong())
        holder.binding.timeDate.text = Date(timeStamp.time).toString()
        holder.binding.coin.text =listHistory[position].coin

        if (listHistory[position].isWithDrawal){
            holder.binding.status.text = "- Money Withdrawal"
        }
        else{
            holder.binding.status.text = "+ Money Added"
        }

    }
}