package pl.ccki.szypwyp.blinkee.presentation

import pl.ccki.szypwyp.blinkee.domain.IconProvider
import javax.inject.Inject

class BlinkeeIconProvider @Inject constructor() : IconProvider {
    override val icon: Int = R.drawable.ic_map_marker
}
