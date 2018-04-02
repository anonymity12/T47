package com.example.thinkpad.t47checklisthead.rx;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.thinkpad.t47checklisthead.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by paul on 30/03/2018.
 * 1. test Rx on Android App ok
 * 2. write some code by hand
 * 3. use from operator
 * 4. interval操作符
 * 5. just, range, filter
 * 6. 加载图片
 * 7. 基本模式：observable.subscribe(observer);
 * 8. chain programming
 * 9. a sample of using rx for mainUI thread's widget, like button
 */

public class RxDemoActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "RxDemoActivity";
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        final long count = 3;
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(count + 1)
                .map(new Function<Long, Long>() {
                    public Long apply(@NonNull Long aLong) throws Exception {
                        return count - aLong;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        button.setEnabled(false);
                        button.setTextColor(Color.BLACK);
                    }
                })
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        button.setText("Remaining" + aLong + "s");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        button.setEnabled(true);
                        button.setTextColor(Color.RED);
                        button.setText("Send SMS now");
                    }
                });
    }
}
