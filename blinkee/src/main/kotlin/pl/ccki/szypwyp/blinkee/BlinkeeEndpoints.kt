package pl.ccki.szypwyp.blinkee

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BlinkeeEndpoints {
    @GET("/api/regions/{region}/vehicles")
    fun get(@Path("region") regionId: Int): Call<BlinkeeResponse>
}