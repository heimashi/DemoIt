package com.sw.retrofit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.sw.retrofit.bean.AData;
import com.sw.retrofit.hello.MockService;
import com.sw.retrofit.hello.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Demo1Activity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrofit_activity_a);
        findViewById(R.id.get_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doGetA();
            }
        });
    }

    private void doGetA() {
        MockService mockService = RetrofitClient.getInstance().getRetrofit().create(MockService.class);
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

    public static void invoke(Context context) {
        context.startActivity(new Intent(context, Demo1Activity.class));
    }

}
