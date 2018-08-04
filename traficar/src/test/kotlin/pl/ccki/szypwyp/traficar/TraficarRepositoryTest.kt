package pl.ccki.szypwyp.traficar

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Test
import pl.ccki.szypwyp.traficar.config.TraficarEndpoints
import pl.ccki.szypwyp.traficar.config.TraficarRetrofitFactory

class TraficarRepositoryTest {

    @Test
    fun getAll() {
        val server = MockWebServer()
        server.enqueue(MockResponse().apply {
            setBody(response)
        })
        server.start()
        val endpoints = TraficarRetrofitFactory.create(server.url("/").toString())
            .create(TraficarEndpoints::class.java)
        val repository = TraficarRepository(endpoints)

        val result = repository.getAll()

        Assert.assertTrue(result.isNotEmpty())
    }
}

private val response = """{
    "cars": [
        {
            "id": 34109,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WGM28959",
            "vin": "VF15RB20A58250401",
            "orderNumber": 1027,
            "fuel": 26,
            "latitude": 50.03333499125615,
            "location": "Kraków, w pobliżu Norymberska",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.906798071866433,
            "vehicleName": "WGM28959",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 30329,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WGM14576",
            "vin": "VF15RB20A57456247",
            "orderNumber": 306,
            "fuel": 36,
            "latitude": 50.04338219952523,
            "location": "Kraków, w pobliżu Księcia Józefa",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.83176673628655,
            "vehicleName": "WGM14576",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 24607,
            "model": "OPEL Corsa 1,4",
            "regNumber": "WND53092",
            "vin": "W0L0XEP68G4219136",
            "orderNumber": 160,
            "fuel": 21.6,
            "latitude": 50.083824,
            "location": "Kraków, w pobliżu Celarowska",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.957494,
            "vehicleName": "WND53092",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 30296,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WGM14094",
            "vin": "VF15RB20A57585408",
            "orderNumber": 211,
            "fuel": 44,
            "latitude": 49.99221725245267,
            "location": "Wieliczka, w pobliżu Reformacka",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 20.0562682241157,
            "vehicleName": "WGM14094",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 28922,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WL4359J",
            "vin": "VF15RB20A57254581",
            "orderNumber": 236,
            "fuel": 42,
            "latitude": 50.048805369726225,
            "location": "Kraków, w pobliżu Gazowa",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.946947631355062,
            "vehicleName": "WL4359J",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 30298,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WGM14456",
            "vin": "VF15RB20A57585448",
            "orderNumber": 305,
            "fuel": 23,
            "latitude": 50.016737631573015,
            "location": "Kraków, w pobliżu Złocieniowa",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 20.050162034997708,
            "vehicleName": "WGM14456",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 34120,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WGM28950",
            "vin": "VF15RB20A58250373",
            "orderNumber": 1038,
            "fuel": 37,
            "latitude": 50.078909428493056,
            "location": "Kraków, w pobliżu Aleja Solidarności",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 20.067133347537897,
            "vehicleName": "WGM28950",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 28944,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WL4754J",
            "vin": "VF15RB20A57436557",
            "orderNumber": 258,
            "fuel": 23,
            "latitude": 50.10222769431948,
            "location": "Kraków, w pobliżu Arcybiskupa Zygmunta Szczęsnego Felińskiego",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.968186104579214,
            "vehicleName": "WL4754J",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 34300,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WGM28893",
            "vin": "VF15RB20A58673511",
            "orderNumber": 1218,
            "fuel": 33,
            "latitude": 50.04159610505727,
            "location": "Kraków, w pobliżu Zakręt",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.833610887362454,
            "vehicleName": "WGM28893",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 30295,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WGM14453",
            "vin": "VF15RB20A57585403",
            "orderNumber": 212,
            "fuel": 33,
            "latitude": 50.06213789898609,
            "location": "Kraków, w pobliżu Rzepichy",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.832799802398515,
            "vehicleName": "WGM14453",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 30284,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WGM14531",
            "vin": "VF15RB20A57585383",
            "orderNumber": 293,
            "fuel": 45,
            "latitude": 50.04208446358293,
            "location": "Kraków, w pobliżu 780",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.812095791476914,
            "vehicleName": "WGM14531",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 34287,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WX97256",
            "vin": "VF15RB20A58673526",
            "orderNumber": 1205,
            "fuel": 41,
            "latitude": 50.06589791812418,
            "location": "Kraków, w pobliżu Bosacka",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.950475424061374,
            "vehicleName": "WX97256",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 34143,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WX97258",
            "vin": "VF15RB20A58250473",
            "orderNumber": 1061,
            "fuel": 25,
            "latitude": 50.0866890139577,
            "location": "Kraków, w pobliżu Krowoderskich Zuchów",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.93148920571603,
            "vehicleName": "WX97258",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 30261,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WGM14539",
            "vin": "VF15RB20A57585335",
            "orderNumber": 271,
            "fuel": 32,
            "latitude": 50.02208225459855,
            "location": "Kraków, w pobliżu Topazowa",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 20.048022478240203,
            "vehicleName": "WGM14539",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 34133,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WX97519",
            "vin": "VF15RB20A58250567",
            "orderNumber": 1051,
            "fuel": 26,
            "latitude": 50.004118857081416,
            "location": "Kraków, w pobliżu Rżącka",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.987792162591738,
            "vehicleName": "WX97519",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 30282,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WGM14452",
            "vin": "VF15RB20A57585378",
            "orderNumber": 291,
            "fuel": 35,
            "latitude": 50.08528199498868,
            "location": "Kraków, w pobliżu Stadionowa",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 20.03941131724411,
            "vehicleName": "WGM14452",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 38613,
            "model": "RENAULT Kangoo 1,5 dCi ON 2x4 MT",
            "regNumber": "WGM38796",
            "vin": "VF1FW51H160222948",
            "orderNumber": 9033,
            "fuel": 35,
            "latitude": 50.088939561289216,
            "location": "Kraków, w pobliżu Josepha Conrada",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.898451580700677,
            "vehicleName": "WGM38796",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 28913,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WL4366J",
            "vin": "VF15RB20A57254479",
            "orderNumber": 227,
            "fuel": 40,
            "latitude": 50.068998824007366,
            "location": "Kraków, w pobliżu Księdza Władysława Bandurskiego",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.960365537979552,
            "vehicleName": "WL4366J",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 28917,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WL4356J",
            "vin": "VF15RB20A57254486",
            "orderNumber": 231,
            "fuel": 35,
            "latitude": 49.9932588563011,
            "location": "Kraków, w pobliżu Juliusza Osterwy",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.985043011450806,
            "vehicleName": "WL4356J",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 34295,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WGM28937",
            "vin": "VF15RB20A58673517",
            "orderNumber": 1213,
            "fuel": 21,
            "latitude": 50.06953499385721,
            "location": "Kraków, w pobliżu Ostatnia",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.97869947325916,
            "vehicleName": "WGM28937",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 28937,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WL4729J",
            "vin": "VF15RB20A57254472",
            "orderNumber": 251,
            "fuel": 16,
            "latitude": 50.029445198523824,
            "location": "Kraków, w pobliżu Wiktora Heltmana",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.96782239700591,
            "vehicleName": "WL4729J",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 28916,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WL4737J",
            "vin": "VF15RB20A57254484",
            "orderNumber": 230,
            "fuel": 44,
            "latitude": 50.04034959553375,
            "location": "Kraków, w pobliżu Łutnia",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 20.068407177818106,
            "vehicleName": "WL4737J",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 22953,
            "model": "OPEL Corsa 1,4",
            "regNumber": "KR2L260",
            "vin": "W0L0XEP68G4056437",
            "orderNumber": 101,
            "fuel": 26.55,
            "latitude": 50.061294,
            "location": "Kraków, w pobliżu Stanisława Tondosa",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.891943,
            "vehicleName": "KR2L260",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 24573,
            "model": "OPEL Corsa 1,4",
            "regNumber": "WND53052",
            "vin": "W0L0XEP68G4217344",
            "orderNumber": 194,
            "fuel": 35.55,
            "latitude": 50.055217,
            "location": "Kraków, w pobliżu Masarska",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.954433,
            "vehicleName": "WND53052",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 34291,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WGM28897",
            "vin": "VF15RB20A58673521",
            "orderNumber": 1209,
            "fuel": 35,
            "latitude": 50.0425933126761,
            "location": "Kraków, w pobliżu Parkowa",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.950830593898214,
            "vehicleName": "WGM28897",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 24585,
            "model": "OPEL Corsa 1,4",
            "regNumber": "WND53074",
            "vin": "W0L0XEP68G4217853",
            "orderNumber": 182,
            "fuel": 42.75,
            "latitude": 49.984661,
            "location": "Kraków, w pobliżu Zakopiańska",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.91246,
            "vehicleName": "WND53074",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 34176,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WGM28878",
            "vin": "VF15RB20A58250361",
            "orderNumber": 1094,
            "fuel": 28,
            "latitude": 50.04212544471795,
            "location": "Kraków, w pobliżu 780",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.811995046186656,
            "vehicleName": "WGM28878",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 24657,
            "model": "OPEL Corsa 1,4",
            "regNumber": "WND53252",
            "vin": "W0L0XEP68G4223091",
            "orderNumber": 110,
            "fuel": 45,
            "latitude": 50.085155,
            "location": "Kraków, w pobliżu Osiedle Złota Podkowa",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.879426,
            "vehicleName": "WND53252",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 34247,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WGM28445",
            "vin": "VF15RB20A58673573",
            "orderNumber": 1165,
            "fuel": 24,
            "latitude": 50.07610222074418,
            "location": "Kraków, w pobliżu Lucjana Rydla",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.901472231861117,
            "vehicleName": "WGM28445",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 34294,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WGM28936",
            "vin": "VF15RB20A58673518",
            "orderNumber": 1212,
            "fuel": 34,
            "latitude": 50.077335069889365,
            "location": "Kraków, w pobliżu Kamienna",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.9473489049688,
            "vehicleName": "WGM28936",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 34167,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WGM28486",
            "vin": "VF15RB20A58250380",
            "orderNumber": 1085,
            "fuel": 38,
            "latitude": 49.997958026450064,
            "location": "Kraków, w pobliżu Borowinowa",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.93845429112215,
            "vehicleName": "WGM28486",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 34286,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WX97255",
            "vin": "VF15RB20A58673527",
            "orderNumber": 1204,
            "fuel": 38,
            "latitude": 50.07763559821285,
            "location": "Kraków, w pobliżu Kąt",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.962394104163046,
            "vehicleName": "WX97255",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 34258,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WGM28814",
            "vin": "VF15RB20A58673560",
            "orderNumber": 1176,
            "fuel": 14,
            "latitude": 50.04159610505727,
            "location": "Kraków, w pobliżu Stanisława Smolki",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.94230139517216,
            "vehicleName": "WGM28814",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 30260,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WGM14581",
            "vin": "VF15RB20A57456305",
            "orderNumber": 270,
            "fuel": 39,
            "latitude": 50.01959948083525,
            "location": "Kraków, w pobliżu Zakopiańska",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.932563252963014,
            "vehicleName": "WGM14581",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 32503,
            "model": "RENAULT Kangoo 1,5 dCi ON 2x4 MT",
            "regNumber": "WGM20234",
            "vin": "VF1FW51H158410483",
            "orderNumber": 9002,
            "fuel": 46,
            "latitude": 50.08876880655997,
            "location": "Kraków, w pobliżu Josepha Conrada",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.898591599578662,
            "vehicleName": "WGM20234",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 34276,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WGM28413",
            "vin": "VF15RB20A58673540",
            "orderNumber": 1194,
            "fuel": 28,
            "latitude": 50.068903201358985,
            "location": "Kraków, w pobliżu Niezapominajek",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.84472019004748,
            "vehicleName": "WGM28413",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 34288,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WGM28896",
            "vin": "VF15RB20A58673525",
            "orderNumber": 1206,
            "fuel": 12,
            "latitude": 50.064808502951564,
            "location": "Kraków, w pobliżu Kazimierza Wyżgi",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.836175623395793,
            "vehicleName": "WGM28896",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 24613,
            "model": "OPEL Corsa 1,4",
            "regNumber": "WND53201",
            "vin": "W0L0XEP68G4219502",
            "orderNumber": 154,
            "fuel": 18.9,
            "latitude": 50.070571,
            "location": "Kraków, w pobliżu Armii Krajowej",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.90133,
            "vehicleName": "WND53201",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 34121,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WGM28948",
            "vin": "VF15RB20A58250370",
            "orderNumber": 1039,
            "fuel": 32,
            "latitude": 50.04740859604096,
            "location": "Kraków, w pobliżu Wioślarska",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.90295438291101,
            "vehicleName": "WGM28948",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 28911,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WL4283J",
            "vin": "VF15RB20A57254477",
            "orderNumber": 225,
            "fuel": 41,
            "latitude": 50.003046517381726,
            "location": "Kraków, w pobliżu Jana Hallera",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.99360123848083,
            "vehicleName": "WL4283J",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 30269,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WGM14536",
            "vin": "VF15RB20A57585353",
            "orderNumber": 278,
            "fuel": 22,
            "latitude": 49.98750783701995,
            "location": "Kraków, w pobliżu Macieja Dębskiego",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.903550316916093,
            "vehicleName": "WGM14536",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 22954,
            "model": "OPEL Corsa 1,4",
            "regNumber": "KR2L386",
            "vin": "W0L0XEP68G4076808",
            "orderNumber": 100,
            "fuel": 18.45,
            "latitude": 50.020214,
            "location": "Kraków, w pobliżu Mariana Domagały",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 20.050067,
            "vehicleName": "KR2L386",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 24632,
            "model": "OPEL Corsa 1,4",
            "regNumber": "WND53224",
            "vin": "W0L0XEP68G4220717",
            "orderNumber": 135,
            "fuel": 41.4,
            "latitude": 50.039138,
            "location": "Kraków, w pobliżu Osikowa",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 20.073745,
            "vehicleName": "WND53224",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 30250,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WGM14553",
            "vin": "VF15RB20A57456295",
            "orderNumber": 261,
            "fuel": 44,
            "latitude": 50.1011075432956,
            "location": "Kraków, w pobliżu Arcybiskupa Zygmunta Szczęsnego Felińskiego",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.96846784988248,
            "vehicleName": "WGM14553",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 34298,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WGM28892",
            "vin": "VF15RB20A58673514",
            "orderNumber": 1216,
            "fuel": 23,
            "latitude": 50.10077969421543,
            "location": "Kraków, w pobliżu Niebyła",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 20.060427809320238,
            "vehicleName": "WGM28892",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 24656,
            "model": "OPEL Corsa 1,4",
            "regNumber": "WND53253",
            "vin": "W0L0XEP68G4223071",
            "orderNumber": 111,
            "fuel": 37.8,
            "latitude": 49.987712,
            "location": "Kraków, w pobliżu Tytusa Chałubińskiego",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.941444,
            "vehicleName": "WND53253",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 34297,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WGM28847",
            "vin": "VF15RB20A58673515",
            "orderNumber": 1215,
            "fuel": 26,
            "latitude": 50.01880717889153,
            "location": "Kraków, w pobliżu Henryka i Karola Czeczów",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 20.05677365811428,
            "vehicleName": "WGM28847",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 24635,
            "model": "OPEL Corsa 1,4",
            "regNumber": "WND53221",
            "vin": "W0L0XEP68G4220786",
            "orderNumber": 132,
            "fuel": 27.45,
            "latitude": 50.084144,
            "location": "Kraków, w pobliżu Włosika Bogdana",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 20.029045,
            "vehicleName": "WND53221",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 30294,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WGM14528",
            "vin": "VF15RB20A57585400",
            "orderNumber": 209,
            "fuel": 32,
            "latitude": 50.10213548676568,
            "location": "Kraków, w pobliżu Powstańców",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.98428998309481,
            "vehicleName": "WGM14528",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 34135,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WX97520",
            "vin": "VF15RB20A58250484",
            "orderNumber": 1053,
            "fuel": 32,
            "latitude": 50.05892087988701,
            "location": "Kraków, w pobliżu Rzepichy",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.829222490820722,
            "vehicleName": "WX97520",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 24646,
            "model": "OPEL Corsa 1,4",
            "regNumber": "WND71705",
            "vin": "W0L0XEP68G4221402",
            "orderNumber": 121,
            "fuel": 36.9,
            "latitude": 50.016395,
            "location": "Kraków, w pobliżu Majora Henryka Sucharskiego",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 20.049985,
            "vehicleName": "WND71705",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 35692,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WGM34344",
            "vin": "VF15RB20A59564524",
            "orderNumber": 1240,
            "fuel": 41,
            "latitude": 50.0465787280568,
            "location": "Kraków, w pobliżu Krakowska",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.94395259340401,
            "vehicleName": "WGM34344",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 28932,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WL4239J",
            "vin": "VF15RB20A57254458",
            "orderNumber": 244,
            "fuel": 32,
            "latitude": 50.01354110304145,
            "location": "Kraków, w pobliżu Jana Kantego Federowicza",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.883805947572878,
            "vehicleName": "WL4239J",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 34164,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WGM28964",
            "vin": "VF15RB20A58250391",
            "orderNumber": 1082,
            "fuel": 23,
            "latitude": 49.981056723348864,
            "location": "Wieliczka, w pobliżu Fryderyka Chopina",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 20.066013196514014,
            "vehicleName": "WGM28964",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 34107,
            "model": "RENAULT Clio 0,9",
            "regNumber": "WX97389",
            "vin": "VF15RB20A58250405",
            "orderNumber": 1025,
            "fuel": 29,
            "latitude": 50.07679206985035,
            "location": "Kraków, w pobliżu Jerzego Szablowskiego",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 19.892152438738634,
            "vehicleName": "WX97389",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        },
        {
            "id": 24583,
            "model": "OPEL Corsa 1,4",
            "regNumber": "WND53049",
            "vin": "W0L0XEP68G4217730",
            "orderNumber": 184,
            "fuel": 18.45,
            "latitude": 50.055374,
            "location": "Kraków, w pobliżu Michała Wołodyjowskiego",
            "distanceAccumulated": 1000,
            "distanceCounter": 1000,
            "longitude": 20.042654,
            "vehicleName": "WND53049",
            "priceList": {
                "kmPrice": 0.8,
                "stopPrice": 0.1,
                "drivingPrice": 0.5
            }
        }
    ],
    "shapeId": 1
}"""