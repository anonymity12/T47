package com.example.thinkpad.t47checklisthead.rx;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.example.thinkpad.t47checklisthead.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by paul on 30/03/2018.
 * 1. test Rx on Android App ok
 * 2. write some code by hand
 * 3. use from operator
 * 4. interval操作符
 * 5. just, range, filter
 * 6. 加载图片
 * 7. 基本模式：observable.subscribe(observer);
 */

public class RxDemoActivity extends Activity {
    private static final String TAG = "RxDemoActivity";
    private ImageView img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        img = findViewById(R.id.imageView);
        getObservable().subscribe(getObserver());
    }

    public Observable<String> getObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("you are handsome");
                e.onNext("you are wonderful");
                e.onNext("unsubscribe");
                e.onNext("you are awesome and ready to unsubscribe");
                e.onNext("still here");
                e.onComplete();
            }
        });
    }

    public Observer<String> getObserver(){
        return new Observer<String>() {
            Disposable disposable = null;
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: "+s );
                if (s.equals("unsubscribe")) {
                    disposable.dispose();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: !!!");//tt：这行不会被执行，因为在onNext里有清理了disposable。
            }
        };
    }


}
