package com.example.android.teletaleem

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
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