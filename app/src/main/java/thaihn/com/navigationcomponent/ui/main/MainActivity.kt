package thaihn.com.navigationcomponent.ui.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker
import androidx.navigation.Navigation
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.thaihn.kotlinstart.screen.base.BaseActivity
import thaihn.com.navigationcomponent.R
import thaihn.com.navigationcomponent.util.Constants
import thaihn.com.navigationcomponent.util.SharedPrefs

class MainActivity : BaseActivity() {

    private val TAG = "MainActivity"

    private val PERMISSIONS_REQUEST_CODE = 29
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override var layoutResource: Int = R.layout.activity_main

    override fun initVariable() {
    }

    override fun initData() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.nav_host).navigateUp()
    }

    override fun onStart() {
        super.onStart()
        if (!checkPermissions()) {
            requestPermissions()
        } else {
            getLastLocation()
        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray
    ) {
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            when {
                grantResults.isEmpty() -> Log.i(TAG, "User interaction was cancelled.")
                (grantResults[0] == PackageManager.PERMISSION_GRANTED) -> getLastLocation()
                else -> {
                    Log.i(TAG, "Missing permission")
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        fusedLocationClient.lastLocation
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful && task.result != null) {
                        SharedPrefs.instance.put(Constants.PREFERENCE_LOCATION_LAT,
                                task.result.latitude)
                        SharedPrefs.instance.put(Constants.PREFERENCE_LOCATION_LONG,
                                task.result.longitude)
                        Log.i(TAG, "Lat " + task.result.latitude)
                        Log.i(TAG, "Long " + task.result.longitude)
                    }
                }
    }

    private fun checkPermissions() =
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PermissionChecker.PERMISSION_GRANTED

    private fun startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                PERMISSIONS_REQUEST_CODE)
    }

    private fun requestPermissions() {
        startLocationPermissionRequest()
    }
}
