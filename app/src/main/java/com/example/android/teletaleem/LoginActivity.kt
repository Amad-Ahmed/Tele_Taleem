package com.example.android.teletaleem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.teletaleem.R
import com.example.android.teletaleem.LoginActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import android.content.Intent
import android.view.View
import android.widget.*
import com.example.android.teletaleem.JoinActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.util.regex.Pattern
var logintype=""
var message=""
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
        var loginBtn:ImageView = findViewById(R.id.btn)
        var signupBtn:TextView = findViewById(R.id.SignBTN)
        loginBtn.setOnClickListener(View.OnClickListener {
            val email: String
            val pass: String
            email = emailBox.getText().toString()
            pass = passwordBox.getText().toString()
            if (email !== "" && isValidEmailAddress(email) && pass.length >= 8) {
                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        //Checking LoginType
                            var studentids= mutableListOf("")
                        var teacherids= mutableListOf("")
                        var myref=db.getReference().child("Student Details")
                        myref.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                for (childSnapshot in dataSnapshot.children) {
                                    studentids.add(childSnapshot.key.toString())
                                }
                                if(studentids.contains(auth.currentUser!!.uid)){
                                    logintype="Student"
                                    message="Your Related Teachers Are"
                                    Toast.makeText(this@LoginActivity, "Logged In Student", Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this@LoginActivity, JoinActivity::class.java))
                                    //Only For Slide Show Between Intent
                                    overridePendingTransition(R.anim.slidein, R.anim.slideout)
                                }
                            }

                            override fun onCancelled(databaseError: DatabaseError) {}
                        })
                        //Teachers Side
                        var myreft=db.getReference().child("Teacher Details")
                        myreft.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                for (childSnapshot in dataSnapshot.children) {
                                    teacherids.add(childSnapshot.key.toString())
                                }
                                if(teacherids.contains(auth.currentUser!!.uid)){
                                    logintype="Teacher"
                                    message="Your Related Students Are"
                                    Toast.makeText(this@LoginActivity, "Logged In Teacher", Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this@LoginActivity, JoinActivity::class.java))
                                    //Only For Slide Show Between Intent
                                    overridePendingTransition(R.anim.slidein, R.anim.slideout)
                                }
                            }

                            override fun onCancelled(databaseError: DatabaseError) {}
                        })

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
            startActivity(Intent(this@LoginActivity, SelectionActivity::class.java))
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