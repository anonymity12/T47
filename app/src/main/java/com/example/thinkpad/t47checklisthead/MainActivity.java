package com.example.thinkpad.t47checklisthead;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.os.Handler;

import com.example.thinkpad.t47checklisthead.fragment.ContentFragment;
import com.example.thinkpad.t47checklisthead.fragment.WalkingFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;//you need dependency:'com.github.ozodrukh:CircularReveal:1.0.4'

import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.model.SlideMenuItem;
import yalantis.com.sidemenu.util.ViewAnimator;

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
 * 15。 姿态识别，毕业设计相关代码
 * 16. bind MonitorService now, but MonitorService has thread problem in one test.
 */
public class MainActivity extends AppCompatActivity  implements ViewAnimator.ViewAnimatorListener,SoundPool.OnLoadCompleteListener  {
    private static final String TAG = "MainActivity";
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private List<SlideMenuItem> list = new ArrayList<>();
    private ContentFragment contentFragment;
    private ViewAnimator viewAnimator;
    private int res = R.mipmap.res_walk;
    private LinearLayout linearLayout;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: !!!!");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    SoundPool sp;
    HashMap<Integer,Integer> hm;
    int soundId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contentFragment = ContentFragment.newInstance(R.drawable.ic_walk);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, contentFragment)
                .commit();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        linearLayout = (LinearLayout) findViewById(R.id.left_drawer);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });


        setActionBar();
        createMenuList();
        viewAnimator = new ViewAnimator<>(this, list, contentFragment, drawerLayout, this);
        //bindService(new Intent(this,MonitorService.class),serviceConnection,BIND_AUTO_CREATE);
        startCameraKacha(this);

    }

    public static void startCameraKacha(Context c) {
        final SoundPool soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
        AudioManager mgr = (AudioManager) c.getSystemService(Context.AUDIO_SERVICE);
        float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
        float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        final float volume = streamVolumeCurrent / streamVolumeMax;
        final int id = soundPool.load(c, R.raw.alert, 1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                soundPool.play(id, volume, volume, 1, 0, 1f);
            }
        }, 20);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unbindService(serviceConnection);
    }

    private void createMenuList() {
        SlideMenuItem menuItem0 = new SlideMenuItem(ContentFragment.CLOSE, R.drawable.ic_close);
        list.add(menuItem0);
        SlideMenuItem menuItem = new SlideMenuItem(ContentFragment.WALKING, R.drawable.ic_walk);
        list.add(menuItem);
        SlideMenuItem menuItem2 = new SlideMenuItem(ContentFragment.STUMBLE, R.drawable.ic_cliff_jumping);
        list.add(menuItem2);
        SlideMenuItem menuItem3 = new SlideMenuItem(ContentFragment.SIT, R.drawable.ic_airline_seat_legroom_extra);
        list.add(menuItem3);
    }


    private void setActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                linearLayout.removeAllViews();
                linearLayout.invalidate();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset > 0.6 && linearLayout.getChildCount() == 0)
                    viewAnimator.showMenuContent();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private ScreenShotable replaceFragment(ScreenShotable screenShotable, int topPosition) {
        this.res = this.res == R.mipmap.res_walk ? R.mipmap.ccb_comm_bg0 : R.mipmap.res_walk;//tt: change between film and music
        View view = findViewById(R.id.content_frame);
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);

        findViewById(R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
        animator.start();
        ContentFragment contentFragment = ContentFragment.newInstance(this.res);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, contentFragment).commit();
        return contentFragment;
    }

    @Override
    public ScreenShotable onSwitch(Resourceble slideMenuItem, ScreenShotable screenShotable, int position) {
        switch (slideMenuItem.getName()) {
            case ContentFragment.CLOSE:
                return screenShotable;
            case ContentFragment.WALKING:
                Log.d(TAG, "onSwitch: fragment walking");
                WalkingFragment walkingFragment = WalkingFragment.newInstance("arg");
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, walkingFragment).commit();
                return walkingFragment;
            default:
                return replaceFragment(screenShotable, position);//tt: guess position is where we click
        }
    }

    @Override
    public void disableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(false);

    }

    @Override
    public void enableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout.closeDrawers();

    }

    @Override
    public void addViewToContainer(View view) {
        linearLayout.addView(view);
    }

    void initSoundPool() {
        hm = new HashMap<>();
        hm.put(1,soundId);
    }

    void playSound(int sound) { // 获取AudioManager引用
        AudioManager am = (AudioManager) this
                .getSystemService(Context.AUDIO_SERVICE);
        // 获取当前音量
        float streamVolumeCurrent = am
                .getStreamVolume(AudioManager.STREAM_MUSIC);
        // 获取系统最大音量
        float streamVolumeMax = am
                .getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        // 计算得到播放音量
        float volume = streamVolumeCurrent / streamVolumeMax;
        // 调用SoundPool的play方法来播放声音文件
        Log.d(TAG, "playSound: gonna play volume = " + volume);
        sp.play(hm.get(sound), volume, volume, 1, -1, 1.f);
    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
        Log.d(TAG, "onLoadComplete: soundPool:sampleId:status:" + soundPool + sampleId + status);
        soundPool.play(sampleId, 0.9f, 0.9f, 1, -1, 1f);
    }
}

