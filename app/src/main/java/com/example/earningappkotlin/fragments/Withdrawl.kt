package com.example.earningappkotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.earningappkotlin.R
import com.example.earningappkotlin.databinding.FragmentWithdrawlBinding
import com.example.earningappkotlin.models.HistoryModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class Withdrawl : BottomSheetDialogFragment() {

    private lateinit var binding : FragmentWithdrawlBinding
    var currentCoins =0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWithdrawlBinding.inflate(inflater, container, false)

        Firebase.database.reference.child("playerCoin").child(Firebase.auth.currentUser!!.uid)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()){
                        currentCoins = snapshot.value as Long
                       // binding.coin.text = currentCoins.toString()

                    }

                }
                override fun onCancelled(error: DatabaseError) {
                }

            })

        binding.submit.setOnClickListener {
            if (binding.enterAmount.text.toString().toDouble()<=currentCoins){
                Firebase.database.reference.child("playerCoin").child(Firebase.auth.currentUser!!.uid).setValue(currentCoins-binding.enterAmount.text.toString().toDouble())

                var historyModel = HistoryModel(System.currentTimeMillis().toString(),binding.enterAmount.text.toString(),true)
                Firebase.database.reference.child("playerCoinHistory").child(Firebase.auth.currentUser!!.uid).push().setValue(historyModel)
            }
            else{
                Toast.makeText(activity,"Out of money",Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    companion object {

    }
}