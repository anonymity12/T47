package com.example.thinkpad.t47checklisthead.rx;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thinkpad.t47checklisthead.R;
import com.example.thinkpad.t47checklisthead.rx.bean.NetBean;
import com.example.thinkpad.t47checklisthead.rx.bean.UserBean;
import com.example.thinkpad.t47checklisthead.rx.bean.UserParam;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
 * 10. hensen sample login to httpbin.org
 * 11. T47: merge rx into master
 * 12. RxJava2与RxBinding的使用，优化搜索请求，textChanges
 * ---> modified at 1126/2018
 * 0.4 practice Rx: create basic usage
 */

public class RxDemoActivity extends AppCompatActivity {
    private static final String TAG = "RxDemoActivity";
    ApiService apiService;
    TextView tv_text;
    EditText editText;
    StringBuilder mRxOperatorsText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        tv_text = findViewById(R.id.tv_text);
        editText = findViewById(R.id.editText);
        mRxOperatorsText = new StringBuilder();


        Observable.create(new ObservableOnSubscribe<Integer>() {
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                // event 1
                mRxOperatorsText.append("Observable emit 1" + "\n");
                Log.e(TAG, "Observable emit 1" + "\n");
                e.onNext(1);
                // 2
                mRxOperatorsText.append("Observable emit 2" + "\n");
                Log.e(TAG, "Observable emit 2" + "\n");
                e.onNext(2);
                // ev 3
                mRxOperatorsText.append("Observable emit 3" + "\n");
                Log.e(TAG, "Observable emit 3" + "\n");
                e.onNext(3);
                // complete event
                e.onComplete();
                // fake ev 4
                mRxOperatorsText.append("Observable emit 4" + "\n");
                Log.e(TAG, "Observable emit 4" + "\n");
                e.onNext(4);
            }
        }).subscribe(new Observer<Integer>() {
            private int i;
            private Disposable mDisposable;

            public void onSubscribe(@NonNull Disposable d) {
                mRxOperatorsText.append("onSubscribe: " + d.isDisposed() + "\n");
                Log.e(TAG, "onSubscribe: " + d.isDisposed() + "\n");
                mDisposable = d;
            }

            public void onNext(@NonNull Integer integer) {
                mRxOperatorsText.append("onNext : value : " + integer + "\n");
                Log.e(TAG, "onNext : value: " + integer + "\n");
                i++;
                // tt 如果以下的if 没有注释（aka 进行了disposable 的清理）， 则不会执行到 tt1
                if (i == 2) {
                    // clear dispose(like event sequence), so the lower reachers can not receive the upstream events anymore
                    mDisposable.dispose();
                    mRxOperatorsText.append("onNext: isDisposed : " + mDisposable.isDisposed() + "\n");
                    Log.e(TAG, "onNext: isDisposed: " + mDisposable.isDisposed() + "\n");
                }
            }
            public void onError(@NonNull Throwable e) {
                mRxOperatorsText.append("onError: value:" + e.getMessage() + "\n");
                Log.e(TAG, "onError : value: " + e.getMessage() + "\n");
            }
            // tt1
            public void onComplete() {
                mRxOperatorsText.append("onComplete" + " \n");
                tv_text.setText(mRxOperatorsText);
                Log.e(TAG, "onComplete" + "\n");
            }
        });
    }

}
