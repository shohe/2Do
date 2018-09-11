package com.fubic.a2do.extension

import android.support.annotation.IdRes
import android.view.View

/**
 * Created by shoheohtani on 2018/09/10.
 */

fun <T : View> View.bindView(@IdRes id: Int): Lazy<T> = lazy {
    findViewById(id) as T
}