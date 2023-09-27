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
import com.example.earningappkotlin.models.User
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.util.Collections


class HistoryFragment : Fragment() {
    val binding by lazy {
        FragmentHistoryBinding.inflate(layoutInflater)
    }
    private val listHistory = ArrayList<HistoryModel>()
    private val listHistoryReverse = ArrayList<HistoryModel>()
    lateinit var adapter : HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         adapter = HistoryAdapter(listHistory)
        Firebase.database.reference.child("playerCoinHistory").child(Firebase.auth.currentUser!!.uid)
            .addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                   listHistory.clear()
                    for (dataSnapshot in snapshot.children){

                        var  data = dataSnapshot.getValue(HistoryModel::class.java)
                        listHistory.add(data!!)

                    }
                    listHistory.reverse()

                    adapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding.historyRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.historyRecyclerView.adapter = adapter
        binding.historyRecyclerView.setHasFixedSize(true)

        binding.coin.setOnClickListener {
            val bottomSheetDialog: BottomSheetDialogFragment = Withdrawl()
            bottomSheetDialog.show(requireActivity().supportFragmentManager, "Test")
            bottomSheetDialog.enterTransition
        }
        binding.withDraw.setOnClickListener {
            val bottomSheetDialog: BottomSheetDialogFragment = Withdrawl()
            bottomSheetDialog.show(requireActivity().supportFragmentManager, "Test")
            bottomSheetDialog.enterTransition
        }

        Firebase.database.reference.child("Users").child(Firebase.auth.currentUser!!.uid)
            .addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {

                        var user = snapshot.getValue<User>()
                        binding.name.text = user?.name

                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                }
            )
        Firebase.database.reference.child("playerCoin").child(Firebase.auth.currentUser!!.uid)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        binding.coin.text = snapshot.value.toString()


                    }

                }

                override fun onCancelled(error: DatabaseError) {
                }

            })


        return binding.root
    }

}