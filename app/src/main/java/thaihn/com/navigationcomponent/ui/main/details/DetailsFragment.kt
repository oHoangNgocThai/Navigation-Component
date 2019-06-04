package thaihn.com.navigationcomponent.ui.main.details


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thaihn.kotlinstart.screen.base.BaseFragment
import thaihn.com.navigationcomponent.R
import thaihn.com.navigationcomponent.util.Constants
import thaihn.com.weatherapp.data.model.Response

/**
 * A simple [Fragment] subclass.
 *
 */
class DetailsFragment : BaseFragment() {

    private val TAG = "DetailsFragment"

    var response: Response? = null
    var mType: String? = null
    var recycle: RecyclerView? = null

    var hourlyAdapter = HourlyAdapter(listOf())
    var weeklyAdapter = WeeklyAdapter(listOf())

    companion object {
        val type = "type"
        val data = "data"
    }

    override var layoutResource: Int = R.layout.fragment_details

    override fun initVariable(saveInstanceState: Bundle?, rootView: View) {
        response = arguments?.getParcelable(data)
        mType = arguments?.getString(type, "AAA")
        recycle = rootView.findViewById(R.id.recycle_detail)
    }

    override fun initData(saveInstanceState: Bundle?) {
        chooseType(mType!!)
    }

    private fun chooseType(_type: String) {
        when (_type) {
            Constants.TYPE_HOURLY -> {
                recycle?.apply {
                    adapter = hourlyAdapter
                    hasFixedSize()
                    layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                }
                hourlyAdapter.setNewData(response!!.hourly.data)
            }
            Constants.TYPE_DAILY -> {
                recycle?.apply {
                    adapter = weeklyAdapter
                    hasFixedSize()
                    layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                }
                weeklyAdapter.setNewData(response!!.daily.data)
            }
        }
    }
}
