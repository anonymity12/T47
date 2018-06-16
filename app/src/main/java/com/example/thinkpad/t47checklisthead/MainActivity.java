package com.example.thinkpad.t47checklisthead;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendBroadcast(View view) {
        Intent intent = new Intent("com.example.thinkpad.t47checklisthead.CUSTOM_INTENT");
        sendBroadcast(intent);
        Log.d(TAG, "sendBroadcast: sent!!"+ intent.getAction());
    }

}
