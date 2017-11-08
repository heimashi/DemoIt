package com.sw.anko.common

import android.app.Activity
import android.os.Bundle
import org.jetbrains.anko.*

/**
 * Created by shiwang on 08/11/2017.
 */
class AnkoTest01Activity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var value1 = intent.extras.getString("key")
        alert("this is the msg") {
            customTitle {
                verticalLayout {
                    editText { hint = "hint_title" }
                }
            }
            okButton { startActivity<Test02Activity>("key" to "value", "key2" to 1) }
            cancelButton { toast("button-cancel"+value1) }
        }.show()
    }


}