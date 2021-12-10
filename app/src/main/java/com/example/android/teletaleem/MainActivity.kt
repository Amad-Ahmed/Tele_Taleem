package com.example.android.teletaleem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.teletaleem.R
import android.content.Intent
import android.os.Handler
import com.example.android.teletaleem.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.hide()
        Handler().postDelayed({
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            //Only For Slide Show Between Intent
            overridePendingTransition(R.anim.slidein, R.anim.slideout)
        }, 2000)
    }

    //Disable Back Button
    override fun onBackPressed() {}
}