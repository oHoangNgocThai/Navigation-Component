package thaihn.com.weatherapp.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Hourly(
        @SerializedName("summary") val summary: String,
        @SerializedName("icon") val icon: String,
        @SerializedName("data") val data: List<DataHourly>
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.createTypedArrayList(DataHourly)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(summary)
        parcel.writeString(icon)
        parcel.writeTypedList(data)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Hourly> {
        override fun createFromParcel(parcel: Parcel): Hourly {
            return Hourly(parcel)
        }

        override fun newArray(size: Int): Array<Hourly?> {
            return arrayOfNulls(size)
        }
    }
}

class DataHourly(
        @SerializedName("time") val time: Long,
        @SerializedName("summary") val summary: String,
        @SerializedName("icon") val icon: String,
        @SerializedName("precipIntensity") val precipIntensity: Double,
        @SerializedName("precipProbability") val precipProbability: Double,
        @SerializedName("precipType") val precipType: String,
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
            parcel.readString(),
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
        parcel.writeString(precipType)
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

    companion object CREATOR : Parcelable.Creator<DataHourly> {
        override fun createFromParcel(parcel: Parcel): DataHourly {
            return DataHourly(parcel)
        }

        override fun newArray(size: Int): Array<DataHourly?> {
            return arrayOfNulls(size)
        }
    }
}
