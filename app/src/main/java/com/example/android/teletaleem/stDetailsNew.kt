package com.example.android.teletaleem

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android.teletaleem.databinding.ActivityStDetailsNewBinding
import java.util.regex.Matcher
import java.util.regex.Pattern

class stDetailsNew : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityStDetailsNewBinding =
            ActivityStDetailsNewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val times = arrayOf("1pm to 2pm", "2pm to 3pm", "3pm to 4pm")
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, R.layout.simple_spinner_dropdown_item, times)
        binding.timings.adapter = adapter
        val classes = arrayOf("9th", "10th", "11th", "12th")
        val adapter1: ArrayAdapter<String> =
            ArrayAdapter<String>(this, R.layout.simple_spinner_dropdown_item, classes)
        binding.Class.adapter = adapter1
        var english: CheckBox = findViewById(com.example.android.teletaleem.R.id.eng)
        var math: CheckBox = findViewById(com.example.android.teletaleem.R.id.math)
        var physics: CheckBox = findViewById(com.example.android.teletaleem.R.id.phy)
        var chemistry: CheckBox = findViewById(com.example.android.teletaleem.R.id.chem)
        var biology: CheckBox = findViewById(com.example.android.teletaleem.R.id.bio)
        var islamiat: CheckBox = findViewById(com.example.android.teletaleem.R.id.isl)
        var urdu: CheckBox = findViewById(com.example.android.teletaleem.R.id.urdu)
        var punjabi: CheckBox = findViewById(com.example.android.teletaleem.R.id.pun)
        var computer: CheckBox = findViewById(com.example.android.teletaleem.R.id.com)
        binding.btn.setOnClickListener() {
            //Work Here
            var myclass = binding.Class.selectedItem.toString()
            var mytiming = binding.timings.selectedItem.toString()
            var mypassword = ""
            if (binding.PassBox.text.toString() != "" && binding.confBox.text.toString() != "") {
                if (binding.PassBox.text.toString() == binding.confBox.text.toString()) {
                    mypassword = binding.PassBox.text.toString()
                } else {
                    binding.confBox.setError("Please Enter Matching Password")
                }
            }
            var count = 0
            if (english.isChecked()) {
                count++
            }
            if (math.isChecked()) {
                count++
            }
            if (physics.isChecked()) {
                count++
            }
            if (chemistry.isChecked()) {
                count++
            }
            if (biology.isChecked()) {
                count++
            }
            if (islamiat.isChecked()) {
                count++
            }
            if (urdu.isChecked()) {
                count++
            }
            if (punjabi.isChecked()) {
                count++
            }
            if (computer.isChecked()) {
                count++
            }
            //Checking If Valid Data Is Entered
            if (mypassword != "" && myclass != "" && mytiming != "" && isValidPassword(mypassword) && count != 0) {
                //Making An Account
                auth.createUserWithEmailAndPassword(email, mypassword)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            //Adding Student Details
                            var count = 0
                            var student = db.getReference().child("Student Details")
                                .child(auth.currentUser!!.uid.toString())
                            student.child("Name").setValue(name)
                            student.child("CNIC").setValue(CNIC)
                            student.child("Mobile No").setValue(mobilenumber)
                            student.child("Email").setValue(email)
                            student.child("Gender").setValue(mygender)
                            student.child("Class").setValue(myclass)
                            student.child("Timing").setValue(mytiming)
                            student.child("Password").setValue(mypassword)
                            //Putting Student in Related Subjects
                            if (english.isChecked()) {
                                student.child("Subjects").child(count.toString())
                                    .setValue("English")
                                count++
                                db.getReference().child("Students").child("English").child(myclass)
                                    .child(mytiming).child(auth.currentUser!!.uid.toString())
                                    .setValue(name)
                            }
                            if (math.isChecked()) {
                                student.child("Subjects").child(count.toString()).setValue("Math")
                                count++
                                db.getReference().child("Students").child("Math").child(myclass)
                                    .child(mytiming).child(auth.currentUser!!.uid.toString())
                                    .setValue(name)
                            }
                            if (physics.isChecked()) {
                                student.child("Subjects").child(count.toString())
                                    .setValue("Physics")
                                count++
                                db.getReference().child("Students").child("Physics").child(myclass)
                                    .child(mytiming).child(auth.currentUser!!.uid.toString())
                                    .setValue(name)
                            }
                            if (chemistry.isChecked()) {
                                student.child("Subjects").child(count.toString())
                                    .setValue("Chemistry")
                                count++
                                db.getReference().child("Students").child("Chemistry")
                                    .child(myclass).child(mytiming)
                                    .child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                            if (biology.isChecked()) {
                                student.child("Subjects").child(count.toString())
                                    .setValue("Biology")
                                count++
                                db.getReference().child("Students").child("Biology").child(myclass)
                                    .child(mytiming).child(auth.currentUser!!.uid.toString())
                                    .setValue(name)
                            }
                            if (islamiat.isChecked()) {
                                student.child("Subjects").child(count.toString())
                                    .setValue("Islamiat")
                                count++
                                db.getReference().child("Students").child("Islamiat").child(myclass)
                                    .child(mytiming).child(auth.currentUser!!.uid.toString())
                                    .setValue(name)
                            }
                            if (urdu.isChecked()) {
                                student.child("Subjects").child(count.toString()).setValue("Urdu")
                                count++
                                db.getReference().child("Students").child("Urdu").child(myclass)
                                    .child(mytiming).child(auth.currentUser!!.uid.toString())
                                    .setValue(name)
                            }
                            if (punjabi.isChecked()) {
                                student.child("Subjects").child(count.toString())
                                    .setValue("Punjabi")
                                count++
                                db.getReference().child("Students").child("Punjabi").child(myclass)
                                    .child(mytiming).child(auth.currentUser!!.uid.toString())
                                    .setValue(name)
                            }
                            if (computer.isChecked()) {
                                student.child("Subjects").child(count.toString())
                                    .setValue("Computer")
                                count++
                                db.getReference().child("Students").child("Computer").child(myclass)
                                    .child(mytiming).child(auth.currentUser!!.uid.toString())
                                    .setValue(name)
                            }
                            Toast.makeText(
                                this,
                                "Account is Created. You can Log In Now",
                                Toast.LENGTH_SHORT
                            ).show()
                            //Move To Login
                            startActivity(Intent(this, LoginActivity::class.java))
                            //Only For Slide Show Between Intent
                            overridePendingTransition(
                                com.example.android.teletaleem.R.anim.slidein,
                                com.example.android.teletaleem.R.anim.slideout
                            )
                        } else {
                            Toast.makeText(
                                this,
                                task.exception!!.localizedMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                if (myclass == "") {
                    Toast.makeText(applicationContext, "Please Select A Class", Toast.LENGTH_SHORT)
                        .show()
                } else if (mytiming == "") {
                    Toast.makeText(applicationContext, "Please Select A Timing", Toast.LENGTH_SHORT)
                        .show()
                } else if (count == 0) {
                    Toast.makeText(
                        applicationContext,
                        "Please Select A Subject First",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (binding.PassBox.text.toString() != binding.confBox.text.toString()) {
                    binding.PassBox.setError("Please Enter Matching Password")
                } else if (mypassword == "") {
                    binding.PassBox.setError("Please Enter A Password First")
                }
                if (!isValidPassword(mypassword)) {
                    binding.PassBox.setError("Please Enter A Proper Password")
                }
            }
        }
    }

//Password Checker
    fun isValidPassword(password: String?): Boolean {
    val pattern: Pattern
    val matcher: Matcher
    val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
    pattern = Pattern.compile(PASSWORD_PATTERN)
    matcher = pattern.matcher(password)
    return matcher.matches()
}
}