package pl.ccki.szypwyp.platform.implementations.sharedprefs

import android.content.Context
import androidx.core.content.edit
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.repositories.FiltersRepository
import javax.inject.Inject

class SharedPrefsFiltersRepository @Inject constructor(
    context: Context
) : FiltersRepository {

    companion object {
        private const val PREFS_NAME = "ccki.config.filters"
        private const val KEY_ID = "filter.id"
    }

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override var disabled: Set<PluginId>
        get() = prefs.getStringSet(KEY_ID, emptySet()).orEmpty().map { PluginId(it) }.toSet()
        set(value) {
            prefs.edit {
                putStringSet(KEY_ID, value.map { it.id }.toSet())
            }
        }
}
