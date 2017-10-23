package com.sw.mvp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sw.mvp.R;
import com.sw.mvp.bean.BTaskData;


public class BTaskView extends RelativeLayout implements BaseView<BTaskData> {

    private TextView titleTv, contentTv, contentTv2;

    public BTaskView(Context context) {
        this(context, null);
    }

    public BTaskView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        inflate(context, R.layout.mvp_view_btask, this);
        titleTv = (TextView) findViewById(R.id.title);
        contentTv = (TextView) findViewById(R.id.content);
        contentTv2 = (TextView) findViewById(R.id.content2);
        Button detailBtn = (Button) findViewById(R.id.detail_btn);
        detailBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback != null) {
                    callback.onUpdateCallback();
                }
            }
        });
        setBackgroundColor(0xffc0c0c0);
    }


    @Override
    public void updateData(BTaskData data) {
        titleTv.setText(data.title);
        contentTv.setText(data.content);
        contentTv2.setText(data.content2);
    }

    private ATaskViewCallback callback;

    public void setCallback(ATaskViewCallback callback) {
        this.callback = callback;
    }

    public interface ATaskViewCallback {

        void onUpdateCallback();
    }

}
