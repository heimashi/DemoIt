package com.sw.retrofit.hello;

import com.sw.retrofit.bean.AData;
import com.sw.retrofit.bean.BData;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by shiwang on 26/10/2017.
 */

public interface MockService {

    @GET("path/a")
    Call<AData> getDataA();


    @POST("path/a")
    @FormUrlEncoded
    Call<AData> getPostDataA(@Field("username") String username, @Field("password") String password);


    @POST
    @FormUrlEncoded
    Call<BData> getPostDataB(@Url String url, @FieldMap Map<String, String> maps);


    @GET("path/a")
    Observable<AData> getDataC();
}
