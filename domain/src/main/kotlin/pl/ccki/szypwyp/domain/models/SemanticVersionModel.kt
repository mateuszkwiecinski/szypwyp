package pl.ccki.szypwyp.domain.models

data class SemanticVersionModel(
    val major: Int,
    val minor: Int,
    val rev: Int,
    val beta: Int? = null
)

operator fun SemanticVersionModel.compareTo(other: SemanticVersionModel): Int {
    val majorCompare = major.compareTo(other.major)
    return if (majorCompare == 0) {
        val minorCompare = minor.compareTo(other.minor)
        if (minorCompare == 0) {
            val revCompare = rev.compareTo(other.rev)
            if (revCompare == 0) {
                (beta ?: Integer.MAX_VALUE).compareTo((other.beta ?: Integer.MAX_VALUE))
            } else {
                revCompare
            }
        } else {
            minorCompare
        }
    } else {
        majorCompare
    }
}
