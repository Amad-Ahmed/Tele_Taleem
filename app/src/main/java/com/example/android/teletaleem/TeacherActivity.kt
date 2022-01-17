package com.example.android.teletaleem

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Matcher
import java.util.regex.Pattern


class TeacherActivity : AppCompatActivity() {
    var edtName: EditText? = null
    var edtCnic: EditText? = null
    var edtMobile: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher)
        var edtName = findViewById<EditText>(R.id.name)
        var edtCnic = findViewById<EditText>(R.id.cnic)
        var timings = findViewById<Spinner>(R.id.timings)
        var gender = findViewById<Spinner>(R.id.gender)
        var edtMobile: EditText=findViewById(R.id.mobile)
        var edtEmail: EditText=findViewById(R.id.email)
        var PassBox:EditText=findViewById(R.id.passwordbox)
        // CV
        var education:EditText=findViewById(R.id.education)
        var years:EditText=findViewById(R.id.years)
        var keysubjects:EditText=findViewById(R.id.KeySubj)
        var Certification:EditText=findViewById(R.id.certification)
        //get the spinner from the xml.
        //create a list of items for the spinner.
        val times = arrayOf("1pm to 2pm", "2pm to 3pm", "3pm to 4pm")
        val genders = arrayOf("Male", "Female", "Other")
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, times)
        val adapter2: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, genders)
        gender.setAdapter(adapter2)
        timings.setAdapter(adapter)
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
            var mytiming=timings.getSelectedItem().toString()
            var mypassword=PassBox.text.toString()
            var myeducation=education.text.toString()
            var myyears=years.text.toString()
            var mysubjects=keysubjects.text.toString()
            var mycertificate=Certification.text.toString()
            var english: CheckBox =findViewById(R.id.eng)
            var math: CheckBox =findViewById(R.id.math)
            var physics: CheckBox =findViewById(R.id.phy)
            var chemistry: CheckBox =findViewById(R.id.chem)
            var biology: CheckBox =findViewById(R.id.bio)
            var islamiat: CheckBox =findViewById(R.id.isl)
            var urdu: CheckBox =findViewById(R.id.urdu)
            var punjabi: CheckBox =findViewById(R.id.pun)
            var computer: CheckBox =findViewById(R.id.com)
            //Classes CheckBoxes
            var ninth: CheckBox =findViewById(R.id.ninth)
            var tenth: CheckBox =findViewById(R.id.tenth)
            var eleventh: CheckBox =findViewById(R.id.eleventh)
            var twelveth: CheckBox =findViewById(R.id.twelve)
            var counts=0
            var countc=0
            if(english.isChecked()){
                counts++
            }
            if(math.isChecked()){
                counts++
            }
            if(physics.isChecked()){
                counts++
            }
            if(chemistry.isChecked()){
                counts++
            }
            if(biology.isChecked()){
                counts++
            }
            if(islamiat.isChecked()){
                counts++
            }
            if(urdu.isChecked()){
                counts++
            }
            if(punjabi.isChecked()){
                counts++
            }
            if(computer.isChecked()){
                counts++
            }
            if(ninth.isChecked()){
                countc++
            }
            if(tenth.isChecked()){
                countc++
            }
            if(eleventh.isChecked()){
                countc++
            }
            if(twelveth.isChecked()){
                countc++
            }
            //Checking If Valid Data Is Entered
            if(name.length>5 && CNIC.trim().length==13 && mobilenumber.trim().length==10 && mygender!="" && mytiming!="" && isValidPassword(mypassword) && StudentActivity.isValidEmailAddress(
                    email
                ) && countc!=0 && counts!=0 && myeducation!="" && myyears!="" && mysubjects!="" && mycertificate!="") {
                //Making An Account
                auth.createUserWithEmailAndPassword(email, mypassword).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        //Adding Student Details
                        var teacher=db.getReference().child("Teacher Details").child(auth.currentUser!!.uid.toString())
                        teacher.child("Name").setValue(name)
                        teacher.child("CNIC").setValue(CNIC)
                        teacher.child("Mobile No").setValue(mobilenumber)
                        teacher.child("Email").setValue(email)
                        teacher.child("Gender").setValue(mygender)
                        teacher.child("Education").setValue(myeducation)
                        teacher.child("Experience").setValue(myyears)
                        teacher.child("Certification").setValue(mycertificate)
                        teacher.child("Key Subjects").setValue(mysubjects)
                        teacher.child("Timing").setValue(mytiming)
                        teacher.child("Password").setValue(mypassword)
                        //Putting Student in Related Subjects
                        if(english.isChecked()){
                            if(ninth.isChecked()){
                                db.getReference().child("Teachers").child("English").child("9th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                            if(tenth.isChecked()){
                                db.getReference().child("Teachers").child("English").child("10th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                            if(eleventh.isChecked()){
                                db.getReference().child("Teachers").child("English").child("11th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                            if(twelveth.isChecked()){
                                db.getReference().child("Teachers").child("English").child("12th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                        }
                        if(math.isChecked()){
                            if(ninth.isChecked()){
                                db.getReference().child("Teachers").child("Math").child("9th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                            if(tenth.isChecked()){
                                db.getReference().child("Teachers").child("Math").child("10th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                            if(eleventh.isChecked()){
                                db.getReference().child("Teachers").child("Math").child("11th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                            if(twelveth.isChecked()){
                                db.getReference().child("Teachers").child("Math").child("12th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                        }
                        if(physics.isChecked()){
                            if(ninth.isChecked()){
                                db.getReference().child("Teachers").child("Physics").child("9th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                            if(tenth.isChecked()){
                                db.getReference().child("Teachers").child("Physics").child("10th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                            if(eleventh.isChecked()){
                                db.getReference().child("Teachers").child("Physics").child("11th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                            if(twelveth.isChecked()){
                                db.getReference().child("Teachers").child("Physics").child("12th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                        }
                        if(chemistry.isChecked()){
                            if(ninth.isChecked()){
                                db.getReference().child("Teachers").child("Chemistry").child("9th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                            if(tenth.isChecked()){
                                db.getReference().child("Teachers").child("Chemistry").child("10th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                            if(eleventh.isChecked()){
                                db.getReference().child("Teachers").child("Chemistry").child("11th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                            if(twelveth.isChecked()){
                                db.getReference().child("Teachers").child("Chemistry").child("12th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                        }
                        if(biology.isChecked()){
                            if(ninth.isChecked()){
                                db.getReference().child("Teachers").child("Biology").child("9th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                            if(tenth.isChecked()){
                                db.getReference().child("Teachers").child("Biology").child("10th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                            if(eleventh.isChecked()){
                                db.getReference().child("Teachers").child("Biology").child("11th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                            if(twelveth.isChecked()){
                                db.getReference().child("Teachers").child("Biology").child("12th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                        }
                        if(islamiat.isChecked()){
                            if(ninth.isChecked()){
                                db.getReference().child("Teachers").child("Islamiat").child("9th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                            if(tenth.isChecked()){
                                db.getReference().child("Teachers").child("Islamiat").child("10th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                            if(eleventh.isChecked()){
                                db.getReference().child("Teachers").child("Islamiat").child("11th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                            if(twelveth.isChecked()){
                                db.getReference().child("Teachers").child("Islamiat").child("12th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                        }
                        if(urdu.isChecked()){
                            if(ninth.isChecked()){
                                db.getReference().child("Teachers").child("Urdu").child("9th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                            if(tenth.isChecked()){
                                db.getReference().child("Teachers").child("Urdu").child("10th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                            if(eleventh.isChecked()){
                                db.getReference().child("Teachers").child("Urdu").child("11th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                            if(twelveth.isChecked()){
                                db.getReference().child("Teachers").child("Urdu").child("12th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                        }
                        if(punjabi.isChecked()){
                            if(ninth.isChecked()){
                                db.getReference().child("Teachers").child("Punjabi").child("9th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                            if(tenth.isChecked()){
                                db.getReference().child("Teachers").child("Punjabi").child("10th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                            if(eleventh.isChecked()){
                                db.getReference().child("Teachers").child("Punjabi").child("11th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                            if(twelveth.isChecked()){
                                db.getReference().child("Teachers").child("Punjabi").child("12th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                        }
                        if(computer.isChecked()){
                            if(ninth.isChecked()){
                                db.getReference().child("Teachers").child("Computer").child("9th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                            if(tenth.isChecked()){
                                db.getReference().child("Teachers").child("Computer").child("10th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                            if(eleventh.isChecked()){
                                db.getReference().child("Teachers").child("Computer").child("11th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
                            }
                            if(twelveth.isChecked()){
                                db.getReference().child("Teachers").child("Computer").child("12th").child(mytiming).child(auth.currentUser!!.uid.toString()).setValue(name)
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
                if(myeducation==""){
                    education.setError("Please Fill This Field")
                }
                if(myyears==""){
                    years.setError("Please Fill This Field")
                }
                if(mysubjects==""){
                    keysubjects.setError("Please Fill This Field")
                }
                if(mycertificate==""){
                    Certification.setError("Please Fill This Field")
                }
                if(mygender==""){
                    Toast.makeText(applicationContext, "Please Select A Gender", Toast.LENGTH_SHORT).show()
                }
                else if(mytiming==""){
                    Toast.makeText(applicationContext, "Please Select A Timing", Toast.LENGTH_SHORT).show()
                }
                else if(counts==0){
                    Toast.makeText(applicationContext, "Please Select A Subject First", Toast.LENGTH_SHORT).show()
                }
                else if(countc==0){
                    Toast.makeText(applicationContext, "Please Select A Class First", Toast.LENGTH_SHORT).show()
                }
                if(!isValidPassword(mypassword)){
                    PassBox.setError("Please Enter A Proper Password")
                }
                if(!StudentActivity.isValidEmailAddress(email)){
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