package pl.ccki.szypwyp.domain.models

import org.junit.Test

import org.junit.Assert.*

class LatLngKtTest {

    @Test
    fun distanceTo() {
        val wroclaw = LatLng(51.10, 17.0333)
        val newYork = LatLng(40.7143, -74.0060)

        val back = wroclaw.distanceTo(newYork)
        val forth = wroclaw.distanceTo(newYork)

        assertEquals(back, forth)
        assertEquals(Kilometers(6677), back)
    }
}