// Write Article Step One Controller
function writeArticleStepOneCtrl($scope, $cookies, $rootScope) {
    $scope.stepControl("one");

    // Initialize simditor object
    var toolbar = [
        'title',
        'image'
    ];
    var editor = new Simditor({
        textarea: $('#editor'),
        placeholder: 'Please write you article content here',
        toolbar: toolbar,
        defaultImage: 'img/a1.jpg',
        upload: {
            url: webUriPrefix + '/v2/stylistDrafts/imageURL',
            params: null,
            fileKey: 'image',
            connectionCount: 10,
            leaveConfirm: 'Uploading is in progress, are you sure to leave this page?'
        },
        pasteImage: true,
        toolbarFloat: true
    });

    if ($scope.editor != null) {
        editor.setValue($scope.editor.getValue());
    }

    // Pass Editor Variables from child controller to parent controller
    $scope.setEditor(editor);

    // Edit article.
    if ($cookies.get("articleId") != null) {
        if (typeof($rootScope.editArticleContent) != "undefined" && $rootScope.editArticleContent != "") {
            var content = $scope.jsonToSimditorHtml($rootScope.editArticleContent);
            $scope.articleTitle.title = content.title;
            $scope.articleTitle.subtitle = content.subtitle;
            $scope.articleTitle.coverImage = content.coverImage;
            editor.setValue(content.content);
            fileList = document.getElementById("fileList");
            var img = document.createElement("img");
            img.src = $scope.articleTitle.coverImage;
            img.setAttribute("width", "45%");
            img.setAttribute("height", "45%");
            fileList.innerHTML = "";
            fileList.appendChild(img);

            $rootScope.resultImageContainerEdit = $rootScope.pointJSONToHtml($rootScope.editArticleContent);

            $rootScope.editArticleContent = "";

            $rootScope.isEditing = true;
            $rootScope.articleId = $cookies.get("articleId");
        } else {
            $rootScope.isEditing = false;
            $rootScope.articleId = null;
        }

        $cookies.put("articleId", null);
    } else {
        $rootScope.isEditing = false;
        $rootScope.articleId = null;
    }
}

// Write Article Step Two Controller
function writeArticleStepTwoCtrl($scope, $modal, $rootScope, $compile) {
    $scope.stepControl("two");
    ///////////
    //Taggify//
    ///////////
    // Define variables For This scope
    $rootScope.contentContainer = document.getElementById("contentContainer");
    $rootScope.resultContainer = document.getElementById("resultContainer");
    $rootScope.currentPointId = -1;
    $rootScope.currentImageId = 0;
    $rootScope.resultImageContainer = [];
    $rootScope.pointedImageCount = 0;

    var imageCount = -1;    // For count

    if ($rootScope.resultImageContainerEdit != []) {
        var parser = new DOMParser()
        for (var i = 0; i < $rootScope.resultImageContainerEdit.length; i++) {
            var img = parser.parseFromString($rootScope.resultImageContainerEdit[i], "text/html").getElementsByTagName("body")[0].children[0].children[0].src;
            for (var j = 0; j < $scope.imageList.length; j++) {
                if ($scope.imageList[j].src == img) {
                    $rootScope.resultImageContainer.push($rootScope.resultImageContainerEdit[i].replace("pointImageContainer" + i, "pointImageContainer" + j));
                    break;
                }
            }
        }
        imageCount = $rootScope.resultImageContainer.length - 1;
    }


    // Click one of the image in the top of the page to set the image into taggify area.
    $scope.chooseImage = function (img, index) {
        $scope.saveImage();
        $rootScope.contentContainer.addEventListener("click", $rootScope.getClickPosition, false);
        $rootScope.currentImageId = index;
        $scope.currentChosenImage = img.src;

        // If this image is taggified. Then put the points in the image.
        for (var i = 0; i < $rootScope.resultImageContainer.length; i++) {
            if ($rootScope.resultImageContainer[i].indexOf(img.src) > -1 && $rootScope.currentImageId == i) {
                // Get all points
                var parser = new DOMParser();
                var elements = parser.parseFromString($rootScope.resultImageContainer[i].replace(/itemInfoPopUp/g, "changePoint").replace(/sk-spinner-pulse/g, "sk-spinner-pulse-dot"), "text/html").getElementsByTagName("body")[0].children[0].children;
                for (var j = 0; j < elements.length; j++) {
                    if (elements[j].tagName == "DIV") {
                        $rootScope.contentContainer.appendChild(elements[j].cloneNode(true));
                    }
                }
                var elements = parser.parseFromString($rootScope.resultImageContainer[i], "text/html").getElementsByTagName("body")[0].children[0].children;
                for (var j = 0; j < elements.length; j++) {
                    if (elements[j].tagName == "DIV") {
                        $rootScope.resultContainer.appendChild(elements[j].cloneNode(true));
                    }
                }
            }
        }
        $compile($rootScope.contentContainer)($scope);
    }

    // Create a point upon the mouse clicked position
    $rootScope.getClickPosition = function (e) {
        imageCount++;
        $rootScope.currentPointId = imageCount;

        clientWidth = 40;
        clientHeight = 40;

        var parentPosition = getPosition(e.currentTarget);
        var xPosition = e.clientX - parentPosition.x - (clientWidth / 2);
        var yPosition = e.clientY - parentPosition.y - (clientHeight / 2);

        var div = document.createElement('div');
        div.style.left = (xPosition / e.currentTarget.offsetWidth) * 100 + '%';
        div.style.top = (yPosition / e.currentTarget.offsetHeight) * 100 + '%';
        div.style.position = 'absolute';
        div.id = 'point' + imageCount;

        div.innerHTML = '<div ng-click="changePoint($event)" id="pointClick' + imageCount + '" class="sk-spinner sk-spinner-pulse-dot"></div>';
        $rootScope.contentContainer.appendChild(div);
        $compile(div)($scope);

        var div2 = div.cloneNode(true);
        div2.id = 'point' + imageCount;

        div2.innerHTML = '<div ng-click="itemInfoPopUp($event)" id="pointClick' + imageCount + '" class="sk-spinner sk-spinner-pulse"></div>';
        $rootScope.resultContainer.appendChild(div2);

        $rootScope.contentContainer.removeEventListener("click", $rootScope.getClickPosition, false);

        /* Pop up a window to pick up item */
        $scope.itemPickPopUp();

    }
    function getPosition(element) {
        var xPosition = 0;
        var yPosition = 0;

        while (element) {
            xPosition += (element.offsetLeft - element.scrollLeft + element.clientLeft);
            yPosition += (element.offsetTop - element.scrollTop + element.clientTop);
            element = element.offsetParent;
        }
        return {x: xPosition, y: yPosition};
    }

    // Change the content attached to the point
    $scope.changePoint = function ($event) {
        $rootScope.contentContainer.removeEventListener("click", $rootScope.getClickPosition, false);
        $rootScope.currentPointId = $event.target.parentNode.id.replace("point", "").replace("Click", "");
        var labels = $event.target.getElementsByTagName("label");
        if (labels.length > 1) {
            var item = {
                itemId: labels[0].id,
                link: labels[1].id
            };
        }
        $scope.itemPickPopUp2(item);
    }

    // Pop up item pick hover window
    $scope.itemPickPopUp = function () {
        var modalInstance = $modal.open({
            templateUrl: 'views/write_steps/itempick.html',
            size: "lg",
            controller: CustomModalInstanceCtrl,
            resolve: {
                itemInfo: function () {
                    return null;
                }
            },
            backdrop: 'static'
        });
    }
    $scope.itemPickPopUp2 = function (itemInfo) {
        var modalInstance1 = $modal.open({
            templateUrl: 'views/write_steps/itempick.html',
            size: "lg",
            controller: CustomModalInstanceCtrl,
            resolve: {
                itemInfo: function () {
                    return itemInfo;
                }
            },
            backdrop: 'static'
        });
    }

    // If the image is selected, Then set the event listener
    if ($scope.currentChosenImage != "") {
        $rootScope.contentContainer.addEventListener("click", $rootScope.getClickPosition, false);
    }

    // Add points to the image
    if ($rootScope.resultImageContainer != []) {
        var img = document.createElement("img");
        img.src = $scope.imageList[0].src;
        $rootScope.currentImageId = 0;

        // If this image is taggified. Then put the points in the image.
        for (var i = 0; i < $rootScope.resultImageContainer.length; i++) {
            if ($rootScope.resultImageContainer[i].indexOf(img.src) > -1) {
                // Get all points
                var parser = new DOMParser();
                var elements = parser.parseFromString($rootScope.resultImageContainer[i].replace(/itemInfoPopUp/g, "changePoint").replace(/sk-spinner-pulse/g, "sk-spinner-pulse-dot"), "text/html").getElementsByTagName("body")[0].children[0].children;
                for (var j = 0; j < elements.length; j++) {
                    if (elements[j].tagName == "DIV") {
                        $rootScope.contentContainer.appendChild(elements[j].cloneNode(true));
                    }
                }
                var elements = parser.parseFromString($rootScope.resultImageContainer[i], "text/html").getElementsByTagName("body")[0].children[0].children;
                for (var j = 0; j < elements.length; j++) {
                    if (elements[j].tagName == "DIV") {
                        $rootScope.resultContainer.appendChild(elements[j].cloneNode(true));
                    }
                }
            }
        }
        $compile($rootScope.contentContainer)($scope);
    }
}

