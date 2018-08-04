package pl.ccki.szypwyp.domain

import io.reactivex.Maybe

interface ServicesConfigurationStorage {
    fun get(): Maybe<ServiceConfigurationModel>
}