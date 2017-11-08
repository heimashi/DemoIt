package com.sw.demoit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.sw.anko.common.AnkoTest01Activity
import com.sw.demoit.annotation.clazz.BindViewClazzActivity
import com.sw.demoit.annotation.runtime.BindViewRuntimeActivity
import com.sw.retrofit.Demo1Activity
import com.sw.rxjava.hello.TestRxjavaActivity
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()

    }

    private fun initView() {
        setContentView(R.layout.activity_main)
        val mvpView = findViewById(R.id.mvp_tv)
        val annotationView = findViewById(R.id.runtime_annotation_tv)
        val classAnnotationView = findViewById(R.id.class_annotation_tv)
        val routerView = findViewById(R.id.router_tv)
        val retrofitView = findViewById(R.id.retrofit_tv)

        mvpView?.setOnClickListener {
            //TaskActivity.invoke(this@MainActivity)
            ARouter.getInstance().build("/mvp/taskctivity").navigation()
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
        findViewById(R.id.rxjava_tv)?.setOnClickListener {
            TestRxjavaActivity.invoke(this@MainActivity)
        }
        findViewById(R.id.anko_tv)?.setOnClickListener {
            startActivity<AnkoTest01Activity>("key" to "value")
        }
    }
}
