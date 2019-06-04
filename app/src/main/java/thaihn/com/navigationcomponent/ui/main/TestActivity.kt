package thaihn.com.navigationcomponent.ui.main

import android.content.Intent
import androidx.core.app.NavUtils
import androidx.core.app.TaskStackBuilder
import com.thaihn.kotlinstart.screen.base.BaseActivity
import kotlinx.android.synthetic.main.activity_test.*
import thaihn.com.navigationcomponent.R

class TestActivity : BaseActivity() {

    override var layoutResource: Int = R.layout.activity_test

    override fun initVariable() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun initData() {
        toolbar.setNavigationOnClickListener {
            val upIntent: Intent? = NavUtils.getParentActivityIntent(this)
            when {
                upIntent == null -> throw IllegalStateException("No Parent Activity Intent") as Throwable
                NavUtils.shouldUpRecreateTask(this, upIntent) -> {
                    TaskStackBuilder.create(this)
                            .addNextIntentWithParentStack(upIntent)
                            .startActivities()
                }
                else -> {
                    NavUtils.navigateUpTo(this, upIntent)
                }
            }
        }
    }
}
