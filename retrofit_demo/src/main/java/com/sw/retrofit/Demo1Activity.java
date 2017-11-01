package com.sw.retrofit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sw.retrofit.bean.AData;
import com.sw.retrofit.bean.BData;
import com.sw.retrofit.hello.MockService;
import com.sw.retrofit.hello.RetrofitClient;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class Demo1Activity extends Activity {

    MockService mockService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrofit_activity_a);
        mockService = RetrofitClient.getInstance().getRetrofit().create(MockService.class);
        findViewById(R.id.get_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doGet();
            }
        });
        findViewById(R.id.post_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doPost();
            }
        });
        findViewById(R.id.post2_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doPost2();
            }
        });
        findViewById(R.id.rx1_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testRxjavaAndRetrofit1();
            }
        });
    }

    private void doGet() {
        Call<AData> aDataCall = mockService.getDataA();
        aDataCall.enqueue(new Callback<AData>() {
            @Override
            public void onResponse(Call<AData> call, Response<AData> response) {
                AData data = response.body();

                Toast.makeText(Demo1Activity.this, data == null ? "" : data.data + "  " + data.status, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<AData> call, Throwable t) {
                Toast.makeText(Demo1Activity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void doPost() {
        Call<AData> aDataCall = mockService.getPostDataA("jack", "123456");
        aDataCall.enqueue(new Callback<AData>() {
            @Override
            public void onResponse(Call<AData> call, Response<AData> response) {
                AData data = response.body();

                Toast.makeText(Demo1Activity.this, data == null ? "" : data.data + "  " + data.status, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<AData> call, Throwable t) {
                Toast.makeText(Demo1Activity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void doPost2() {
        HashMap<String, String> params = new HashMap<>();
        params.put("username", "jack");
        params.put("password", "123456");
        Call<BData> aDataCall = mockService.getPostDataB("path/b/c", params);
        aDataCall.enqueue(new Callback<BData>() {
            @Override
            public void onResponse(Call<BData> call, Response<BData> response) {
                BData data = response.body();

                Toast.makeText(Demo1Activity.this, data == null ? "" : data.key1 + "  " + data.key2, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BData> call, Throwable t) {
                Toast.makeText(Demo1Activity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void testRxjavaAndRetrofit1() {
        mockService.getDataC()
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<AData>() {
                    @Override
                    public void call(AData aData) {
                        //save to db
                        Log.i("test", "save to db");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AData>() {
                    @Override
                    public void onCompleted() {
                        Log.i("test", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("test", "onError");
                    }

                    @Override
                    public void onNext(AData aData) {
                        Log.i("test", "onNext:" + aData.data);
                    }
                });

    }

    public static void invoke(Context context) {
        context.startActivity(new Intent(context, Demo1Activity.class));
    }

}
