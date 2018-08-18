package pl.ccki.szypwyp.goscooter.config

import pl.ccki.szypwyp.goscooter.models.GoScooterResponse
import retrofit2.Call
import retrofit2.http.GET

interface GoScooterEndpoints {
    @GET("/Home/GetAllFreeDevices")
    fun allAvailableDevices(): Call<List<GoScooterResponse>>
}
