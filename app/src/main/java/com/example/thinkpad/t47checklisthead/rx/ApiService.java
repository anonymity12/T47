package com.example.thinkpad.t47checklisthead.rx;


import com.example.thinkpad.t47checklisthead.rx.bean.NetBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by thinkpad on 2018/4/2.
 */

public interface ApiService {
    @FormUrlEncoded
    @POST("/post")
    Observable<NetBean> getUserInfo(@Field("username") String username, @Field("password") String password);
}
