package com.example.android.teletaleem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.szagurskii.patternedtextwatcher.PatternedTextWatcher;

public class TeacherActivity extends AppCompatActivity {
    EditText edtName,edtCnic,edtMobile;
    Spinner gender,timings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        edtName=findViewById(R.id.name);
        edtCnic=findViewById(R.id.cnic);
        timings=findViewById(R.id.timings);
        gender=findViewById(R.id.gender);
        //get the spinner from the xml.
        timings= findViewById(R.id.timings);
        //create a list of items for the spinner.
        String[] times = new String[]{"1pm to 2pm", "2pm to 3pm", "3pm to 4pm"};
        String[] genders = new String[]{"Male", "Female", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, times);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, genders);
        gender.setAdapter(adapter2);
        timings.setAdapter(adapter);

        edtCnic.addTextChangedListener(new PatternedTextWatcher("#####-#######-#"));
//        edtMobile.addTextChangedListener(new PatternedTextWatcher("####-#######"));

    }
}