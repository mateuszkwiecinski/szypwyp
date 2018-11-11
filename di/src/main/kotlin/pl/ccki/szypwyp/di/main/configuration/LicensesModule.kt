package pl.ccki.szypwyp.di.main.configuration

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pl.ccki.szypwyp.di.ViewModelKey
import pl.ccki.szypwyp.presentation.configuration.ui.LicensesViewModel

@Module
abstract class LicensesModule {

    @IntoMap
    @Binds
    @ViewModelKey(LicensesViewModel::class)
    abstract fun licenses(vm: LicensesViewModel): ViewModel
}
