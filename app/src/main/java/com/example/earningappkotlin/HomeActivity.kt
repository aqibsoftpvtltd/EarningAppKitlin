package com.example.earningappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var navController = findNavController(R.id.fragmentContainerView)
        var bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigationView)
        bottomNavigationView.setupWithNavController(navController)
    }
}