package pl.ccki.szypwyp.di

import pl.ccki.szypwyp.domain.models.AppId
import pl.ccki.szypwyp.domain.models.CityId
import pl.ccki.szypwyp.domain.models.CityModel
import pl.ccki.szypwyp.domain.models.Kilometers
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.services.ExternalPlugin

object DelayingPlugin : ExternalPlugin {
    val id: PluginId = PluginId("someId")
    override val appId: AppId
        get() = AppId("com.google.android.googlequicksearchbox")
    override val name = "Delaying"
    override val supportedCities: Iterable<CityModel>
        get() = listOf(CityModel(object : CityId {}, LatLng(0.0, 0.0), radius = Kilometers(Long.MAX_VALUE)))

    override fun findInLocation(location: Iterable<CityId>): List<MarkerModel> {
        Thread.sleep(3000)
        val list = mutableListOf<MarkerModel>()
        for (i in 1..200) {
            list += object : MarkerModel {
                override val id: String
                    get() = "$i"
                override val location: LatLng = LatLng(51.100 + i * Math.random() / 1000, 17.0385 + i * Math.random() / 1000)
                override val name: String
                    get() = "marker $i"
            }
        }

        return list
    }
}

object ThrowingPlugin : ExternalPlugin {
    val id: PluginId = PluginId("throwing")
    override val name = "Throwing"
    override val appId: AppId
        get() = AppId("com.google.android.googlequicksearchbox")
    override val supportedCities: Iterable<CityModel>
        get() = listOf(CityModel(object : CityId {}, LatLng(0.0, 0.0), radius = Kilometers(Long.MAX_VALUE)))

    override fun findInLocation(location: Iterable<CityId>): List<MarkerModel> {
        Thread.sleep(1500)
        throw IllegalStateException()
    }
}
