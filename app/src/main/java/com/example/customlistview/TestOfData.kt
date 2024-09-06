package com.example.customlistview

import android.content.Context
import android.widget.Toast

fun testOfData(cont: Context, name: String, price: String, pictIsSet: String): Boolean {
    if (name == "" || price == "") {
        Toast.makeText(cont, cont.resources.getString(R.string.error_null), Toast.LENGTH_SHORT)
            .show()
        return false
    }
    if (price.toIntOrNull() == null) {
        Toast.makeText(cont, cont.resources.getString(R.string.error_not_digit), Toast.LENGTH_SHORT)
            .show()
        return false
    }
    if (price.toIntOrNull()!! < 1) {
        Toast.makeText(cont, cont.resources.getString(R.string.error_bad_price), Toast.LENGTH_SHORT)
            .show()
        return false
    }
    if (pictIsSet == "false") {
        Toast.makeText(cont, cont.resources.getString(R.string.error_not_pict), Toast.LENGTH_SHORT)
            .show()
        return false
    }
    return true
}