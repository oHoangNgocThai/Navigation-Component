package thaihn.com.navigationcomponent.util

import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun changeIconToName(@IconState name: String) = when (name) {
        Constants.CLEAR_DAY -> "ic_clear_day"
        Constants.CLEAR_NIGHT -> "ic_clear_night"
        Constants.RAIN -> "ic_rain"
        Constants.CLOUDY -> "ic_cloudy"
        Constants.FOG -> "ic_fog"
        Constants.PARTLY_CLOUDY_DAY -> "ic_partly_cloudy_day"
        Constants.PARTLY_CLOUDY_NIGHT -> "ic_partly_cloudy_night"
        Constants.SLEET -> "ic_sleet"
        Constants.SNOW -> "ic_snow"
        Constants.WIND -> "ic_wind"
        else -> "ic_clear_day"
    }

    fun getImage(imageName: String, context: Context): Int {
        return context.resources.getIdentifier(imageName, "drawable", context.packageName)
    }

    fun convertTime(format: String, time: Long): String {
        val simpleDateFormat = SimpleDateFormat(format, Locale.getDefault())
        return simpleDateFormat.format(time * 1000)
    }

    fun convertTimeToHour(time: Long): String {
        val hour = convertTime("HH", time).toInt()
        val day = convertTime("dd", time).toInt()
        val current_hour = getCalendar().get(Calendar.HOUR_OF_DAY)
        val current_day = getCalendar().get(Calendar.DAY_OF_MONTH)
        if (day != current_day) {
            return hour.toString()
        } else {
            return if (hour == current_hour) "Now"
            else hour.toString()
        }
    }

    fun convertTimeToDay(time: Long): String {
        return SimpleDateFormat("EEEE", Locale.ENGLISH).format(time * 1000).toString()
    }

    private fun getCalendar() = Calendar.getInstance()

    fun makeTemp() = 0x00B0.toChar()

    fun changeTempFToC(tempF: Double): Int {
        var result = (tempF - 32) / 1.8
        return result.toInt()
    }
}