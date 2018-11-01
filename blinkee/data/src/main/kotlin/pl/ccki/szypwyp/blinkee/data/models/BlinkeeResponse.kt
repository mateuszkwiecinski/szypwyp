package pl.ccki.szypwyp.blinkee.data.models

import com.google.gson.annotations.SerializedName

data class BlinkeeResponse(
    @SerializedName("data")
    val data: BlinkeDataResponse?
)

data class BlinkeDataResponse(
    @SerializedName("totalItems")
    val totalItems: Int?,
    @SerializedName("items")
    val items: List<BlinkeItemResponse>?
)

data class BlinkeItemResponse(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("position")
    val position: BlinkeePositionResponse?,
    @SerializedName("type")
    val type: String?
)

data class BlinkeePositionResponse(
    @SerializedName("lat")
    val latitude: Double?,
    @SerializedName("lng")
    val longitude: Double?
)
