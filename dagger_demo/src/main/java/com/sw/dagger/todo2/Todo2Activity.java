package com.sw.dagger.todo2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

/**
 * Created by shiwang on 12/12/2017.
 */

public class Todo2Activity extends Activity implements Todo2Contract.View {

    @Inject
    Todo2Contract.Presenter presenter;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerTodo2Component.builder().todo2Module(new Todo2Module(this)).build().inject(this);

        textView = new TextView(this);
        setContentView(textView);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.reloadData();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void updateView(String msg) {
        textView.setText(msg);
    }

    @Override
    public void showLoadingView() {
        textView.setText("加载中。。。");
    }


    public static void invoke(Context context) {
        context.startActivity(new Intent(context, Todo2Activity.class));
    }
}
