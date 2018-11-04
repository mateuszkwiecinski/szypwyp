package pl.ccki.szypwyp.blinkee.data.models

import pl.ccki.szypwyp.blinkee.domain.models.BlinkeeRegion

internal val BlinkeeRegion.regionId: Int
    get() = when (this) {
        BlinkeeRegion.Warszawa -> 1
        BlinkeeRegion.Poznan -> 2
        BlinkeeRegion.Krakow -> 4
        BlinkeeRegion.Trojmiasto -> 5
        BlinkeeRegion.Wroclaw -> 6
        BlinkeeRegion.Lodz -> 7
        BlinkeeRegion.Kolobrzeg -> 8
        BlinkeeRegion.Lublin -> 9
        BlinkeeRegion.Bialystok -> 12
        BlinkeeRegion.Gizycko -> 13
        BlinkeeRegion.Rzeszow -> 17
        BlinkeeRegion.PowiatOstrowski -> 18
        BlinkeeRegion.Budapest -> 11
    }
