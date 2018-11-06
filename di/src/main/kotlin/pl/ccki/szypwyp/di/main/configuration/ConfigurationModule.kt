package pl.ccki.szypwyp.di.main.configuration

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pl.ccki.szypwyp.di.ViewModelKey
import pl.ccki.szypwyp.presentation.configuration.vm.ConfigurationViewModel

@Module
abstract class ConfigurationModule {

    @IntoMap
    @Binds
    @ViewModelKey(ConfigurationViewModel::class)
    abstract fun configuration(vm: ConfigurationViewModel): ViewModel
}
