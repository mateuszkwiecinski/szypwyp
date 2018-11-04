package pl.ccki.szypwyp.traficar.data.models

import pl.ccki.szypwyp.traficar.domain.models.TraficarRegion

internal val TraficarRegion.regionId: Int
    get() = when (this) {
        TraficarRegion.Krakow -> 1
        TraficarRegion.Warszawa -> 2
        TraficarRegion.Wroclaw -> 3
        TraficarRegion.Poznan -> 4
        TraficarRegion.Gdansk -> 5
        TraficarRegion.Katowice -> 6
        TraficarRegion.Bydgoszcz -> 7
        TraficarRegion.Lublin -> 8
        TraficarRegion.Lodz -> 9
    }
