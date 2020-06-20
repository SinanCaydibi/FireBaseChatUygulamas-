package com.example.sinchatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private Toolbar actionbarLogin;
    private EditText txtEmail,txtPassword;
    private Button btnlogin,btnRegisterLogin;
    private FirebaseAuth auth;
    private FirebaseUser aktifKullanici;
    //sayfa kontrolleri
    public void init(){
        actionbarLogin=(Toolbar) findViewById(R.id.actionbarlogin);
        setSupportActionBar(actionbarLogin);
        getSupportActionBar().setTitle("Giriş Yap");

        auth=FirebaseAuth.getInstance();
        aktifKullanici=auth.getCurrentUser();// aktif kullanıcı varsa verı ceılecek

        txtEmail =(EditText)findViewById(R.id.txtEmailLogin);
        txtPassword=(EditText) findViewById(R.id.txtPaswordLogin);
        btnlogin=(Button)findViewById(R.id.btnLogin);
        btnRegisterLogin=(Button) findViewById(R.id.btnRegisterLogin);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 loginUser();
            }
        });
        btnRegisterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gitRegisterActivity();

            }
        });
    }

    private void gitRegisterActivity() {
        Intent registerIntent=new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(registerIntent);
        finish();
    }

    // Başarılı giriş yapıldı mı yapılmadı ı kontrl ediyoruz.
    private void loginUser() {
        String email=txtEmail.getText().toString();
        String password=txtPassword.getText().toString();
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Email Alanı Boş bırakılamaz.",Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Password Alanı Boş bırakılamaz.",Toast.LENGTH_LONG).show();
        }else{
            btnlogin.setEnabled(false);

            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
            {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Email Alanı Boş bırakılamaz.", Toast.LENGTH_LONG).show();
                        Intent mainIntent = new Intent(LoginActivity.this, Main2Activity.class);
                        startActivity(mainIntent);


                    } else {
                        Toast.makeText(LoginActivity.this, "Giriş Başarısız.", Toast.LENGTH_LONG).show();
                        btnlogin.setEnabled(true);
                    }
                }
            });


        }


    }
}