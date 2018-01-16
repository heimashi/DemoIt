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
import com.sw.nest_demo.CoordinatorLayout2Activity
import com.sw.onepixel.OnePixelReceiver
import com.sw.others.localbroad.ALocalBroadcastActivity
import com.sw.vr_demo.GVRActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerOnePixel()
        initView()
    }

    private fun initView() {
        setContentView(R.layout.activity_main)


        mvp_tv?.setOnClickListener {
            //TaskActivity.invoke(this@MainActivity)
            //ARouter.getInstance().build("/mvp/taskctivity").navigation()
            //TestScopeActivity.invoke(this@MainActivity)
            //Rxjava2Activity.invoke(this@MainActivity)
            GVRActivity.invoke(this@MainActivity)
        }
        runtime_annotation_tv?.setOnClickListener {
            BindViewRuntimeActivity.invoke(this@MainActivity)
        }
        class_annotation_tv!!.setOnClickListener {
            BindViewClazzActivity.invoke(this@MainActivity)
        }
        router_tv?.setOnClickListener {
            //            var i = 0
//            Observable.interval(2, 3, TimeUnit.SECONDS)
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(Action1 { Toast.makeText(this@MainActivity, "haha" + i++, Toast.LENGTH_SHORT).show() })
            ARouter.getInstance().build("/test/aactivity").navigation()
        }
        retrofit_tv?.setOnClickListener {
            //            Observable.timer(3, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(Action1 {
//                Toast.makeText(this@MainActivity, "haha", Toast.LENGTH_SHORT).show()
//            })
            Demo1Activity.invoke(this@MainActivity)
        }
        rxjava_tv?.setOnClickListener {
            TestRxjavaActivity.invoke(this@MainActivity)
        }
        anko_tv?.setOnClickListener {
            startActivity<AnkoTest01Activity>("key" to "value")
        }
        other_tv?.setOnClickListener {
            startActivity(Intent(this@MainActivity, ALocalBroadcastActivity::class.java))

        }

        nest_tv?.setOnClickListener { startActivity(Intent(this@MainActivity, CoordinatorLayout2Activity::class.java)) }
    }

    private fun registerOnePixel() {
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_SCREEN_ON)
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        registerReceiver(OnePixelReceiver(), filter)
    }
}
