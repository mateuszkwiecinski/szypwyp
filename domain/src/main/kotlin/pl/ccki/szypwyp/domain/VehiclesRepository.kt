package pl.ccki.szypwyp.domain

interface VehiclesRepository {
    fun get(param: SearchModel): List<MarkerModel>
}
