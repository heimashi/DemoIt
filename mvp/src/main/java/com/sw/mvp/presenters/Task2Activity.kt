package com.sw.mvp.presenters

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout

import com.sw.mvp.R
import com.sw.mvp.views.ATaskView

/**
 * Created by shiwang on 20/10/2017.
 */

class Task2Activity : Activity() {

    private var aTaskView: ATaskView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        setContentView(R.layout.mvp_activity_task)
        val containerLayout = findViewById<LinearLayout>(R.id.container_layout)
        aTaskView = ATaskView(this)
        containerLayout.addView(aTaskView)

        aTaskView!!.setOnClickListener {
            val a = 4
            val b = a + 5
        }
    }


}
