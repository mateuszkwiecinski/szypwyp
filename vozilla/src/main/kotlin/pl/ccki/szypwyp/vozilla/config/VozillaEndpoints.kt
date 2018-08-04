package pl.ccki.szypwyp.vozilla.config

import pl.ccki.szypwyp.vozilla.model.VozillaReponse
import retrofit2.Call
import retrofit2.http.GET

interface VozillaEndpoints {
    @GET("/map?objectType=VEHICLE&vehicleStatus=AVAILABLE&vehicleStatus=RESERVED")
    fun get(): Call<VozillaReponse>
}
