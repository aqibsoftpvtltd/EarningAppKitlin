package com.example.earningappkotlin.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.earningappkotlin.Models.categoryModel
import com.example.earningappkotlin.databinding.CategoryItemBinding

class categoryAdapter(var categoryList : ArrayList<categoryModel>) : RecyclerView.Adapter<categoryAdapter.MyCategoryViewHolder> (){
    class MyCategoryViewHolder(var binding : CategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCategoryViewHolder {
       return MyCategoryViewHolder(CategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount() = categoryList.size

    override fun onBindViewHolder(holder: MyCategoryViewHolder, position: Int) {
        var dataList = categoryList[position]
        holder.binding.categoryImage.setImageResource(dataList.cartImg)
        holder.binding.category.setText(dataList.text)
    }
}