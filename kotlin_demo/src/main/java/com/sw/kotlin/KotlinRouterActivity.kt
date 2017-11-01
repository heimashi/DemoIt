package com.sw.kotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView

/**
 * Created by shiwang on 29/10/2017.
 */

class KotlinRouterActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kotlin_activity_a)

        val textView = TextView(this@KotlinRouterActivity)
        textView.setOnClickListener {
            println("haha")
        }
        textView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                println("aaaa")
            }
        })

    }

    companion object {
        fun invoke(context: Context) {
            context.startActivity(Intent(context, KotlinRouterActivity::class.java))
        }
    }
}
