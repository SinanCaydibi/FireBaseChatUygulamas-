package com.example.sinchatapp.firebase;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MsgService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getData().size() > 0) {
            Log.d("ssssss", "Message data payload: " + remoteMessage.getData());
            if(onMsgListener!=null)
                onMsgListener.onMsg(remoteMessage.getData().get("msg"));
        }
    }

    @Override
    public void onNewToken(@NonNull String s) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("projectData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("firebaseToken", s);
        editor.apply();
    }


    private static OnMsgListener onMsgListener;

    public static void setMsgListener(OnMsgListener _onMsgListener){
        onMsgListener = _onMsgListener;
    }

    public interface OnMsgListener {
        void onMsg(String msg);
    }
}