function writeArticleStepThreeCtrl($scope, $rootScope, $compile, $modal, $q, $http, $cookies, $state) {
    $scope.stepControl("three");
    $scope.isEditing = $rootScope.isEditing;
    // Set custon HTML to be trusted
    // Trusted html code will be compiled correctly by angular compiler
    $scope.to_trusted = function (html_code) {
        return $sce.trustAsHtml(html_code);
    }

    // Store taggified image
    function saveTaggifiedImageRequest(url, taggifiedImage, draftId) {
        var deferred = $q.defer();
        var req = {
            method: 'POST',
            url: url,
            headers: {
                'stylistId': $cookies.get("userId"),
                'draftId': draftId
            },
            data: taggifiedImage
        }
        $http(req)
            .success(function (response) {
                deferred.resolve(response);
            })
            .error(function (data, status, headers, config) {
                deferred.reject("Error");
            });
        return deferred.promise;
    }

    // Submit article to the draft
    $scope.submitArticleRequest = function (content) {
        var deferred = $q.defer();

        var url = webUriPrefix + "/v2/stylistDrafts";

        var req = {
            method: 'POST',
            url: url,
            headers: {
                'stylistId': $cookies.get("userId"),
                'type': "article",
                'status': 1
            },
            data: content
        }
        $http(req)
            .success(function (response) {
                deferred.resolve(response);
            })
            .error(function (data, status, headers, config) {
                deferred.reject("Error");
            });
        return deferred.promise;
    }
    $scope.submitArticle = function ($event) {
        $event.currentTarget.children[1].style.display = "inline";
        var article = document.getElementById("theNewArticle");
        var tempDiv = document.createElement("div");
        tempDiv.appendChild(article.cloneNode(true));

        var myPromise = $scope.submitArticleRequest($scope.htmlToJson(tempDiv.innerHTML, $scope.imageList));

        myPromise.then(function (resolve) {
            $scope.message = resolve.message;
            if ($scope.message.code == 0) {

                // Save the taggified images
                if (typeof($rootScope.resultImageContainer) != 'undefined') {
                    for (var k = 0; k < $rootScope.resultImageContainer.length; k++) {
                        if ($rootScope.resultImageContainer[k].indexOf("itemInfoPopUp($event)") > -1) {
                            // Path for saving taggified image
                            var path = webUriPrefix + "/v2/stylistDrafts/taggifiedImages";

                            var myPromise = saveTaggifiedImageRequest(path, $rootScope.resultImageContainer[k].replace("height: 100%", "height: 100vh"), resolve.payload.draft.id);

                            myPromise.then(function (resolve) {
                                console.log(resolve);
                            }, function (reject) {
                                console.log(reject);
                            });
                        }
                    }
                }
                $rootScope.toasterSuccess("Successfully Submit the article.");
                $event.currentTarget.children[1].style.display = "none";
                $state.go('pages.drafts');
            } else {
                $rootScope.toasterFail($scope.message.description);
            }

        }, function (reject) {
            $event.currentTarget.children[1].style.display = "none";
            $rootScope.toasterFail("Fail to submit the article.");
        });
    }

    // Update draft article request
    $scope.updateArticleDraftRequest = function (content) {
        var deferred = $q.defer();

        var url = webUriPrefix + "/v2/stylistDrafts/" + $rootScope.articleId;
        var url1 = webUriPrefix + "/v2/stylistDrafts/taggifiedImages/draft/" + $rootScope.articleId;

        var req = {
            method: 'POST',
            url: url,
            headers: {
                'stylistId': $cookies.get("userId"),
                'type': "article",
                'status': 1
            },
            data: content
        }
        var req2 = {
            method: 'POST',
            url: url1,
            headers: {
                'userId': $cookies.get("userId"),
            }
        }
        $http(req2)
            .success(function (response) {
                $http(req).success(function (response) {
                    deferred.resolve(response);
                }).error(function () {
                    deferred.reject("Error");
                });

            })
            .error(function (data, status, headers, config) {
                deferred.reject("Error");
            });
        return deferred.promise;
    }
    $scope.updateArticleDraft = function ($event) {
        $event.currentTarget.children[1].style.display = "inline";
        var article = document.getElementById("theNewArticle");
        var tempDiv = document.createElement("div");
        tempDiv.appendChild(article.cloneNode(true));

        var myPromise = $scope.updateArticleDraftRequest($scope.htmlToJson(tempDiv.innerHTML, $scope.imageList));

        myPromise.then(function (resolve) {
            $scope.message = resolve.message;

            if ($scope.message.code == 0) {
                $rootScope.toasterSuccess("Successfully update the article.");

                // Save the taggified images
                if (typeof($rootScope.resultImageContainer) != 'undefined') {
                    for (var k = 0; k < $rootScope.resultImageContainer.length; k++) {
                        if ($rootScope.resultImageContainer[k].indexOf("itemInfoPopUp($event)") > -1) {
                            // Path for saving taggified image
                            var path = webUriPrefix + "/v2/stylistDrafts/taggifiedImages";

                            var myPromise = saveTaggifiedImageRequest(path, $rootScope.resultImageContainer[k].replace("height: 100%", "height: 100vh"), $rootScope.articleId);

                            myPromise.then(function (resolve) {
                                console.log(resolve);
                            }, function (reject) {
                                console.log(reject);
                            });
                        }
                    }
                }
                $event.currentTarget.children[1].style.display = "none";

                $state.go('pages.drafts');
            }

            $event.currentTarget.children[1].style.display = "none";
        }, function (reject) {
            $rootScope.toasterSuccess("Fail to update the article.");
            $event.currentTarget.children[1].style.display = "none";
        });
    }

    // Pop up item info
    $scope.getItemRequest = function (url) {
        var deferred = $q.defer();

        var url = url;

        var req = {
            method: 'GET',
            url: url,
        }
        $http(req)
            .success(function (response) {
                deferred.resolve(response);
            })
            .error(function (data, status, headers, config) {
                deferred.reject("Error");
            });
        return deferred.promise;
    }
    $scope.itemInfoPopUp = function ($event) {
        // Get item infomation
        var labels = $event.target.getElementsByTagName("label");
        var itemId = labels[0].id;
        var link = labels[1].id;

        if (itemId == 'undefined') {
            var modalInstance = $modal.open({
                templateUrl: 'views/write_steps/iteminfo.html',
                size: "lg2",
                controller: CustomItemInfoModalInstanceCtrl,
                resolve: {
                    item: function () {
                        return null;
                    },
                    link: function () {
                        return link;
                    }
                }
            });

            return;
        }

        // Get item info from server
        var path = webUriPrefix + "/v2/items/" + itemId;

        var myPromise = $scope.getItemRequest(path);
        myPromise.then(function (resolve) {
            $scope.message = resolve.message;

            if ($scope.message.code == 0) {
                var modalInstance = $modal.open({
                    templateUrl: 'views/write_steps/iteminfo.html',
                    size: "lg2",
                    controller: CustomItemInfoModalInstanceCtrl,
                    resolve: {
                        item: function () {
                            return resolve.payload.item;
                        },
                        link: function () {
                            return link;
                        }
                    }
                });
            }
            return;
        }, function (reject) {
            return reject;
        });
    }

    // Set article content to preview
    var articleContent = document.getElementById("articleContent");
    articleContent.innerHTML = $scope.articleContentWithTaggify;
    $compile(articleContent)($scope);
}

