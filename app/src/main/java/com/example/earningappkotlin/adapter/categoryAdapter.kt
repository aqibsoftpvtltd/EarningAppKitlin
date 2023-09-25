package com.example.earningappkotlin.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.earningappkotlin.models.categoryModel
import com.example.earningappkotlin.QuizActivity
import com.example.earningappkotlin.databinding.CategoryItemBinding

class categoryAdapter(var categoryList: ArrayList<categoryModel>, var requireActivity: FragmentActivity) : RecyclerView.Adapter<categoryAdapter.MyCategoryViewHolder> (){
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

        holder.binding.categoryBtn.setOnClickListener {
            var intent = Intent(requireActivity, QuizActivity::class.java)
            intent.putExtra("categoryImage",dataList.cartImg)
            intent.putExtra("questionType",dataList.text)
            requireActivity.startActivity(intent)
        }
    }
}