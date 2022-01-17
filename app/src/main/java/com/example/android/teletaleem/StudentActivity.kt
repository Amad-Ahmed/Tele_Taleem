package com.example.android.teletaleem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.szagurskii.patternedtextwatcher.PatternedTextWatcher;

public class StudentActivity extends AppCompatActivity {
    EditText edtName,edtCnic,edtMobile;
    Spinner gender,timings,Class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        edtName=findViewById(R.id.name);
        edtCnic=findViewById(R.id.cnic);
        timings=findViewById(R.id.timings);
        gender=findViewById(R.id.gender);
        //get the spinner from the xml.
        timings= findViewById(R.id.timings);
        Class=findViewById(R.id.Class);
        //create a list of items for the spinner.
        String[] times = new String[]{"1pm to 2pm", "2pm to 3pm", "3pm to 4pm"};
        String[] genders = new String[]{"Male", "Female", "Other"};
        String[] classes=new String[]{"9th","10th","11th","12th"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, times);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, genders);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, classes);
        gender.setAdapter(adapter2);
        timings.setAdapter(adapter);
        Class.setAdapter(adapter3);

        edtCnic.addTextChangedListener(new PatternedTextWatcher("#####-#######-#"));
//        edtMobile.addTextChangedListener(new PatternedTextWatcher("####-#######"));
    }
}