// Item info modal
function CustomItemInfoModalInstanceCtrl($scope, $modalInstance, item, link) {
    $scope.isLink = false;
    $scope.isItem = true;

    $scope.item = item;
    $scope.link = link;
    if (link != null && link != 'undefined' && link != "") {
        $scope.isLink = true;
        $scope.isItem = false;
    }

    $scope.subString = function (s, l) {
        if (s.length < l) {
            return s;
        } else {
            return s.substring(0, l - 1) + " ...";
        }
    }

    $scope.cancel = function () {
        $modalInstance.close();
    }
}

// Controller for item picking modal
function CustomModalInstanceCtrl($rootScope, $scope, $http, $q, $modalInstance, $rootScope, $cookies, itemInfo) {

    $scope.types = [
        {id: '1', text: 'Tops'},
        {id: '3', text: 'Bottoms'},
        {id: '2', text: 'Shoes'},
        {id: '4', text: 'Intimates'},
        {id: '5', text: 'Dress'},
        {id: '6', text: 'Denim'},
        {id: '7', text: 'Swimwear'},
        {id: '8', text: 'Beauty'},
        {id: '9', text: 'Accessories'},
    ];

    // Get brand list from sever
    $scope.brands = [
        // { id: '12', text: 'CK' },
    ];

    // Store search key words.
    $scope.item = {};

    // Store the items got from api.
    $scope.selectedItem = "";         // Store the item selected
    $scope.items = [];
    $scope.ifItemSelected = false;

    $scope.loadmore = false;

    if (typeof(itemInfo) != 'undefined' && itemInfo != "" && itemInfo != null) {
        if (itemInfo.link == 'undefined') {
            $scope.item.link = "";
        } else {
            $scope.item.link = itemInfo.link;
        }
    }

    $scope.subString = function (s, l) {
        if (s.length < l) {
            return s;
        } else {
            return s.substring(0, l - 1) + " ...";
        }
    }

    // Pop up item info
    $scope.getItemRequest = function (url) {
        var deferred = $q.defer();

        var url = url;

        var req = {
            method: 'GET',
            url: url,
        }
        $http(req)
            .success(function (response) {
                deferred.resolve(response);
            })
            .error(function (data, status, headers, config) {
                deferred.reject("Error");
            });
        return deferred.promise;
    }
    $scope.getItem = function () {
        // Get item info from server
        var path = webUriPrefix + "/v2/items/" + itemInfo.itemId;

        var myPromise = $scope.getItemRequest(path);
        myPromise.then(function (resolve) {
            $scope.message = resolve.message;

            if ($scope.message.code == 0) {
                $scope.selectedItem = resolve.payload.item;
            }
            return;
        }, function (reject) {
            return reject;
        });
    }
    if (itemInfo != null && itemInfo.itemId != 'undefined') {
        $scope.getItem();
    }

    // Get brands list from server
    $scope.getBrandsCall = function () {
        var deferred = $q.defer();

        url = webUriPrefix + "/v2/stylistDrafts/brands"
        var req = {
            method: "GET",
            url: url,
        }
        $http(req)
            .success(function (response) {
                deferred.resolve(response);
            })
            .error(function (data, status, headers, config) {
                deferred.reject("Error");
            });

        return deferred.promise;
    }
    $scope.getBrands = function () {
        var myPromise = $scope.getBrandsCall();

        myPromise.then(function (resolve) {
            $scope.brandList = resolve.payload.brands;
            for (var i = 0; i < $scope.brandList.length; i++) {
                var brand = {
                    id: $scope.brandList[i].brandId,
                    text: $scope.brandList[i].brandName
                }
                $scope.brands.push(brand);
            }
            $rootScope.contentContainer.addEventListener("click", $rootScope.getClickPosition, false);
        }, function (reject) {
            console.log(reject);
            $rootScope.contentContainer.addEventListener("click", $rootScope.getClickPosition, false);
        });
    }

    // Search items and get item list from remote
    var oldUrl = "";
    var newUrl = "";
    var offset = 0;
    $scope.searchCall = function () {
        var deferred = $q.defer();

        var typeList = [];
        var brandList = [];

        if (typeof($scope.item.types) != "undefined") {
            for (var i = 0; i < $scope.item.types.length; i++) {
                typeList.push($scope.item.types[i].id);
            }
        }
        if (typeof($scope.item.brands) != "undefined") {
            for (var j = 0; j < $scope.item.brands.length; j++) {
                brandList.push($scope.item.brands[j].id);
            }
        }
        url = webUriPrefix + "/v2/stylistDrafts/searchItems?";
        if (typeof($scope.item.name) != "undefined") {
            url += "itemName=" + $scope.item.name;
        }
        url += "&brandIdList=" + brandList.toString();
        url += "&typeList=" + typeList.toString();
        url += "&limit=" + SEARCHED_ITEM_NUMBER;

        newUrl = url;

        if (newUrl == oldUrl) {
            offset += SEARCHED_ITEM_NUMBER;
        } else {
            offset = 0;
            $scope.items = [];
        }
        oldUrl = newUrl;

        url += "&offset=" + offset;

        var req = {
            method: 'GET',
            url: url,
        }

        $http(req)
            .success(function (response) {
                deferred.resolve(response);
            })
            .error(function (data, status, headers, config) {
                deferred.reject("Error");
            });

        return deferred.promise;
    }
    // Search items
    $scope.search = function () {
        var myPromise = $scope.searchCall();

        myPromise.then(function (resolve) {
            if (resolve.message.code != 0) {
                $rootScope.toasterFail("No more items.");
                return;
            }
            if (resolve.payload != null) {
                if (resolve.payload.items.length < SEARCHED_ITEM_NUMBER) {
                    $scope.loadmore = false;
                } else {
                    $scope.loadmore = true;
                }
                for (var i = 0; i < resolve.payload.items.length; i++) {
                    $scope.items.push(resolve.payload.items[i]);
                }
            }

        }, function (reject) {
            console.log(reject);
        });
    }

    // Add item to the point
    $scope.addItem = function (item) {
        $scope.selectedItem = item;
        $scope.ifItemSelected = true;
        $rootScope.toasterSuccess("Successfully add item.");
    }

    // Delete item of the point
    $scope.deleteItem = function () {
        if ($scope.selectedItem == "") {
            $rootScope.toasterFail("Please add an item firstly.");
        } else {
            $scope.selectedItem = "";
            $scope.ifItemSelected = false;
            $rootScope.toasterSuccess("Successfully delete item.");
        }
    }

    // Save item and link to the point
    $scope.save = function () {

        if ($scope.selectedItem === "" && typeof($scope.item.link) == 'undefined') {
            $scope.delete();
            $modalInstance.close();
            return;
        }

        if (typeof($scope.item.link) != 'undefined' && $scope.selectedItem === "") {
            if ($scope.item.link.indexOf("http://") > -1 || $scope.item.link.indexOf("https://") > -1) {

            } else {
                $rootScope.toasterFail("Invalid Item Link!");
                return;
            }
        }

        // Find the current point
        var elements = $rootScope.resultContainer.getElementsByTagName("div");
        var elements1 = $rootScope.contentContainer.getElementsByTagName("div");
        var currentPointClick;
        var currentPointClick1;
        for (var i = 0; i < elements.length; i++) {
            if (elements[i].id.indexOf("pointClick" + $rootScope.currentPointId) > -1) {
                var currentPointClick = elements[i];
            }
        }
        for (var j = 0; j < elements1.length; j++) {
            if (elements1[j].id.indexOf("pointClick" + $rootScope.currentPointId) > -1) {
                var currentPointClick1 = elements1[j];
            }
        }

        // Setup a div for storing item id and item link
        var div = document.createElement("div");

        div.id = "point" + $rootScope.currentPointId + "img";
        var label1 = document.createElement("label");
        label1.id = $scope.selectedItem.itemId;
        var label2 = document.createElement("label");
        label2.id = $scope.item.link;

        div.appendChild(label1.cloneNode(true));
        div.appendChild(label2.cloneNode(true));

        // Clear the old data before appending new data
        currentPointClick.innerHTML = "";
        currentPointClick.appendChild(div.cloneNode(true));

        currentPointClick1.innerHTML = "";
        currentPointClick1.appendChild(div.cloneNode(true));

        $rootScope.contentContainer.addEventListener("click", $rootScope.getClickPosition, false);
        $modalInstance.dismiss("cancel");
        $rootScope.toasterSuccess("Successfully Save This Tag.");
    };

    // Delete the point
    $scope.delete = function () {
        // Delete current point
        var elements = $rootScope.resultContainer.children;
        for (var i = 0; i < elements.length; i++) {
            if (elements[i].tagName == "DIV" && elements[i].id.indexOf($rootScope.currentPointId) > -1) {
                $rootScope.resultContainer.removeChild(elements[i]);
                i--;
            }
        }
        elements = $rootScope.contentContainer.children;
        for (var i = 0; i < elements.length; i++) {
            if (elements[i].tagName == "DIV" && elements[i].id.indexOf($rootScope.currentPointId) > -1) {
                $rootScope.contentContainer.removeChild(elements[i]);
                i--;
            }
        }

        $rootScope.contentContainer.addEventListener("click", $rootScope.getClickPosition, false);
        $modalInstance.close();
    }

    $scope.cancel = function () {
        if ($scope.selectedItem == "" && typeof($scope.item.link) == 'undefined') {
            $scope.delete();
            $rootScope.contentContainer.addEventListener("click", $rootScope.getClickPosition, false);
            $modalInstance.close();
        } else {
            $scope.save();
        }
    };

    // Call initial methods
    $scope.getBrands();
};

