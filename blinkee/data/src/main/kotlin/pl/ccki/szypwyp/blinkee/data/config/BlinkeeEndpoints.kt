package pl.ccki.szypwyp.blinkee.data.config

import pl.ccki.szypwyp.blinkee.data.models.BlinkeeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BlinkeeEndpoints {
    @GET("/api/regions/{region}/vehicles")
    fun get(@Path("region") regionId: Int): Call<BlinkeeResponse>
}
