package pl.ccki.szypwyp.nextbike.data.config

import pl.ccki.szypwyp.nextbike.data.models.NextbikeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NextbikeEndpoints {
    @GET("maps/nextbike-live.json")
    fun json(@Query("city") regionId: Int): Call<NextbikeResponse>
}
