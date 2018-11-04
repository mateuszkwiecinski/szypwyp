@file:Suppress("unused")

package pl.ccki.szypwyp.domain.models

import pl.ccki.szypwyp.domain.services.ExternalPlugin

fun ExternalPlugin.cityWroclaw(id: CityId) = CityModel(id, LatLng(51.1079, 17.0385), Kilometers(50))

fun ExternalPlugin.cityWarsaw(id: CityId) = CityModel(id, LatLng(52.2297, 21.0122), Kilometers(50))

fun ExternalPlugin.cityKrakow(id: CityId) = CityModel(id, LatLng(50.0647, 19.9450), Kilometers(35))

fun ExternalPlugin.cityPoznan(id: CityId) = CityModel(id, LatLng(52.4064, 16.9252), Kilometers(50))

fun ExternalPlugin.cityLodz(id: CityId) = CityModel(id, LatLng(51.7592, 19.4560), Kilometers(40))

fun ExternalPlugin.cityTrojmiasto(id: CityId) = CityModel(id, LatLng(54.3520, 18.6466), Kilometers(80))

fun ExternalPlugin.cityKatowice(id: CityId) = CityModel(id, LatLng(50.2649, 19.0238), Kilometers(30))
fun ExternalPlugin.cityBydgoszcz(id: CityId) = CityModel(id, LatLng(53.1235, 18.0084), Kilometers(40))
fun ExternalPlugin.cityLublin(id: CityId) = CityModel(id, LatLng(51.2465, 22.5684), Kilometers(70))
