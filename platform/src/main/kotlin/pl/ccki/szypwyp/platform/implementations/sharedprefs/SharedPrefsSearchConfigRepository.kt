package pl.ccki.szypwyp.platform.implementations.sharedprefs

import android.content.Context
import androidx.core.content.edit
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.repositories.SearchConfigRepository
import javax.inject.Inject

class SharedPrefsSearchConfigRepository @Inject constructor(
    context: Context
) : SearchConfigRepository {

    companion object {
        private const val PREFS_NAME = "ccki.config.search"
        private const val KEY_LATITUDE = "search.latitude"
        private const val KEY_LONGITUDE = "search.longitude"
    }

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override var target: LatLng?
        get() {
            val latitude = prefs.takeIf {
                it.contains(KEY_LATITUDE)
            }
                ?.getLong(KEY_LATITUDE, 0L)
                ?.let(Double.Companion::fromBits)
                ?: return null

            val longitude = prefs.takeIf {
                it.contains(KEY_LONGITUDE)
            }
                ?.getLong(KEY_LONGITUDE, 0L)
                ?.let(Double.Companion::fromBits)
                ?: return null

            return LatLng(latitude, longitude)
        }
        set(value) {
            prefs.edit {
                value?.let {
                    prefs.edit {
                        putLong(KEY_LATITUDE, it.latitude.toBits())
                        putLong(KEY_LONGITUDE, it.longitude.toBits())
                    }
                } ?: prefs.edit {
                    remove(KEY_LATITUDE)
                    remove(KEY_LONGITUDE)
                }
            }
        }
}
