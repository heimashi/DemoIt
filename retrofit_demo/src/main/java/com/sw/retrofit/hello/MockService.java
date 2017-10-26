package com.sw.retrofit.hello;

import com.sw.retrofit.bean.AData;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by shiwang on 26/10/2017.
 */

public interface MockService {

    @GET("path/a")
    Call<AData> getDataA();

}
