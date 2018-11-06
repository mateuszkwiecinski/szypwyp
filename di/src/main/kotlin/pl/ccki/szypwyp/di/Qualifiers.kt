package pl.ccki.szypwyp.di

import javax.inject.Qualifier

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class DebugObject

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ProductionObject
