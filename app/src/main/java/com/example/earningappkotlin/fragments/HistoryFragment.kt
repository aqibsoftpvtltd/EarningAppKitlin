package com.example.earningappkotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.earningappkotlin.adapter.HistoryAdapter
import com.example.earningappkotlin.models.HistoryModel
import com.example.earningappkotlin.databinding.FragmentHistoryBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class HistoryFragment : Fragment() {
    val binding by lazy {
        FragmentHistoryBinding.inflate(layoutInflater)
    }
    private val listHistory = ArrayList<HistoryModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listHistory.add(HistoryModel("12:03","200"))
        listHistory.add(HistoryModel("12:34","300"))
        listHistory.add(HistoryModel("12:06","100"))
        listHistory.add(HistoryModel("12:43","500"))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding.historyRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        var adapter = HistoryAdapter(listHistory)
        binding.historyRecyclerView.adapter = adapter
        binding.historyRecyclerView.setHasFixedSize(true)

        binding.coin.setOnClickListener {
            val bottomSheetDialog : BottomSheetDialogFragment = Withdrawl()
            bottomSheetDialog.show(requireActivity().supportFragmentManager,"Test")
            bottomSheetDialog.enterTransition
        }
        binding.withDraw.setOnClickListener {
            val bottomSheetDialog : BottomSheetDialogFragment = Withdrawl()
            bottomSheetDialog.show(requireActivity().supportFragmentManager,"Test")
            bottomSheetDialog.enterTransition
        }
        return binding.root
    }

}