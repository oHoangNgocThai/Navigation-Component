package thaihn.com.navigationcomponent.ui.github

//import kotlinx.android.synthetic.main.fragment_user.*
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.thaihn.kotlinstart.screen.base.BaseFragment
import thaihn.com.navigationcomponent.R

/**
 * A simple [Fragment] subclass.
 *
 */
class UserFragment : BaseFragment() {

    override var layoutResource: Int = R.layout.fragment_user

    override fun initVariable(saveInstanceState: Bundle?, rootView: View) {
        rootView.findViewById<Button>(R.id.button_to_detail).setOnClickListener {
            NavHostFragment.findNavController(this@UserFragment).navigate(R.id.detailUserFragment)
        }
    }

    override fun initData(saveInstanceState: Bundle?) {

    }
}
