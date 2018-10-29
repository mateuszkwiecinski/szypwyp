package pl.ccki.szypwyp.blinkee.data.models

import com.google.gson.annotations.SerializedName

class BlinkeeResponse {
    @SerializedName("data")
    var data: BlinkeDataResponse? = null
}

class BlinkeDataResponse {
    @SerializedName("totalItems")
    var totalItems: Int? = null
    @SerializedName("items")
    var items: List<BlinkeItemResponse>? = null
}

class BlinkeItemResponse {
    @SerializedName("id")
    var id: Long? = null
    @SerializedName("position")
    var position: BlinkeePositionResponse? = null
    @SerializedName("type")
    var type: String? = null
}

class BlinkeePositionResponse {
    @SerializedName("lat")
    var latitude: Double? = null
    @SerializedName("lng")
    var longitude: Double? = null
}
