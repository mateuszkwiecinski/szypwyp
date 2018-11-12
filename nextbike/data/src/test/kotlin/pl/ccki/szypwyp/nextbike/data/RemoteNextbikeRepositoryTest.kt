package pl.ccki.szypwyp.nextbike.data

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertFalse
import org.junit.Ignore
import org.junit.Test
import pl.ccki.szypwyp.nextbike.data.config.NextbikeEndpoints
import pl.ccki.szypwyp.nextbike.data.config.NextbikeRetrofitFactory
import java.text.Normalizer

class RemoteNextbikeRepositoryTest {

//    @Test
//    fun getAll() {
//        val server = MockWebServer()
//        server.enqueue(MockResponse().apply {
//            setBody(response())
//        })
//        server.start()
//        val endpoints = NextbikeRetrofitFactory.create(server.url("/").toString())
//            .create(NextbikeEndpoints::class.java)
//        val repository = RemoteNextbikeRepository(endpoints)
//
//        val result = repository.getAll(NextbikeRegion.Wroclaw)
//
//        assertTrue(result.isNotEmpty())
//        result.first().let {
//            assertEquals(50.0808282066324, it.location.latitude, 0.000001)
//            assertEquals(8.218089938163757, it.location.longitude, 0.000001)
//        }
//    }

    @Ignore
    @Test
    fun iterate() {
        val endpoints = NextbikeRetrofitFactory.create("https://api.nextbike.net/")
            .create(NextbikeEndpoints::class.java)

        val all = (1..600).toList().map {
            Single.fromCallable {
                val result = endpoints.json(it).execute().body()!!
                if (it % 30 == 0) {
                    println(it)
                }
                it to result
            }
                .subscribeOn(Schedulers.computation())
        }

        val result = Single.merge(all)
            .subscribeOn(Schedulers.computation())
            .toList()
            .blockingGet()

        assertFalse(result.isEmpty())
    }
}

private fun String?.toName(): String = this.orEmpty()
    .replace(".", "")
    .replace(" ", "")
    .replace("-", "")
    .replace("(RL)", "")
    .replace("(", "")
    .replace(")", "")
    .let {
        Normalizer.normalize(it, Normalizer.Form.NFD).replace(Regex("[^\\p{ASCII}]"), "")
    }
    .capitalize()
