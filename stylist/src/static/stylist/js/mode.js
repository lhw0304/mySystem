document.addEventListener("DOMContentLoaded", function(event) {
    var imageContainer = document.getElementById("mode_pointImageContainer");
    console.log(imageContainer);
    var imageId = imageContainer.getAttribute("data-id");

    var uri = "http://localhost:8080/stylist//#/image?imageId="+imageId;

    var iframe = '<div style="position: relative; display: block; height: 0; padding: 0; overflow: hidden; padding-bottom: 100% !important; margin-bottom: 5px"><iframe style="position: absolute; top: 0; bottom: 0; left: 0; width: 100%; height: 100%; border: 0;" frameborder="0" src="'+ uri +'"></iframe></div>';

    imageContainer.innerHTML = iframe;
});
