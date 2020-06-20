package com.example.sinchatapp.firebase;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendMsg extends AsyncTask<String,Void,Void> {

    final static private String FCM_URL = "https://fcm.googleapis.com/fcm/send";

    public SendMsg(String message, String tokenId) {
        this.message = message;
        this.tokenId = tokenId;
    }

    private String message;
    private String tokenId;

    @Override
    protected Void doInBackground(String... strings) {
        try{

            URL url = new URL(FCM_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Authorization","key=AAAARZRF808:APA91bFEoWvQbpBspIVdQOPTMNL6baoWlg-cr9_93xwc__95S4jpq6NZsGC0S5dHmg2316FiplPrsui8htJfffolxvCU5auvUk3cNMPoz0Xma-Rlkj7pVbYWfntsM9Ky2HSQYPevzHkB");
            conn.setRequestProperty("Content-Type","application/json");


            JSONObject infoJson = new JSONObject();
            infoJson.put("msg",message);

            JSONObject json = new JSONObject();
            json.put("to",tokenId.trim());
            json.put("data", infoJson);

            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(json.toString());
            wr.flush();
            int status = 0;


            status = conn.getResponseCode();

            if( status != 0){
                if( status == 200 ){


                }
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
}