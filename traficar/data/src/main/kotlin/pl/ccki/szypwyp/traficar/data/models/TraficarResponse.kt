package pl.ccki.szypwyp.traficar.data.models

class TraficarResponse {
    var cars: List<Car>? = null
    var shapeId: Int? = null
}

class Car {
    var id: Int? = null
    var model: String? = null
    var regNumber: String? = null
    var vin: String? = null
    var orderNumber: Int? = null
    var fuel: Double? = null
    var latitude: Double? = null
    var location: String? = null
    var distanceAccumulated: Int? = null
    var distanceCounter: Int? = null
    var longitude: Double? = null
    var vehicleName: String? = null
    var priceList: PriceList? = null
}

class PriceList {
    var kmPrice: Double? = null
    var stopPrice: Double? = null
    var drivingPrice: Double? = null
}
