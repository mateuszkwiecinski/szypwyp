package pl.ccki.szypwyp.traficar.data.models

import com.google.gson.annotations.SerializedName

class TraficarResponse {
    @SerializedName("cars") var cars: List<Car>? = null
    @SerializedName("shapeId") var shapeId: Int? = null
}

class Car {
    @SerializedName("id") var id: Int? = null
    @SerializedName("model") var model: String? = null
    @SerializedName("regNumber") var regNumber: String? = null
    @SerializedName("vin") var vin: String? = null
    @SerializedName("orderNumber") var orderNumber: Int? = null
    @SerializedName("fuel") var fuel: Double? = null
    @SerializedName("latitude") var latitude: Double? = null
    @SerializedName("location") var location: String? = null
    @SerializedName("distanceAccumulated") var distanceAccumulated: Int? = null
    @SerializedName("distanceCounter") var distanceCounter: Int? = null
    @SerializedName("longitude") var longitude: Double? = null
    @SerializedName("vehicleName") var vehicleName: String? = null
    @SerializedName("priceList") var priceList: PriceList? = null
}

class PriceList {
    @SerializedName("kmPrice") var kmPrice: Double? = null
    @SerializedName("stopPrice") var stopPrice: Double? = null
    @SerializedName("drivingPrice") var drivingPrice: Double? = null
}
