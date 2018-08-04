package pl.ccki.szypwyp.blinkee.models

enum class Regions {
    Warszawa,
    Poznan,
    Krakow,
    Trojmiasto,
    Wroclaw,
    Lodz,
    Kolobrzeg,
    Lublin,
    Bialystok,
    Gizycko,
    Rzeszow,
    PowiatOstrowski,
    Budapest;

    internal val regionId: Int
        get() = when (this) {
            Warszawa -> 1
            Poznan -> 2
            Krakow -> 4
            Trojmiasto -> 5
            Wroclaw -> 6
            Lodz -> 7
            Kolobrzeg -> 8
            Lublin -> 9
            Bialystok -> 12
            Gizycko -> 13
            Rzeszow -> 17
            PowiatOstrowski -> 18
            Budapest -> 11
        }
}
