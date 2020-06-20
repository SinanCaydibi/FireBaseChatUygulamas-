package com.example.sinchatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager.widget.ViewPager;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import com.example.sinchatapp.firebase.FirebaseUtil;
import com.example.sinchatapp.firebase.Member;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {
    private Toolbar actionbar;
    private ViewPager vpMain;
    private TabLayout tabsmain;
    private TabsAdapter tabsadapter;
    private FirebaseUser aktifKullanici;
    private FirebaseAuth auth;



    public void  init(){
        actionbar=(Toolbar) findViewById(R.id.actionBar);
        setSupportActionBar(actionbar);
        getSupportActionBar().setTitle(R.string.app_name);


        auth= FirebaseAuth.getInstance();
        aktifKullanici=auth.getCurrentUser();
        if (aktifKullanici==null || aktifKullanici.getEmail()==null){
            Intent MainIntent = new Intent(Main2Activity.this,MainActivity .class);
            startActivity(MainIntent);
            finish();
        }

        vpMain=(ViewPager) findViewById(R.id.vpMain);
        tabsadapter =new TabsAdapter(getSupportFragmentManager());
        vpMain.setAdapter(tabsadapter);
        tabsmain=(TabLayout) findViewById(R.id.tabsMain);
        tabsmain.setupWithViewPager(vpMain);


        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("projectData", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("firebaseToken","?");

        Member member = new Member();
        member.setEmail(aktifKullanici.getEmail());
        member.setToken(token);
        FirebaseUtil.sendToken(member);

        DatabaseReference databaseMember = FirebaseDatabase.getInstance().getReference();
        databaseMember.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, String> map = (Map<String, String>) dataSnapshot.child("members").getValue();

                ArrayList<Member> memberArrayList = new ArrayList<>();
                for (Map.Entry<String,String> entry : map.entrySet()){
                    Member member = new Member();
                    member.setEmail(entry.getKey());
                    member.setToken(entry.getValue());
                    memberArrayList.add(member);
                }

                if(memberArrayList.size()>0){

                }
                else {

                }

                /*String value = dataSnapshot.child("members").getValue(String.class);
                Log.d("aaaaaa", "aa"+value);*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
// Başlangıc kullanıcıcı firebasede kayıtlı mı aktif mi ?


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();

    }
    @Override
    protected void onStart() {
        if (aktifKullanici==null){
            Intent MainIntent = new Intent(Main2Activity.this,MainActivity .class);
            startActivity(MainIntent);
            finish();

        }
        super.onStart();
    }
    // prjemize dahil etmek için kullanılan metot çıkıs cubuğunu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      super.onCreateOptionsMenu(menu);

      getMenuInflater().inflate(R.menu.menu_main,menu);

        return true;

    }
// seçildğinde yapılması gereken
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.mainLogout){
            auth.signOut();
            Intent loginIntent = new Intent(Main2Activity.this,LoginActivity.class);
            startActivity(loginIntent);
            finish();

        }
        return true;
    }
}
