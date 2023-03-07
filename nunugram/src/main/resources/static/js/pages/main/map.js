
$(document).ready(function(){

    
});


function initMap() {
    this.map = new google.maps.Map(document.getElementById("googleMapDiv"), {
        zoom: 11,
    });

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


    const markers = locations.map((position, i) => {
        const marker = new google.maps.Marker({
            position, 
            title: '성대 저수지',
            label: {
                text : '성대 저수지',
                fontSize: "19px",
                fontWeight: "bolder",
                className: "marker-label",
            },
            icon: {
                url : "/images/pngwing.png",
                scaledSize: new google.maps.Size(27, 36), // scaled size    
            } 
        })
        
        // marker.addListener("click", function() {
        //     infoWindow.setContent('sex');
        //     infoWindow.open(map, marker);
        // });

        marker.addListener("click", function() {
            locationClickEvent();
        });
        return marker;
    });
    
    const markerCluster = new markerClusterer.MarkerClusterer({ map, markers});
}


function setDefaultMapLocation() {
    this.map.setCenter(new google.maps.LatLng(37.541, 126.986));
}

function locationClickEvent() {
    
    if ($(".locationClickEventDiv").css("display") == "none") {
        $("#locationClickEventDiv").slideToggle(400);
        
    } else {
        $("#locationClickEventDiv").slideToggle(400);
    }
}

var locations = [
    { lat: 37.541, lng: 126.986  },
    { lat: 37.531, lng: 126.986  },
    { lat: 37.521, lng: 126.986  },
    { lat: 37.511, lng: 126.986  },
];
