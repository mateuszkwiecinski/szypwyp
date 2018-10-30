package pl.ccki.szypwyp.goscooter.data.config

import pl.ccki.szypwyp.goscooter.data.models.GoScooterResponse
import retrofit2.Call
import retrofit2.http.GET

interface GoScooterEndpoints {
    @GET("/Home/GetAllFreeDevices")
    fun allAvailableDevices(): Call<List<GoScooterResponse>>
}
