package com.sw.mvp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sw.mvp.R;
import com.sw.mvp.bean.ATaskData;


public class ATaskView extends RelativeLayout implements BaseView<ATaskData> {

    private TextView titleTv, contentTv;

    public ATaskView(Context context) {
        this(context, null);
    }

    public ATaskView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        inflate(context, R.layout.mvp_view_atask, this);
        titleTv = (TextView) findViewById(R.id.title);
        contentTv = (TextView) findViewById(R.id.content);
        Button detailBtn = (Button) findViewById(R.id.detail_btn);
        detailBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback != null) {
                    callback.onUpdateCallback();
                }
            }
        });
    }


    @Override
    public void updateData(ATaskData data) {
        titleTv.setText(data.title);
        contentTv.setText(data.content);
    }

    private ATaskViewCallback callback;

    public void setCallback(ATaskViewCallback callback) {
        this.callback = callback;
    }

    public interface ATaskViewCallback {

        void onUpdateCallback();
    }

}
