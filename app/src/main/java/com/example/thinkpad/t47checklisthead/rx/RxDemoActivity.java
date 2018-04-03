package com.example.thinkpad.t47checklisthead.rx;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.example.thinkpad.t47checklisthead.R;
import com.example.thinkpad.t47checklisthead.rx.bean.NetBean;
import com.example.thinkpad.t47checklisthead.rx.bean.UserBean;
import com.example.thinkpad.t47checklisthead.rx.bean.UserParam;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
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
 */

public class RxDemoActivity extends AppCompatActivity {
    private static final String TAG = "RxDemoActivity";
    ApiService apiService;
    TextView tv_text;
    EditText editText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        tv_text = findViewById(R.id.tv_text);
        editText = findViewById(R.id.editText);

        RxTextView.textChanges(editText)
                .debounce(500, TimeUnit.MILLISECONDS)
                .switchMap(new Function<CharSequence, ObservableSource<List<String>>>() {
                    @Override
                    public ObservableSource<List<String>> apply(CharSequence charSequence) throws Exception {
                        List<String> list = new ArrayList<String>();
                        list.add("1024");
                        list.add("2018");
                        return Observable.just(list);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Exception {
                        System.out.println(strings.toString());
                    }
                });
    }

}
