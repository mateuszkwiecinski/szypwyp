package pl.ccki.szypwyp.blinkee.data.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class BlinkeeResponse(
    @SerializedName("data")
    val data: BlinkeDataResponse?
)

@Keep
data class BlinkeDataResponse(
    @SerializedName("totalItems")
    val totalItems: Int?,
    @SerializedName("items")
    val items: List<BlinkeItemResponse>?
)

@Keep
data class BlinkeItemResponse(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("position")
    val position: BlinkeePositionResponse?,
    @SerializedName("type")
    val type: String?
)

@Keep
data class BlinkeePositionResponse(
    @SerializedName("lat")
    val latitude: Double?,
    @SerializedName("lng")
    val longitude: Double?
)
