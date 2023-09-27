package com.example.earningappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.earningappkotlin.databinding.ActivityQuizBinding
import com.example.earningappkotlin.fragments.Withdrawl
import com.example.earningappkotlin.models.Question
import com.example.earningappkotlin.models.User
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.core.View
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class QuizActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityQuizBinding.inflate(layoutInflater)
    }
    private lateinit var questionList :ArrayList<Question>
    var currentQuestion = 0
    var score = 0
    var currentChance =0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        questionList = ArrayList<Question>()
        var image = intent.getIntExtra("categoryImage",0)
        var catText = intent.getStringExtra("questionType")

        Firebase.database.reference.child("PlayChance").child(Firebase.auth.currentUser!!.uid)
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()){
                        currentChance = snapshot.value as Long
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

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
        Firebase.database.reference.child("playerCoin").child(Firebase.auth.currentUser!!.uid)
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()){
                        binding.coin.text  = snapshot.value.toString()


                    }

                }
                override fun onCancelled(error: DatabaseError) {
                }

            })


        Firebase.firestore.collection("Questions").document(catText.toString()).collection("question1").get().addOnSuccessListener {
            questionData ->
            questionList.clear()
            for (data in questionData.documents){

                var questions : Question? = data.toObject(Question::class.java)
                questionList.add(questions!!)
            }

            Log.d("MyTag","")
            if (questionList.size>0){
                binding.question.text= questionList[currentQuestion].question
                binding.option1.text= questionList[currentQuestion].option1
                binding.option2.text= questionList[currentQuestion].option2
                binding.option3.text= questionList[currentQuestion].option3
                binding.option4.text= questionList[currentQuestion].option4
            }


        }
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

        binding.option1.setOnClickListener {
            nextQuestionAndScoreUpdate(binding.option1.text.toString())
        }
        binding.option2.setOnClickListener {
            nextQuestionAndScoreUpdate(binding.option2.text.toString())
        }
        binding.option3.setOnClickListener {
            nextQuestionAndScoreUpdate(binding.option3.text.toString())
        }
        binding.option4.setOnClickListener {
            nextQuestionAndScoreUpdate(binding.option4.text.toString())
        }
    }

    private fun nextQuestionAndScoreUpdate(s:String) {

        if (s == questionList[currentQuestion].ans){
            score+=10
        }
        currentQuestion++
        if (currentQuestion>=questionList.size){
            if (score>=20){


                Firebase.database.reference.child("PlayChance").child(Firebase.auth.currentUser!!.uid).setValue(currentChance+1)



                binding.winner.visibility = android.view.View.VISIBLE
                binding.mainLyt.visibility = android.view.View.GONE
                binding.sorry.visibility = android.view.View.GONE
            }
            else{
                binding.winner.visibility = android.view.View.GONE
                binding.mainLyt.visibility = android.view.View.GONE
                binding.sorry.visibility = android.view.View.VISIBLE
            }

            Toast.makeText(this,"End Reached",Toast.LENGTH_SHORT).show()
        }
        else{

            binding.question.text= questionList[currentQuestion].question
            binding.option1.text= questionList[currentQuestion].option1
            binding.option2.text= questionList[currentQuestion].option2
            binding.option3.text= questionList[currentQuestion].option3
            binding.option4.text= questionList[currentQuestion].option4
        }

    }
}