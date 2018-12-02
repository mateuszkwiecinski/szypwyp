package pl.ccki.szypwyp.di.modules.presentation

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.presentation.interfaces.FilterViewsProvider
import pl.ccki.szypwyp.vozilla.di.Vozilla

@Module
class FilterViewsModule {

    @IntoSet
    @Provides
    fun vozilla(@Vozilla id: PluginId, @Vozilla provider: FilterViewsProvider): Pair<PluginId, FilterViewsProvider> =
        id to provider
}
