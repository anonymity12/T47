package com.example.thinkpad.t47checklisthead;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thinkpad.t47checklisthead.fragment.HeadFragment;
import com.example.thinkpad.t47checklisthead.view.panelview;


public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private panelview mPanelview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fragment_container,new HeadFragment())
                .commit();
//        seekBar = findViewById(R.id.seek_bar);
//        mPanelview = findViewById(R.id.my_custom_view);
//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                mPanelview.setdg(progress);
//                Log.d("MainActivity", String.valueOf(seekBar.getProgress()));
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
////                Toast.makeText(MainActivity.this, "pressed seek bar",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
////                Toast.makeText(MainActivity.this, "release seek bar", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
