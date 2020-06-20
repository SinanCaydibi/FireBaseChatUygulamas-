package com.example.sinchatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sinchatapp.firebase.MsgService;
import com.example.sinchatapp.firebase.SendMsg;

public class MesajActivity extends AppCompatActivity implements View.OnClickListener {

    TextView gonderen;
    LinearLayout layoutMsg;
    EditText editMesaj;
    ImageButton send;

    String token;
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesaj);

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        gonderen = findViewById(R.id.gonderen);
        layoutMsg = findViewById(R.id.layoutMsg);
        editMesaj = findViewById(R.id.editMesaj);
        send = findViewById(R.id.send);

        send.setOnClickListener(this);

        token = getIntent().getStringExtra("token");
        String email = getIntent().getStringExtra("email");
        gonderen.setText(email);

        MsgService.setMsgListener(new MsgService.OnMsgListener() {
            @Override
            public void onMsg(final String msg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        layoutMsg.addView(getGreenTextView(msg));
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View view) {
        String mesaj = editMesaj.getText().toString();
        if(!mesaj.trim().equals("")){
            karsiyaGonder(mesaj);
        }

        editMesaj.setText("");
    }

    private void karsiyaGonder(String msg){
        layoutMsg.addView(getBlueTextView(msg));
        new SendMsg(msg,token).execute();
    }

    private TextView getGreenTextView(String msg){
        TextView textView = (TextView) inflater.inflate(R.layout.item_msg_green,layoutMsg,false);
        textView.setText(msg);
        return textView;
    }
    private TextView getBlueTextView(String msg){
        TextView textView = (TextView) inflater.inflate(R.layout.item_msg_blue,layoutMsg,false);
        textView.setText(msg);
        return textView;
    }
}
