package com.example.earningappkotlin.fragments

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.earningappkotlin.R
import com.example.earningappkotlin.databinding.FragmentSpinBinding
import com.example.earningappkotlin.models.User
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlin.random.Random


class SpinFragment : Fragment() {
    private lateinit var binding: FragmentSpinBinding
    private lateinit var timer : CountDownTimer
    private val itemTitlees = arrayOf("100","Try Again", "500", "Try Again", "200", "Try Again")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSpinBinding.inflate(inflater,container, false)
        // Inflate the layout for this fragment
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

        Firebase.database.reference.child("Users").child(Firebase.auth.currentUser!!.uid).addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    var user = snapshot.getValue<User>()
                    binding.name.text=user?.name

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            }
        )

        return binding.root
    }

    private fun showResult(itemTitle : String){
        Toast.makeText(requireContext(),itemTitle,Toast.LENGTH_SHORT).show()
        binding.spin.isEnabled = true

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        binding.spin.setOnClickListener{
                binding.spin.isEnabled =false

            val spin = java.util.Random().nextInt(6)
            val degrees = 60f * spin

            timer = object : CountDownTimer(5000, 50){

                var rotation = 0f
                override fun onTick(millisUntilFinished: Long) {
                    rotation += 5f
                    if (rotation>=degrees){

                        rotation =degrees
                        timer.cancel()
                        showResult(itemTitlees[spin])
                    }
                    binding.wheel.rotation =rotation
                }

                override fun onFinish() {
                    TODO("Not yet implemented")
                }

            }.start()
        }
    }

}