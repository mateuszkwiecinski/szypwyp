package pl.ccki.szypwyp.di.main.map

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pl.ccki.szypwyp.di.FragmentScope
import pl.ccki.szypwyp.di.ViewModelKey
import pl.ccki.szypwyp.domain.persistences.InMemoryBehaviorSubjectBasedMapEventsPersistence
import pl.ccki.szypwyp.domain.persistences.MapEventsPersistence
import pl.ccki.szypwyp.presentation.map.vm.MapViewModel

@Module
abstract class MapModule {

    @IntoMap
    @Binds
    @ViewModelKey(MapViewModel::class)
    abstract fun map(vm: MapViewModel): ViewModel

    @FragmentScope
    @Binds
    abstract fun persistence(implementation: InMemoryBehaviorSubjectBasedMapEventsPersistence): MapEventsPersistence
}
