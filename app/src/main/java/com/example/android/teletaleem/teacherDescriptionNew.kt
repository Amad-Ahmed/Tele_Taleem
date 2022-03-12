package com.example.android.teletaleem

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.teletaleem.databinding.ActivityTeacherDescriptionNewBinding
lateinit var mydetails:String
class teacherDescriptionNew : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding : ActivityTeacherDescriptionNewBinding = ActivityTeacherDescriptionNewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.logBTN.setOnClickListener(){
            mydetails=binding.nameBox.text.toString()
            if(mydetails!=""){
                startActivity(Intent(this,teacherDetailsNew::class.java))
            }
        }
        binding.btn.setOnClickListener(){
            mydetails=binding.nameBox.text.toString()
            if(mydetails!=""){
                startActivity(Intent(this,teacherDetailsNew::class.java))
            }
        }
    }
}