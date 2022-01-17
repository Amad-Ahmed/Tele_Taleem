package com.example.android.teletaleem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.teletaleem.R
import android.content.Intent
import android.view.View
import android.widget.Button
import com.example.android.teletaleem.TeacherActivity
import com.example.android.teletaleem.StudentActivity
import com.example.android.teletaleem.LoginActivity

class SelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)
        var teacher: Button = findViewById(R.id.Teacher)
        var student : Button= findViewById(R.id.Student)
        var login: Button = findViewById(R.id.loginBtn)
        teacher.setOnClickListener(
            View.OnClickListener
            //for teacher journey
            {
                val i = Intent(this@SelectionActivity, TeacherActivity::class.java)
                startActivity(i)
            })
        student.setOnClickListener(
            View.OnClickListener
            //for student journey
            {
                val i = Intent(this@SelectionActivity, StudentActivity::class.java)
                startActivity(i)
            })
        login.setOnClickListener(
            View.OnClickListener
            //for login journey
            {
                val i = Intent(this@SelectionActivity, LoginActivity::class.java)
                startActivity(i)
            })
    }
    override fun onBackPressed() {
    }
}