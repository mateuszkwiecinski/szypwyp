package pl.ccki.szypwyp.traficar.config

import pl.ccki.szypwyp.traficar.models.TraficarResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TraficarEndpoints {
    @GET("eaw-rest-api/car")
    fun get(@Query("shapeId") shapeId: Int): Call<TraficarResponse>
}
