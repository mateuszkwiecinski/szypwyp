package pl.ccki.szypwyp.traficar.data.models

import com.google.gson.annotations.SerializedName

data class TraficarResponse(
    @SerializedName("cars") val cars: List<Car>?,
    @SerializedName("shapeId") val shapeId: Int?
)

data class Car(
    @SerializedName("id") val id: Int?,
    @SerializedName("model") val model: String?,
    @SerializedName("regNumber") val regNumber: String?,
    @SerializedName("vin") val vin: String?,
    @SerializedName("orderNumber") val orderNumber: Int?,
    @SerializedName("fuel") val fuel: Double?,
    @SerializedName("latitude") val latitude: Double?,
    @SerializedName("location") val location: String?,
    @SerializedName("distanceAccumulated") val distanceAccumulated: Int?,
    @SerializedName("distanceCounter") val distanceCounter: Int?,
    @SerializedName("longitude") val longitude: Double?,
    @SerializedName("vehicleName") val vehicleName: String?,
    @SerializedName("priceList") val priceList: PriceList?
)

data class PriceList(
    @SerializedName("kmPrice") val kmPrice: Double?,
    @SerializedName("stopPrice") val stopPrice: Double?,
    @SerializedName("drivingPrice") val drivingPrice: Double?
)
