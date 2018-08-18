package pl.ccki.szypwyp.goscooter.models

import com.google.gson.annotations.SerializedName

class GoScooterResponse {

    @SerializedName("DeviceTypeId")
    var typeId: Long? = null

    @SerializedName("DeviceId")
    var id: Long? = null

    @SerializedName("DeviceName")
    var name: String? = null

    @SerializedName("DeviceNetworkKey")
    var networkKey: String? = null

    @SerializedName("DeviceLatitude")
    var latitude: Double? = null

    @SerializedName("DeviceLongitude")
    var longitude: Double? = null

    @SerializedName("DeviceAltitude")
    var altitude: Int? = null

    @SerializedName("DevicePositionStamp")
    var positionStamp: String? = null

    @SerializedName("Image")
    var image: String? = null
}
