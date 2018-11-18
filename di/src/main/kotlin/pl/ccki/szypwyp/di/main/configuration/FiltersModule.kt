package pl.ccki.szypwyp.di.main.configuration

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pl.ccki.szypwyp.di.ViewModelKey
import pl.ccki.szypwyp.presentation.filters.vm.FiltersViewModel

@Module
abstract class FiltersModule {

    @IntoMap
    @Binds
    @ViewModelKey(FiltersViewModel::class)
    abstract fun filters(vm: FiltersViewModel): ViewModel
}
