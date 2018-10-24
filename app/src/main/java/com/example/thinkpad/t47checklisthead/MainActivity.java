package com.example.thinkpad.t47checklisthead;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.thinkpad.t47checklisthead.dynamic.Dynamic;

import java.io.File;
import java.io.IOException;

import dalvik.system.DexClassLoader;

/**
 * Created by paul on 2018/10/23
 * last modified at 21:44.
 * Desc:
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Dynamic dynamic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //添加一个点击事件
        findViewById(R.id.tx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDexClass();
            }
        });
    }

    /**
     * 加载dex文件中的class，并调用其中的sayHello方法
     */
    private void loadDexClass() {
        File cacheFile = FileUtils.getCacheDir(getApplicationContext());
        String internalPath = cacheFile.getAbsolutePath() + File.separator + "dynamic_dex.jar";
        Log.d(TAG, "loadDexClass at position: " + internalPath);
        File desFile = new File(internalPath);
        try {
            if (!desFile.exists()) {
                desFile.createNewFile();
                FileUtils.copyFiles(this, "dynamic_dex.jar", desFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //下面开始加载dex class


        // 定义DexClassLoader
        // 第一个参数：是dex压缩文件的路径
        // 第二个参数：是dex解压缩后存放的目录
        // 第三个参数：是C/C++依赖的本地库文件目录,可以为null
        // 第四个参数：是上一级的类加载器
        DexClassLoader dexClassLoader = new DexClassLoader(internalPath, cacheFile.getAbsolutePath(), null, getClassLoader());
        try {
            Class libClazz = dexClassLoader.loadClass("com.example.thinkpad.t47checklisthead.dynamic.DynamicImpl");
            dynamic = (Dynamic) libClazz.newInstance();
            if (dynamic != null)
                Toast.makeText(this, dynamic.sayHello(), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


