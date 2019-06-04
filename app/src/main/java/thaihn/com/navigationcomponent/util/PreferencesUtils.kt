package thaihn.com.navigationcomponent.util

import android.content.Context
import android.content.SharedPreferences
import thaihn.com.navigationcomponent.MainApplication

class SharedPrefs private constructor() {
    private val mSharedPreferences: SharedPreferences

    init {
        mSharedPreferences = MainApplication.self().getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE)
    }

    @Suppress("UNCHECKED_CAST")
    operator fun <T> get(key: String, anonymousClass: Class<T>): T {
        return when (anonymousClass) {
            String::class.java -> mSharedPreferences.getString(key, "") as T
            Boolean::class.java -> java.lang.Boolean.valueOf(
                    mSharedPreferences.getBoolean(key, false)) as T
            Float::class.java -> java.lang.Float.valueOf(mSharedPreferences.getFloat(key, 0f)) as T
            Int::class.java -> Integer.valueOf(mSharedPreferences.getInt(key, 0)) as T
            Long::class.java -> java.lang.Long.valueOf(mSharedPreferences.getLong(key, 0)) as T
            else -> MainApplication.self().gSon?.fromJson(mSharedPreferences.getString(key, ""),
                    anonymousClass)!!
        }
    }

    @Suppress("UNCHECKED_CAST")
    operator fun <T> get(key: String, anonymousClass: Class<T>, defaultValue: T): T {
        return when (anonymousClass) {
            String::class.java -> mSharedPreferences.getString(key, defaultValue as String) as T
            Boolean::class.java -> java.lang.Boolean.valueOf(
                    mSharedPreferences.getBoolean(key, defaultValue as Boolean)) as T
            Float::class.java -> java.lang.Float.valueOf(
                    mSharedPreferences.getFloat(key, defaultValue as Float)) as T
            Int::class.java -> Integer.valueOf(
                    mSharedPreferences.getInt(key, defaultValue as Int)) as T
            Long::class.java -> java.lang.Long.valueOf(
                    mSharedPreferences.getLong(key, defaultValue as Long)) as T
            else -> MainApplication.self().gSon?.fromJson(mSharedPreferences.getString(key, ""),
                    anonymousClass)!!
        }
    }

    fun <T> put(key: String, data: T) {
        val editor = mSharedPreferences.edit()
        when (data) {
            is String -> editor.putString(key, data as String)
            is Boolean -> editor.putBoolean(key, data as Boolean)
            is Float -> editor.putFloat(key, data as Float)
            is Int -> editor.putInt(key, data as Int)
            is Long -> editor.putLong(key, data as Long)
            else -> editor.putString(key, MainApplication.self().gSon?.toJson(data))
        }
        editor.apply()
    }

    fun clear() {
        mSharedPreferences.edit().clear().apply()
    }

    companion object {
        private val PREFS_NAME = "share_prefs"
        private var mInstance: SharedPrefs? = null
        val instance: SharedPrefs
            get() {
                if (mInstance == null) {
                    mInstance = SharedPrefs()
                }
                return mInstance as SharedPrefs
            }
    }
}
