package thaihn.com.weatherapp.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Response(
        @SerializedName("latitude") val latitude: String,
        @SerializedName("longitude") val longitude: String,
        @SerializedName("timezone") val timezone: String,
        @SerializedName("currently") val currently: Currently,
        @SerializedName("hourly") val hourly: Hourly,
        @SerializedName("daily") val daily: Daily,
        @SerializedName("offset") val offset: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(Currently::class.java.classLoader),
            parcel.readParcelable(Hourly::class.java.classLoader),
            parcel.readParcelable(Daily::class.java.classLoader),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(latitude)
        parcel.writeString(longitude)
        parcel.writeString(timezone)
        parcel.writeParcelable(currently, flags)
        parcel.writeParcelable(hourly, flags)
        parcel.writeParcelable(daily, flags)
        parcel.writeInt(offset)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Response> {
        override fun createFromParcel(parcel: Parcel): Response {
            return Response(parcel)
        }

        override fun newArray(size: Int): Array<Response?> {
            return arrayOfNulls(size)
        }
    }
}
