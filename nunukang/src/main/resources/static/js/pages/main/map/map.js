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

    if (type == null) {
        $.ajax({
            url: "/api/v4/map/fisingSpot?type=OCEAN",
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

    this.map = new google.maps.Map(document.getElementById("googleMapDiv"), {
        // zoom: 11,
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
            } 
        })

        // marker.addListener("click", function() {
        //     infoWindow.setContent("이름 : " + i["name"] + '</br>' + "전화번호 : "+i["number"] + "</br>주소 : "+i["address"]);
        //     infoWindow.open(map, marker);
        // });   


        marker.addListener("click", function() {
            locationClickEvent();
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

                                    
                                '</div>' +
                                '<div class="tmp">' +
                                    '<div class="fishingSpotInfoOutterDiv" id="fishingSpotInfoOutterDiv">' +
                                        '<div></div'+
                                        '<div></div'+
                                        '<div></div'+
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
    
    if ($(".fishingSpotInfoOutterDiv").css("display") != "none") {
        // $("#fishingSpotInfoOutterDiv").slideToggle(400);
        // $(".fishingSpotInfoOutterDiv").css("height", "0px");
        // $(".fishingSpotInfoOutterDiv").css("display", "block");
        // $("#fishingSpotInfoOutterDiv").animate({'top': "78vh"}, 1000);
        // $("#fishingSpotInfoOutterDiv").animate({'top': '78%'}, 1000);
        $(".fishingSpotInfoOutterDiv").slideToggle(400);    
    } else {
        // $(".fishingSpotInfoOutterDiv").css("display", "block");
        // $("#fishingSpotInfoOutterDiv").animate({'top': '78%'}, 1000);
        // $(".fishingSpotInfoOutterDiv").css("display", "none");
        // $("#fishingSpotInfoOutterDiv").animate({'bottom': $("#fishingSpotInfoOutterDiv").height()}, 1000);
        // $(".fishingSpotInfoOutterDiv").css("display", "block");
        // $("#fishingSpotInfoOutterDiv").animate({'top': "-6vh"}, 1000);
        $(".fishingSpotInfoOutterDiv").slideToggle(400);
        

    }
}

