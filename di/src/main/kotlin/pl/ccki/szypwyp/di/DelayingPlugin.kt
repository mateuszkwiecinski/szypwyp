package pl.ccki.szypwyp.di

import pl.ccki.szypwyp.domain.models.CityId
import pl.ccki.szypwyp.domain.models.CityModel
import pl.ccki.szypwyp.domain.models.ExternalAppId
import pl.ccki.szypwyp.domain.models.Kilometers
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import java.lang.IllegalStateException

object DelayingPlugin : ExternalPlugin {
    val id: PluginId = object : PluginId {
        override val id: String
            get() = "someId"
    }
    override val appId: ExternalAppId
        get() = ExternalAppId("com.google.android.googlequicksearchbox")
    override val supportedCities: Iterable<CityModel>
        get() = listOf(CityModel(object : CityId {}, LatLng(0.0, 0.0), radius = Kilometers(Long.MAX_VALUE)))

    override fun findInLocation(location: CityId): List<MarkerModel> {
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
    val id: PluginId = object : PluginId {
        override val id: String
            get() = "throwing"
    }
    override val appId: ExternalAppId
        get() = ExternalAppId("com.google.android.googlequicksearchbox")
    override val supportedCities: Iterable<CityModel>
        get() = listOf(CityModel(object : CityId {}, LatLng(0.0, 0.0), radius = Kilometers(Long.MAX_VALUE)))

    override fun findInLocation(location: CityId): List<MarkerModel> {
        Thread.sleep(1500)
        throw IllegalStateException()
    }
}
