package com.example.android.teletaleem

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import org.jitsi.meet.sdk.JitsiMeetActivity
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import java.net.URL
var auth:FirebaseAuth= FirebaseAuth.getInstance()
class JoinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)
        getSupportActionBar()?.hide()

        var roomno: EditText =findViewById(R.id.enterroomno)
        var joinbtn: Button =findViewById(R.id.joinbtn)
        var logintext:TextView=findViewById(R.id.loginType)
        var messagetext:TextView=findViewById(R.id.message)
        logintext.setText(logintype)
        messagetext.setText(message)
        var list1:ListView=findViewById(R.id.listView)
        if(logintype=="Student"){
            var studentclass=""
            var studenttiming=""
            var subjects= mutableListOf<String>()
           var studentclassref=db.getReference().child("Student Details").child(auth.currentUser!!.uid).child("Class")
            var studentsubjectref=db.getReference().child("Student Details").child(auth.currentUser!!.uid).child("Subjects")
            var studenttimingref=db.getReference().child("Student Details").child(auth.currentUser!!.uid).child("Timing")
            //Reading Class
            studentclassref.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    studentclass = dataSnapshot.value.toString()

                }
                override fun onCancelled(databaseError: DatabaseError) {}
            })
            //Reading Timing
            studenttimingref.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    studenttiming = dataSnapshot.value.toString()
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
            //Reading Subjects
            studentsubjectref.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (childSnapshot in dataSnapshot.children) {
                        subjects.add(childSnapshot.value.toString())
                    }
                    var teachers= mutableListOf<String>()
                    for(sub in subjects){
                        var teacherref=db.getReference().child("Teachers").child(sub).child(studentclass).child(studenttiming)
                        teacherref.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                for (childSnapshot in dataSnapshot.children) {
                                    teachers.add(childSnapshot.value.toString())
                                }
                                var adapter= ArrayAdapter(this@JoinActivity,R.layout.support_simple_spinner_dropdown_item,teachers)
                                list1.adapter=adapter
                            }

                            override fun onCancelled(databaseError: DatabaseError) {}
                        })
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })

        }
        else if(logintype=="Teacher"){

        }
        joinbtn.setOnClickListener(){
            var room=roomno.text.toString()
            roomno.setText("")
            if(room==""){
                roomno.setError("Please Enter A Room No First")
            }
            else{
                val options = JitsiMeetConferenceOptions.Builder()
                    .setServerURL(URL("https://meet.jit.si"))
                    .setRoom(room)
                    .setAudioMuted(false)
                    .setVideoMuted(false)
                    .setAudioOnly(false)
                    .setWelcomePageEnabled(false)
                    .setConfigOverride(auth.currentUser?.uid.toString(), true)
                    .build()
                JitsiMeetActivity.launch(this,options)
            }
        }
    }
    //Disable Back Button
    override fun onBackPressed() {}
}