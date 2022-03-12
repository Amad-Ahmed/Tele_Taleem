package com.example.android.teletaleem

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android.teletaleem.databinding.ActivityTeacherSignupNewBinding
import java.util.regex.Pattern
lateinit var name : String
lateinit var CNIC :String
lateinit var mobilenumber:String
lateinit var email:String
lateinit var mygender:String
class teacherSignupNew : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding:ActivityTeacherSignupNewBinding= ActivityTeacherSignupNewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var list : List<String> = listOf<String>("Male","Female")
        val adapter = ArrayAdapter(this,
            R.layout.simple_spinner_item,list )
        binding.gender.adapter = adapter
        binding.btn.setOnClickListener() {
             name = binding.nameBox.text.toString()
             CNIC = binding.cnicBox.text.toString()
             mobilenumber = binding.MobileBox.text.toString()
             email = binding.EmailBox.text.toString()
            mygender = binding.gender.getSelectedItem().toString()
            if (name.length > 5 && CNIC.trim().length == 13 && mobilenumber.trim().length == 10 && mygender != "" && isValidEmailAddress(
                    email
                )
            ) {
                startActivity(Intent(this,teacherDescriptionNew::class.java))
            }
            else if(name.length<=5){
                binding.nameBox.setError("Please Enter A Name With Atleast 5 Characters")
            }
            else if(CNIC.trim().length!=13){
                binding.cnicBox.setError("Please Enter A Proper CNIC (13 Characters)")
            }
            else if(mobilenumber.trim().length!=10){
                binding.MobileBox.setError("Please Enter A Proper Mobile No (10 Characters)")
            }
            else if(mygender==""){
                Toast.makeText(applicationContext, "Please Select A Gender First", Toast.LENGTH_SHORT).show()
            }
            else if (!isValidEmailAddress(email)){
                binding.EmailBox.setError("Please Enter A Valid Email")
            }
        }

        binding.logBTN.setOnClickListener(){
            name = binding.nameBox.text.toString()
            CNIC = binding.cnicBox.text.toString()
            mobilenumber = binding.MobileBox.text.toString()
            email = binding.EmailBox.text.toString()
            mygender = binding.gender.getSelectedItem().toString()
            if (name.length > 5 && CNIC.trim().length == 13 && mobilenumber.trim().length == 10 && mygender != "" && isValidEmailAddress(
                    email
                )
            ) {
                startActivity(Intent(this,teacherDetailsNew::class.java))
            }
            else if(name.length<=5){
                binding.nameBox.setError("Please Enter A Name With Atleast 5 Characters")
            }
            else if(CNIC.trim().length!=13){
                binding.cnicBox.setError("Please Enter A Proper CNIC (13 Characters)")
            }
            else if(mobilenumber.trim().length!=10){
                binding.MobileBox.setError("Please Enter A Proper Mobile No (10 Characters)")
            }
            else if(mygender==""){
                Toast.makeText(applicationContext, "Please Select A Gender First", Toast.LENGTH_SHORT).show()
            }
            else if (!isValidEmailAddress(email)){
                binding.EmailBox.setError("Please Enter A Valid Email")
            }
        }
    }
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