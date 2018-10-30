package pl.ccki.szypwyp.traficar.presentation

import pl.ccki.szypwyp.traficar.domain.IconProvider
import javax.inject.Inject

class TraficarIconProvider @Inject constructor() : IconProvider {
    override val icon: Int = R.drawable.ic_traficar
}
