package pl.ccki.szypwyp.goscooter

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Test
import pl.ccki.szypwyp.goscooter.config.GoScooterEndpoints
import pl.ccki.szypwyp.goscooter.config.GoScooterRetrofitFactory

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
        val repository = GoScooterRepository(endpoints, "")

        val result = repository.getAll()

        Assert.assertTrue(result.isNotEmpty())
    }
}

private val page = """<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>GoScooter</title>
    <script src="/Scripts/modernizr-2.6.2.js"></script>

    <link href="/Content/bootstrap.css" rel="stylesheet">
    <link href="/Content/Site.css" rel="stylesheet">
</head>
<body style="padding: 0px; margin: 0px; height: 100vh;">

    <style>
        .box {
            display: flex;
            flex-flow: column;
            height: 100%;
        }

            .box .rowBox {
                border: 1px dotted grey;
            }

            .box .rowBoxBox.header {
                flex: 0 1 auto;
                /* The above is shorthand for:
        flex-growBox: 0,
        flex-shrink: 1,
        flex-basis: auto
        */
            }

            .box .rowBox.content {
                flex: 1 1 auto;
            }

            .box .rowBox.footer {
                flex: 0 1 15px;
                padding: 3px;
            }
    </style>

    <div class="box">
        <div class="rowBox header" style="height: 50px">

            <div class="navbar navbar-default navbar-fixed-top" style="background-color: #23b24d">

                <div class="container">


                    <div class="row">


                            <div class="navbar-header col-xs-12 col-sm-12 col-md-12">
		<a href="http://goscooter.pl/" style="display:inline; padding: 2px;" class="navbar-brand">
			<img src="/Img/logo.png" alt="goscooter.pl" style="display: inline;">
		</a>
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                    <li><a href="/" id="loginLink" class="active">MAPA</a></li>
                    <li>

                        <a class="active" style="margin-left: -5px;" data-toggle="dropdown">
                            <span class="caret"></span>
                            <span class="sr-only">Zmiana miasta</span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/Home/ChangeArea/1">WROCŁAW</a></li>
                        </ul>

                    </li>
                                    <li><a href="/Account/Register" id="loginLink">ZAREJESTRUJ</a></li>
                                    <li><a href="/Account/Login" id="loginLink">ZALOGUJ</a></li>
            </ul>
        </div>
    </div>



                    </div>



                </div>
            </div>
        </div>
        <div class="rowBox content" style="height: calc(100vh - 74px);">



<style>
    #map {
        height: 100%;
        width: 100%;
    }
</style>

<div id="map">
</div>

<script type="text/javascript">
    var map;
    var infowindow;
    function AddMarker(myLatlng, desc, range) {
		var marker = new google.maps.Marker({
			position: myLatlng,
			map: map,
			icon:
			{
                url: '/Img/pinezka-zielona.png',
				scaledSize: new google.maps.Size(48, 64)
			},
            title: desc
        });

        marker.addListener('click', function() {
            infowindow.setContent('<a href="/Ride/StartReservation/'+desc+'">'+'Zarezerwuj</a><br />Zasięg: <strong>'+ range + ' km</strong>' );
            infowindow.open(map, marker);
        });

    }

    function AddMarker2Hemlet(myLatlng, desc, range) {
        var marker = new google.maps.Marker({
            position: myLatlng,
            map: map,
            icon: '/Img/scooterIco2helmets.png',
            title: desc
        });

        marker.addListener('click', function() {
            infowindow.setContent('<a href="/Ride/StartReservation/'+desc+'">'+'Zarezerwuj</a><br />Zasięg: <strong>'+ range + ' km</strong>' );
            infowindow.open(map, marker);
        });

    }

    function AddMarkerHere(myLatlng, desc) {
        var marker = new google.maps.Marker({
            position: myLatlng,
            map: map,
            icon: '/Img/hereIco2.png',
            title: desc
        });



    }

    function initMap() {

        var zoom = 11;


		map = new google.maps.Map(document.getElementById('map'), {
			center: myLatlng,
			zoom: zoom
		});




		infowindow = new google.maps.InfoWindow({
			content: 'Empty'
		});


		var myLatlng =  new google.maps.LatLng(0, 0);
		var desc = 'Empty';
		var latlngbounds = new google.maps.LatLngBounds();


        myLatlng = new google.maps.LatLng(51.101520, 17.109007);


        latlngbounds.extend(myLatlng);


        desc = '105';
        range = '14,90';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.125006, 16.995302);


        latlngbounds.extend(myLatlng);


        desc = '108';
        range = '26,53';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.086266, 17.048240);


        latlngbounds.extend(myLatlng);


        desc = '111';
        range = '30,59';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.108488, 17.021600);


        latlngbounds.extend(myLatlng);


        desc = '113';
        range = '23,17';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.072055, 17.033899);


        latlngbounds.extend(myLatlng);


        desc = '114';
        range = '23,46';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.103061, 17.114861);


        latlngbounds.extend(myLatlng);


        desc = '120';
        range = '38,54';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.135032, 17.069988);


        latlngbounds.extend(myLatlng);


        desc = '122';
        range = '15,44';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.075794, 17.015339);


        latlngbounds.extend(myLatlng);


        desc = '123';
        range = '21,01';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.127174, 16.964207);


        latlngbounds.extend(myLatlng);


        desc = '127';
        range = '24,87';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.086760, 17.034966);


        latlngbounds.extend(myLatlng);


        desc = '130';
        range = '40,59';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.130976, 17.059578);


        latlngbounds.extend(myLatlng);


        desc = '132';
        range = '25,34';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.088619, 17.048522);


        latlngbounds.extend(myLatlng);


        desc = '136';
        range = '23,49';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.116613, 17.050887);


        latlngbounds.extend(myLatlng);


        desc = '137';
        range = '17,87';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.107291, 16.956326);


        latlngbounds.extend(myLatlng);


        desc = '138';
        range = '17,60';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.122833, 17.028866);


        latlngbounds.extend(myLatlng);


        desc = '140';
        range = '40,94';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.114756, 16.983127);


        latlngbounds.extend(myLatlng);


        desc = '141';
        range = '10,74';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.133147, 16.957052);


        latlngbounds.extend(myLatlng);


        desc = '144';
        range = '12,20';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.092594, 16.979814);


        latlngbounds.extend(myLatlng);


        desc = '145';
        range = '19,75';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.140380, 17.070274);


        latlngbounds.extend(myLatlng);


        desc = '146';
        range = '40,66';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.134616, 17.037414);


        latlngbounds.extend(myLatlng);


        desc = '149';
        range = '16,73';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.121774, 17.033698);


        latlngbounds.extend(myLatlng);


        desc = '153';
        range = '14,68';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.116452, 17.012776);


        latlngbounds.extend(myLatlng);


        desc = '154';
        range = '16,73';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.091783, 16.983980);


        latlngbounds.extend(myLatlng);


        desc = '158';
        range = '32,27';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.107137, 16.956246);


        latlngbounds.extend(myLatlng);


        desc = '160';
        range = '12,10';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.107751, 17.073496);


        latlngbounds.extend(myLatlng);


        desc = '161';
        range = '39,35';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.099818, 17.029794);


        latlngbounds.extend(myLatlng);


        desc = '162';
        range = '12,92';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.123931, 17.029098);


        latlngbounds.extend(myLatlng);


        desc = '166';
        range = '17,30';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.084013, 17.022996);


        latlngbounds.extend(myLatlng);


        desc = '168';
        range = '14,31';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.107394, 17.056781);


        latlngbounds.extend(myLatlng);


        desc = '169';
        range = '14,65';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.135065, 17.069254);


        latlngbounds.extend(myLatlng);


        desc = '173';
        range = '21,41';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.116353, 17.052609);


        latlngbounds.extend(myLatlng);


        desc = '177';
        range = '27,35';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.121714, 17.028889);


        latlngbounds.extend(myLatlng);


        desc = '179';
        range = '17,50';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.113068, 16.993933);


        latlngbounds.extend(myLatlng);


        desc = '180';
        range = '11,11';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.119200, 17.019748);


        latlngbounds.extend(myLatlng);


        desc = '185';
        range = '34,48';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.122988, 17.044643);


        latlngbounds.extend(myLatlng);


        desc = '186';
        range = '20,07';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.112521, 17.027385);


        latlngbounds.extend(myLatlng);


        desc = '189';
        range = '21,38';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.117637, 16.989031);


        latlngbounds.extend(myLatlng);


        desc = '190';
        range = '24,13';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.137018, 17.074453);


        latlngbounds.extend(myLatlng);


        desc = '191';
        range = '19,43';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.137207, 16.951505);


        latlngbounds.extend(myLatlng);


        desc = '193';
        range = '18,36';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.109488, 16.969002);


        latlngbounds.extend(myLatlng);


        desc = '194';
        range = '14,08';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.107879, 17.073924);


        latlngbounds.extend(myLatlng);


        desc = '196';
        range = '36,23';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);





        myLatlng = new google.maps.LatLng(51.116257, 17.041857);


        latlngbounds.extend(myLatlng);


        desc = '203';
        range = '13,74';
        if(range <= 0){
            range = "Brak danych";
        }

            AddMarker(myLatlng, desc, range);








        map.fitBounds(latlngbounds);



		var flightPlanCoordinates = [


					   {lat: 51.09763000, lng: 17.03307000},
					   {lat: 51.09520000, lng: 17.03290000},
					   {lat: 51.09590000, lng: 17.03680000},
					   {lat: 51.09706000, lng: 17.03701000},
				];
					var flightPath = new google.maps.Polygon({
			path: flightPlanCoordinates,
            geodesic: true,
			strokeColor: '#990000',
            strokeOpacity: 1.00000,
			strokeWeight: 1,
			fillColor: '#ff0000',
			fillOpacity:  0.50000,

		});

				flightPath.setMap(map);



		var flightPlanCoordinates = [


					   {lat: 51.10811000, lng: 17.03935000},
					   {lat: 51.10798000, lng: 17.04103000},
					   {lat: 51.10876000, lng: 17.04218000},
					   {lat: 51.10901000, lng: 17.04091000},
					   {lat: 51.10901000, lng: 17.03891000},
				];
					var flightPath = new google.maps.Polygon({
			path: flightPlanCoordinates,
            geodesic: true,
			strokeColor: '#990000',
            strokeOpacity: 1.00000,
			strokeWeight: 1,
			fillColor: '#ff0000',
			fillOpacity:  0.50000,

		});

				flightPath.setMap(map);



		var flightPlanCoordinates = [


					   {lat: 51.07693000, lng: 17.04320000},
					   {lat: 51.07530000, lng: 17.04313000},
					   {lat: 51.07533000, lng: 17.04094000},
					   {lat: 51.07694000, lng: 17.04104000},
				];
					var flightPath = new google.maps.Polygon({
			path: flightPlanCoordinates,
            geodesic: true,
			strokeColor: '#990000',
            strokeOpacity: 1.00000,
			strokeWeight: 1,
			fillColor: '#ff0000',
			fillOpacity:  0.50000,

		});

				flightPath.setMap(map);



		var flightPlanCoordinates = [


					   {lat: 51.11183000, lng: 17.05920000},
					   {lat: 51.11212000, lng: 17.05977000},
					   {lat: 51.11225000, lng: 17.06035000},
					   {lat: 51.11364000, lng: 17.06021000},
					   {lat: 51.11359000, lng: 17.05813000},
					   {lat: 51.11295000, lng: 17.05771000},
					   {lat: 51.11272000, lng: 17.05842000},
					   {lat: 51.11225000, lng: 17.05812000},
				];
					var flightPath = new google.maps.Polygon({
			path: flightPlanCoordinates,
            geodesic: true,
			strokeColor: '#990000',
            strokeOpacity: 1.00000,
			strokeWeight: 1,
			fillColor: '#ff0000',
			fillOpacity:  0.50000,

		});

				flightPath.setMap(map);



		var flightPlanCoordinates = [


					   {lat: 51.10706000, lng: 17.02530000},
					   {lat: 51.10556000, lng: 17.02845000},
					   {lat: 51.10684000, lng: 17.02942000},
					   {lat: 51.10784000, lng: 17.02635000},
				];
					var flightPath = new google.maps.Polygon({
			path: flightPlanCoordinates,
            geodesic: true,
			strokeColor: '#990000',
            strokeOpacity: 1.00000,
			strokeWeight: 1,
			fillColor: '#ff0000',
			fillOpacity:  0.50000,

		});

				flightPath.setMap(map);



		var flightPlanCoordinates = [


					   {lat: 51.09921000, lng: 17.02709000},
					   {lat: 51.09851000, lng: 17.02980000},
					   {lat: 51.09973000, lng: 17.03083000},
					   {lat: 51.10037000, lng: 17.02840000},
				];
					var flightPath = new google.maps.Polygon({
			path: flightPlanCoordinates,
            geodesic: true,
			strokeColor: '#990000',
            strokeOpacity: 1.00000,
			strokeWeight: 1,
			fillColor: '#ff0000',
			fillOpacity:  0.50000,

		});

				flightPath.setMap(map);



		var flightPlanCoordinates = [


					   {lat: 51.09349000, lng: 17.02002000},
					   {lat: 51.09409000, lng: 17.01778000},
					   {lat: 51.09544000, lng: 17.01942000},
					   {lat: 51.09470000, lng: 17.02132000},
				];
					var flightPath = new google.maps.Polygon({
			path: flightPlanCoordinates,
            geodesic: true,
			strokeColor: '#990000',
            strokeOpacity: 1.00000,
			strokeWeight: 1,
			fillColor: '#ff0000',
			fillOpacity:  0.50000,

		});

				flightPath.setMap(map);



		var flightPlanCoordinates = [


					   {lat: 51.10733000, lng: 17.03389000},
					   {lat: 51.10734000, lng: 17.03483000},
					   {lat: 51.10778000, lng: 17.03475000},
					   {lat: 51.10793000, lng: 17.03404000},
				];
					var flightPath = new google.maps.Polygon({
			path: flightPlanCoordinates,
            geodesic: true,
			strokeColor: '#990000',
            strokeOpacity: 1.00000,
			strokeWeight: 1,
			fillColor: '#ff0000',
			fillOpacity:  0.50000,

		});

				flightPath.setMap(map);



		var flightPlanCoordinates = [


					   {lat: 51.07433000, lng: 17.00683000},
					   {lat: 51.07446000, lng: 17.00564000},
					   {lat: 51.07175000, lng: 17.00165000},
					   {lat: 51.06914000, lng: 16.99743000},
					   {lat: 51.06742000, lng: 16.99865000},
					   {lat: 51.06564000, lng: 16.98878000},
					   {lat: 51.06662000, lng: 16.98906000},
					   {lat: 51.07020000, lng: 16.99176000},
					   {lat: 51.07489000, lng: 16.98672000},
					   {lat: 51.07767000, lng: 16.99627000},
					   {lat: 51.08142000, lng: 17.00041000},
					   {lat: 51.08362000, lng: 16.99833000},
					   {lat: 51.08769000, lng: 16.99717000},
					   {lat: 51.08739000, lng: 16.99905000},
					   {lat: 51.08926000, lng: 17.00054000},
					   {lat: 51.08943000, lng: 16.99980000},
					   {lat: 51.08979000, lng: 16.99569000},
					   {lat: 51.08692000, lng: 16.99612000},
					   {lat: 51.08782000, lng: 16.99205000},
					   {lat: 51.08929000, lng: 16.99267000},
					   {lat: 51.09029000, lng: 16.98696000},
					   {lat: 51.09072000, lng: 16.98322000},
					   {lat: 51.09189000, lng: 16.98356000},
					   {lat: 51.09257000, lng: 16.97816000},
					   {lat: 51.09007000, lng: 16.97694000},
					   {lat: 51.08903000, lng: 16.97644000},
					   {lat: 51.08927000, lng: 16.97599000},
					   {lat: 51.08937000, lng: 16.97539000},
					   {lat: 51.08946000, lng: 16.97473000},
					   {lat: 51.09035000, lng: 16.97494000},
					   {lat: 51.09278000, lng: 16.97579000},
					   {lat: 51.09359000, lng: 16.97026000},
					   {lat: 51.09496000, lng: 16.97040000},
					   {lat: 51.09545000, lng: 16.97022000},
					   {lat: 51.09682000, lng: 16.97529000},
					   {lat: 51.10677000, lng: 17.01221000},
					   {lat: 51.10873000, lng: 17.00865000},
					   {lat: 51.10537000, lng: 17.00465000},
					   {lat: 51.10522000, lng: 17.00287000},
					   {lat: 51.11063000, lng: 16.99630000},
					   {lat: 51.10276000, lng: 16.99394000},
					   {lat: 51.10255000, lng: 16.98860000},
					   {lat: 51.10415000, lng: 16.98530000},
					   {lat: 51.10761000, lng: 16.98053000},
					   {lat: 51.11047000, lng: 16.97593000},
					   {lat: 51.10654000, lng: 16.97608000},
					   {lat: 51.10529000, lng: 16.97134000},
					   {lat: 51.10353000, lng: 16.96327000},
					   {lat: 51.10588000, lng: 16.96200000},
					   {lat: 51.10630000, lng: 16.95864000},
					   {lat: 51.10753000, lng: 16.95421000},
					   {lat: 51.10888000, lng: 16.95269000},
					   {lat: 51.11006000, lng: 16.95517000},
					   {lat: 51.11082000, lng: 16.95431000},
					   {lat: 51.11178000, lng: 16.94995000},
					   {lat: 51.11759000, lng: 16.94473000},
					   {lat: 51.11888000, lng: 16.93864000},
					   {lat: 51.12186000, lng: 16.93991000},
					   {lat: 51.12201000, lng: 16.94067000},
					   {lat: 51.12740000, lng: 16.94348000},
					   {lat: 51.12919000, lng: 16.94735000},
					   {lat: 51.12960000, lng: 16.94915000},
					   {lat: 51.12740000, lng: 16.95227000},
					   {lat: 51.12693000, lng: 16.95309000},
					   {lat: 51.12755000, lng: 16.95422000},
					   {lat: 51.12685000, lng: 16.95525000},
					   {lat: 51.12883000, lng: 16.95943000},
					   {lat: 51.12983000, lng: 16.95997000},
					   {lat: 51.13051000, lng: 16.95560000},
					   {lat: 51.12729000, lng: 16.95361000},
					   {lat: 51.12957000, lng: 16.95250000},
					   {lat: 51.13003000, lng: 16.95106000},
					   {lat: 51.13083000, lng: 16.95183000},
					   {lat: 51.13206000, lng: 16.94883000},
					   {lat: 51.13161000, lng: 16.94819000},
					   {lat: 51.13253000, lng: 16.94697000},
					   {lat: 51.13486000, lng: 16.94723000},
					   {lat: 51.13655000, lng: 16.94910000},
					   {lat: 51.13797000, lng: 16.94977000},
					   {lat: 51.13693000, lng: 16.95359000},
					   {lat: 51.14124000, lng: 16.95686000},
					   {lat: 51.14316000, lng: 16.95398000},
					   {lat: 51.14341000, lng: 16.95129000},
					   {lat: 51.14257000, lng: 16.94849000},
					   {lat: 51.14307000, lng: 16.94625000},
					   {lat: 51.14431000, lng: 16.94822000},
					   {lat: 51.14432000, lng: 16.94912000},
					   {lat: 51.14475000, lng: 16.94917000},
					   {lat: 51.14469000, lng: 16.94983000},
					   {lat: 51.14454000, lng: 16.95051000},
					   {lat: 51.14423000, lng: 16.95121000},
					   {lat: 51.14420000, lng: 16.95219000},
					   {lat: 51.14355000, lng: 16.95603000},
					   {lat: 51.14239000, lng: 16.95831000},
					   {lat: 51.14476000, lng: 16.96414000},
					   {lat: 51.14396000, lng: 16.96760000},
					   {lat: 51.14039000, lng: 16.97137000},
					   {lat: 51.14091000, lng: 16.97407000},
					   {lat: 51.13931000, lng: 16.97491000},
					   {lat: 51.13903000, lng: 16.98022000},
					   {lat: 51.13719000, lng: 16.98319000},
					   {lat: 51.13355000, lng: 16.98206000},
					   {lat: 51.13432000, lng: 16.97613000},
					   {lat: 51.13231000, lng: 16.97373000},
					   {lat: 51.13757000, lng: 16.96470000},
					   {lat: 51.13880000, lng: 16.96174000},
					   {lat: 51.13543000, lng: 16.96042000},
					   {lat: 51.13249000, lng: 16.95936000},
					   {lat: 51.13064000, lng: 16.96787000},
					   {lat: 51.12953000, lng: 16.97165000},
					   {lat: 51.13048000, lng: 16.97225000},
					   {lat: 51.12990000, lng: 16.97398000},
					   {lat: 51.12905000, lng: 16.97331000},
					   {lat: 51.12715000, lng: 16.97954000},
					   {lat: 51.13042000, lng: 16.98226000},
					   {lat: 51.12957000, lng: 16.98793000},
					   {lat: 51.13143000, lng: 16.98753000},
					   {lat: 51.13220000, lng: 16.98653000},
					   {lat: 51.13340000, lng: 16.98918000},
					   {lat: 51.13255000, lng: 16.99013000},
					   {lat: 51.13223000, lng: 16.99002000},
					   {lat: 51.13188000, lng: 16.98912000},
					   {lat: 51.12956000, lng: 16.98921000},
					   {lat: 51.12907000, lng: 16.99255000},
					   {lat: 51.12874000, lng: 16.99441000},
					   {lat: 51.12710000, lng: 16.99485000},
					   {lat: 51.12635000, lng: 17.00115000},
					   {lat: 51.12193000, lng: 16.99994000},
					   {lat: 51.12251000, lng: 17.00144000},
					   {lat: 51.12241000, lng: 17.00246000},
					   {lat: 51.12222000, lng: 17.00353000},
					   {lat: 51.12164000, lng: 17.00696000},
					   {lat: 51.12220000, lng: 17.01223000},
					   {lat: 51.12456000, lng: 17.01939000},
					   {lat: 51.12213000, lng: 17.02076000},
					   {lat: 51.11866000, lng: 17.01740000},
					   {lat: 51.11783000, lng: 17.01755000},
					   {lat: 51.11752000, lng: 17.01870000},
					   {lat: 51.11889000, lng: 17.01827000},
					   {lat: 51.12180000, lng: 17.02263000},
					   {lat: 51.11990000, lng: 17.02511000},
					   {lat: 51.11741000, lng: 17.02605000},
					   {lat: 51.11562000, lng: 17.02707000},
					   {lat: 51.11524000, lng: 17.02840000},
					   {lat: 51.11514000, lng: 17.03114000},
					   {lat: 51.11503000, lng: 17.03393000},
					   {lat: 51.11438000, lng: 17.03420000},
					   {lat: 51.11481000, lng: 17.03587000},
					   {lat: 51.11312000, lng: 17.03957000},
					   {lat: 51.11201000, lng: 17.04443000},
					   {lat: 51.11117000, lng: 17.04864000},
					   {lat: 51.11208000, lng: 17.04931000},
					   {lat: 51.11260000, lng: 17.04788000},
					   {lat: 51.11317000, lng: 17.04431000},
					   {lat: 51.11457000, lng: 17.04255000},
					   {lat: 51.11661000, lng: 17.04103000},
					   {lat: 51.11776000, lng: 17.03881000},
					   {lat: 51.11744000, lng: 17.03725000},
					   {lat: 51.11578000, lng: 17.03357000},
					   {lat: 51.11584000, lng: 17.03113000},
					   {lat: 51.11579000, lng: 17.02820000},
					   {lat: 51.11631000, lng: 17.02736000},
					   {lat: 51.11927000, lng: 17.02634000},
					   {lat: 51.12062000, lng: 17.02574000},
					   {lat: 51.12264000, lng: 17.02265000},
					   {lat: 51.12291000, lng: 17.02209000},
					   {lat: 51.12376000, lng: 17.02169000},
					   {lat: 51.12508000, lng: 17.02178000},
					   {lat: 51.12566000, lng: 17.02459000},
					   {lat: 51.12611000, lng: 17.02753000},
					   {lat: 51.12996000, lng: 17.02780000},
					   {lat: 51.12975000, lng: 17.03152000},
					   {lat: 51.13064000, lng: 17.03726000},
					   {lat: 51.13003000, lng: 17.03724000},
					   {lat: 51.12958000, lng: 17.04070000},
					   {lat: 51.13098000, lng: 17.04562000},
					   {lat: 51.12835000, lng: 17.04735000},
					   {lat: 51.12732000, lng: 17.04418000},
					   {lat: 51.12824000, lng: 17.04343000},
					   {lat: 51.12795000, lng: 17.04170000},
					   {lat: 51.12804000, lng: 17.03988000},
					   {lat: 51.12683000, lng: 17.03874000},
					   {lat: 51.12654000, lng: 17.03654000},
					   {lat: 51.12520000, lng: 17.03620000},
					   {lat: 51.12560000, lng: 17.04143000},
					   {lat: 51.12434000, lng: 17.04413000},
					   {lat: 51.12630000, lng: 17.04641000},
					   {lat: 51.12570000, lng: 17.04730000},
					   {lat: 51.12424000, lng: 17.04576000},
					   {lat: 51.11887000, lng: 17.04280000},
					   {lat: 51.11745000, lng: 17.04279000},
					   {lat: 51.11751000, lng: 17.04668000},
					   {lat: 51.11708000, lng: 17.05065000},
					   {lat: 51.11984000, lng: 17.05080000},
					   {lat: 51.12634000, lng: 17.05421000},
					   {lat: 51.12837000, lng: 17.05478000},
					   {lat: 51.12893000, lng: 17.05623000},
					   {lat: 51.12845000, lng: 17.05694000},
					   {lat: 51.12425000, lng: 17.05545000},
					   {lat: 51.12089000, lng: 17.06252000},
					   {lat: 51.11866000, lng: 17.06825000},
					   {lat: 51.11702000, lng: 17.06759000},
					   {lat: 51.11688000, lng: 17.07099000},
					   {lat: 51.11463000, lng: 17.07207000},
					   {lat: 51.11167000, lng: 17.07010000},
					   {lat: 51.10819000, lng: 17.06945000},
					   {lat: 51.10699000, lng: 17.06718000},
					   {lat: 51.10680000, lng: 17.06572000},
					   {lat: 51.10681000, lng: 17.05720000},
					   {lat: 51.10791000, lng: 17.05473000},
					   {lat: 51.10955000, lng: 17.05339000},
					   {lat: 51.10904000, lng: 17.05171000},
					   {lat: 51.10774000, lng: 17.05247000},
					   {lat: 51.10650000, lng: 17.05413000},
					   {lat: 51.10515000, lng: 17.05845000},
					   {lat: 51.10299000, lng: 17.06625000},
					   {lat: 51.10206000, lng: 17.06429000},
					   {lat: 51.10280000, lng: 17.05556000},
					   {lat: 51.09958000, lng: 17.05706000},
					   {lat: 51.09612000, lng: 17.05743000},
					   {lat: 51.09262000, lng: 17.06177000},
					   {lat: 51.09212000, lng: 17.06099000},
					   {lat: 51.09527000, lng: 17.05601000},
					   {lat: 51.09607000, lng: 17.05036000},
					   {lat: 51.09841000, lng: 17.04439000},
					   {lat: 51.09677000, lng: 17.04228000},
					   {lat: 51.09483000, lng: 17.04652000},
					   {lat: 51.09386000, lng: 17.04547000},
					   {lat: 51.09204000, lng: 17.04923000},
					   {lat: 51.09047000, lng: 17.04733000},
					   {lat: 51.09025000, lng: 17.04728000},
					   {lat: 51.09016000, lng: 17.04770000},
					   {lat: 51.08987000, lng: 17.04878000},
					   {lat: 51.08966000, lng: 17.04886000},
					   {lat: 51.08943000, lng: 17.04924000},
					   {lat: 51.08922000, lng: 17.04992000},
					   {lat: 51.08908000, lng: 17.05039000},
					   {lat: 51.08886000, lng: 17.05140000},
					   {lat: 51.08858000, lng: 17.05482000},
					   {lat: 51.08875000, lng: 17.05500000},
					   {lat: 51.08847000, lng: 17.05701000},
					   {lat: 51.08827000, lng: 17.05704000},
					   {lat: 51.08815000, lng: 17.05809000},
					   {lat: 51.08451000, lng: 17.05867000},
					   {lat: 51.08405000, lng: 17.06098000},
					   {lat: 51.08451000, lng: 17.06453000},
					   {lat: 51.07755000, lng: 17.07182000},
					   {lat: 51.07768000, lng: 17.06654000},
					   {lat: 51.07636000, lng: 17.05825000},
					   {lat: 51.08069000, lng: 17.05505000},
					   {lat: 51.08077000, lng: 17.05109000},
					   {lat: 51.08056000, lng: 17.05082000},
					   {lat: 51.07644000, lng: 17.05266000},
					   {lat: 51.07621000, lng: 17.04905000},
					   {lat: 51.07125000, lng: 17.04843000},
					   {lat: 51.07068000, lng: 17.03202000},
					   {lat: 51.07434000, lng: 17.03217000},
					   {lat: 51.08275000, lng: 17.03438000},
					   {lat: 51.08321000, lng: 17.02748000},
					   {lat: 51.07283000, lng: 17.02228000},
					   {lat: 51.07358000, lng: 17.01641000},
					   {lat: 51.07263000, lng: 17.01542000},
					   {lat: 51.07194000, lng: 17.01208000},
					   {lat: 51.07043000, lng: 17.01096000},
					   {lat: 51.07050000, lng: 17.01040000},
					   {lat: 51.06907000, lng: 17.00549000},
					   {lat: 51.06935000, lng: 17.00432000},
				];
					var flightPath = new google.maps.Polygon({
			path: flightPlanCoordinates,
            geodesic: true,
			strokeColor: '#000000',
            strokeOpacity: 1.00000,
			strokeWeight: 1,
			fillColor: '#00A82D',
			fillOpacity:  0.20000,

		});

				flightPath.setMap(map);



		var flightPlanCoordinates = [


					   {lat: 51.09871000, lng: 17.10082000},
					   {lat: 51.09937000, lng: 17.09324000},
					   {lat: 51.10119000, lng: 17.09451000},
					   {lat: 51.10302000, lng: 17.09488000},
					   {lat: 51.10417000, lng: 17.08597000},
					   {lat: 51.10384000, lng: 17.08221000},
					   {lat: 51.10798000, lng: 17.06959000},
					   {lat: 51.10998000, lng: 17.07633000},
					   {lat: 51.11075000, lng: 17.07928000},
					   {lat: 51.10818000, lng: 17.08103000},
					   {lat: 51.10569000, lng: 17.07892000},
					   {lat: 51.10550000, lng: 17.07918000},
					   {lat: 51.10820000, lng: 17.08451000},
					   {lat: 51.10655000, lng: 17.08680000},
					   {lat: 51.10649000, lng: 17.09190000},
					   {lat: 51.10890000, lng: 17.09503000},
					   {lat: 51.10911000, lng: 17.09555000},
					   {lat: 51.11354000, lng: 17.09536000},
					   {lat: 51.11345000, lng: 17.09153000},
					   {lat: 51.11316000, lng: 17.09033000},
					   {lat: 51.11802000, lng: 17.08942000},
					   {lat: 51.11812000, lng: 17.09117000},
					   {lat: 51.11605000, lng: 17.09226000},
					   {lat: 51.11387000, lng: 17.09253000},
					   {lat: 51.11398000, lng: 17.09667000},
					   {lat: 51.11572000, lng: 17.09680000},
					   {lat: 51.11604000, lng: 17.10343000},
					   {lat: 51.11464000, lng: 17.10432000},
					   {lat: 51.11371000, lng: 17.10438000},
					   {lat: 51.11277000, lng: 17.10675000},
					   {lat: 51.11115000, lng: 17.10949000},
					   {lat: 51.10671000, lng: 17.10651000},
					   {lat: 51.10592000, lng: 17.11090000},
					   {lat: 51.10439000, lng: 17.11373000},
					   {lat: 51.10123000, lng: 17.11748000},
					   {lat: 51.10032000, lng: 17.11145000},
					   {lat: 51.10175000, lng: 17.10215000},
				];
					var flightPath = new google.maps.Polygon({
			path: flightPlanCoordinates,
            geodesic: true,
			strokeColor: '#000000',
            strokeOpacity: 1.00000,
			strokeWeight: 1,
			fillColor: '#00A82D',
			fillOpacity:  0.20000,

		});

				flightPath.setMap(map);



		var flightPlanCoordinates = [


					   {lat: 51.09483000, lng: 16.93421000},
					   {lat: 51.09331000, lng: 16.93896000},
					   {lat: 51.09380000, lng: 16.93964000},
					   {lat: 51.09509000, lng: 16.94154000},
					   {lat: 51.09594000, lng: 16.94285000},
					   {lat: 51.09461000, lng: 16.94675000},
					   {lat: 51.09599000, lng: 16.94820000},
					   {lat: 51.09637000, lng: 16.94801000},
					   {lat: 51.09721000, lng: 16.94876000},
					   {lat: 51.09697000, lng: 16.95129000},
					   {lat: 51.09675000, lng: 16.95385000},
					   {lat: 51.09696000, lng: 16.95427000},
					   {lat: 51.09711000, lng: 16.95438000},
					   {lat: 51.09728000, lng: 16.95443000},
					   {lat: 51.09743000, lng: 16.95449000},
					   {lat: 51.09776000, lng: 16.95326000},
					   {lat: 51.09805000, lng: 16.95341000},
					   {lat: 51.09837000, lng: 16.95211000},
					   {lat: 51.10074000, lng: 16.95342000},
					   {lat: 51.10271000, lng: 16.95231000},
					   {lat: 51.10258000, lng: 16.95149000},
					   {lat: 51.10415000, lng: 16.95070000},
					   {lat: 51.10480000, lng: 16.95127000},
					   {lat: 51.10547000, lng: 16.94961000},
					   {lat: 51.10391000, lng: 16.94639000},
					   {lat: 51.10444000, lng: 16.94344000},
					   {lat: 51.10585000, lng: 16.94414000},
					   {lat: 51.10594000, lng: 16.94419000},
					   {lat: 51.10691000, lng: 16.94454000},
					   {lat: 51.10695000, lng: 16.94420000},
					   {lat: 51.10758000, lng: 16.94046000},
					   {lat: 51.10693000, lng: 16.94010000},
					   {lat: 51.10587000, lng: 16.93973000},
					   {lat: 51.10468000, lng: 16.93947000},
					   {lat: 51.10460000, lng: 16.93957000},
					   {lat: 51.10446000, lng: 16.94031000},
					   {lat: 51.10256000, lng: 16.94039000},
					   {lat: 51.10135000, lng: 16.93964000},
					   {lat: 51.10111000, lng: 16.93794000},
					   {lat: 51.10084000, lng: 16.93592000},
					   {lat: 51.09985000, lng: 16.93520000},
					   {lat: 51.09944000, lng: 16.93649000},
					   {lat: 51.09869000, lng: 16.93625000},
					   {lat: 51.09866000, lng: 16.93405000},
					   {lat: 51.09722000, lng: 16.93372000},
					   {lat: 51.09514000, lng: 16.93230000},
				];
					var flightPath = new google.maps.Polygon({
			path: flightPlanCoordinates,
            geodesic: true,
			strokeColor: '#000000',
            strokeOpacity: 1.00000,
			strokeWeight: 1,
			fillColor: '#00A82D',
			fillOpacity:  0.20000,

		});

				flightPath.setMap(map);



		var flightPlanCoordinates = [


					   {lat: 51.13424000, lng: 17.02830000},
					   {lat: 51.13259000, lng: 17.02809000},
					   {lat: 51.13326000, lng: 17.03620000},
					   {lat: 51.13506000, lng: 17.04201000},
					   {lat: 51.13590000, lng: 17.04203000},
					   {lat: 51.13670000, lng: 17.04989000},
					   {lat: 51.13472000, lng: 17.05016000},
					   {lat: 51.13238000, lng: 17.05586000},
					   {lat: 51.13028000, lng: 17.06070000},
					   {lat: 51.13068000, lng: 17.06170000},
					   {lat: 51.13007000, lng: 17.06592000},
					   {lat: 51.13287000, lng: 17.06803000},
					   {lat: 51.13334000, lng: 17.06723000},
					   {lat: 51.13498000, lng: 17.06907000},
					   {lat: 51.13457000, lng: 17.07078000},
					   {lat: 51.13617000, lng: 17.07266000},
					   {lat: 51.13791000, lng: 17.08163000},
					   {lat: 51.13956000, lng: 17.08243000},
					   {lat: 51.14180000, lng: 17.08178000},
					   {lat: 51.13922000, lng: 17.07322000},
					   {lat: 51.13573000, lng: 17.06899000},
					   {lat: 51.13730000, lng: 17.06627000},
					   {lat: 51.13794000, lng: 17.06818000},
					   {lat: 51.13841000, lng: 17.06868000},
					   {lat: 51.13994000, lng: 17.07069000},
					   {lat: 51.14241000, lng: 17.07162000},
					   {lat: 51.14301000, lng: 17.07027000},
					   {lat: 51.14074000, lng: 17.06946000},
					   {lat: 51.14099000, lng: 17.06847000},
					   {lat: 51.13891000, lng: 17.06678000},
					   {lat: 51.14097000, lng: 17.06162000},
					   {lat: 51.14022000, lng: 17.06108000},
					   {lat: 51.14090000, lng: 17.05808000},
					   {lat: 51.13974000, lng: 17.05864000},
					   {lat: 51.13887000, lng: 17.05920000},
					   {lat: 51.13787000, lng: 17.05126000},
					   {lat: 51.13650000, lng: 17.03740000},
					   {lat: 51.13859000, lng: 17.03391000},
					   {lat: 51.13824000, lng: 17.03291000},
					   {lat: 51.13842000, lng: 17.03079000},
					   {lat: 51.14090000, lng: 17.03270000},
					   {lat: 51.14222000, lng: 17.03242000},
					   {lat: 51.14296000, lng: 17.03233000},
					   {lat: 51.14417000, lng: 17.03444000},
					   {lat: 51.14560000, lng: 17.03204000},
					   {lat: 51.14422000, lng: 17.02907000},
					   {lat: 51.14315000, lng: 17.02721000},
					   {lat: 51.14219000, lng: 17.02559000},
					   {lat: 51.14093000, lng: 17.02215000},
					   {lat: 51.13921000, lng: 17.01881000},
					   {lat: 51.13555000, lng: 17.02413000},
				];
					var flightPath = new google.maps.Polygon({
			path: flightPlanCoordinates,
            geodesic: true,
			strokeColor: '#000000',
            strokeOpacity: 1.00000,
			strokeWeight: 1,
			fillColor: '#00A82D',
			fillOpacity:  0.20000,

		});

				flightPath.setMap(map);



		var flightPlanCoordinates = [


					   {lat: 51.13898000, lng: 16.92786000},
					   {lat: 51.13838000, lng: 16.93359000},
					   {lat: 51.13898000, lng: 16.93380000},
					   {lat: 51.13980000, lng: 16.92829000},
				];
					var flightPath = new google.maps.Polygon({
			path: flightPlanCoordinates,
            geodesic: true,
			strokeColor: '#000000',
            strokeOpacity: 1.00000,
			strokeWeight: 1,
			fillColor: '#00A82D',
			fillOpacity:  0.20000,

		});

				flightPath.setMap(map);



		var flightPlanCoordinates = [


					   {lat: 51.11520000, lng: 17.07307000},
					   {lat: 51.11471000, lng: 17.07405000},
					   {lat: 51.11628000, lng: 17.07531000},
					   {lat: 51.11790000, lng: 17.07729000},
					   {lat: 51.11986000, lng: 17.07805000},
					   {lat: 51.11926000, lng: 17.08227000},
					   {lat: 51.12199000, lng: 17.08504000},
					   {lat: 51.12523000, lng: 17.08632000},
					   {lat: 51.12532000, lng: 17.08565000},
					   {lat: 51.12238000, lng: 17.08353000},
					   {lat: 51.12105000, lng: 17.08196000},
					   {lat: 51.12104000, lng: 17.07983000},
					   {lat: 51.12305000, lng: 17.07990000},
					   {lat: 51.12306000, lng: 17.07860000},
					   {lat: 51.12503000, lng: 17.07884000},
					   {lat: 51.12512000, lng: 17.07728000},
					   {lat: 51.11976000, lng: 17.07645000},
					   {lat: 51.11674000, lng: 17.07382000},
				];
					var flightPath = new google.maps.Polygon({
			path: flightPlanCoordinates,
            geodesic: true,
			strokeColor: '#000000',
            strokeOpacity: 1.00000,
			strokeWeight: 1,
			fillColor: '#00A82D',
			fillOpacity:  0.20000,

		});

				flightPath.setMap(map);





    }
</script>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBcSiuhShTDJYAIkXMXqEV6iaoGuiStZK0&callback=initMap"
        async defer></script>
        </div>
        <div class="rowBox footer">
            <footer>
	&copy; 2018 - <a href="http://goscooter.pl">GoScooter.pl</a> && <a href="http://cybertrick.pl">Software Cybertrick.pl</a>. Wszelkie prawa zastrzeżone <a href="http://goscooter.pl#contact"> Kontakt</a>
</footer>
        </div>
    </div>

    <script src="/Scripts/jquery-3.1.1.js"></script>

    <script src="/Scripts/bootstrap.js"></script>
<script src="/Scripts/respond.js"></script>



</body>
</html>""".trimMargin()