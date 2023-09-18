package com.example.earningappkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.earningappkotlin.databinding.ActivitySignUpBinding
import com.example.earningappkotlin.models.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignUp : AppCompatActivity() {
    private val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.signupBtn.setOnClickListener {
            if (binding.yourName.text.toString().equals("")||
                binding.yourAge.text.toString().equals("") ||
                binding.yourEmail.text.toString().equals("") ||
                binding.password.text.toString().equals(""))
            {

                Toast.makeText(this@SignUp,"Please fill all details",Toast.LENGTH_SHORT).show()
            }

            else{
                Firebase.auth.createUserWithEmailAndPassword(binding.yourEmail.text.toString(),binding.password.text.toString()).addOnCompleteListener{

                    if (it.isSuccessful){
                        var user = User(binding.yourName.text.toString(),binding.yourAge.text.toString().toInt(), binding.yourEmail.text.toString(),binding.password.text.toString())
                        Firebase.database.reference.child("Users")
                            .child(Firebase.auth.currentUser!!.uid).setValue(user).addOnSuccessListener { startActivity(Intent(this@SignUp,HomeActivity::class.java))
                                finish() }

                    }
                    else{
                        Toast.makeText(this@SignUp,""+it.exception?.localizedMessage,Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (Firebase.auth.currentUser!=null){
            startActivity(Intent(this@SignUp,HomeActivity::class.java))
            finish()
        }
    }
}