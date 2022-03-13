package com.example.android.teletaleem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.teletaleem.R
import android.content.Intent
import android.os.Handler
import com.example.android.teletaleem.LoginActivity
import com.google.firebase.database.FirebaseDatabase

var db:FirebaseDatabase= FirebaseDatabase.getInstance()
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getSupportActionBar()?.hide()
        Handler().postDelayed({
            if(auth.currentUser==null) {
                startActivity(Intent(this@MainActivity, SelectionActivity::class.java))
                //Only For Slide Show Between Intent
                overridePendingTransition(R.anim.slidein, R.anim.slideout)
            }
            else{
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                //Only For Slide Show Between Intent
                overridePendingTransition(R.anim.slidein, R.anim.slideout)
            }
        }, 2000)
    }

    //Disable Back Button
    override fun onBackPressed() {}
}