package com.example.android.teletaleem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.teletaleem.databinding.ActivityTeacherDashboardBinding

class TeacherDashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding : ActivityTeacherDashboardBinding = ActivityTeacherDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main, teacherHome())
        transaction.addToBackStack(null)
        transaction.commit()
        binding.BOTTOMNAV.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.home ->{
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.main, teacherHome())
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
                R.id.assignment ->{
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.main, teacherAssignment())
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
                R.id.chat ->{
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.main, teacherChat())
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
                R.id._class ->{
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.main, teacherClass())
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
                R.id.quizt ->{
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.main, TeacherQuiz())
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