// Write article contriller
function writeCtrl($scope, $http, $state, $rootScope, toaster, $q, $cookies) {

    // Define the variables which are visible to all the child controllers.
    $scope.editor;
    $scope.articleContent = "";             // HTML String
    $scope.articleContentWithTaggify = "";  // Article with taggified images
    $scope.articleTitle = {
        title: "",                          // Title of the article
        subtitle: "",                       // Subtitle of the article
        coverImage: ""
    };
    $scope.imageList = [];                  // Store all the image urls appeared in the article
    $scope.currentChosenImage = "";         // Store the image selected for now
    $scope.hasImage = false;

    $rootScope.resultImageContainerEdit = [];
    $rootScope.resultImageContainer = [];

    ///////////
    //Toaster//
    ///////////
    $rootScope.toasterSuccess = function (message) {
        toaster.success({body: message});
    };
    $rootScope.toasterWarning = function (message) {
        toaster.warning({
            body: message
        });
    };
    $rootScope.toasterFail = function (message) {
        toaster.pop({
            type: 'error',
            body: message,
            showCloseButton: true,
            timeout: 6000
        });
    };

    // Store taggified image
    function saveTaggifiedImageRequest(url, taggifiedImage) {
        var deferred = $q.defer();
        var req = {
            method: 'POST',
            url: url,
            headers: {
                'stylistId': $cookies.get("userId")
            },
            data: taggifiedImage
        }
        $http(req)
            .success(function (response) {
                deferred.resolve(response);
            })
            .error(function (data, status, headers, config) {
                deferred.reject("Error");
            });
        return deferred.promise;
    }

    // Clean up the points in the image
    $scope.pointCleanUp = function () {
        var divElements1 = $rootScope.resultContainer.children;
        var divElements2 = $rootScope.contentContainer.children;

        var j = 0;
        for (j = 0; j < divElements1.length; j++) {
            if (divElements1[j].tagName == "DIV") {
                $rootScope.resultContainer.removeChild(divElements1[j]);
                j--;
            }
        }
        for (j = 0; j < divElements2.length; j++) {
            if (divElements2[j].tagName == "DIV") {
                $rootScope.contentContainer.removeChild(divElements2[j]);
                j--;
            }
        }
    }

    // Save taggified image
    $scope.saveImage = function () {
        var i = 0;
        for (i = 0; i < $scope.imageList.length; i++) {
            if ($scope.currentChosenImage == $scope.imageList[i].src) {
                var tempDiv = document.createElement('div');
                var tempContainer = $rootScope.resultContainer.cloneNode(true);
                tempContainer.removeAttribute("style");
                tempContainer.setAttribute("style", 'position: relative;width: 100%;height:' +
                    ' 100%;overflow: hidden;margin-left: auto;margin-right: auto;');
                tempContainer.id = "pointImageContainer" + $rootScope.currentImageId;
                tempDiv.appendChild(tempContainer);

                $scope.pointCleanUp();

                // Check if the image is poined.
                // If it has been pointed, replace it with the new pointed image.
                // Otherwise, Put the new image into the array.
                var isPointed = false;
                for (j = 0; j < $rootScope.resultImageContainer.length; j++) {
                    if ($rootScope.resultImageContainer[j].indexOf("pointImageContainer" + $rootScope.currentImageId) > -1) {
                        isPointed = true;
                        $rootScope.resultImageContainer[j] = tempDiv.innerHTML;
                    }
                }
                if (!isPointed) {
                    $rootScope.resultImageContainer.push(tempDiv.innerHTML);
                }
            }
        }
    }

    ////////////////////
    //Step One Methods//
    ////////////////////
    // Set simditor object and set simditor content
    $scope.setEditor = function (editor) {
        $scope.editor = editor;
    }
    $scope.setSimditorContent = function () {
        $scope.editor.setValue($scope.articleContent);
    }

    $scope.uploadingCoverImage = false;
    // Upload Cover Image
    $scope.uploadCoverImage = function ($event) {
        $scope.uploadingCoverImage = true;
        $event.currentTarget.children[2].style.display = "inline";

        var formData = new FormData();
        cropper1 = [];
        cropper1[0] = $('#dataX1').val();
        cropper1[1] = $('#dataY1').val();
        cropper1[2] = $('#dataWidth1').val();
        cropper1[3] = $('#dataHeight1').val();
        cropperString = cropper1.join('-');
        formData.append("image", inputImage1.files[0]);
        $http({
            method: 'POST',
            url: webUriPrefix + "/v2/stylistDrafts/coverImage/imageURL?cropperString=" + cropperString,
            headers: {
                'Content-Type': undefined
            },
            data: formData,
            transformRequest: function (data, headersGetterFunction) {
                return data;
            }
        })
            .success(function (response) {
                $scope.articleTitle.coverImage = response.payload.url;
                $event.currentTarget.children[2].style.display = "none";
                $rootScope.toasterSuccess("Successfully upload cover image.")
                $scope.uploadingCoverImage = false
            })
            .error(function (data, status) {
                $rootScope.toasterFail("Fail to upload cover image.")
                $event.currentTarget.children[2].style.display = "none";
                $scope.uploadingCoverImage = false;
            })
    }

    ////////////////////
    //Step Two Methods//
    ////////////////////
    $scope.goToStepTwo = function () {
        if ($scope.editor.body.find('img.uploading').length > 0 || $scope.uploadingCoverImage) {
            $rootScope.toasterWarning("Please wait until all the images are uploaded.");
            return;
        }
        if ($scope.articleTitle.coverImage == "") {
            $rootScope.toasterWarning("Please upload the cover image before going to next step.");
            return;
        }

        // Clear the array first
        $scope.imageList = [];

        // Get all the image in the article.
        var parser = new DOMParser();
        var images = parser.parseFromString($scope.editor.getValue(), "text/html").getElementsByTagName('img');
        var src;
        for (var i = 0; i < images.length; i++) {
            src = images[i].src;
            if (src.indexOf("http://img.cdn.whatsmode.com/images") > -1) {
                var img = {
                    image_ratio: images[i].getAttribute("data-image-size"),
                    src: images[i].src
                };

                $scope.imageList.push(img);
            }
        }

        // Get and store the article content
        $scope.articleContent = $scope.editor.getValue();
        console.log($scope.editor.getValue());

        if ($scope.articleTitle.title == "") {
            $rootScope.toasterFail("Please input the title of your article.")
            return;
        }
        if ($scope.articleContent == "") {
            $rootScope.toasterFail("Please input content of your article.")
            return;
        }

        if ($scope.imageList.length > 0) {
            $scope.hasImage = true;
            // Set current chosen image as the first image as default
            $scope.currentChosenImage = $scope.imageList[0].src;


            // Go to step two page : taggify the images
            $state.go("app.write.step_two");
        } else {
            // If there are no image in the article, go to step three.
            $scope.goToStepThree();
        }
    }

    //////////////////////
    //Step Three Methods//
    //////////////////////
    $scope.goToStepThree = function () {
        $scope.saveImage();

        var parser = new DOMParser();
        var articleNode = parser.parseFromString($scope.articleContent, "text/html").getElementsByTagName("body");
        var elements = articleNode[0].children;
        var cloneElements = [];

        // Replace the unpointed image with pointed image
        for (var i = 0; i < elements.length; i++) {
            if (elements[i].innerHTML.indexOf('img') > -1) {
                var imgs = parser.parseFromString(elements[i].innerHTML, "text/html").getElementsByTagName("img");

                for (var k = 0; k < imgs.length; k++) {
                    var img = imgs[k].cloneNode(true);

                    var setornot = 0;
                    for (var j = 0; j < $rootScope.resultImageContainer.length; j++) {
                        var tempdiv = document.createElement("div");
                        tempdiv.innerHTML = $rootScope.resultImageContainer[j];
                        var container = parser.parseFromString($rootScope.resultImageContainer[j], "text/html");
                        var imgElements = container.getElementsByTagName("img");

                        if (img.src == imgElements[0].src) {
                            setornot = 1;
                            cloneElements.push(tempdiv.cloneNode(true));
                        }
                    }
                    if (setornot == 0) {
                        img.className = "col-lg-12";
                        img.setAttribute("width", "100%");
                        img.setAttribute("height", "100%");
                        cloneElements.push(img);
                    }
                }
            } else {
                cloneElements.push(elements[i].cloneNode(true));
            }
        }

        var div = document.createElement('div');
        var j = 0;
        for (var j = 0; j < cloneElements.length; j++) {
            div.appendChild(cloneElements[j]);
        }
        $scope.articleContentWithTaggify = div.innerHTML;

        // Go to step three page : preview the article
        $state.go("app.write.step_three");
    }

    //////////////////////////////////////
    //STRIP HTML CLEAN USELESS HTML TAGS//
    //////////////////////////////////////
    function strip(html) {
        var tmp = document.createElement("DIV");
        tmp.innerHTML = html;
        return tmp.textContent || tmp.innerText;
    }


    ////////////////
    //HTML TO JSON//
    ////////////////
    $scope.htmlToJson = function (htmlString, imageList) {
        var articleJson = {
            coverImage: "",
            author: $cookies.get("nickname"),
            title: "",
            subtitle: "",
            content: []
        };

        var parser = new DOMParser();
        var html = parser.parseFromString(htmlString, "text/html").getElementsByTagName("body")[0].children[0];

        var coverImage = html.children[0].src;
        var title = html.getElementsByTagName("h1")[0].innerHTML;
        var subtitle = html.getElementsByTagName("h3")[0].innerHTML;

        var articleContents = html.children[2].children;

        articleJson.coverImage = coverImage;
        articleJson.title = title;
        articleJson.subtitle = subtitle;

        for (var i = 0; i < articleContents.length; i++) {
            if (articleContents[i].tagName == "H3") {
                var text = {
                    type: "text",
                    content: "",
                    bold: true
                };

                text.content = strip(articleContents[i].innerHTML);

                if (text.content != '\n') {
                    articleJson.content.push(text);
                }
            } else if (articleContents[i].tagName == "P") {
                var text = {
                    type: "",
                    content: "",
                    bold: false
                };
                text.type = "text";
                text.content = strip(articleContents[i].innerHTML);
                if (text.content != '\n') {
                    articleJson.content.push(text);
                }
            } else if (articleContents[i].tagName == "DIV") {
                var image = {
                    type: "image",
                    defaultImage: "",
                    widthHeightRatio: "",
                    url: "",
                    items: "",
                    points: []
                };
                if (typeof(articleContents[i].children[0].children[0]) != 'undefined') {
                    image.defaultImage = articleContents[i].children[0].children[0].src;

                    var points = articleContents[i].children[0].children;
                    var items = [];
                    for (var j = 1; j < points.length; j++) {
                        try {
                            var point = {
                                left: "",
                                top: "",
                                itemId: "",
                                link: ""
                            };
                            point.left = points[j].style.left;
                            point.top = points[j].style.top;

                            if (typeof(points[j].children[0]) != 'undefined' && typeof(points[j].children[0].children[0]) != 'undefined') {
                                point.itemId = points[j].children[0].children[0].children[0].id;
                                point.link = points[j].children[0].children[0].children[1].id;
                            }

                            image.points.push(point);
                            if (point.itemId != 'undefined') {
                                items.push(point.itemId);
                            }
                        } catch (err) {

                        }
                    }
                    image.items = items.join('-');

                    for (var j = 0; j < imageList.length; j++) {
                        if (imageList[j].src == image.defaultImage) {
                            if (imageList[j].image_ratio == "") {
                                image.widthHeightRatio = "null";
                            } else {
                                image.widthHeightRatio = imageList[j].image_ratio;
                            }
                        }
                    }

                    articleJson.content.push(image);
                }
            } else if (articleContents[i].tagName == "IMG") {
                var image = {
                    type: "image",
                    defaultImage: "",
                    widthHeightRatio: "",
                    url: "",
                    items: "",
                    points: []
                };

                image.defaultImage = articleContents[i].src;

                for (var k = 0; k < imageList.length; k++) {
                    if (imageList[k].src == image.defaultImage) {
                        image.widthHeightRatio = imageList[k].image_ratio;
                    }
                }

                articleJson.content.push(image);
            }
        }

        return JSON.stringify(articleJson);
    }

    //////////////////////
    //Write Step Control//
    //////////////////////
    $scope.stepControl = function (step) {
        var step_one = document.getElementById("article_step_one");
        var step_two = document.getElementById("article_step_two");
        var step_three = document.getElementById("article_step_three");
        if (step == "one") {
            step_one.className = "current";
            step_two.className = "";
            step_three.className = "";
        } else if (step == "two") {
            step_one.className = "";
            step_two.className = "current";
            step_three.className = "";
        } else if (step == "three") {
            step_one.className = "";
            step_two.className = "";
            step_three.className = "current";

        }
    }

    /////////////////////////////////
    //JSON TO SIMDITOR CONTENT HTML//
    /////////////////////////////////
    $scope.jsonToSimditorHtml = function (jsonArticle) {
        var article = JSON.parse(jsonArticle);

        var title = article.title;
        var subtitle = article.subtitle;
        var coverImage = article.coverImage;
        var content = article.content;

        var simditorContentContainer = document.createElement("div");

        for (var i = 0; i < content.length; i++) {
            if (content[i].type == "text") {
                if (content[i].bold == false) {
                    var p = document.createElement("p");
                    p.innerHTML = content[i].content;
                    simditorContentContainer.appendChild(p.cloneNode(true));
                } else {
                    var h3 = document.createElement("h3");
                    h3.innerHTML = content[i].content;
                    simditorContentContainer.appendChild(h3.cloneNode(true));
                }
            } else if (content[i].type == "image") {
                var p = document.createElement("p");
                var img = document.createElement("img");
                img.src = content[i].defaultImage;
                img.setAttribute("width", "100%");
                img.setAttribute("height", "100%");
                img.setAttribute("data-image-size", content[i].widthHeightRatio);

                p.appendChild(img.cloneNode(true));
                simditorContentContainer.appendChild(p.cloneNode(true));
            }
        }

        var simditorArticle = {
            title: title,
            subtitle: subtitle,
            coverImage: coverImage,
            content: simditorContentContainer.innerHTML
        };

        return simditorArticle;
    }

    ////////////////////////////
    //POINT JSON TO POINT HTML//
    ////////////////////////////
    $rootScope.pointJSONToHtml = function (pointJson) {
        var pointJson = JSON.parse(pointJson);

        var imageContainers = [];

        var imageCount = 0;
        for (var i = 0; i < pointJson.content.length; i++) {
            if (pointJson.content[i].type == "image") {
                var imageContainer = document.createElement("div");
                imageContainer.id = "pointImageContainer" + imageCount;
                imageContainer.setAttribute("style", 'position: relative;width: 100%;height:' +
                    ' 100%;overflow: hidden;margin-left: auto;margin-right: auto;');

                var img = document.createElement("img");
                img.src = pointJson.content[i].defaultImage;
                img.setAttribute("width", "100%");
                img.setAttribute("height", "100%");

                imageContainer.appendChild(img.cloneNode(true));
                for (var j = 0; j < pointJson.content[i].points.length; j++) {
                    var jsonPoint = pointJson.content[i].points[j];
                    var point = document.createElement("div");
                    point.id = "point" + j;
                    point.setAttribute("style", "position:absolute;left: " + jsonPoint.left + ";top: " + jsonPoint.top + ";")

                    var pointClick = document.createElement("div");
                    pointClick.id = "pointClick" + j;
                    pointClick.setAttribute("ng-click", "itemInfoPopUp($event)");
                    pointClick.setAttribute("class", "sk-spinner sk-spinner-pulse");

                    var pointInfo = document.createElement("div");
                    pointInfo.id = "point" + imageCount + "img";

                    var labelItem = document.createElement("label");
                    labelItem.id = pointJson.content[i].points[j].itemId;

                    var labelLink = document.createElement("label");
                    labelLink.id = pointJson.content[i].points[j].link;

                    pointInfo.appendChild(labelItem.cloneNode(true));
                    pointInfo.appendChild(labelLink.cloneNode(true));
                    pointClick.appendChild(pointInfo.cloneNode(true));
                    point.appendChild(pointClick.cloneNode(true));

                    imageContainer.appendChild(point.cloneNode(true));
                }

                var tempDiv = document.createElement("div");
                tempDiv.innerHTML = "";
                tempDiv.appendChild(imageContainer.cloneNode(true));

                imageContainers.push(tempDiv.innerHTML);

                imageCount++;
            }
        }

        return imageContainers;
    }


    ////////////////////////////////
    //JSON TO ARTICLE PREVIEW HTML//
    ////////////////////////////////
    $scope.jsonToHtml = function (jsonArticle) {
        var article = JSON.parse(jsonArticle);

        var title = article.title;
        var subtitle = article.subtitle;
        var coverImage = article.coverImage;
        var content = article.content;

        var articleWrapper = document.createElement("div");

        var article = document.createElement("div");
        article.id = "theNewArticle";
        article.className = "ibox";

        var articleCoverImage = document.createElement("img");
        articleCoverImage.src = coverImage;
        articleCoverImage.className = "col-lg-12";

        var articleTitleWrapper = document.createElement("div");
        articleTitleWrapper.className = "text-center article-title";
        var articleTitle = document.createElement("h1");
        articleTitle.innerHTML = title;
        var subtitleWrapper = document.createElement("div");
        subtitleWrapper.className = "pull-right";
        var articleSubtitle = document.createElement("h3");
        articleSubtitle.innerHTML = subtitle;
        articleTitleWrapper.appendChild(articleTitle);
        subtitleWrapper.appendChild(articleSubtitle);
        articleTitleWrapper.appendChild(subtitleWrapper);

        var articleContentWrapper = document.createElement("div");

        for (var i = 0; i < content.length; i++) {
            if (content[i].type == "text") {
                var text = document.createElement("p");
                text.innerHTML = content[i].content;

                articleContentWrapper.appendChild(text.cloneNode(true));
            } else if (content[i].type == "image") {
                var pointedImageWrapper = document.createElement("div");

                var pointedImageContainer = document.createElement("div");
                pointedImageContainer.style.position = "relative";
                pointedImageContainer.style.width = "100%";
                pointedImageContainer.style.height = "100%";
                pointedImageContainer.style.overflow = "hidden";
                pointedImageContainer.style.marginRight = "auto";
                pointedImageContainer.style.marginLeft = "auto";

                var pointedImage = document.createElement("img");
                pointedImage.src = content[i].defaultImage;
                pointedImage.setAttribute("width", "100%");
                pointedImage.setAttribute("height", "100%");

                pointedImageContainer.appendChild(pointedImage);

                for (var j = 0; j < content[i].points.length; j++) {
                    var pointWrapper = document.createElement("div");
                    pointWrapper.id = "point" + j;
                    pointWrapper.style.left = content[i].points[j].left;
                    pointWrapper.style.top = content[i].points[j].top;
                    pointWrapper.style.position = "absolute";

                    var clickWrapper = document.createElement("div");
                    clickWrapper.setAttribute("ng-click", "itemInfoPopUp($event)");
                    clickWrapper.id = "pointClick" + j;
                    clickWrapper.className = "sk-spinner sk-spinner-pulse";

                    var labelWrapper = document.createElement("div");
                    var labelItem = document.createElement("label");
                    labelItem.id = content[i].points[j].itemId;
                    var labelLink = document.createElement("label");
                    labelLink.id = content[i].points[j].link;

                    labelWrapper.appendChild(labelItem);
                    labelWrapper.appendChild(labelLink);
                    clickWrapper.appendChild(labelWrapper);
                    pointWrapper.appendChild(clickWrapper);

                    pointedImageContainer.appendChild(pointWrapper);
                }

                pointedImageWrapper.appendChild(pointedImageContainer.cloneNode(true));

                articleContentWrapper.appendChild(pointedImageWrapper);
            }
        }

        article.appendChild(articleCoverImage.cloneNode(true));
        article.appendChild(articleTitle.cloneNode(true));
        article.appendChild(articleContentWrapper.cloneNode(true));

        articleWrapper.appendChild(article.cloneNode(true));

        return articleWrapper.innerHTML;
    }
}

