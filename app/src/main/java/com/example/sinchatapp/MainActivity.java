package com.example.sinchatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn_login,btn_register;
    public  void  init(){
        btn_login=(Button) findViewById(R.id.btn_login);
        btn_register=(Button) findViewById(R.id.btn_register);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intentLogin);
                finish();
            }
        });
        //TIklandığında diğer aktiviteye geçişi sağlıyoruz...
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister= new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intentRegister);
                finish();

            }
        });


    }
}
