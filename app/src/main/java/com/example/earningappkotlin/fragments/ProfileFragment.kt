package com.example.earningappkotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.earningappkotlin.R
import com.example.earningappkotlin.databinding.FragmentProfileBinding
import com.example.earningappkotlin.models.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

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

        Firebase.database.reference.child("Users").child(Firebase.auth.currentUser!!.uid).addValueEventListener(
            object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {

                    var user = snapshot.getValue<User>()
                    binding.name.text=user?.name
                    binding.nameProfile.text=user?.name
                    binding.email.text=user?.email
                    binding.password.text=user?.password
                    binding.age.text=user?.age.toString()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            }
        )

        return binding.root
    }

}