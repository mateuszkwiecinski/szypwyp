package pl.ccki.szypwyp.traficar.data.models

enum class Regions {
    Krakow,
    Warszawa,
    Wroclaw,
    Poznan,
    Gdansk,
    Katowice,
    Bydgoszcz,
    Lubin,
    Lodz;

    internal val regionId: Int
        get() = when (this) {
            Krakow -> 1
            Warszawa -> 2
            Wroclaw -> 3
            Poznan -> 4
            Gdansk -> 5
            Katowice -> 6
            Bydgoszcz -> 7
            Lubin -> 8
            Lodz -> 9
        }
}
