function previewImage(file) {
        var MAXWIDTH = 110;
        var MAXHEIGHT = 110;
        var div = document.getElementById('preview');
        if (file.files && file.files[0]) {
            div.innerHTML = '<img id=target class="img-circle circle-border m-b-md" alt="profile" onclick="uploadImage();">';
            var img = document.getElementById('target');
            img.onload = function () {
                var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
                img.width = rect.width;
                img.height = rect.height;
                img.style.marginLeft = rect.left + 'px';
                img.style.marginTop = rect.top + 'px';
                img.setAttribute('class','img-circle circle-border m-b-md');
            }
            var reader = new FileReader();
            reader.onload = function (evt) {
                img.src = evt.target.result;
            }
            reader.readAsDataURL(file.files[0]);
        }
        else {
            var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
            file.select();
            var src = document.selection.createRange().text;
            div.innerHTML = '<img id=target>';
            var img = document.getElementById('target');
            img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            status = ('rect:' + rect.top + ',' + rect.left + ',' + rect.width + ',' + rect.height);
            div.innerHTML = "<div id=divhead style='width:" + rect.width + "px;height:" + rect.height + "px;margin-top:" + rect.top + "px;margin-left:" + rect.left + "px;" + sFilter + src + "\"'></div>";
        }
        // upload image
        var formData = new FormData();
        //formData.append("avatar", file1.files[0]);
        formData.append("avatar", file.files[0]);
        userId = $.cookie("userId");
        token = $.cookie("X-Auth-Token");
        $.ajax({
            type: "post",
            headers: {
                'userId':userId,
                'X-Auth-Token':token,
            },
            data: formData,
            timeout: 60000,
            contentType: false,
            processData: false,
            dataType: "json",
             url:webUriPrefix+"/v2/profiles/avatar/upload",
            success: function (data) {
                var img = document.getElementById("avatar");
                img.src = data.payload.imageURL;
            },
            error: function (e) {
                
            }
        });

    }
function clacImgZoomParam(maxWidth, maxHeight, width, height) {
        var param = {top: 0, left: 0, width: width, height: height};
        if (width > maxWidth) {
            rate = maxWidth / width;
            param.width = maxWidth;
            param.height = height * rate;
        }
        else if (height > maxHeight) {
            rate = maxHeight / height;
            param.height = maxHeight;
            param.width = width * rate;
        }
        else {
            param.width = width;
            param.height = height;
        }
        return param;
    }