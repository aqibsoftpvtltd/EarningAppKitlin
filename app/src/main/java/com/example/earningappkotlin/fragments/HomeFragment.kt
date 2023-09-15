package com.example.earningappkotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.earningappkotlin.adapter.categoryAdapter
import com.example.earningappkotlin.models.categoryModel
import com.example.earningappkotlin.R
import com.example.earningappkotlin.databinding.FragmentHomeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class HomeFragment : Fragment() {

    private  val binding :FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }
    private var categoryList = ArrayList<categoryModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding.coin.setOnClickListener {
            val bottomSheetDialog :BottomSheetDialogFragment = Withdrawl()
            bottomSheetDialog.show(requireActivity().supportFragmentManager,"Test")
            bottomSheetDialog.enterTransition
        }
        binding.withDraw.setOnClickListener {
            val bottomSheetDialog :BottomSheetDialogFragment = Withdrawl()
            bottomSheetDialog.show(requireActivity().supportFragmentManager,"Test")
            bottomSheetDialog.enterTransition
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryList.clear()
        categoryList.add(categoryModel(R.drawable.scince1,"Science"))
        categoryList.add(categoryModel(R.drawable.english1,"English"))
        categoryList.add(categoryModel(R.drawable.geography,"Geography"))
        categoryList.add(categoryModel(R.drawable.math,"Math"))

        binding.categoryRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        val adapter = categoryAdapter(categoryList,requireActivity())
        binding.categoryRecyclerView.adapter = adapter
        binding.categoryRecyclerView.setHasFixedSize(true)
    }
    companion object {

    }
}