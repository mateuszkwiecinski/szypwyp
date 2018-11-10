package pl.ccki.szypwyp.platform.implementations.remoteconfig.models

import com.google.gson.annotations.SerializedName

class VersionsResponse(
    @SerializedName("minimum") val minimum: String?,
    @SerializedName("latest") val latest: String?
)
