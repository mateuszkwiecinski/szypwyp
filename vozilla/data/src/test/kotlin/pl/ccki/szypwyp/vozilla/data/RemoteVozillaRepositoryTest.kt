package pl.ccki.szypwyp.vozilla.data

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import pl.ccki.szypwyp.vozilla.data.config.VozillaEndpoints
import pl.ccki.szypwyp.vozilla.data.config.VozillaRetrofitFactory

class RemoteVozillaRepositoryTest {
    @Test
    fun getAll() {
        val server = MockWebServer()
        server.enqueue(MockResponse().apply {
            setBody(response)
        })
        server.start()
        val endpoints = VozillaRetrofitFactory.create(server.url("/").toString())
            .create(VozillaEndpoints::class.java)
        val repository = RemoteVozillaRepository(endpoints)

        val result = repository.getAll()

        assertTrue(result.isNotEmpty())
        result.first().let {
            assertEquals(51.144344, it.location.latitude, 0.000001)
            assertEquals(17.071172, it.location.longitude, 0.000001)
        }
    }
}

private const val response = """{
    "objects": [
        {
            "discriminator": "vehicle",
            "platesNumber": "DW9R181",
            "sideNumber": "181",
            "color": "Biały",
            "type": "CAR",
            "picture": {
                "id": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "name": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "extension": null
            },
            "rangeKm": 38,
            "batteryLevelPct": 25,
            "reservationEnd": null,
            "status": "AVAILABLE",
            "locationDescription": null,
            "id": "e009f35e-a5d7-488c-bcc2-6ec51534c3d3",
            "name": "NISSAN Leaf",
            "description": null,
            "location": {
                "latitude": 51.144344,
                "longitude": 17.071172
            },
            "metadata": null
        },
        {
            "discriminator": "vehicle",
            "platesNumber": "DW9R033",
            "sideNumber": "033",
            "color": "Biały",
            "type": "CAR",
            "picture": {
                "id": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "name": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "extension": null
            },
            "rangeKm": 103,
            "batteryLevelPct": 57,
            "reservationEnd": null,
            "status": "AVAILABLE",
            "locationDescription": null,
            "id": "7338c044-ef9b-48f6-b219-7426ccac586f",
            "name": "NISSAN Leaf",
            "description": null,
            "location": {
                "latitude": 51.14896,
                "longitude": 16.880214
            },
            "metadata": null
        },
        {
            "discriminator": "vehicle",
            "platesNumber": "DW9R158",
            "sideNumber": "158",
            "color": "Biały",
            "type": "CAR",
            "picture": {
                "id": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "name": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "extension": null
            },
            "rangeKm": 127,
            "batteryLevelPct": 62,
            "reservationEnd": null,
            "status": "AVAILABLE",
            "locationDescription": null,
            "id": "25a21c55-020d-45db-b2b4-5f816ac3cd32",
            "name": "NISSAN Leaf",
            "description": null,
            "location": {
                "latitude": 51.134155,
                "longitude": 17.054727
            },
            "metadata": null
        },
        {
            "discriminator": "vehicle",
            "platesNumber": "DW9R149",
            "sideNumber": "149",
            "color": "Biały",
            "type": "CAR",
            "picture": {
                "id": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "name": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "extension": null
            },
            "rangeKm": 172,
            "batteryLevelPct": 97,
            "reservationEnd": null,
            "status": "AVAILABLE",
            "locationDescription": null,
            "id": "6af92847-9e34-4a27-a6c2-8c4c6e124456",
            "name": "NISSAN Leaf",
            "description": null,
            "location": {
                "latitude": 51.116519,
                "longitude": 17.073581
            },
            "metadata": null
        },
        {
            "discriminator": "vehicle",
            "platesNumber": "DW9R112",
            "sideNumber": "112",
            "color": "Biały",
            "type": "CAR",
            "picture": {
                "id": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "name": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "extension": null
            },
            "rangeKm": 37,
            "batteryLevelPct": 23,
            "reservationEnd": null,
            "status": "AVAILABLE",
            "locationDescription": null,
            "id": "51dcdd55-f6b4-4516-8ef8-b9ecef8933a2",
            "name": "NISSAN Leaf",
            "description": null,
            "location": {
                "latitude": 51.117843,
                "longitude": 17.044969
            },
            "metadata": null
        },
        {
            "discriminator": "vehicle",
            "platesNumber": "DW9R028",
            "sideNumber": "028",
            "color": "Biały",
            "type": "CAR",
            "picture": {
                "id": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "name": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "extension": null
            },
            "rangeKm": 127,
            "batteryLevelPct": 78,
            "reservationEnd": null,
            "status": "AVAILABLE",
            "locationDescription": null,
            "id": "5e5ee33d-2e60-4f44-b72e-c40b6efb59e9",
            "name": "NISSAN Leaf",
            "description": null,
            "location": {
                "latitude": 51.116958,
                "longitude": 17.04317
            },
            "metadata": null
        },
        {
            "discriminator": "vehicle",
            "platesNumber": "DW9R046",
            "sideNumber": "046",
            "color": "Biały",
            "type": "CAR",
            "picture": {
                "id": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "name": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "extension": null
            },
            "rangeKm": 118,
            "batteryLevelPct": 61,
            "reservationEnd": "2018-08-04T13:18:44.128",
            "status": "RESERVED",
            "locationDescription": null,
            "id": "35501b95-5880-4edf-b48a-f894599d89e5",
            "name": "NISSAN Leaf",
            "description": null,
            "location": {
                "latitude": 51.097084,
                "longitude": 16.936019
            },
            "metadata": null
        },
        {
            "discriminator": "vehicle",
            "platesNumber": "DW9R099",
            "sideNumber": "099",
            "color": "Biały",
            "type": "CAR",
            "picture": {
                "id": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "name": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "extension": null
            },
            "rangeKm": 135,
            "batteryLevelPct": 85,
            "reservationEnd": null,
            "status": "AVAILABLE",
            "locationDescription": null,
            "id": "6565524f-5827-420b-8e3d-bbcd1281e3d3",
            "name": "NISSAN Leaf",
            "description": null,
            "location": {
                "latitude": 51.153461,
                "longitude": 16.862379
            },
            "metadata": null
        },
        {
            "discriminator": "vehicle",
            "platesNumber": "DW9R081",
            "sideNumber": "081",
            "color": "Biały",
            "type": "CAR",
            "picture": {
                "id": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "name": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "extension": null
            },
            "rangeKm": 94,
            "batteryLevelPct": 54,
            "reservationEnd": null,
            "status": "AVAILABLE",
            "locationDescription": null,
            "id": "52299a19-55c7-4262-b2fc-7099f2b26210",
            "name": "NISSAN Leaf",
            "description": null,
            "location": {
                "latitude": 51.112655,
                "longitude": 17.091772
            },
            "metadata": null
        },
        {
            "discriminator": "vehicle",
            "platesNumber": "DW9R079",
            "sideNumber": "079",
            "color": "Biały",
            "type": "CAR",
            "picture": {
                "id": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "name": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "extension": null
            },
            "rangeKm": 154,
            "batteryLevelPct": 83,
            "reservationEnd": null,
            "status": "AVAILABLE",
            "locationDescription": null,
            "id": "881b76df-eebc-46a0-8518-1813ce15f268",
            "name": "NISSAN Leaf",
            "description": null,
            "location": {
                "latitude": 51.089672,
                "longitude": 17.045574
            },
            "metadata": null
        },
        {
            "discriminator": "vehicle",
            "platesNumber": "DW9R078",
            "sideNumber": "078",
            "color": "Biały",
            "type": "CAR",
            "picture": {
                "id": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "name": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "extension": null
            },
            "rangeKm": 100,
            "batteryLevelPct": 61,
            "reservationEnd": null,
            "status": "AVAILABLE",
            "locationDescription": null,
            "id": "8d754b8e-a95f-431a-94ab-e0b5d86523e5",
            "name": "NISSAN Leaf",
            "description": null,
            "location": {
                "latitude": 51.145233,
                "longitude": 17.150508
            },
            "metadata": null
        },
        {
            "discriminator": "vehicle",
            "platesNumber": "DW9R117",
            "sideNumber": "117",
            "color": "Biały",
            "type": "CAR",
            "picture": {
                "id": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "name": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "extension": null
            },
            "rangeKm": 87,
            "batteryLevelPct": 59,
            "reservationEnd": null,
            "status": "AVAILABLE",
            "locationDescription": null,
            "id": "64003c31-d877-4923-a2b7-e6631bcf7bb4",
            "name": "NISSAN Leaf",
            "description": null,
            "location": {
                "latitude": 51.157402,
                "longitude": 17.008333
            },
            "metadata": null
        },
        {
            "discriminator": "vehicle",
            "platesNumber": "DW9R170",
            "sideNumber": "170",
            "color": "Biały",
            "type": "CAR",
            "picture": {
                "id": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "name": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "extension": null
            },
            "rangeKm": 79,
            "batteryLevelPct": 49,
            "reservationEnd": null,
            "status": "AVAILABLE",
            "locationDescription": null,
            "id": "bb885c35-b281-4647-98bf-cc8a547d1c71",
            "name": "NISSAN Leaf",
            "description": null,
            "location": {
                "latitude": 51.101459,
                "longitude": 17.043893
            },
            "metadata": null
        },
        {
            "discriminator": "vehicle",
            "platesNumber": "DW9R008",
            "sideNumber": "008",
            "color": "Biały",
            "type": "CAR",
            "picture": {
                "id": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "name": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "extension": null
            },
            "rangeKm": 147,
            "batteryLevelPct": 82,
            "reservationEnd": null,
            "status": "AVAILABLE",
            "locationDescription": null,
            "id": "8f0ddb49-0e61-49db-9c22-f184e22719b1",
            "name": "NISSAN Leaf",
            "description": null,
            "location": {
                "latitude": 51.114772,
                "longitude": 16.998579
            },
            "metadata": null
        },
        {
            "discriminator": "vehicle",
            "platesNumber": "DW9R036",
            "sideNumber": "036",
            "color": "Biały",
            "type": "CAR",
            "picture": {
                "id": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "name": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "extension": null
            },
            "rangeKm": 98,
            "batteryLevelPct": 55,
            "reservationEnd": null,
            "status": "AVAILABLE",
            "locationDescription": null,
            "id": "5382af22-7f55-4ede-b961-87e52c715270",
            "name": "NISSAN Leaf",
            "description": null,
            "location": {
                "latitude": 51.13824,
                "longitude": 16.879165
            },
            "metadata": null
        },
        {
            "discriminator": "vehicle",
            "platesNumber": "DW9R115",
            "sideNumber": "115",
            "color": "Biały",
            "type": "CAR",
            "picture": {
                "id": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "name": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "extension": null
            },
            "rangeKm": 56,
            "batteryLevelPct": 34,
            "reservationEnd": null,
            "status": "AVAILABLE",
            "locationDescription": null,
            "id": "144a9ece-8c8e-459e-a10f-699ceca30eb2",
            "name": "NISSAN Leaf",
            "description": null,
            "location": {
                "latitude": 51.158958,
                "longitude": 17.008058
            },
            "metadata": null
        },
        {
            "discriminator": "vehicle",
            "platesNumber": "DW9R129",
            "sideNumber": "129",
            "color": "Biały",
            "type": "CAR",
            "picture": {
                "id": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "name": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "extension": null
            },
            "rangeKm": 136,
            "batteryLevelPct": 76,
            "reservationEnd": "2018-08-04T13:21:11.629",
            "status": "RESERVED",
            "locationDescription": null,
            "id": "20e6d079-0ed6-4e39-9c8e-35d55756d1ca",
            "name": "NISSAN Leaf",
            "description": null,
            "location": {
                "latitude": 51.10868,
                "longitude": 17.068521
            },
            "metadata": null
        },
        {
            "discriminator": "vehicle",
            "platesNumber": "DW9R157",
            "sideNumber": "157",
            "color": "Biały",
            "type": "CAR",
            "picture": {
                "id": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "name": "d5619a4f-48e9-4eb0-a93e-2ca788b47c2c",
                "extension": null
            },
            "rangeKm": 94,
            "batteryLevelPct": 53,
            "reservationEnd": null,
            "status": "AVAILABLE",
            "locationDescription": null,
            "id": "fdf98040-5f35-4d20-b85f-8456217228f0",
            "name": "NISSAN Leaf",
            "description": null,
            "location": {
                "latitude": 51.099903,
                "longitude": 17.152915
            },
            "metadata": null
        }
    ]
}"""
