package com.example.thinkpad.t47checklisthead.rx;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import rx.Subscriber;
import rx.Observable;

/**
 * Created by paul on 30/03/2018.
 * 1. test Rx on Android App ok
 */

public class RxDemoActivity extends Activity{
    private static final String TAG = "RxDemoActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rxJavaCreate();
    }

    //创建
    private static void rxJavaCreate() {
        //定义被观察者
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //判断是否有订阅的关系
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext("Hello");
                    subscriber.onNext("Hi");
                    subscriber.onNext(getJson());
                    subscriber.onNext("END");
                    //完成
                    subscriber.onCompleted();
                }
            }
        });

        Subscriber<String> showSub = new Subscriber<String>() {
            //结束
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }

            //发生错误
            @Override
            public void onError(Throwable e) {
                Log.i(TAG, e.toString());
            }

            //继续执行
            @Override
            public void onNext(String s) {
                Log.i(TAG, s);
            }
        };
        //订阅
        observable.subscribe(showSub);
    }

    public static String getJson() {
        //这里进行网络解析 耗时操作
        return "Json Data";
    }
}
