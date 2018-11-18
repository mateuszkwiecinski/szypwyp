package pl.ccki.szypwyp.domain.models

interface PluginId : Comparable<PluginId> {
    val id: String
    override fun compareTo(other: PluginId): Int = id.compareTo(other.id)
}
