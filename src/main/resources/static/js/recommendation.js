var range = document.querySelector("#range")
range.addEventListener("input", function() {
    document.querySelector("#rangeValue").innerHTML= range.value;
})

// HTML5의 geolocation으로 사용할 수 있는지 확인합니다.
if(navigator.geolocation) {
    navigator.geolocation.getCurrentPosition((position => {
        let lat = document.querySelector("#lat");
        let long = document.querySelector("#long");
        lat.na
        lat.value = position.coords.latitude;
        long.value = position.coords.longitude;
    })), function error(msg) {
        alert("GPS 사용을 허가해주세요.")
    }, {enableHighAccuracy: true}
};

