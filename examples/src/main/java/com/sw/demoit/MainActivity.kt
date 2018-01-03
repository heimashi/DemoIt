package com.sw.demoit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import com.alibaba.android.arouter.launcher.ARouter
import com.sw.anko.common.AnkoTest01Activity
import com.sw.demoit.annotation.clazz.BindViewClazzActivity
import com.sw.demoit.annotation.runtime.BindViewRuntimeActivity
import com.sw.retrofit.Demo1Activity
import com.sw.rxjava.hello.TestRxjavaActivity
import org.jetbrains.anko.startActivity
import android.content.IntentFilter
import com.sw.dagger.scope.TestScopeActivity
import com.sw.dagger.todo.TodoActivity
import com.sw.dagger.todo2.Todo2Activity
import com.sw.onepixel.OnePixelReceiver
import com.sw.others.localbroad.ALocalBroadcastActivity
import com.sw.rxjava2_demo.Rxjava2Activity
import com.sw.vr_demo.GVRActivity


class MainActivity : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerOnePixel()
        initView()
    }

    private fun initView() {
        setContentView(R.layout.activity_main)
        val mvpView: TextView? = findViewById(R.id.mvp_tv)
        val annotationView: TextView? = findViewById(R.id.runtime_annotation_tv)
        val classAnnotationView: TextView? = findViewById(R.id.class_annotation_tv)
        val routerView: TextView? = findViewById(R.id.router_tv)
        val retrofitView: TextView? = findViewById(R.id.retrofit_tv)
        val rxjavaTv: TextView? = findViewById(R.id.rxjava_tv)
        val ankoTv: TextView? = findViewById(R.id.anko_tv)
        val otherTv: TextView? = findViewById(R.id.other_tv)



        mvpView?.setOnClickListener {
            //TaskActivity.invoke(this@MainActivity)
            //ARouter.getInstance().build("/mvp/taskctivity").navigation()
            //TestScopeActivity.invoke(this@MainActivity)
            //Rxjava2Activity.invoke(this@MainActivity)
            GVRActivity.invoke(this@MainActivity)
        }
        annotationView?.setOnClickListener {
            BindViewRuntimeActivity.invoke(this@MainActivity)
        }
        classAnnotationView!!.setOnClickListener {
            BindViewClazzActivity.invoke(this@MainActivity)
        }
        routerView?.setOnClickListener {
            //            var i = 0
//            Observable.interval(2, 3, TimeUnit.SECONDS)
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(Action1 { Toast.makeText(this@MainActivity, "haha" + i++, Toast.LENGTH_SHORT).show() })
            ARouter.getInstance().build("/test/aactivity").navigation()
        }
        retrofitView?.setOnClickListener {
            //            Observable.timer(3, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(Action1 {
//                Toast.makeText(this@MainActivity, "haha", Toast.LENGTH_SHORT).show()
//            })
            Demo1Activity.invoke(this@MainActivity)
        }
        rxjavaTv?.setOnClickListener {
            TestRxjavaActivity.invoke(this@MainActivity)
        }
        ankoTv?.setOnClickListener {
            startActivity<AnkoTest01Activity>("key" to "value")
        }
        otherTv?.setOnClickListener {
            startActivity(Intent(this@MainActivity, ALocalBroadcastActivity::class.java))
        }
    }

    private fun registerOnePixel() {
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_SCREEN_ON)
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        registerReceiver(OnePixelReceiver(), filter)
    }
}
