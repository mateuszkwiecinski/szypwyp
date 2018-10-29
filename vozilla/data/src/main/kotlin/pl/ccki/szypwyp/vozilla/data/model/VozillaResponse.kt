package pl.ccki.szypwyp.vozilla.data.model

import com.google.gson.annotations.SerializedName

class VozillaReponse {
    @SerializedName("objects") var list: List<ObjectResponse>? = null
}

class Picture {
    @SerializedName("id") var id: String? = null
    @SerializedName("name") var name: String? = null
    @SerializedName("extension") var extension: String? = null
}

class Location {
    @SerializedName("latitude") var latitude: Double? = null
    @SerializedName("longitude") var longitude: Double? = null
}

class ObjectResponse {
    @SerializedName("id")
    var id: String? = null
    @SerializedName("discriminator")
    var discriminator: String? = null
    @SerializedName("platesNumber")
    var platesNumber: String? = null
    @SerializedName("sideNumber")
    var sideNumber: String? = null
    @SerializedName("color")
    var color: String? = null
    @SerializedName("type")
    var type: String? = null
    @SerializedName("picture")
    var picture: Picture? = null
    @SerializedName("rangeKm")
    var rangeKm: Int? = null
    @SerializedName("batteryLevelPct")
    var batteryLevelPct: Int? = null
    @SerializedName("reservationEnd")
    var reservationEnd: String? = null
    @SerializedName("status")
    var status: String? = null
    @SerializedName("locationDescription")
    var locationDescription: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("location")
    var location: Location? = null
    @SerializedName("metadata")
    var metadata: String? = null
}
