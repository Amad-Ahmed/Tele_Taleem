package com.example.android.teletaleem

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.os.Bundle
import com.example.android.teletaleem.R
import com.example.android.teletaleem.LoginActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import android.widget.Toast
import android.content.Intent
import android.view.View
import android.widget.Button
import com.example.android.teletaleem.JoinActivity
import com.example.android.teletaleem.SignUpActivity
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {
    var emailBox: EditText? = null
    var passwordBox: EditText? = null
    var loginBtn: Button? = null
    var signupBtn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        getSupportActionBar()?.hide()
        var emailBox:EditText = findViewById(R.id.emailbox)
        var passwordBox:EditText = findViewById(R.id.passwordbox)
        var loginBtn:Button = findViewById(R.id.loginBtn)
        var signupBtn:Button = findViewById(R.id.createBtn)
        loginBtn.setOnClickListener(View.OnClickListener {
            val email: String
            val pass: String
            email = emailBox.getText().toString()
            pass = passwordBox.getText().toString()
            if (email !== "" && isValidEmailAddress(email) && pass.length >= 8) {
                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this@LoginActivity, "Logged In", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@LoginActivity, JoinActivity::class.java))
                        //Only For Slide Show Between Intent
                        overridePendingTransition(R.anim.slidein, R.anim.slideout)
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            task.exception!!.localizedMessage,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                emailBox.setError("Password or Email Incorrect")
            }
        })
        signupBtn.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
            //Only For Slide Show Between Intent
            overridePendingTransition(R.anim.slidein, R.anim.slideout)
        })
    }

    //Disable Back Button
    override fun onBackPressed() {}

    companion object {
        //Email Validator
        fun isValidEmailAddress(email: String?): Boolean {
            val stricterFilter = true
            val stricterFilterString = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}"
            val laxString = ".+@.+\\.[A-Za-z]{2}[A-Za-z]*"
            val emailRegex = if (stricterFilter) stricterFilterString else laxString
            val p = Pattern.compile(emailRegex)
            val m = p.matcher(email)
            return m.matches()
        }
    }
}