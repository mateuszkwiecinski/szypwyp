package pl.ccki.szypwyp.platform.internal.di

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.Module
import dagger.Provides

@Module
class LocationModule {

    @Provides
    fun fusedLocationClient(context : Context) = FusedLocationProviderClient(context)
}
