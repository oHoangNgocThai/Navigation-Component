package thaihn.com.navigationcomponent.ui.main.weather


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.thaihn.kotlinstart.screen.base.BaseFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import thaihn.com.navigationcomponent.R
import thaihn.com.navigationcomponent.data.remote.BaseNetwork
import thaihn.com.navigationcomponent.data.repository.WeatherRepository
import thaihn.com.navigationcomponent.ui.main.details.DetailsFragment
import thaihn.com.navigationcomponent.util.Constants
import thaihn.com.navigationcomponent.util.Utils
import thaihn.com.weatherapp.data.model.Response


/**
 * A simple [Fragment] subclass.
 *
 */
class WeathersFragment : BaseFragment(), View.OnClickListener {

    private val TAG = "MainActivity"

    private var repository: WeatherRepository? = null
    private var response: Response? = null

    private var temp: TextView? = null

    override var layoutResource: Int = R.layout.fragment_weathers

    override fun initVariable(saveInstanceState: Bundle?, rootView: View) {
        temp = rootView.findViewById(R.id.text_temp)
        rootView.findViewById<Button>(R.id.button_hourly).setOnClickListener(this)
        rootView.findViewById<Button>(R.id.button_weekly).setOnClickListener(this)
        rootView.findViewById<Button>(R.id.button_github).setOnClickListener(this)
        rootView.findViewById<Button>(R.id.button_activity).setOnClickListener(this)
    }

    @SuppressLint("CheckResult")
    override fun initData(saveInstanceState: Bundle?) {
        repository = WeatherRepository(BaseNetwork().providerWeatherApi())
        repository!!.getWeather(21.0185844, 105.7771888)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { it ->
                            handleResponse(it)
                        },
                        { it ->
                            handleError(it)
                        })
    }

    @SuppressLint("SetTextI18n")
    private fun handleResponse(res: Response) {
        temp?.text = Utils.changeTempFToC(res.currently.temperature).toString() + Utils.makeTemp()
        response = res

    }

    private fun handleError(message: Throwable) {
        message.printStackTrace()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_hourly -> if (response != null) {
                putData(Constants.TYPE_HOURLY)
            }
            R.id.button_weekly -> if (response != null) {
                putData(Constants.TYPE_DAILY)
            }
            R.id.button_github -> {
                val directions = WeathersFragmentDirections.actionTest()
                NavHostFragment.findNavController(this).navigate(directions)
            }
            R.id.button_activity -> {
                val directions = WeathersFragmentDirections.actionTestActivity()
                NavHostFragment.findNavController(this).navigate(directions)
            }
        }
    }

    fun putData(type: String) {
        val bundle = Bundle()
        bundle.putString(DetailsFragment.type, type)
        bundle.putParcelable(DetailsFragment.data, response)
        NavHostFragment.findNavController(this).navigate(R.id.detailsFragment, bundle)
    }
}
