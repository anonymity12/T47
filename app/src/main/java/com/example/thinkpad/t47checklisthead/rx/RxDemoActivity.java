package com.example.thinkpad.t47checklisthead.rx;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.thinkpad.t47checklisthead.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
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
                // tt 如果以下的if 没有注释（aka 进行了disposable 的清理）， 则不会执行到 tt1; aka: 进行disposal 的清理，导致无法执行到onComplete
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

        /*map 将一个 Observable 通过 Function 转换成 另外一种 Observable ，下面的例子：把Integer
 转换 成为了 String */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).map(new Function<Integer, String>() {
            public String apply(@NonNull Integer integer) throws Exception {
                return "This is result: " + integer;
            }
        }).subscribe(new Consumer<String>() {
            public void accept(@NonNull String s) throws Exception {
                mRxOperatorsText.append("accept: " + s + "\n");
                Log.e(TAG, "accept: " + s + "\n");
            }
        });

        //zip
        Observable.zip(getStringObservable(), getIntegerObservable(), new BiFunction<String, Integer, String>() {
            public String apply(@NonNull String s, @NonNull Integer i) throws Exception {
                return s + i;
            }
        }).subscribe(new Consumer<String>() {
            public void accept(@NonNull String s) throws Exception {
                mRxOperatorsText.append("zip: accept: " + s + "\n");
                Log.e(TAG, "zip: accept : " + s + "\n");
            }
        });
    }

    private Observable<String> getStringObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onNext("A");
                    mRxOperatorsText.append("String emit: A \n");
                    Log.e(TAG, "String emit: A");
                    e.onNext("B");
                    mRxOperatorsText.append("String emit: B \n");
                    Log.e(TAG, "String emit: B \n");
                    e.onNext("C");
                    mRxOperatorsText.append("String emit: C \n");
                    Log.e(TAG, "String emit: C \n");
                }
            }
        });
    }

    private Observable<Integer> getIntegerObservable() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onNext(1);
                    mRxOperatorsText.append("Integer emit: 1 \n");
                    Log.e(TAG, "Integer emit: 1 \n ");
                    e.onNext(2);
                    mRxOperatorsText.append("Integer emit: 2 \n");
                    Log.e(TAG, "Integer emit: 2 \n");
                    e.onNext(3);
                    mRxOperatorsText.append("Integer emit: 3 \n");
                    Log.e(TAG, "Integer emit: 3 \n");
                    e.onNext(4);
                    mRxOperatorsText.append("Integer emit: 4 \n");
                    Log.e(TAG, "Integer emit: 4 \n");
                    e.onNext(5);
                    mRxOperatorsText.append("Integer emit: 5 \n");
                    Log.e(TAG, "Integer emit: 5 \n");
                }
            }
        });
    }

}
