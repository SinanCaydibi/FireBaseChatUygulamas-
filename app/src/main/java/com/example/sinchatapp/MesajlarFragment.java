package com.example.sinchatapp;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sinchatapp.firebase.Member;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class MesajlarFragment extends Fragment {


    public MesajlarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_mesajlar, container, false);
        final ListView listView = view.findViewById(R.id.listChat);
        DatabaseReference databaseMember = FirebaseDatabase.getInstance().getReference();
        databaseMember.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, String> map = (Map<String, String>) dataSnapshot.child("members").getValue();


                FirebaseAuth auth= FirebaseAuth.getInstance();
                FirebaseUser aktifKullanici = auth.getCurrentUser();
                if(aktifKullanici==null) return;

                ArrayList<Member> memberArrayList = new ArrayList<>();
                for (Map.Entry<String,String> entry : map.entrySet()){
                    Member member = new Member();
                    String currentMail = entry.getKey().replace("&&n&&",".");
                    if(!currentMail.equals(aktifKullanici.getEmail())) {
                        member.setEmail(currentMail);
                        member.setToken(entry.getValue());
                        memberArrayList.add(member);
                    }
                }

                if(memberArrayList.size()>0){
                    MesajAdapter chatAdapter = new MesajAdapter(getContext(),memberArrayList);
                    listView.setAdapter(chatAdapter);
                }
                else {
                    listView.setVisibility(View.GONE);
                    TextView textView = view.findViewById(R.id.emptyText);
                    textView.setVisibility(View.VISIBLE);
                }

                /*String value = dataSnapshot.child("members").getValue(String.class);
                Log.d("aaaaaa", "aa"+value);*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

}
