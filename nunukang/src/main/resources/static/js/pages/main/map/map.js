function loading() {
    $("body").append(
        '<div class="lodingDiv">' +
            '<i class="fas fa-spinner fa-10x fa-spin"></i>' +
        '</div>'
    );
    if ($(".lodingDiv").css("display") == "none") {
        $(".lodingDiv").css("display", "block") ;
    } else {
        $(".lodingDiv").css("display", "none") ;
    }
}


function getFishingSpot(type) {
    console.log(type);
    if (type == null) {
        console.log('null');
        $.ajax({
            url: "/api/v4/map/fisingSpot?type=OCEAN",
            type: "GET",
            cache: false,
            async: false,
            success: function (result) {
                console.log(result);
                spots = result;
            },
            error: function (err) {
                console.error(err);
            }
        });
        return spots;
    } else {
        $(".fishingSpotButton").css("background-color", "red");

        $.ajax({
            url: "/api/v4/map/fisingSpot?type="+type,
            type: "GET",
            cache: false,
            async: false,
            success: function (result) {
                spots = result;
            },
            error: function (err) {
                console.error(err);
            }
        });
        return spots;
    }
}



function initMap(type) {
    console.log(type);
    this.map = new google.maps.Map(document.getElementById("googleMapDiv"), {
        zoom: 8,
    });

    // google.maps.event.addListenerOnce(map, 'idle', function(){

	//     alert('맵 로딩이 완료되었습니다.');	//맵 로딩이 완료된 후 실행할 함수 추가

    // });

    const infoWindow = new google.maps.InfoWindow({
        content: "",
        disableAutoPan: true,
    });

    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
            (position) => {
                const pos = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude,
                };
                this.map.setCenter(pos);

            },
            () => {
                // could not fetch location
                setDefaultMapLocation()
            }
        );
    } else {
        setDefaultMapLocation()
    }
    
    const spots = getFishingSpot(type);
    const markers = spots.map((i) => {
        console.log(i)
        const marker = new google.maps.Marker({
            position : new google.maps.LatLng(i["gps"]["latitude"], i["gps"]["longitude"]),

            // label: {
                // text : i["name"],
                // color : "#1755FE",
                // fontSize: "40px",
                // fontWeight: "bolder",
            //     className: "marker-label",
            // },
            // animation: google.maps.Animation.DROP,
            icon: {
                url : "/images/pngwing.png",
                scaledSize: new google.maps.Size(43, 58), // scaled size    
            } ,
            address:  {
                locationName : i["name"],
                address : i["address"],
                gps : {
                    latitude : i["gps"]["latitude"],
                    longitude : i["gps"]["longitude"],
                },
                capacity : i["capacity"],
                number : i["number"],
                price : i["price"]
                
            }
        })

        // marker.addListener("click", function() {
        //     infoWindow.setContent("이름 : " + i["name"] + '</br>' + "전화번호 : "+i["number"] + "</br>주소 : "+i["address"]);
        //     infoWindow.open(map, marker);
        // });   


        marker.addListener("click", function() {
            hideFishingSpotInfoContent1();
            hideFishingSpotInfoContent2();
            hideFishingSpotInfoContent3();
            // $(".fishingSpotInfoOutterDiv").css("display", "none");
            locationClickEvent(marker);
            getFishingSpotWeather(marker["address"]);
        });

        return marker;
    })


    
    const markerCluster = new markerClusterer.MarkerClusterer({ map, markers});
    
    $(".googleMapDiv").append(
                                '<div class="fishingSpotTypeButtonDiv">'+
                                    '<div>' +
                                        '<div class="fishingSpotButton fishingSpotType1Button" id="fishingSpotType1Button" onclick="initMap('+"'OCEAN'"+')">' +
                                            '<p>바다</p>' +
                                        '</div>' + 
                                    '</div>' +
                                    '<div>' +
                                        '<div class="fishingSpotButton fishingSpotType2Button" onclick="initMap('+"'RESERVOIR'"+')">' +
                                            '<p>저수지</p>' +
                                        '</div>' +
                                    '</div>' + 

                                    '<div>' +
                                        '<div class="fishingSpotButton fishingSpotType3Button" onclick="initMap('+"'FLAT'"+')">' +
                                            '<p>평지</p>' +
                                        '</div>' +
                                    '</div>' +
                                '</div>');

    buttonColorChanger(type);
    
}

function buttonColorChanger(type) {
    if (type == null) {
        $(".fishingSpotType1Button").css("background-color", "rgba(0, 0, 0, 0.36)");
    } else {
        if (type == "OCEAN") {
            $(".fishingSpotType1Button").css("background-color", "rgba(0, 0, 0, 0.36)");
        } else if (type == "FLAT") {
            $(".fishingSpotType3Button").css("background-color", "rgba(0, 0, 0, 0.36)");
        } else {
            $(".fishingSpotType2Button").css("background-color", "rgba(0, 0, 0, 0.36)");
        }
    }
}

function setDefaultMapLocation() {
    this.map.setCenter(new google.maps.LatLng(37.541, 126.986));
}




function locationClickEvent() {
    
    if ($(".tmp").css("display") != "none") {
        $(".tmp").slideToggle(300);    
    } else {
        $(".tmp").slideToggle(300);
    }
}








