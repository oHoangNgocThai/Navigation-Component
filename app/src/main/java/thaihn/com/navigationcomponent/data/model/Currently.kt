package thaihn.com.weatherapp.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Currently(
        @SerializedName("time") val time: Long,
        @SerializedName("summary") val summary: String,
        @SerializedName("icon") val icon: String,
        @SerializedName("precipIntensity") val precipIntensity: Double,
        @SerializedName("precipProbability") val precipProbability: Double,
        @SerializedName("temperature") val temperature: Double,
        @SerializedName("apparentTemperature") val apparentTemperature: Double,
        @SerializedName("dewPoint") val dewPoint: Double,
        @SerializedName("humidity") val humidity: Double,
        @SerializedName("pressure") val pressure: Double,
        @SerializedName("windSpeed") val windSpeed: Double,
        @SerializedName("windGust") val windGust: Double,
        @SerializedName("windBearing") val windBearing: Int,
        @SerializedName("cloudCover") val cloudCover: Double,
        @SerializedName("uvIndex") val uvIndex: Int,
        @SerializedName("visibility") val visibility: Double,
        @SerializedName("ozone") val ozone: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readInt(),
            parcel.readDouble(),
            parcel.readInt(),
            parcel.readDouble(),
            parcel.readDouble()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(time)
        parcel.writeString(summary)
        parcel.writeString(icon)
        parcel.writeDouble(precipIntensity)
        parcel.writeDouble(precipProbability)
        parcel.writeDouble(temperature)
        parcel.writeDouble(apparentTemperature)
        parcel.writeDouble(dewPoint)
        parcel.writeDouble(humidity)
        parcel.writeDouble(pressure)
        parcel.writeDouble(windSpeed)
        parcel.writeDouble(windGust)
        parcel.writeInt(windBearing)
        parcel.writeDouble(cloudCover)
        parcel.writeInt(uvIndex)
        parcel.writeDouble(visibility)
        parcel.writeDouble(ozone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Currently> {
        override fun createFromParcel(parcel: Parcel): Currently {
            return Currently(parcel)
        }

        override fun newArray(size: Int): Array<Currently?> {
            return arrayOfNulls(size)
        }
    }
}