// Taggified Image Contriller
function taggifiedImageCtrl($scope, $stateParams, $q, $http, $modal, $cookies, $compile) {

    // Pop up item info
    $scope.getItemRequest = function (url) {
        var deferred = $q.defer();

        var url = url;

        var req = {
            method: 'GET',
            url: url,
        }
        $http(req)
            .success(function (response) {
                deferred.resolve(response);
            })
            .error(function (data, status, headers, config) {
                deferred.reject("Error");
            });
        return deferred.promise;
    }
    $scope.itemInfoPopUp = function ($event) {
        // Get item infomation
        var labels = $event.target.getElementsByTagName("label");
        var itemId = labels[0].id;
        var link = labels[1].id;

        if (itemId == 'undefined') {
            var modalInstance = $modal.open({
                templateUrl: 'views/write_steps/iteminfo.html',
                size: "lg",
                controller: CustomItemInfoModalInstanceCtrl,
                resolve: {
                    item: function () {
                        return null;
                    },
                    link: function () {
                        return link;
                    }
                }
            });

            return;
        }

        // Get item info from server
        var path = webUriPrefix + "/v2/items/" + itemId;

        var myPromise = $scope.getItemRequest(path);
        myPromise.then(function (resolve) {
            $scope.message = resolve.message;

            if ($scope.message.code == 0) {
                var modalInstance = $modal.open({
                    templateUrl: 'views/write_steps/iteminfo.html',
                    size: "lg",
                    controller: CustomItemInfoModalInstanceCtrl,
                    resolve: {
                        item: function () {
                            return resolve.payload.item;
                        },
                        link: function () {
                            return link;
                        }
                    }
                });
            }
            return;
        }, function (reject) {
            return reject;
        });
    }

    // Get taggified image info
    function getTaggifiedImageRequest(url) {
        var deferred = $q.defer();
        var req = {
            method: 'GET',
            url: url,
        }
        $http(req)
            .success(function (response) {
                deferred.resolve(response);
            })
            .error(function (data, status, headers, config) {
                deferred.reject("Error");
            });
        return deferred.promise;
    }

    $scope.getTaggifiedImage = function (id) {
        var path = webUriPrefix + "/v2/stylistDrafts/taggifiedImages/" + id;

        var myPromise = getTaggifiedImageRequest(path);
        myPromise.then(function (resolve) {
            $scope.message = resolve.message;

            if ($scope.message.code == 0) {
                var taggifiedImageContainer = document.getElementById("mode_taggifiedImageContainer");
                taggifiedImageContainer.innerHTML = resolve.payload.taggifiedImage.taggifiedImage;
                $compile(taggifiedImageContainer)($scope);

            } else {
                return null;
            }

        }, function (reject) {
            return reject;
        });
    }

    // Get taggified image
    $scope.getTaggifiedImage($stateParams.imageId);
}

