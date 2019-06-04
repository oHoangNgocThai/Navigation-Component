package thaihn.com.navigationcomponent.ui.github

import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.thaihn.kotlinstart.screen.base.BaseActivity
import kotlinx.android.synthetic.main.activity_github.*
import thaihn.com.navigationcomponent.R

class GithubActivity : BaseActivity() {
    override var layoutResource: Int = R.layout.activity_github

    override fun initVariable() {
    }

    override fun initData() {
        val navController = Navigation.findNavController(this, R.id.nav_host_github)
        navigation.let {
            NavigationUI.setupWithNavController(navigation, navController)
        }
    }
}
