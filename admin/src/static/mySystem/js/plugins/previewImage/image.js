window.URL = window.URL || window.webkitURL;
function handleFiles(obj) {
    fileList = document.getElementById("fileList");
    var files = obj.files,
        img = new Image();
    if(!files[0].name.match(/\.(jpg|jpeg|png|gif)$/)){
        alert("Only .jpg .jpeg .png .gif extensions are allowed.");
        obj.value = "";
        return;
    }
    if(files[0].size > 524288) {
        alert("The cover image is too large.");
        obj.value = "";
        return;
    }
    if (window.URL) {
        //File API
        img.src = window.URL.createObjectURL(files[0]);
        img.setAttribute("width", "45%");
        img.setAttribute("height", "45%");
        img.onload = function (e) {
            window.URL.revokeObjectURL(this.src); //object URL
        }
        fileList.innerHTML = "";
        fileList.appendChild(img);
    } else if (window.FileReader) {
        var reader = new FileReader();
        reader.readAsDataURL(files[0]);
        reader.onload = function (e) {
            img.src = this.result;
            img.width = 200;
            fileList.appendChild(img);
        }
    } else {
        obj.select();
        obj.blur();
        var nfile = document.selection.createRange().text;
        document.selection.empty();
        img.src = nfile;
        img.width = 200;
        img.onload = function () {
        }
        fileList.appendChild(img);
    }
}
