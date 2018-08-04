package pl.ccki.szypwyp.goscooter.config

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface GoScooterEndpoints {
    @GET("/")
    fun get(): Call<ResponseBody>
}
