package com.example.android.teletaleem

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android.teletaleem.databinding.ActivityTeacherDetailsNewBinding
import java.util.regex.Matcher
import java.util.regex.Pattern

class teacherDetailsNew : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityTeacherDetailsNewBinding =
            ActivityTeacherDetailsNewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val times = arrayOf("1pm to 2pm", "2pm to 3pm", "3pm to 4pm")
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, R.layout.simple_spinner_dropdown_item, times)
        binding.timings.adapter = adapter
        var english: CheckBox = findViewById(com.example.android.teletaleem.R.id.eng)
        var math: CheckBox = findViewById(com.example.android.teletaleem.R.id.math)
        var physics: CheckBox = findViewById(com.example.android.teletaleem.R.id.phy)
        var chemistry: CheckBox = findViewById(com.example.android.teletaleem.R.id.chem)
        var biology: CheckBox = findViewById(com.example.android.teletaleem.R.id.bio)
        var islamiat: CheckBox = findViewById(com.example.android.teletaleem.R.id.isl)
        var urdu: CheckBox = findViewById(com.example.android.teletaleem.R.id.urdu)
        var punjabi: CheckBox = findViewById(com.example.android.teletaleem.R.id.pun)
        var computer: CheckBox = findViewById(com.example.android.teletaleem.R.id.com)
        //Classes CheckBoxes
        var ninth: CheckBox = findViewById(com.example.android.teletaleem.R.id.ninth)
        var tenth: CheckBox = findViewById(com.example.android.teletaleem.R.id.tenth)
        var eleventh: CheckBox = findViewById(com.example.android.teletaleem.R.id.eleventh)
        var twelveth: CheckBox = findViewById(com.example.android.teletaleem.R.id.twelve)
        binding.btn.setOnClickListener() {
            var counts = 0
            var countc = 0
            if (english.isChecked()) {
                counts++
            }
            if (math.isChecked()) {
                counts++
            }
            if (physics.isChecked()) {
                counts++
            }
            if (chemistry.isChecked()) {
                counts++
            }
            if (biology.isChecked()) {
                counts++
            }
            if (islamiat.isChecked()) {
                counts++
            }
            if (urdu.isChecked()) {
                counts++
            }
            if (punjabi.isChecked()) {
                counts++
            }
            if (computer.isChecked()) {
                counts++
            }
            if (ninth.isChecked()) {
                countc++
            }
            if (tenth.isChecked()) {
                countc++
            }
            if (eleventh.isChecked()) {
                countc++
            }
            if (twelveth.isChecked()) {
                countc++
            }
            var mytiming = binding.timings.selectedItem.toString()
            var mypassword = ""
            if (binding.PassBox.text.toString() != "" && binding.confBox.text.toString() != "") {
                if (binding.PassBox.text.toString() == binding.confBox.text.toString()) {
                    mypassword = binding.PassBox.text.toString()
                } else {
                    binding.confBox.setError("Please Enter Matching Password")
                }
            }
            //Checking If Valid Data Is Entered
            if (mytiming != "" && mypassword != "" && isValidPassword(mypassword) && countc != 0 && counts != 0) {
                //Making An Account

                auth.createUserWithEmailAndPassword(email, mypassword)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            //Adding Student Details
                            var teacher = db.getReference().child("Teacher Details")
                                .child(auth.currentUser!!.uid.toString())
                            var countclass = 0
                            var countsubject = 0
                            teacher.child("Name").setValue(name)
                            teacher.child("CNIC").setValue(CNIC)
                            teacher.child("Mobile No").setValue(mobilenumber)
                            teacher.child("Email").setValue(email)
                            teacher.child("Gender").setValue(mygender)
                            teacher.child("Timing").setValue(mytiming)
                            teacher.child("Password").setValue(mypassword)
                            teacher.child("Details").setValue(mydetails)
                            //Setting Classes
                            if (ninth.isChecked()) {
                                teacher.child("Classes").child(countclass.toString())
                                    .setValue("9th")
                                countclass++
                            }
                            if (tenth.isChecked()) {
                                teacher.child("Classes").child(countclass.toString())
                                    .setValue("10th")
                                countclass++
                            }
                            if (eleventh.isChecked()) {
                                teacher.child("Classes").child(countclass.toString())
                                    .setValue("11th")
                                countclass++
                            }
                            if (twelveth.isChecked()) {
                                teacher.child("Classes").child(countclass.toString())
                                    .setValue("12th")
                                countclass++
                            }
                            //Putting Student in Related Subjects
                            if (english.isChecked()) {
                                teacher.child("Subjects").child(countsubject.toString())
                                    .setValue("English")
                                countsubject++
                                if (ninth.isChecked()) {
                                    db.getReference().child("Teachers").child("English")
                                        .child("9th").child(mytiming)
                                        .child(auth.currentUser!!.uid.toString()).setValue(name)
                                }
                                if (tenth.isChecked()) {
                                    db.getReference().child("Teachers").child("English")
                                        .child("10th").child(mytiming)
                                        .child(auth.currentUser!!.uid.toString()).setValue(name)
                                }
                                if (eleventh.isChecked()) {
                                    db.getReference().child("Teachers").child("English")
                                        .child("11th").child(mytiming)
                                        .child(auth.currentUser!!.uid.toString()).setValue(name)
                                }
                                if (twelveth.isChecked()) {
                                    db.getReference().child("Teachers").child("English")
                                        .child("12th").child(mytiming)
                                        .child(auth.currentUser!!.uid.toString()).setValue(name)
                                }
                            }
                            if (math.isChecked()) {
                                teacher.child("Subjects").child(countsubject.toString())
                                    .setValue("Math")
                                countsubject++
                                if (ninth.isChecked()) {
                                    db.getReference().child("Teachers").child("Math").child("9th")
                                        .child(mytiming).child(auth.currentUser!!.uid.toString())
                                        .setValue(name)
                                }
                                if (tenth.isChecked()) {
                                    db.getReference().child("Teachers").child("Math").child("10th")
                                        .child(mytiming).child(auth.currentUser!!.uid.toString())
                                        .setValue(name)
                                }
                                if (eleventh.isChecked()) {
                                    db.getReference().child("Teachers").child("Math").child("11th")
                                        .child(mytiming).child(auth.currentUser!!.uid.toString())
                                        .setValue(name)
                                }
                                if (twelveth.isChecked()) {
                                    db.getReference().child("Teachers").child("Math").child("12th")
                                        .child(mytiming).child(auth.currentUser!!.uid.toString())
                                        .setValue(name)
                                }
                            }
                            if (physics.isChecked()) {
                                teacher.child("Subjects").child(countsubject.toString())
                                    .setValue("Physics")
                                countsubject++
                                if (ninth.isChecked()) {
                                    db.getReference().child("Teachers").child("Physics")
                                        .child("9th").child(mytiming)
                                        .child(auth.currentUser!!.uid.toString()).setValue(name)
                                }
                                if (tenth.isChecked()) {
                                    db.getReference().child("Teachers").child("Physics")
                                        .child("10th").child(mytiming)
                                        .child(auth.currentUser!!.uid.toString()).setValue(name)
                                }
                                if (eleventh.isChecked()) {
                                    db.getReference().child("Teachers").child("Physics")
                                        .child("11th").child(mytiming)
                                        .child(auth.currentUser!!.uid.toString()).setValue(name)
                                }
                                if (twelveth.isChecked()) {
                                    db.getReference().child("Teachers").child("Physics")
                                        .child("12th").child(mytiming)
                                        .child(auth.currentUser!!.uid.toString()).setValue(name)
                                }
                            }
                            if (chemistry.isChecked()) {
                                teacher.child("Subjects").child(countsubject.toString())
                                    .setValue("Chemistry")
                                countsubject++
                                if (ninth.isChecked()) {
                                    db.getReference().child("Teachers").child("Chemistry")
                                        .child("9th").child(mytiming)
                                        .child(auth.currentUser!!.uid.toString()).setValue(name)
                                }
                                if (tenth.isChecked()) {
                                    db.getReference().child("Teachers").child("Chemistry")
                                        .child("10th").child(mytiming)
                                        .child(auth.currentUser!!.uid.toString()).setValue(name)
                                }
                                if (eleventh.isChecked()) {
                                    db.getReference().child("Teachers").child("Chemistry")
                                        .child("11th").child(mytiming)
                                        .child(auth.currentUser!!.uid.toString()).setValue(name)
                                }
                                if (twelveth.isChecked()) {
                                    db.getReference().child("Teachers").child("Chemistry")
                                        .child("12th").child(mytiming)
                                        .child(auth.currentUser!!.uid.toString()).setValue(name)
                                }
                            }
                            if (biology.isChecked()) {
                                teacher.child("Subjects").child(countsubject.toString())
                                    .setValue("Biology")
                                countsubject++
                                if (ninth.isChecked()) {
                                    db.getReference().child("Teachers").child("Biology")
                                        .child("9th").child(mytiming)
                                        .child(auth.currentUser!!.uid.toString()).setValue(name)
                                }
                                if (tenth.isChecked()) {
                                    db.getReference().child("Teachers").child("Biology")
                                        .child("10th").child(mytiming)
                                        .child(auth.currentUser!!.uid.toString()).setValue(name)
                                }
                                if (eleventh.isChecked()) {
                                    db.getReference().child("Teachers").child("Biology")
                                        .child("11th").child(mytiming)
                                        .child(auth.currentUser!!.uid.toString()).setValue(name)
                                }
                                if (twelveth.isChecked()) {
                                    db.getReference().child("Teachers").child("Biology")
                                        .child("12th").child(mytiming)
                                        .child(auth.currentUser!!.uid.toString()).setValue(name)
                                }
                            }
                            if (islamiat.isChecked()) {
                                teacher.child("Subjects").child(countsubject.toString())
                                    .setValue("Islamiat")
                                countsubject++
                                if (ninth.isChecked()) {
                                    db.getReference().child("Teachers").child("Islamiat")
                                        .child("9th").child(mytiming)
                                        .child(auth.currentUser!!.uid.toString()).setValue(name)
                                }
                                if (tenth.isChecked()) {
                                    db.getReference().child("Teachers").child("Islamiat")
                                        .child("10th").child(mytiming)
                                        .child(auth.currentUser!!.uid.toString()).setValue(name)
                                }
                                if (eleventh.isChecked()) {
                                    db.getReference().child("Teachers").child("Islamiat")
                                        .child("11th").child(mytiming)
                                        .child(auth.currentUser!!.uid.toString()).setValue(name)
                                }
                                if (twelveth.isChecked()) {
                                    db.getReference().child("Teachers").child("Islamiat")
                                        .child("12th").child(mytiming)
                                        .child(auth.currentUser!!.uid.toString()).setValue(name)
                                }
                            }
                            if (urdu.isChecked()) {
                                teacher.child("Subjects").child(countsubject.toString())
                                    .setValue("Urdu")
                                countsubject++
                                if (ninth.isChecked()) {
                                    db.getReference().child("Teachers").child("Urdu").child("9th")
                                        .child(mytiming).child(auth.currentUser!!.uid.toString())
                                        .setValue(name)
                                }
                                if (tenth.isChecked()) {
                                    db.getReference().child("Teachers").child("Urdu").child("10th")
                                        .child(mytiming).child(auth.currentUser!!.uid.toString())
                                        .setValue(name)
                                }
                                if (eleventh.isChecked()) {
                                    db.getReference().child("Teachers").child("Urdu").child("11th")
                                        .child(mytiming).child(auth.currentUser!!.uid.toString())
                                        .setValue(name)
                                }
                                if (twelveth.isChecked()) {
                                    db.getReference().child("Teachers").child("Urdu").child("12th")
                                        .child(mytiming).child(auth.currentUser!!.uid.toString())
                                        .setValue(name)
                                }
                            }
                            if (punjabi.isChecked()) {
                                teacher.child("Subjects").child(countsubject.toString())
                                    .setValue("Punjabi")
                                countsubject++
                                if (ninth.isChecked()) {
                                    db.getReference().child("Teachers").child("Punjabi")
                                        .child("9th").child(mytiming)
                                        .child(auth.currentUser!!.uid.toString()).setValue(name)
                                }
                                if (tenth.isChecked()) {
                                    db.getReference().child("Teachers").child("Punjabi")
                                        .child("10th").child(mytiming)
                                        .child(auth.currentUser!!.uid.toString()).setValue(name)
                                }
                                if (eleventh.isChecked()) {
                                    db.getReference().child("Teachers").child("Punjabi")
                                        .child("11th").child(mytiming)
                                        .child(auth.currentUser!!.uid.toString()).setValue(name)
                                }
                                if (twelveth.isChecked()) {
                                    db.getReference().child("Teachers").child("Punjabi")
                                        .child("12th").child(mytiming)
                                        .child(auth.currentUser!!.uid.toString()).setValue(name)
                                }
                            }
                            if (computer.isChecked()) {
                                teacher.child("Subjects").child(countsubject.toString())
                                    .setValue("Computer")
                                countsubject++
                                if (ninth.isChecked()) {
                                    db.getReference().child("Teachers").child("Computer")
                                        .child("9th").child(mytiming)
                                        .child(auth.currentUser!!.uid.toString()).setValue(name)
                                }
                                if (tenth.isChecked()) {
                                    db.getReference().child("Teachers").child("Computer")
                                        .child("10th").child(mytiming)
                                        .child(auth.currentUser!!.uid.toString()).setValue(name)
                                }
                                if (eleventh.isChecked()) {
                                    db.getReference().child("Teachers").child("Computer")
                                        .child("11th").child(mytiming)
                                        .child(auth.currentUser!!.uid.toString()).setValue(name)
                                }
                                if (twelveth.isChecked()) {
                                    db.getReference().child("Teachers").child("Computer")
                                        .child("12th").child(mytiming)
                                        .child(auth.currentUser!!.uid.toString()).setValue(name)
                                }
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
            }
            else if(mypassword==""){
                binding.PassBox.setError("Please Enter A Password First")
            }
            else if(isValidPassword(mypassword)){
                binding.PassBox.setError("Please Enter A Valid Password (8 Characters long and must contain Cap Alpha,Number and A Special Symbol)")
            }
            else if(countc==0){
                Toast.makeText(applicationContext, "Please select atleast one class", Toast.LENGTH_SHORT).show()
            }
            else if(counts==0){
                Toast.makeText(applicationContext, "Please select atleast one subject", Toast.LENGTH_SHORT).show()
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