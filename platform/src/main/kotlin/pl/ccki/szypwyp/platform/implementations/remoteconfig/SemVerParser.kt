package pl.ccki.szypwyp.platform.implementations.remoteconfig

import pl.ccki.szypwyp.domain.models.SemanticVersionModel
import java.util.regex.Pattern

private val pattern by lazy {
    Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)-?(beta(\\d*))?")
}

fun String.toSemanticVersion(): SemanticVersionModel {
    val matcher = pattern.matcher(this)
    if (!matcher.matches())
        throw IllegalArgumentException("Malformed FW version $this")

    val major = matcher.group(1).toInt()
    val minor = matcher.group(2).toInt()
    val rev = matcher.group(3).toInt()
    val beta = when {
        matcher.group(4) == null -> null
        matcher.group(5).isEmpty() -> 1
        else -> matcher.group(5).toInt()
    }
    return SemanticVersionModel(major, minor, rev, beta)
}


