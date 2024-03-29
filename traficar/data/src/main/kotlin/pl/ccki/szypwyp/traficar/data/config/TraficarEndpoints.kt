package pl.ccki.szypwyp.traficar.data.config

import pl.ccki.szypwyp.traficar.data.models.TraficarResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TraficarEndpoints {
    @GET("eaw-rest-api/car")
    fun get(@Query("shapeId") shapeId: Int): Call<TraficarResponse>
}
