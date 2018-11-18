package pl.ccki.szypwyp.di.modules.presentation

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dagger.multibindings.IntoSet
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.presentation.interfaces.FilterViewsProvider
import pl.ccki.szypwyp.vozilla.domain.VozillaId
import pl.ccki.szypwyp.vozilla.presentation.filters.VozillaFilterViewsProvider

@Module
class FilterViewsModule {

    @IntoSet
    @Provides
    fun vozilla(id: VozillaId, provider: VozillaFilterViewsProvider): Pair<PluginId, FilterViewsProvider> =
        id to provider
}
