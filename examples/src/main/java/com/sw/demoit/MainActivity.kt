package com.sw.demoit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sw.mvp.presenters.TaskActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()

    }

    private fun initView() {
        setContentView(R.layout.activity_main)
        val mvpView = findViewById(R.id.mvp_tv)
        val annotationView = findViewById(R.id.annotation_tv)
        mvpView!!.setOnClickListener {
            TaskActivity.invoke(this@MainActivity)
        }
        annotationView!!.setOnClickListener{
            BindViewRuntimeActivity.invoke(this@MainActivity)
        }
    }
}
