package pl.ccki.szypwyp.goscooter.presentation

import pl.ccki.szypwyp.goscooter.domain.IconProvider
import javax.inject.Inject

class GoScooterIconProvider @Inject constructor() : IconProvider {
    override val icon: Int = R.drawable.is_goscooter
}
