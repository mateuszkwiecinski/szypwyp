package pl.ccki.szypwyp.nextbike.data.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class NextbikeResponse(@SerializedName("countries") val countries: List<CountryResponse>?)

@Keep
data class CountryResponse(
    @SerializedName("country_name") val name: String?,
    @SerializedName("cities") val cities: List<CityResponse>?
)

@Keep
data class CityResponse(
    @SerializedName("uid") val id: Long?,
    @SerializedName("name") val name: String?,
    @SerializedName("lat") val lat: Double?,
    @SerializedName("lng") val lng: Double?,
    @SerializedName("places") val places: List<PlaceResponse>?
)

@Keep
data class PlaceResponse(
    @SerializedName("uid") val id: Long?,
    @SerializedName("number") val number: Int?,
    @SerializedName("lat") val latitude: Double?,
    @SerializedName("lng") val longitude: Double?,
    @SerializedName("name") val name: String?,
    @SerializedName("bike_racks") val racksTotal: Int?,
    @SerializedName("free_racks") val racksFree: Int?
)
