package pl.ccki.szypwyp.domain.repositories

import pl.ccki.szypwyp.domain.models.ServiceId

interface ServicesConfigurationRepository {
    var selected: Collection<ServiceId>?
}
