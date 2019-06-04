package thaihn.com.weatherapp.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Daily(
        @SerializedName("summary") val summary: String,
        @SerializedName("icon") val icon: String,
        @SerializedName("data") val data: List<DataDaily>
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.createTypedArrayList(DataDaily)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(summary)
        parcel.writeString(icon)
        parcel.writeTypedList(data)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Daily> {
        override fun createFromParcel(parcel: Parcel): Daily {
            return Daily(parcel)
        }

        override fun newArray(size: Int): Array<Daily?> {
            return arrayOfNulls(size)
        }
    }
}

class DataDaily(
        @SerializedName("time") val time: Long,
        @SerializedName("summary") val summary: String,
        @SerializedName("sunriseTime") val sunriseTime: Long,
        @SerializedName("sunsetTime") val sunsetTime: Long,
        @SerializedName("icon") val icon: String,
        @SerializedName("precipIntensity") val precipIntensity: Double,
        @SerializedName("precipProbability") val precipProbability: Double,
        @SerializedName("temperature") val temperature: Double,
        @SerializedName("temperatureHigh") val temperatureHigh: Double,
        @SerializedName("temperatureLow") val temperatureLow: Double,
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
        @SerializedName("ozone") val ozone: Double,
        @SerializedName("precipType") val precipType: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readLong(),
            parcel.readLong(),
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
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readInt(),
            parcel.readDouble(),
            parcel.readInt(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(time)
        parcel.writeString(summary)
        parcel.writeLong(sunriseTime)
        parcel.writeLong(sunsetTime)
        parcel.writeString(icon)
        parcel.writeDouble(precipIntensity)
        parcel.writeDouble(precipProbability)
        parcel.writeDouble(temperature)
        parcel.writeDouble(temperatureHigh)
        parcel.writeDouble(temperatureLow)
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
        parcel.writeString(precipType)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataDaily> {
        override fun createFromParcel(parcel: Parcel): DataDaily {
            return DataDaily(parcel)
        }

        override fun newArray(size: Int): Array<DataDaily?> {
            return arrayOfNulls(size)
        }
    }
}
