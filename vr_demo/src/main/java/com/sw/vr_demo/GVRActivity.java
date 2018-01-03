package com.sw.vr_demo;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;

public class GVRActivity extends Activity {

    private VrPanoramaView vrPanoramaView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vr_layout);

        initVrPaNormalView();
    }

    private void initVrPaNormalView() {
        vrPanoramaView = findViewById(R.id.vr_panorama_view);
//        VrPanoramaView.Options paNormalOptions = new VrPanoramaView.Options();
//        paNormalOptions.inputType = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;
////      vrPanoramaView.setFullscreenButtonEnabled (false); //隐藏全屏模式按钮
//        vrPanoramaView.setInfoButtonEnabled(false); //设置隐藏最左边信息的按钮
//        vrPanoramaView.setStereoModeButtonEnabled(false); //设置隐藏立体模型的按钮
//        vrPanoramaView.setEventListener(new ActivityEventListener()); //设置监听
        //加载本地的图片源
        vrPanoramaView.loadImageFromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.imggugong),null);
        //设置网络图片源
//        panoWidgetView.loadImageFromByteArray();
    }

    private class ActivityEventListener extends VrPanoramaEventListener {
        @Override
        public void onLoadSuccess() {//图片加载成功
        }


        @Override
        public void onLoadError(String errorMessage) {//图片加载失败
        }

        @Override
        public void onClick() {//当我们点击了VrPanoramaView 时候触发            super.onClick();
        }

        @Override
        public void onDisplayModeChanged(int newDisplayMode) {
            super.onDisplayModeChanged(newDisplayMode);
        }
    }

    public static void invoke(Context context) {
        context.startActivity(new Intent(context, GVRActivity.class));
    }

}
