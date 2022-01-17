package com.example.android.teletaleem

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.facebook.react.modules.core.Timing
import java.util.regex.Matcher
import java.util.regex.Pattern

var myid=""

class StudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)
        var edtName = findViewById<EditText>(R.id.name)
        var edtCnic = findViewById<EditText>(R.id.cnic)
        var timings = findViewById<Spinner>(R.id.timings)
        var gender = findViewById<Spinner>(R.id.gender)
        var edtMobile: EditText=findViewById(R.id.mobile)
        var edtEmail: EditText=findViewById(R.id.email)
        var PassBox:EditText=findViewById(R.id.passwordbox)
        //get the spinner from the xml.
        var Class = findViewById<Spinner>(R.id.Class)
        //create a list of items for the spinner.
        val times = arrayOf("1pm to 2pm", "2pm to 3pm", "3pm to 4pm")
        val genders = arrayOf("Male", "Female", "Other")
        val classes = arrayOf("9th", "10th", "11th", "12th")
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, times)
        val adapter2: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, genders)
        val adapter3: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, classes)
        gender.setAdapter(adapter2)
        timings.setAdapter(adapter)
        Class.setAdapter(adapter3)
        //edtCnic.addTextChangedListener(PatternedTextWatcher("#####-#######-#"))
        //        edtMobile.addTextChangedListener(new PatternedTextWatcher("####-#######"));
        //Complete Button
        var Completebtn: Button =findViewById<Button>(R.id.done)
        Completebtn.setOnClickListener(){
            var name=edtName.text.toString()
            var CNIC=edtCnic.text.toString()
            var mobilenumber=edtMobile.text.toString()
            var email=edtEmail.text.toString()
            var mygender=gender.getSelectedItem().toString()
            var myclass=Class.getSelectedItem().toString()
            var mytiming=timings.getSelectedItem().toString()
            var mypassword=PassBox.text.toString()
            var english:CheckBox=findViewById(R.id.eng)
            var math:CheckBox=findViewById(R.id.math)
            var physics:CheckBox=findViewById(R.id.phy)
            var chemistry:CheckBox=findViewById(R.id.chem)
            var biology:CheckBox=findViewById(R.id.bio)
            var islamiat:CheckBox=findViewById(R.id.isl)
            var urdu:CheckBox=findViewById(R.id.urdu)
            var punjabi:CheckBox=findViewById(R.id.pun)
            var computer:CheckBox=findViewById(R.id.com)
            var count=0
            if(english.isChecked()){
                count++
            }
            if(math.isChecked()){
                count++
            }
            if(physics.isChecked()){
                count++
            }
            if(chemistry.isChecked()){
                count++
            }
            if(biology.isChecked()){
                count++
            }
            if(islamiat.isChecked()){
                count++
            }
            if(urdu.isChecked()){
                count++
            }
            if(punjabi.isChecked()){
                count++
            }
            if(computer.isChecked()){
                count++
            }
            //Checking If Valid Data Is Entered
            if(name.length>5 && CNIC.trim().length==13 && mobilenumber.trim().length==10 && mygender!="" && myclass!="" && mytiming!="" && isValidPassword(mypassword) && isValidEmailAddress(email) && count!=0) {
                //Making An Account
                auth.createUserWithEmailAndPassword(email, mypassword).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        //Adding Student Details
                        var student=db.getReference().child("Student Details").child(auth.currentUser!!.uid.toString())
                        student.child("Name").setValue(name)
                        student.child("CNIC").setValue(CNIC)
                        student.child("Mobile No").setValue(mobilenumber)
                        student.child("Email").setValue(email)
                        student.child("Gender").setValue(mygender)
                        student.child("Class").setValue(myclass)
                        student.child("Timing").setValue(mytiming)
                        student.child("Password").setValue(mypassword)
                        //Putting Student in Related Subjects
                        if(english.isChecked()){
                            db.getReference().child("Students").child("English").child(myclass).child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                        }
                        if(math.isChecked()){
                            db.getReference().child("Students").child("Math").child(myclass).child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                        }
                        if(physics.isChecked()){
                            db.getReference().child("Students").child("Physics").child(myclass).child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                        }
                        if(chemistry.isChecked()){
                            db.getReference().child("Students").child("Chemistry").child(myclass).child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                        }
                        if(biology.isChecked()){
                            db.getReference().child("Students").child("Biology").child(myclass).child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                        }
                        if(islamiat.isChecked()){
                            db.getReference().child("Students").child("Islamiat").child(myclass).child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                        }
                        if(urdu.isChecked()){
                            db.getReference().child("Students").child("Urdu").child(myclass).child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                        }
                        if(punjabi.isChecked()){
                            db.getReference().child("Students").child("Punjabi").child(myclass).child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                        }
                        if(computer.isChecked()){
                            db.getReference().child("Students").child("Computer").child(myclass).child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                        }
                        Toast.makeText(
                            this,
                            "Account is Created. You can Log In Now",
                            Toast.LENGTH_SHORT
                        ).show()
                        //Move To Login
                        startActivity(Intent(this, LoginActivity::class.java))
                        //Only For Slide Show Between Intent
                        overridePendingTransition(R.anim.slidein, R.anim.slideout)
                    } else {
                        Toast.makeText(
                            this,
                            task.exception!!.localizedMessage,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            else{
                if(name.length<=5) {
                    edtName.setError("Please Enter A Name (Min 6 Chars)")
                }
                if(CNIC.trim().length!=13){
                    edtCnic.setError("Please Enter A Proper CNIC (13 Chars)")
                }
                if(mobilenumber.trim().length!=10){
                    edtMobile.setError("Please Enter A Proper Mobile No (10 Chars)")
                }
                if(mygender==""){
                    Toast.makeText(applicationContext, "Please Select A Gender", Toast.LENGTH_SHORT).show()
                }
                else if(myclass==""){
                    Toast.makeText(applicationContext, "Please Select A Class", Toast.LENGTH_SHORT).show()
                }
                else if(mytiming==""){
                    Toast.makeText(applicationContext, "Please Select A Timing", Toast.LENGTH_SHORT).show()
                }
                else if(count==0){
                    Toast.makeText(applicationContext, "Please Select A Subject First", Toast.LENGTH_SHORT).show()
                }
                if(!isValidPassword(mypassword)){
                    PassBox.setError("Please Enter A Proper Password")
                }
                if(!isValidEmailAddress(email)){
                    edtEmail.setError("Please Enter A Proper Email")
                }
            }
        }
    }
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
    //Password Checker
    open fun isValidPassword(password: String?): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        matcher = pattern.matcher(password)
        return matcher.matches()
    }
}