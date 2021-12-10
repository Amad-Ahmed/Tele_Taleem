package com.example.android.teletaleem

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.os.Bundle
import com.example.android.teletaleem.R
import com.example.android.teletaleem.SignUpActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import android.widget.Toast
import android.content.Intent
import android.view.View
import android.widget.Button
import com.example.android.teletaleem.LoginActivity
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {
    var emailBox: EditText? = null
    var passwordBox: EditText? = null
    var nameBox: EditText? = null
    var loginBtn: Button? = null
    var signupBtn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        getSupportActionBar()?.hide()
        var emailBox:EditText = findViewById(R.id.emailbox)
        var passwordBox:EditText = findViewById(R.id.passwordbox)
        var nameBox:EditText = findViewById(R.id.name)
        var loginBtn:Button = findViewById(R.id.loginBtn)
        var signupBtn:Button = findViewById(R.id.createBtn)
        signupBtn.setOnClickListener(View.OnClickListener {
            val email: String
            val pass: String
            val name: String
            email = emailBox.getText().toString()
            pass = passwordBox.getText().toString()
            name = nameBox.getText().toString()
            if (email !== "" && pass.length >= 8 && isValidEmailAddress(email.trim { it <= ' ' }) && name.length >= 5) {
                auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this@SignUpActivity,
                            "Account is created.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@SignUpActivity,
                            task.exception!!.localizedMessage,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                if (!isValidEmailAddress(email.trim { it <= ' ' })) {
                    emailBox.setError("Please Enter Proper Email ")
                }
                if (pass.length < 8) {
                    passwordBox.setError("Please Fill This Field Correctly (Min 8 Characters)")
                }
                if (name.length < 5) {
                    nameBox.setError("Please Fill This Field Correctly (Min 5 Char)")
                }
            }
        })
        loginBtn.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
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