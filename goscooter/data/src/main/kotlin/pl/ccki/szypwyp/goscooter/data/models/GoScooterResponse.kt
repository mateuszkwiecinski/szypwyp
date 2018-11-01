package pl.ccki.szypwyp.goscooter.data.models

import com.google.gson.annotations.SerializedName

data class GoScooterResponse(
    @SerializedName("DeviceTypeId")
    val typeId: Long?,
    @SerializedName("DeviceId")
    val id: Long?,
    @SerializedName("DeviceName")
    val name: String?,
    @SerializedName("DeviceNetworkKey")
    val networkKey: String?,
    @SerializedName("DeviceLatitude")
    val latitude: Double?,
    @SerializedName("DeviceLongitude")
    val longitude: Double?,
    @SerializedName("DeviceAltitude")
    val altitude: Int?,
    @SerializedName("DevicePositionStamp")
    val positionStamp: String?,
    @SerializedName("Image")
    val image: String?
)
