package pl.ccki.szypwyp.vozilla.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class VozillaReponse(
    @SerializedName("objects")
    val list: List<ObjectResponse>?
)

@Keep
data class Picture(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("extension")
    val extension: String?
)

@Keep
data class Location(
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude: Double?
)

@Keep
data class ObjectResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("discriminator")
    val discriminator: String?,
    @SerializedName("platesNumber")
    val platesNumber: String?,
    @SerializedName("sideNumber")
    val sideNumber: String?,
    @SerializedName("color")
    val color: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("picture")
    val picture: Picture?,
    @SerializedName("rangeKm")
    val rangeKm: Int?,
    @SerializedName("batteryLevelPct")
    val batteryLevelPct: Int?,
    @SerializedName("reservationEnd")
    val reservationEnd: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("locationDescription")
    val locationDescription: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("location")
    val location: Location?,
    @SerializedName("metadata")
    val metadata: String?
)
