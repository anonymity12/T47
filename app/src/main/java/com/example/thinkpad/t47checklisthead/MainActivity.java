package com.example.thinkpad.t47checklisthead;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Basic activity for testings.
 * tested:
 * 1. seek bar control the panel view
 * 2. using fragment manager to use fragment.
 * 3. using circle button.
 * 4. using tab layout and view pager. Tutorial from http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0731/3247.html
 * 5. google example: Creating Swipe Views with Tabs.
 * 6. 03142018 test handler
 * 7. Test AIDL by jinliang gao
 * 8. Using messenger # Android art c2.4.3 @03192018
 * 9. show u how to write file in Android.
 * 11. show u how to find screen size
 * 12. show u how to use view stub
 * 13. show u how to use nfc on samsung
 * 14. test ThreadLocal
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: !!!");

    }

    @Override
    protected void onResume() {
       super.onResume();
    }

}

