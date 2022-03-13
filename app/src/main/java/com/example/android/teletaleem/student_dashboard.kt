package com.example.android.teletaleem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.teletaleem.databinding.ActivityStudentDashboardBinding
import com.google.android.material.bottomnavigation.BottomNavigationMenu

class student_dashboard : AppCompatActivity() {
    var bottomNavigation: BottomNavigationMenu? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding :ActivityStudentDashboardBinding = ActivityStudentDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main, StHomeFragment())
        transaction.addToBackStack(null)
        transaction.commit()
        binding.BOTTOMNAV.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.home ->{
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.main, StHomeFragment())
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
                R.id.assignment ->{
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.main, StAssignmentFragment())
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
                R.id.chat ->{
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.main, StChatFragment())
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
                R.id.quiz ->{
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.main, StQuizFragment())
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
                R.id.quizt ->{
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.main, StProgressFragment())
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
            }
            true
        }
    }
    override fun onBackPressed() {
    }
}