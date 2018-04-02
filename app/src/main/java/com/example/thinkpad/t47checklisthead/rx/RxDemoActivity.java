package com.example.thinkpad.t47checklisthead.rx;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.thinkpad.t47checklisthead.R;
import com.example.thinkpad.t47checklisthead.rx.bean.NetBean;
import com.example.thinkpad.t47checklisthead.rx.bean.UserBean;
import com.example.thinkpad.t47checklisthead.rx.bean.UserParam;

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
 */

public class RxDemoActivity extends AppCompatActivity {
    private static final String TAG = "RxDemoActivity";
    ApiService apiService;
    TextView tv_text;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        tv_text = findViewById(R.id.tv_text);
        //retrofit
        apiService = new Retrofit.Builder()
                .baseUrl("https://httpbin.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class);

        //rxjava
        UserParam param = new UserParam("hensen", "123456");
        Observable.just(param)
                .flatMap(new Function<UserParam, ObservableSource<NetBean>>() {
                    @Override
                    public ObservableSource<NetBean> apply(@NonNull UserParam userParam) throws Exception {
                        return apiService.getUserInfo(userParam.getParam1(), userParam.getParam2());
                    }
                })
                .flatMap(new Function<NetBean, ObservableSource<UserBean>>() {
                    @Override
                    public ObservableSource<UserBean> apply(NetBean netBean) throws Exception {
                        UserBean user = new UserBean(netBean.getForm().getUsername(), netBean.getForm().getPassword());
                        return Observable.just(user);
                    }
                })
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserBean>() {
                    @Override
                    public void accept(@NonNull UserBean userBean) throws Exception {
                        tv_text.setText("用户名" + userBean.getUsername() + "--密码" + userBean.getPasswrod());
                    }
                });
    }

}
