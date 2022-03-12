package com.example.android.teletaleem

import android.graphics.ColorSpace
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationMenu

class student_dashboard : AppCompatActivity() {
    var bottomNavigation: BottomNavigationMenu? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_dashboard)
    }
}