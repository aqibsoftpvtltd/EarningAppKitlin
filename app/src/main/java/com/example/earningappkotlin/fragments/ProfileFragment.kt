package com.example.earningappkotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.earningappkotlin.R
import com.example.earningappkotlin.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    val binding by lazy { FragmentProfileBinding.inflate(layoutInflater) }
    var isExpand = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment



        binding.personalInfoLyt.setOnClickListener {
            if (isExpand){
                binding.expandableLyt.visibility = View.VISIBLE
                binding.arrow.setImageResource(R.drawable.arrowup)
            }
            else{
                binding.expandableLyt.visibility = View.GONE
                binding.arrow.setImageResource(R.drawable.downarrow)

            }
            isExpand =! isExpand

        }
        return binding.root
    }

}