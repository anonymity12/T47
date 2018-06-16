package com.example.thinkpad.t47checklisthead;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button);
        Log.d(TAG, "onClick: before send broadcast");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setAction("com.abc.mydata");
                i.putExtra("data", "Data String broadcasted from MainActivity");
                sendBroadcast(i,"com.abc.permission.GET_DATA");
                Log.d(TAG, "onClick: send broadcast");
            }
        });
    }
}
