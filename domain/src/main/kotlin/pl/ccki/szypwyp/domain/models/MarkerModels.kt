package pl.ccki.szypwyp.domain.models

inline class Percent(val value: Int)

inline class Kilometers(val value: Int)

operator fun Kilometers.compareTo(other: Kilometers): Int =
    value.compareTo(other.value)
