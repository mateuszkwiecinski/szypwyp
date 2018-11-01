package pl.ccki.szypwyp.domain.repositories

import pl.ccki.szypwyp.domain.models.PluginId

interface ServicesConfigurationRepository {
    var selected: Iterable<PluginId>?
}
