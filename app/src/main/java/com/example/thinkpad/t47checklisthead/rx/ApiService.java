package com.example.thinkpad.t47checklisthead.rx;

import android.database.Observable;

import com.example.thinkpad.t47checklisthead.rx.bean.NetBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by thinkpad on 2018/4/2.
 */

public interface ApiService {
    @FormUrlEncoded
    @POST
    Observable<NetBean> getUserInfo(@Field("username") String username, @Field("password") String password);
}
