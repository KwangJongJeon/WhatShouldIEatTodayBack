var range = document.querySelector("#range")
range.addEventListener("input", function() {
    document.querySelector("#rangeValue").innerHTML= range.value;
})