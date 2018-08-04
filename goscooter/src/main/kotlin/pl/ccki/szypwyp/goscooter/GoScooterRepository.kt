package pl.ccki.szypwyp.goscooter

import org.jsoup.Jsoup
import pl.ccki.szypwyp.domain.LatLng
import pl.ccki.szypwyp.domain.MarkerModel
import pl.ccki.szypwyp.domain.ServiceType
import pl.ccki.szypwyp.goscooter.config.GoScooterEndpoints
import java.util.regex.Pattern

class GoScooterRepository(
    private val endpoint: GoScooterEndpoints,
    private val apiUrl: String
) {
    companion object {
        private val pattern by lazy {
            Pattern.compile("(LatLng\\()(.+),\\s(.+)\\);\\n\\s+.+\\s+.+=\\s'(.+)';\\s+.+'(.+)';")
        }
    }

    fun getAll(): List<MarkerModel> {
        val response = endpoint.get().execute()
        val raw = response.body()
        val parsed = Jsoup.parse(raw?.byteStream(), "UTF-8", apiUrl)
        val element = parsed.body().getElementsByTag("script").first {
            it.html().contains("initMap")
        }.html()
        val m = pattern.matcher(element)
        val result = mutableListOf<MarkerModel>()
        while (m.find()) {
            if (m.groupCount() == 5) {
                val lat = m.group(2).toDoubleOrNull() ?: continue
                val lng = m.group(3).toDoubleOrNull() ?: continue
                val desc = m.group(4) ?: continue
                val range = m.group(5)
                result += MarkerModel(
                    id = desc,
                    location = LatLng(lat, lng),
                    type = ServiceType.GoScooter
                )
            }
        }

        return result
    }
}
