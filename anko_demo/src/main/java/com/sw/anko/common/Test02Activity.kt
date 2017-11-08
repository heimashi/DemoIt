package com.sw.anko.common

import android.app.Activity
import android.os.Bundle
import org.jetbrains.anko.selector
import org.jetbrains.anko.toast

/**
 * Created by shiwang on 08/11/2017.
 */
class Test02Activity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var value1 = intent.extras.getString("key")
        var value2 = intent.extras.getInt("key2")

        val countries = listOf("Russia", "USA", "England", "Australia")
        selector("Where are you from?", countries) { ds, i ->
            toast("So you're living in ${countries[i]}, right?")
        }

    }


}