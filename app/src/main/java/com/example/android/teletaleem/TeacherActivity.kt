package com.example.android.teletaleem

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern


class TeacherActivity : AppCompatActivity() {
    var edtName: EditText? = null
    var edtCnic: EditText? = null
    var edtMobile: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher)
        edtName = findViewById<EditText>(R.id.name)
        edtCnic = findViewById<EditText>(R.id.cnic)
        var timings = findViewById<Spinner>(R.id.timings)
        var gender = findViewById<Spinner>(R.id.gender)
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
}