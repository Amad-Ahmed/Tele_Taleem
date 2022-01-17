package com.example.android.teletaleem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectionActivity extends AppCompatActivity {
    Button teacher,student,login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        teacher=findViewById(R.id.Teacher);
        student=findViewById(R.id.Student);
        login=findViewById(R.id.loginBtn);

        teacher.setOnClickListener(new View.OnClickListener() {//for teacher journey
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SelectionActivity.this,TeacherActivity.class);
                startActivity(i);
            }
        });
        student.setOnClickListener(new View.OnClickListener() {//for student journey
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SelectionActivity.this,StudentActivity.class);
                startActivity(i);

            }
        });
        login.setOnClickListener(new View.OnClickListener() {//for login journey
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SelectionActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
}