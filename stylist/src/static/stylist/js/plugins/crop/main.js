$(function() {
      'use strict';

      var console = window.console || {
        log: function() {}
      };
      var $image1 = $('#image1');
      var $dataX1 = $('#dataX1');
      var $dataY1 = $('#dataY1');
      var $dataHeight1 = $('#dataHeight1');
      var $dataWidth1 = $('#dataWidth1');
      var options1 = {
        aspectRatio: 1.5,
        zoomOnWheel: 0,
        background: 0,
        viewMode: 1,
        modal: 0,
        preview: '.img-preview',
        crop: function(e) {
          $dataX1.val(Math.round(e.x));
          $dataY1.val(Math.round(e.y));
          $dataHeight1.val(Math.round(e.height));
          $dataWidth1.val(Math.round(e.width));
        }
      };



      $image1.on().cropper(options1);


      // Import image
      var $inputImage1 = $('#inputImage1');
      var URL = window.URL || window.webkitURL;
      var blobURL;
      if (URL) {
        $inputImage1.change(function() {
          var files = this.files;
          var file;
          // if (!$image.data('cropper')) {
          //   alert("1")
          //   return;
          // }
          if (files && files.length) {
            file = files[0];

            if (/^image\/\w+$/.test(file.type)) {
              blobURL = URL.createObjectURL(file);
              $image1.one('built.cropper', function() {

                // Revoke when load complete
                URL.revokeObjectURL(blobURL);
              }).cropper('reset').cropper('replace', blobURL);
              //$inputImage.val('');
            } else {
              window.alert('Please choose an image file.');
            }
          }
        });
      } else {
        $inputImage1.prop('disabled', true).parent().addClass('disabled');
      }
    })