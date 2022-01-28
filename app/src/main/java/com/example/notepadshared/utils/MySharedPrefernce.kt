package com.example.notepadshared.utils

import android.content.Context
import android.content.SharedPreferences

object MySharedPrefernce {
    private const val NAME = "app_name_contact"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preference: SharedPreferences

    fun init(context: Context) {
        preference = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor: SharedPreferences.Editor = edit()
        editor.apply()
    }

    var userData: String?
        get() = preference.getString("user", "")
        set(value) = preference.edit() {
            if (value != null) {
                it.putString("user", value)
            }
        }
}