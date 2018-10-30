package pl.ccki.szypwyp.goscooter.data

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Test
import pl.ccki.szypwyp.goscooter.data.config.GoScooterEndpoints
import pl.ccki.szypwyp.goscooter.data.config.GoScooterRetrofitFactory

class GoScooterRepositoryTest {

    @Test
    fun get() {
        val server = MockWebServer()
        server.enqueue(MockResponse().apply {
            setBody(page)
        })
        server.start()
        val endpoints = GoScooterRetrofitFactory.create(server.url("/").toString())
            .create(GoScooterEndpoints::class.java)
        val repository = RemoteGoScooterRepository(endpoints)

        val result = repository.getAll()

        Assert.assertTrue(result.isNotEmpty())
    }
}

private val page = """[
    {
        "DeviceTypeId": 4,
        "DeviceId": 105,
        "DeviceName": "DX037A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.133159,
        "DeviceLongitude": 17.066913,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611950000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 109,
        "DeviceName": "DX062A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.122265,
        "DeviceLongitude": 17.039912,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611949000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 110,
        "DeviceName": "DX016A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.091696,
        "DeviceLongitude": 16.984187,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611963000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 111,
        "DeviceName": "DX085A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.129114,
        "DeviceLongitude": 17.057429,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611972000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 112,
        "DeviceName": "DX038A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.140084,
        "DeviceLongitude": 17.022894,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611940000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 113,
        "DeviceName": "DX027A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.082291,
        "DeviceLongitude": 17.025852,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611936000)/",
        "Image": "blinkee_thief.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 114,
        "DeviceName": "DX019A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.139068,
        "DeviceLongitude": 17.080751,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611963000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 118,
        "DeviceName": "DX077A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.076522,
        "DeviceLongitude": 16.994068,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611936000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 119,
        "DeviceName": "DX058A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.125366,
        "DeviceLongitude": 16.961971,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611969000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 122,
        "DeviceName": "DX026A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.123724,
        "DeviceLongitude": 16.993485,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611968000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 124,
        "DeviceName": "DX030A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.090751,
        "DeviceLongitude": 16.998711,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611969000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 127,
        "DeviceName": "DX001A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.134508,
        "DeviceLongitude": 17.03708,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611973000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 128,
        "DeviceName": "DX002A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.138874,
        "DeviceLongitude": 16.93157,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611965000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 129,
        "DeviceName": "DX005A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.109458,
        "DeviceLongitude": 17.024389,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611939000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 130,
        "DeviceName": "DX007A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.093734,
        "DeviceLongitude": 17.04273,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611972000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 131,
        "DeviceName": "DX013A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.124912,
        "DeviceLongitude": 17.086009,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611951000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 132,
        "DeviceName": "DX018A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.112086,
        "DeviceLongitude": 16.988464,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611942000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 134,
        "DeviceName": "DX022A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.098798,
        "DeviceLongitude": 17.038311,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611934000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 135,
        "DeviceName": "DX017A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.095181,
        "DeviceLongitude": 16.940723,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611937000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 136,
        "DeviceName": "DX023A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.091276,
        "DeviceLongitude": 16.985923,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611947000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 137,
        "DeviceName": "DX095A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.127653,
        "DeviceLongitude": 16.97345,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611940000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 138,
        "DeviceName": "DX046A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.141142,
        "DeviceLongitude": 17.081796,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611970000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 141,
        "DeviceName": "DX049A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.113505,
        "DeviceLongitude": 17.012195,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611964000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 144,
        "DeviceName": "DX033A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.137591,
        "DeviceLongitude": 17.03166,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611949000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 145,
        "DeviceName": "DX044A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.137643,
        "DeviceLongitude": 17.033184,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611939000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 146,
        "DeviceName": "DX063A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.138862,
        "DeviceLongitude": 16.931472,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611942000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 147,
        "DeviceName": "DX098A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.086601,
        "DeviceLongitude": 17.038158,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611971000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 149,
        "DeviceName": "DX090A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.116412,
        "DeviceLongitude": 17.074343,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611969000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 151,
        "DeviceName": "DX091A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.14117,
        "DeviceLongitude": 17.081712,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611952000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 152,
        "DeviceName": "DX071A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.106784,
        "DeviceLongitude": 17.001608,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611967000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 153,
        "DeviceName": "DX004A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.140309,
        "DeviceLongitude": 16.967002,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611969000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 154,
        "DeviceName": "DX052A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.12115,
        "DeviceLongitude": 17.033617,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611940000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 155,
        "DeviceName": "DX081A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.092328,
        "DeviceLongitude": 17.061235,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611963000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 156,
        "DeviceName": "DX080A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.103418,
        "DeviceLongitude": 17.029548,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611943000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 158,
        "DeviceName": "DX015A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.129538,
        "DeviceLongitude": 16.96011,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611956000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 161,
        "DeviceName": "DX008A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.138382,
        "DeviceLongitude": 16.965328,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611950000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 164,
        "DeviceName": "DX076A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.123799,
        "DeviceLongitude": 16.972431,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611962000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 167,
        "DeviceName": "DX039A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.125572,
        "DeviceLongitude": 16.968422,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611956000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 168,
        "DeviceName": "DX003A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.08873,
        "DeviceLongitude": 17.023312,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611953000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 170,
        "DeviceName": "DX045A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.101056,
        "DeviceLongitude": 17.11035,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611956000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 171,
        "DeviceName": "DX010A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.12673,
        "DeviceLongitude": 16.947292,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611965000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 172,
        "DeviceName": "DX092A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.121652,
        "DeviceLongitude": 17.037164,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611968000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 173,
        "DeviceName": "DX083A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.133037,
        "DeviceLongitude": 17.030683,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611954000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 175,
        "DeviceName": "DX067A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.113688,
        "DeviceLongitude": 17.103069,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611966000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 177,
        "DeviceName": "DX073A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.124131,
        "DeviceLongitude": 17.055015,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611938000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 178,
        "DeviceName": "DX064A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.120142,
        "DeviceLongitude": 17.015928,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611967000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 179,
        "DeviceName": "DX097A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.126551,
        "DeviceLongitude": 16.996863,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611970000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 180,
        "DeviceName": "DX034A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.135806,
        "DeviceLongitude": 16.952724,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611953000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 181,
        "DeviceName": "DX006A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.126724,
        "DeviceLongitude": 16.947491,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611965000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 182,
        "DeviceName": "DX061A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.105444,
        "DeviceLongitude": 17.111307,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611940000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 185,
        "DeviceName": "DX089A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.088718,
        "DeviceLongitude": 17.023361,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611940000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 186,
        "DeviceName": "DX020A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.128806,
        "DeviceLongitude": 17.028474,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611942000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 189,
        "DeviceName": "DX065A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.114458,
        "DeviceLongitude": 16.99502,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611952000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 191,
        "DeviceName": "DX025A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.122036,
        "DeviceLongitude": 17.040014,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611948000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 193,
        "DeviceName": "DX059A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.13316,
        "DeviceLongitude": 16.950344,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611945000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 194,
        "DeviceName": "DX047A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.13712,
        "DeviceLongitude": 17.022047,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611950000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 197,
        "DeviceName": "DX009A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.097278,
        "DeviceLongitude": 16.982002,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611953000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 198,
        "DeviceName": "DX048A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.126799,
        "DeviceLongitude": 16.961963,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611948000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 202,
        "DeviceName": "DX031A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.101589,
        "DeviceLongitude": 17.001553,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611945000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 203,
        "DeviceName": "DX041A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.090782,
        "DeviceLongitude": 17.004331,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611963000)/",
        "Image": "blinkee_point_transparent.png"
    },
    {
        "DeviceTypeId": 4,
        "DeviceId": 204,
        "DeviceName": "DX087A",
        "DeviceNetworkKey": null,
        "DeviceLatitude": 51.132179,
        "DeviceLongitude": 16.948198,
        "DeviceAltitude": 0,
        "DevicePositionStamp": "/Date(1534611972000)/",
        "Image": "blinkee_thief.png"
    }
]""".trimMargin()
