package pl.ccki.szypwyp.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import pl.ccki.szypwyp.presentation.configuration.vm.ConfigurationViewModel
import pl.ccki.szypwyp.presentation.map.vm.MapViewModel
import kotlin.reflect.KClass

@Module
abstract class ViewModelsModule {

    @IntoMap
    @Binds
    @ViewModelKey(MapViewModel::class)
    abstract fun map(vm: MapViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(ConfigurationViewModel::class)
    abstract fun configuration(vm: ConfigurationViewModel): ViewModel
}

@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
