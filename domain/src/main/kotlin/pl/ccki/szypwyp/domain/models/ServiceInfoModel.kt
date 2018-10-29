package pl.ccki.szypwyp.domain.models

typealias ServiceId = String
typealias ServiceIcon = Int

data class ServiceInfoModel(val id: ServiceId, val icon: ServiceIcon? = null)
