package com.example.sinchatapp.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class FirebaseUtil {
    public static void sendToken(Member member){
        DatabaseReference databaseMember = FirebaseDatabase.getInstance().getReference();
        //databaseMember.push().setValue(member);
        databaseMember.child("members").child(member.getEmail().replace(".","&&n&&")).setValue(member.getToken());
        /*DatabaseReference databaseToken = database.child("token");
        databaseToken.child("ss").setValue(token);
        databaseToken.child("ss").setValue(token+"2");
        databaseToken.child("ss").setValue(token+"3");
        Log.d("geldi",databaseToken.child("ss").getRepo());*/
    }



}
