package pl.ccki.szypwyp.domain.repositories

import pl.ccki.szypwyp.domain.models.PluginId

interface FiltersRepository {
    var selected: Iterable<PluginId>?
}