function getFishingSpotWeather(address) {
    loading();

    const formData = new FormData();
    formData.append("latitude", address["gps"]["latitude"]);
    formData.append("longitude", address["gps"]["longitude"]);
    
    $.ajax({
        url:'http://127.0.0.1:5000/weather/location',
        type:'POST',
        cache : false,
        contentType: false,               // * 중요 *
        processData: false,               // * 중요 *
        enctype : 'multipart/form-data',  // * 중요 *
        // crossDomain: true,
        data : formData,
        success: function (result) {
            console.log(result)
            $(".fishingSpotNamePTag").text(address["locationName"]);

            result.at(0).forEach(setupWeatherDatas);
            
            getFishingSpotMap(address);
            loading();            
        },
        error: function (err) {
            loading();          
        }
    });
}



function setupWeatherDatas(weatherData) {
    $(".fishingSpotWeatherContentDiv").append(
        '<div>'+
            '<div class="timeDiv">'+
                '<p>'+weatherData["dt_txt"].slice(8,10) + "일 " +weatherData["dt_txt"].slice(11,13)+'시</p>'+
            '</div>'+
            '<div class="weatherIconDiv">'+
                '<img id="weatherIcon" src="'+"http://openweathermap.org/img/wn/"+weatherData["weather"][0]["icon"]+"@2x.png"+'">'+
            '</div>'+
            '<div class="tempDiv">'+
                '<p>'+changeToCelsius(weatherData["main"]["temp"])+'°C</p>'+
            '</div>'+
            '<div class="feelsLikeDiv">'+
                '<p>'+changeToCelsius(weatherData["main"]["feels_like"])+'°C</p>'+
            '</div>'+
            '<div class="rainDiv">'+
                '<p>'+weatherData["clouds"]["all"]+'mm</p>'+
            '</div>'+
            '<div class="windDiv">'+
                '<p>'+weatherData['wind']['speed']+'</p>'+
            '</div>'+
            '<div class="humidityDiv">'+
                '<p>'+weatherData["main"]["humidity"]+'</p>'+
            '</div>'+
            '<div class="seaLevelDiv">'+
                '<p>'+weatherData["main"]["sea_level"]+'</p>'+
            '</div>'+
        '</div>'            
    );

    showFishingSpotInfoContent1();
    showFishingSpotInfoContent2();
    showFishingSpotInfoContent3();
}


function showFishingSpotInfoContent1() {
    $(".fishingSpotInfoContent1").css("display", "flex");
}

function hideFishingSpotInfoContent1() {
    $(".fishingSpotNamePTag").text("");
    $(".fishingSpotInfoContent1").css("display", "none");
}

function showFishingSpotInfoContent2() {
    $(".fishingSpotInfoContent2").css("display", "flex");
}

function hideFishingSpotInfoContent2() {
    $(".fishingSpotInfoContent2").css("display", "none");
}


function showFishingSpotInfoContent3() {
    $(".fishingSpotInfoContent3").css("display", "flex");
}

function hideFishingSpotInfoContent3() {
    $(".fishingSpotInfoContent3").css("display", "none");
}


function changeToCelsius(Fahrenheit) {
    return Math.floor(Fahrenheit - 273.15, 3);
}


function getFishingSpotMap(address) {

    const fishingSpotMap = new google.maps.Map(document.getElementById("fishingSpotMap"), {
        center : {lat : Number(address['gps']['latitude']), lng : Number(address['gps']['longitude'])},
        zoom: 17,
      });

    const marker = new google.maps.Marker({
        position : new google.maps.LatLng(address["gps"]["latitude"], address["gps"]["longitude"]),
        map : fishingSpotMap,
        label: {
            text : address["locationName"],
            fontSize: "40px",
            fontWeight: "bolder",
            className: "marker-label",
        },
    });
    

    setupFishingSpotInfoContent3InnerDiv(address);
}

function setupFishingSpotInfoContent3InnerDiv(obj) {
    document.getElementById("fishingSpotInfoContent3InnerDiv").replaceChildren();

    if (obj["number"] == "") {
        $(".fishingSpotInfoContent3InnerDiv").append(
            '<p>이름 : <span class="nameTextSpan">'+obj["locationName"]+'</span></p>' +
            '<p>주소 : <span class="addressTextSpan">'+obj["address"]+'</span></p>' +
            '<p>가격 : <span class="priceTextSpan">'+obj["price"]+'</span></p>' +
            '<p>수용인원 : <span class="capacityTextSpan">'+obj["capacity"]+'</span></p>' 
        )    
    } else {
        $(".fishingSpotInfoContent3InnerDiv").append(
            '<p>이름 : <span class="nameTextSpan">'+obj["locationName"]+'</span></p>' +
            '<p>주소 : <span class="addressTextSpan">'+obj["address"]+'</span></p>' +
            '<p>전화번호 : <span class="numberTextSpan">'+obj["number"]+'</span></p>' +
            '<p>가격 : <span class="priceTextSpan">'+obj["price"]+'</span></p>' +
            '<p>수용인원 : <span class="capacityTextSpan">'+obj["capacity"]+'</span></p>' 
        )
    }
    
}