// List Taggified Images Controller
function listTaggifiedImagesCtrl($scope, $http, $q, $modal, $cookies, $sce) {

    $scope.offset = 0;
    $scope.taggifiedImages = [];
    $scope.loadmore = true;

    $scope.taggifiedImages1 = [];
    $scope.taggifiedImages2 = [];
    $scope.taggifiedImages3 = [];

    $scope.toProperSize = function (s) {
        return s.replace("height: 100vh", "height: 100%").replace("height:100vh", "height: 100%");
    }

    // Pop up item info
    $scope.getItemRequest = function (url) {
        var deferred = $q.defer();

        var url = url;

        var req = {
            method: 'GET',
            url: url,
        }
        $http(req)
            .success(function (response) {
                deferred.resolve(response);
            })
            .error(function (data, status, headers, config) {
                deferred.reject("Error");
            });
        return deferred.promise;
    }
    $scope.itemInfoPopUp = function ($event) {
        // Get item infomation
        var labels = $event.target.getElementsByTagName("label");
        var itemId = labels[0].id;
        var link = labels[1].id;

        if (itemId == 'undefined') {
            var modalInstance = $modal.open({
                templateUrl: 'views/write_steps/iteminfo.html',
                size: "lg",
                controller: CustomItemInfoModalInstanceCtrl,
                resolve: {
                    item: function () {
                        return null;
                    },
                    link: function () {
                        return link;
                    }
                }
            });

            return;
        }

        // Get item info from server
        var path = webUriPrefix + "/v2/items/" + itemId;

        var myPromise = $scope.getItemRequest(path);
        myPromise.then(function (resolve) {
            $scope.message = resolve.message;

            if ($scope.message.code == 0) {
                var modalInstance = $modal.open({
                    templateUrl: 'views/write_steps/iteminfo.html',
                    size: "lg",
                    controller: CustomItemInfoModalInstanceCtrl,
                    resolve: {
                        item: function () {
                            return resolve.payload.item;
                        },
                        link: function () {
                            return link;
                        }
                    }
                });
            }
            return;
        }, function (reject) {
            return reject;
        });
    }

    // List Taggified Images
    $scope.listTaggifiedImagesRequest = function (url) {
        var deferred = $q.defer();
        var url = url;
        var req = {
            method: 'GET',
            url: url,
        }
        $http(req)
            .success(function (response) {
                deferred.resolve(response);
            })
            .error(function (data, status, headers, config) {
                deferred.reject("Error");
            });
        return deferred.promise;
    }
    $scope.listTaggifiedImages = function () {
        var url = webUriPrefix + "/v2/stylistDrafts/taggifiedImages/stylists/" + $cookies.get("userId") + "?offset=" + $scope.offset + "&limit=9";
        var myPromise = $scope.listTaggifiedImagesRequest(url);
        myPromise.then(function (resolve) {
            if (resolve.payload != null) {
                var count = 1;
                for (var i = 0; i < resolve.payload.taggifiedImages.length; i++) {

                    var imagesLength = [];
                    imagesLength.push($scope.taggifiedImages1.length);
                    imagesLength.push($scope.taggifiedImages2.length);
                    imagesLength.push($scope.taggifiedImages3.length);

                    if (imagesLength[0] == imagesLength[1] && imagesLength[1] == imagesLength[2]) {
                        count = 1;
                    }

                    if (imagesLength[0] < imagesLength[1]) {
                        if (imagesLength[0] < imagesLength[2]) {
                            count = 1;
                        } else {
                            count = 3;
                        }
                    } else {
                        if (imagesLength[1] < imagesLength[2]) {
                            count = 2;
                        } else {
                            count = 3
                        }
                    }

                    switch (count) {
                        case (1):
                            $scope.taggifiedImages1.push(resolve.payload.taggifiedImages[i]);
                            break;
                        case (2):
                            $scope.taggifiedImages2.push(resolve.payload.taggifiedImages[i]);
                            break;
                        case (3):
                            $scope.taggifiedImages3.push(resolve.payload.taggifiedImages[i]);
                            break;
                        default:
                            break;
                    }

                    $scope.taggifiedImages.push(resolve.payload.taggifiedImages[i]);
                }
                $scope.offset += 9;
                $scope.loadmore = true;
            } else {
                $scope.loadmore = false;
            }

        }, function (reject) {
            console.log(reject);
        });
    }

    // Get taggified image scripts and html
    $scope.getPointImageScripts = function ($event) {
        var customScript = '<script src="http://img.cdn.whatsmode.com/web/mode/mode.js"></script>';

        var tempDiv = document.createElement("div");
        var imageInfo = document.createElement("div");
        imageInfo.id = "mode_pointImageContainer";
        imageInfo.setAttribute("class", "mode_taggedImage");
        imageInfo.setAttribute("data-id", $event.currentTarget.id);
        tempDiv.appendChild(imageInfo);
        var image = {
            imageContainer: customScript + tempDiv.innerHTML,
        };

        var modalInstance = $modal.open({
            templateUrl: 'views/write_steps/pointimage.html',
            size: "lg2",
            controller: CustomImageModalInstanceCtrl,
            resolve: {
                image: function () {
                    return image;
                }
            }
        });

        return image;
    }

    // Set custon HTML to be trusted
    // Trusted html code will be compiled correctly by angular compiler
    $scope.to_trusted = function (html_code) {
        return $sce.trustAsHtml(html_code);
    }

    $scope.listTaggifiedImages();
}

function CustomImageModalInstanceCtrl($scope, $modalInstance, image) {
    $scope.image = image;

    $scope.close = function () {
        $modalInstance.dismiss('cancel');
    }
}

angular
    .module('inspinia')
    .controller('taggifiedImageCtrl', taggifiedImageCtrl)
    .controller('listTaggifiedImagesCtrl', listTaggifiedImagesCtrl)
    .controller('writeArticleStepOneCtrl', writeArticleStepOneCtrl)
    .controller('writeArticleStepTwoCtrl', writeArticleStepTwoCtrl)
    .controller('writeArticleStepThreeCtrl', writeArticleStepThreeCtrl)
    .controller('writeCtrl', writeCtrl);