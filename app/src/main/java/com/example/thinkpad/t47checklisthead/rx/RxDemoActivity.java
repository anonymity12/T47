package com.example.thinkpad.t47checklisthead.rx;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.example.thinkpad.t47checklisthead.R;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.*;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by paul on 30/03/2018.
 * 1. test Rx on Android App ok
 * 2. write some code by hand
 * 3. use from operator
 * 4. interval操作符
 * 5. just, range, filter
 * 6. 加载图片
 */

public class RxDemoActivity extends Activity{
    private static final String TAG = "RxDemoActivity";
    private ImageView img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        img = findViewById(R.id.imageView);

//        rxJavaCreate();
//        fromOperation();
//        intervalOperation();
//        justOperation();
//        rangeOperation();
//        filterOperation();
        loadingImg("http://pic38.nipic.com/20140228/5571398_215900721128_2.jpg")
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<byte[]>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: !!!!");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(byte[] bytes) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        img.setImageBitmap(bitmap);
                    }
                });

    }

    private Observable<byte[]> loadingImg(final String imgPath) {
        return Observable.create(new Observable.OnSubscribe<byte[]>() {
            @Override
            public void call(final Subscriber<? super byte[]> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    OkHttpClient client = new OkHttpClient();
                    final Request request = new Request.Builder().url(imgPath).build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                byte[] bytes = response.body().bytes();
                                if (bytes != null) {
                                    subscriber.onNext(bytes);
                                }
                            }
                            subscriber.onCompleted();
                        }
                    });
                }
            }
        });
    }












    private void filterOperation() {
        Observable.just(1,2,3,4,5,6,7,8,9,0).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer < 5;
            }
        }).observeOn(Schedulers.io()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG, "onNext: " + integer.toString());
            }
        });
    }

    //range operator
    private void rangeOperation() {
        Observable observable = Observable.range(1, 11);
        observable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer o) {
                Log.i(TAG, "o.toString()" + o.toString());
            }
        });
    }

    //just operator
    private void justOperation() {
        Integer[] item1 = {1, 2, 3, 4, 5};
        Integer[] item2 = {8, 9, 10, 10, 10};
        Observable observable = Observable.just(item1, item2);
        observable.subscribe(new Subscriber<Integer[]>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: ");
            }

            @Override
            public void onNext(Integer[] o) {
                Log.i(TAG, "onNext: ");
                for (int j = 0; j < o.length; j ++) {
                    Log.i(TAG, o[j].toString());
                }
            }
        });
    }

    //interval operator
    private void intervalOperation() {
        Observable observable = Observable.interval(1, 1, TimeUnit.SECONDS);
        observable.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                Log.i(TAG, o.toString());
            }
        });
    }


    //from操作符
    private void fromOperation(){
        Integer [] items = {1,2,3,4,5};
        //使用在被观察者，返回的对象一般都是数值类型
        Observable observable =Observable.from(items);
        observable.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                Log.i(TAG,o.toString());
            }
        });
    }

    //创建
    private static void rxJavaCreate() {
        //定义被观察者
        Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {

                //判断是否有订阅的关系
                if (!subscriber.isUnsubscribed()) {
                    for (int i = 0; i < 10; i++) {
                        subscriber.onNext(i);
                    }
                    //完成
                    subscriber.onCompleted();
                }
            }
        });

        Subscriber<Integer> showSub = new Subscriber<Integer>() {
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
            public void onNext(Integer s) {
                Log.i(TAG, s+"");
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
