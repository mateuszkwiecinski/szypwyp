package pl.ccki.szypwyp.blinkee

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

    val regionId : Int
        get() = when(this){
            Regions.Warszawa -> 1
            Regions.Poznan -> 2
            Regions.Krakow -> 4
            Regions.Trojmiasto -> 5
            Regions.Wroclaw -> 6
            Regions.Lodz -> 7
            Regions.Kolobrzeg -> 8
            Regions.Lublin -> 9
            Regions.Bialystok -> 12
            Regions.Gizycko -> 13
            Regions.Rzeszow -> 17
            Regions.PowiatOstrowski -> 18
            Regions.Budapest -> 11
        }
}