package com.example.traffic.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 * @author tengfei
 * date 2019-09-25 18:23
 * email tengfeigo@outlook.com
 * description
 */

fun showSoftKeyboard(editText: EditText?,context: Context) {
    try {
        if (editText != null) {
            editText.requestFocus()
            val manager =context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.showSoftInput(editText, 0)
        }
    } catch (e: Exception) {
        //logWarn(TAG, e.message, e)
    }

}