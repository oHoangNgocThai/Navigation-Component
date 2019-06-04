package thaihn.com.navigationcomponent

import android.app.Application
import com.google.gson.Gson

class MainApplication : Application() {

    var gSon: Gson? = null

    companion object {
        private var mSelf: MainApplication? = null
        fun self(): MainApplication {
            return mSelf!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        mSelf = this
        gSon = Gson()
    }
}
