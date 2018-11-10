package pl.ccki.szypwyp.domain.providers

import pl.ccki.szypwyp.domain.models.AppId
import pl.ccki.szypwyp.domain.models.SemanticVersionModel

interface CurrentConfigProvider {
    val appVersion: SemanticVersionModel
    val appId: AppId
}
