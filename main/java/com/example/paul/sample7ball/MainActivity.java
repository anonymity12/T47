package com.example.paul.sample7ball;

import android.graphics.Color;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import static com.example.paul.sample7ball.Constants.BURST_VIEW;
import static com.example.paul.sample7ball.Constants.CLEAN_SCR;
import static com.example.paul.sample7ball.Constants.LEVEL_COMING;
import static com.example.paul.sample7ball.Constants.PAUSE;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    boolean shouldBurst;
    boolean isBursting;
    double burstSoundLevel = 34;
    double soundLevel;
    RecordThread rt = new RecordThread();
    CheckThread ct = new CheckThread();
    ParticleView lz;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == CLEAN_SCR) {
                lz = null;
            } else if (msg.what == LEVEL_COMING) {
                Log.d(TAG, "handleMessage: get sound level = " + soundLevel);
                soundLevel = msg.arg1;
            } else if (msg.what == BURST_VIEW) {
                burst();
            } else if (msg.what == PAUSE) {
                Toast.makeText(MainActivity.this, "Puase!!", Toast.LENGTH_SHORT).show();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;
        Constants.middleScreenX = width / 2;
        Constants.bottomY = height;
        setContentView(R.layout.activity_main);

        rt.start();
        ct.start();

    }

    // show the burst view and start count down
    void burst() {
        if (!isBursting) {
            lz = new ParticleView(this);        //创建一个LiZiView对象
            setContentView(lz);
            isBursting = true;
        } else {
            // is bursting, so we don't care this event
            return;
        }

        // 5s later, isBursting is false, and clr screen
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isBursting = false;

                Message msg = mHandler.obtainMessage();
                msg.what = CLEAN_SCR;
                mHandler.sendMessage(msg);
            }
        }).start();
    }

    class CheckThread extends Thread {
        @Override
        public void run() {
            super.run();
            while(true) {
                shouldBurst = (soundLevel > burstSoundLevel);

                if (shouldBurst) {
                    Log.d(TAG, "checkThread send: >>>>>>bursting!");
                    Message msgOfBurst = mHandler.obtainMessage();
                    msgOfBurst.what = BURST_VIEW;
                    mHandler.sendMessage(msgOfBurst);
                }
                // sleep for 1s
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class RecordThread extends Thread {
        AudioRecord ar;
        int bs;
        int SAMPLE_RATE_IN_HZ = 8000;
        boolean isRun = false;

        RecordThread() {
            super();
            bs = AudioRecord.getMinBufferSize(SAMPLE_RATE_IN_HZ, AudioFormat.CHANNEL_IN_DEFAULT, AudioFormat.ENCODING_PCM_16BIT);
            ar = new AudioRecord(MediaRecorder.AudioSource.MIC, SAMPLE_RATE_IN_HZ, AudioFormat.CHANNEL_IN_DEFAULT, AudioFormat.ENCODING_PCM_16BIT, bs);
        }

        @Override
        public void run() {
            super.run();
            ar.startRecording();
            byte[] buffer = new byte[bs];
            isRun = true;
            while (isRun) {
                // calc
                int r = ar.read(buffer, 0, bs);
                int v = 0;
                for (int i = 0; i < buffer.length; i++) {
                    v += buffer[i] * buffer[i];
                }
                double mean = v / (double) r;
                double volume = 10 * Math.log10(mean);
                Log.d(TAG, "run: db is == " + volume);

                Message msgOfSound = mHandler.obtainMessage();
                msgOfSound.what = LEVEL_COMING;
                msgOfSound.arg1 = (int) volume;
                mHandler.sendMessage(msgOfSound);
            }

            // thread to death, res to release
            ar.stop();
            Log.d(TAG, "run: done, out of while");
            ar.release();
            ar = null;
        }

        public void pause() {
            isRun = false;
            Log.d(TAG, "pause: record th pause");
            Message msgPause = mHandler.obtainMessage();
            msgPause.what = PAUSE;
            mHandler.sendMessage(msgPause);
        }
        public void start() {
            if (!isRun) {
                super.start();
            }
        }
    }


    @Override
    public void onClick(View v) {
        // 从未被触发，那么可能是没有setContentView的原因
        Log.d(TAG, "onClick: should see: pause following");
        rt.pause();
    }
}
