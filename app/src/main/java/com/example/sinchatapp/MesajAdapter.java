package com.example.sinchatapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sinchatapp.firebase.Member;

import java.util.ArrayList;

public class MesajAdapter extends ArrayAdapter {

    private ArrayList<Member> objects;
    private LayoutInflater inflater;

    public MesajAdapter(Context context, ArrayList<Member> objects) {
        super(context, 0, objects);
        this.objects = objects;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Holder holder;

        if(convertView==null){
            convertView = inflater.inflate(R.layout.layout_mesaj, parent, false);
            holder = new Holder();
            holder.textViewEmail = convertView.findViewById(R.id.textEmail);
            convertView.setTag(holder);
        }
        else {
            holder = (Holder) convertView.getTag();
        }

        final Member obj = objects.get(position);

        holder.textViewEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), MesajActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("token",obj.getToken());
                i.putExtra("email",obj.getEmail());
                getContext().startActivity(i);
            }
        });

        holder.textViewEmail.setText(obj.getEmail());

        return convertView;
    }
    private class Holder{
        TextView textViewEmail;
    }
}
