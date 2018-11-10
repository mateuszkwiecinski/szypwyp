package pl.ccki.szypwyp.domain.models

data class VersionsModel(
    val minimum: SemanticVersionModel,
    val latest: SemanticVersionModel
)
