package pl.ccki.szypwyp.vozilla.presentation

import pl.ccki.szypwyp.vozilla.domain.IconProvider
import javax.inject.Inject

class VozillaIconProvider @Inject constructor() : IconProvider {
    override val icon: Int = R.drawable.ic_vozilla
}
