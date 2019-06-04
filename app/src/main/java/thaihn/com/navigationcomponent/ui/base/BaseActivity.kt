package com.thaihn.kotlinstart.screen.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    protected abstract var layoutResource: Int

    protected abstract fun initVariable()

    protected abstract fun initData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
        initVariable()
        initData()
    }
}