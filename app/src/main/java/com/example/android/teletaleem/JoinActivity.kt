package com.example.android.teletaleem

import android.content.Intent
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
            startActivity(Intent(this,student_dashboard::class.java))
        }
        else if(logintype=="Teacher"){
            startActivity(Intent(this,TeacherDashboard::class.java))
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