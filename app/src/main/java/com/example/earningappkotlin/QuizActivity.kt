package com.example.earningappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.earningappkotlin.databinding.ActivityQuizBinding
import com.example.earningappkotlin.fragments.Withdrawl
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class QuizActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityQuizBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var image = intent.getIntExtra("categoryImage",0)
        binding.questionImage.setImageResource(image)

        binding.coin.setOnClickListener {
            val bottomSheetDialog : BottomSheetDialogFragment = Withdrawl()
            bottomSheetDialog.show(this@QuizActivity.supportFragmentManager,"Test")
            bottomSheetDialog.enterTransition
        }
        binding.withDraw.setOnClickListener {
            val bottomSheetDialog : BottomSheetDialogFragment = Withdrawl()
            bottomSheetDialog.show(this@QuizActivity.supportFragmentManager,"Test")
            bottomSheetDialog.enterTransition
        }
    }
}