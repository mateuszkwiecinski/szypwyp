package pl.ccki.szypwyp.domain

import io.reactivex.Maybe

interface ServicesConfiguration {
    fun get(): Maybe<ServiceConfigurationModel>
}