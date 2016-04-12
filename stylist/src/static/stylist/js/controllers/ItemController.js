/**
 * Created by Lei on 3/25/16.
 */

/**
 * Select items
 *
 * @param $scope
 * @param $cookies
 * @param utilService
 * @param httpService
 * @constructor
 */
function SelectItemController($rootScope, $scope, $compile, $modal, utilService, httpService) {

    // Items container
    $scope.items = [];

    $scope.offset = 0;
    const limit = 20;

    $scope.page = 1;
    $scope.total = 0;
    // List cooperation items for the stylists
    $scope.listFirstPage = function (id) {
        id = parseInt(id);

        $scope.offset = (id - 1) * limit;
        const url = webUriPrefix + "/v2/cooperationitems/summary?limit=" + limit + "&offset=" + $scope.offset;

        httpService.GET(url, null, null)
            .then(function (response) {
                if (response.message.code == 0) {
                    $scope.page = id;
                    $scope.payload = response.payload;
                    $scope.total = response.payload.total;
                    $scope.items = $scope.payload.items;
                    $scope.totalPage = Math.ceil($scope.total / limit);
                    $scope.mod = $scope.total % limit;
                    page($scope.page, $scope.totalPage );
                } else {
                    utilService.toasterFail("No More Items!");
                }

            }, function () {
                console.log("Error");
            })
    };

    function page(page,totalPage){
        page=parseInt(page);
        var list=[];
        if(totalPage<5){
            for(i=1;i<=totalPage;i++){
                list.push(i);
            }
        } else {
            if(page <= 3 )list=[1,2,3,4,5];
            else if(page>= totalPage-3) list=[totalPage-4,totalPage-3,totalPage-2,totalPage-1,totalPage];
            else list=[page-2,page-1,page,page+1,page+2];
        }
        str="";
        if (page == 1) {
            str+='<li class="footable-page-arrow disabled"><a href="#" ng-click="listFirstPage(1)"><<</a></li>';
            str+='<li class="footable-page-arrow disabled"><a href="#" ng-click="listFirstPage(1)"><</a></li>';
        }
        else {
            str+='<li class="footable-page-arrow"><a href="#" ng-click="listFirstPage(1)"><<</a></li>';
            str+='<li class="footable-page-arrow"><a href="#" ng-click="listFirstPage(page - 1)"><</a></li>';
        }

        for(i=0;i<list.length;i++){
            if (list[i] == page)
                str+=' <li class = "footable-page active"><a href="#" ng-click="listFirstPage('+list[i]+')" >'+list[i]+'</a></li>';
            else
                str+=' <li class="footable-page"><a href="#" ng-click="listFirstPage('+list[i]+')" >'+list[i]+'</a></li>';
        }
        if (page == totalPage) {
            str+='<li class="footable-page-arrow disabled"><a href="#" ng-click="listFirstPage(page)">></a></li>';
            str+='<li class="footable-page-arrow disabled"><a href="#" ng-click="listFirstPage(totalPage)">>></a></li>';
        }
        else {
            str+='<li class="footable-page-arrow"><a href="#" ng-click="listFirstPage(page + 1)">></a></li>';
            str+='<li class="footable-page-arrow"><a href="#" ng-click="listFirstPage(totalPage)">>></a></li>';
        }

        //$compile(str)($scope);
        var pagination = document.getElementById("pagination");
        pagination.innerHTML=str;
        $compile($(pagination).contents())($scope);
    }

    // Select one item if the stylist wants it
    $scope.selectItem = function (item) {
        const url = webUriPrefix + "/v2/stylists/" + $cookies.get("userId") + "/cooperationitems/" + item.id;

        httpService.POST(url, null, null)
            .then(function (response) {
                if (response.message.code == 0) {
                    item.quantity -= 1;
                    utilService.toasterSuccess("Successfully Selected! See it in your item list.");
                } else if (response.message.code == 44) {
                    utilService.toasterWarning("No more such item!");

                    // If there is no such item any more, remove it.
                    var index = $scope.items.indexOf(item);
                    if (index > -1) {
                        $scope.items.splice(index, 1);
                    }
                } else if (response.message.code == 24) {
                    utilService.toasterWarning("You have selected this item!")
                } else {
                    utilService.toasterFail("Error occurred in the server. Please try it lately.");
                }

            }, function () {
                console.log("Error");
            })
    };

    $scope.goForDetail = function (item) {
        $rootScope.currentItemNumber = item.itemNumber;
        var modalInstance = $modal.open({
            templateUrl: 'views/items/ItemDetail.html',
            size: "lg",
            controller: ItemDetailController,
            resolve: {
                item: function () {
                    return item;
                }
            }
        });
    };

    // List some items while firstly loading the page
    $scope.listFirstPage($scope.page);
}

/**
 * Item Detail Controller
 *
 * @param $scope
 * @param utilService
 * @param httpService
 * @constructor
 */
function ItemDetailController($rootScope, $scope, $cookies, $modalInstance, utilService, httpService, item){
    $scope.itemNumber = $rootScope.currentItemNumber;
    $scope.items = [];

    $scope.currentItem = "";

    const limit = 10;
    $scope.offset = 0;

    // List cooperation items for the stylists
    $scope.listItems = function () {
        const url = webUriPrefix + "/v2/cooperationitems?itemNumber="+ $scope.itemNumber;

        httpService.GET(url, null, null)
            .then(function (response) {
                if (response.message.code == 0) {

                    for (var i = 0; i < response.payload.items.length; i++) {
                        if(response.payload.items[i].size != null)
                            response.payload.items[i].sizecolor = response.payload.items[i].size + ' ' + response.payload.items[i].color;
                        else
                            response.payload.items[i].sizecolor = response.payload.items[i].color;

                        $scope.items.push(response.payload.items[i]);
                    }

                    $scope.offset += response.payload.items.length;
                    $scope.currentItem = $scope.items[0];
                } else {
                    utilService.toasterFail("No More Items!");
                }

            }, function () {
                console.log("Error");
            })
    };

    // Select one item if the stylist wants it
    $scope.selectItem = function () {
        const url = webUriPrefix + "/v2/stylists/" + $cookies.get("userId") + "/cooperationitems/" + $scope.currentItem.id;

        httpService.POST(url, null, null)
            .then(function (response) {
                if (response.message.code == 0) {
                    utilService.toasterSuccess("Successfully Selected! See it in your item list.");
                } else if (response.message.code == 44) {
                    utilService.toasterWarning("No more such item!");

                    // If there is no such item any more, remove it.
                    var index = $scope.items.indexOf($scope_currentItem);
                    if (index > -1) {
                        $scope.items.splice(index, 1);
                    }
                } else if (response.message.code == 24) {
                    utilService.toasterWarning("You have selected this item!")
                } else {
                    utilService.toasterFail("Error occurred in the server. Please try it lately.");
                }

            }, function () {
                console.log("Error");
            })
    };

    $scope.cancel = function () {
        $modalInstance.close();
    }

    $scope.listItems();
}

/**
 * Item Cart
 *
 * @param $scope
 * @param $cookies
 * @param utilService
 * @param httpService
 * @constructor
 */
function CartController($scope, $cookies, utilService, httpService,$http,$compile,SweetAlert) {

    // Store the items
    $scope.items = [];
    $scope.offset = 0;

    const limit = 8;
    $scope.page = 1;

    $scope.itemsToSubmit = [];
    $scope.profile = {
        name : '',
        country:'',
        state:'',
        address : '',
        zipcode:'',
        phone:''
    }
    $scope.showModel = function () {
        if($scope.itemsToSubmit.length <= 0) {
            utilService.toasterFail("Please select item and then Submit!")
            return;
        }
        document.getElementById("model").style.display = "block";
    };
    $scope.cancel = function () {
        document.getElementById("model").style.display = "none";
    };
    $scope.listFirstPage = function (id) {
        id = parseInt(id);

        $scope.offset = (id - 1) * limit;
        url = webUriPrefix + "/v2/stylists/" + $cookies.get("userId") + "/cooperationitems?limit=" + limit + "&offset=" + $scope.offset + "&status=0";
        var req = {
            method: 'GET',
            url: url,
        }
        $http(req)
            .success(function (response) {
                if (response.payload == null) {
                    utilService.toasterFail("No results!");
                    $scope.list = null;
                    return;
                }
                $scope.page = id;
                $scope.payload = response.payload;
                $scope.total = response.payload.total;
                $scope.items = $scope.payload.items;
                $scope.totalPage = Math.ceil($scope.total / limit);
                page($scope.page, $scope.totalPage );
            })
            .error(function (response) {
                utilService.toasterFail("No results!");
            });
    }
    function page(page,totalPage){
        page=parseInt(page);
        var list=[];
        if(totalPage<5){
            for(i=1;i<=totalPage;i++){
                list.push(i);
            }
        } else {
            if(page <= 3 )list=[1,2,3,4,5];
            else if(page>= totalPage-3) list=[totalPage-4,totalPage-3,totalPage-2,totalPage-1,totalPage];
            else list=[page-2,page-1,page,page+1,page+2];
        }
        str="";
        if (page == 1) {
            str+='<li class="footable-page-arrow disabled"><a href="#" ng-click="listFirstPage(1)"><<</a></li>';
            str+='<li class="footable-page-arrow disabled"><a href="#" ng-click="listFirstPage(1)"><</a></li>';
        }
        else {
            str+='<li class="footable-page-arrow"><a href="#" ng-click="listFirstPage(1)"><<</a></li>';
            str+='<li class="footable-page-arrow"><a href="#" ng-click="listFirstPage(page - 1)"><</a></li>';
        }

        for(i=0;i<list.length;i++){
            if (list[i] == page)
                str+=' <li class = "footable-page active"><a href="#" ng-click="listFirstPage('+list[i]+')" >'+list[i]+'</a></li>';
            else
                str+=' <li class="footable-page"><a href="#" ng-click="listFirstPage('+list[i]+')" >'+list[i]+'</a></li>';
        }
        if (page == totalPage) {
            str+='<li class="footable-page-arrow disabled"><a href="#" ng-click="listFirstPage(page)">></a></li>';
            str+='<li class="footable-page-arrow disabled"><a href="#" ng-click="listFirstPage(totalPage)">>></a></li>';
        }
        else {
            str+='<li class="footable-page-arrow"><a href="#" ng-click="listFirstPage(page + 1)">></a></li>';
            str+='<li class="footable-page-arrow"><a href="#" ng-click="listFirstPage(totalPage)">>></a></li>';
        }

        //$compile(str)($scope);
        var pagination = document.getElementById("pagination");
        pagination.innerHTML=str;
        $compile($(pagination).contents())($scope);
    }
    $scope.listFirstPage($scope.page);

    // Delete an item selected.
    $scope.deleteItem = function (item) {
        var url = webUriPrefix + "/v2/stylists/cooperationitems/" + item.stylistItemId;

        httpService.POST(url, null, null)
            .then(function (response) {
                if (response.message.code == 0) {
                    utilService.toasterSuccess("Delete Successfully!");

                    // If there is no such item any more, remove it.
                    var index = $scope.items.indexOf(item);
                    if (index > -1) {
                        $scope.items.splice(index, 1);
                    }

                } else {
                    utilService.toasterFail("Error occurred in the server. Please try it lately.");
                }
            }, function () {
                console.log("Error");
            })
    };

    // Select item to submit
    $scope.selectItemToSubmit = function ($event, item) {

        if ($event.currentTarget.innerHTML == "Cancel") {
            $event.currentTarget.innerHTML = "Select";

            $scope.itemsToSubmit.splice(itemsToSubmit.indexOf(item));

            return;
        }

        $event.currentTarget.innerHTML = "Cancel";

        $scope.itemsToSubmit.push(item);
    };

    // Submit items to check
    $scope.submitItems = function () {

        SweetAlert.swal({
                title: "Are you sure?",
                text: "Note: orders can't be modified/canceled once you submit it.",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Yes, Submit it!",
                cancelButtonText: "No, Cancel it!",
                closeOnConfirm: false,
                closeOnCancel: false },
            function (isConfirm) {
                if (isConfirm) {
                    var itemIds = [];
                    for (var i = 0; i < $scope.itemsToSubmit.length; i++) {
                        itemIds.push($scope.itemsToSubmit[i].stylistItemId);
                    }
                    var url = webUriPrefix + "/v2/stylistOrders";
                    $scope.addressString=JSON.stringify($scope.profile);
                    $scope.order = {
                        "stylistId": $cookies.get("userId"),
                        "detail": itemIds.join(),
                        "address":$scope.addressString,
                    };
                    url = webUriPrefix + "/v2/stylistOrders";
                    httpService.POST(url, null, $scope.order)
                        .then(function (response) {
                            if (response.message.code == 0) {
                                // utilService.toasterSuccess("Successfully Submit these items. Mode Team will" +
                                //     " Check it as soon as possible.");
                                SweetAlert.swal("Deleted!", "Your imaginary file has been deleted.", "success");
                                for (var i = 0; i < $scope.itemsToSubmit.length; i++) {
                                    $scope.items.splice($scope.items.indexOf($scope.itemsToSubmit[i]), 1);
                                }

                                $scope.itemsToSubmit = [];
                            }

                        }, function () {

                        });
                } else {
                    SweetAlert.swal("Cancelled", "Your operation cancel :)", "error");
                }
                $scope.cancel();
            });

    };

}

/**
 * List Orders of the Stylist
 *
 * @param $scope
 * @param $cookies
 * @param utilService
 * @param httpService
 * @constructor
 */
function ListOrderController($scope, $cookies, utilService, httpService,$http,$compile) {

    $scope.orders = [];
    $scope.total = 0;

    $scope.offset = 0;

    const limit = 10;
    $scope.page = 1;

    // List cooperation items the stylists selected
    $scope.listFirstPage = function (id) {
        id = parseInt(id);

        $scope.offset = (id - 1) * PAGE_SIZE;
        url = webUriPrefix + "/v2/stylistOrders?stylistId=" + $cookies.get("userId") + "&limit=" + limit + "&offset=" + $scope.offset;
        var req = {
            method: 'GET',
            url: url,
        }
        $http(req)
            .success(function (response) {
                if (response.payload == null) {
                    utilService.toasterFail("No results!");
                    $scope.list = null;
                    return;
                }
                $scope.page = id;
                $scope.payload = response.payload;
                $scope.total = response.payload.total;
                $scope.list = $scope.payload.orders;
                $scope.totalPage = Math.ceil($scope.total / PAGE_SIZE);
                $scope.mod = $scope.total % PAGE_SIZE;
                page($scope.page, $scope.totalPage );
            })
            .error(function (response) {
                utilService.toasterFail("No results!");
            });
    }
    function page(page,totalPage){
        page=parseInt(page);
        var list=[];
        if(totalPage<5){
            for(i=1;i<=totalPage;i++){
                list.push(i);
            }
        } else {
            if(page <= 3 )list=[1,2,3,4,5];
            else if(page>= totalPage-3) list=[totalPage-4,totalPage-3,totalPage-2,totalPage-1,totalPage];
            else list=[page-2,page-1,page,page+1,page+2];
        }
        str="";
        if (page == 1) {
            str+='<li class="footable-page-arrow disabled"><a href="#" ng-click="listFirstPage(1)"><<</a></li>';
            str+='<li class="footable-page-arrow disabled"><a href="#" ng-click="listFirstPage(1)"><</a></li>';
        }
        else {
            str+='<li class="footable-page-arrow"><a href="#" ng-click="listFirstPage(1)"><<</a></li>';
            str+='<li class="footable-page-arrow"><a href="#" ng-click="listFirstPage(page - 1)"><</a></li>';
        }

        for(i=0;i<list.length;i++){
            if (list[i] == page)
                str+=' <li class = "footable-page active"><a href="#" ng-click="listFirstPage('+list[i]+')" >'+list[i]+'</a></li>';
            else
                str+=' <li class="footable-page"><a href="#" ng-click="listFirstPage('+list[i]+')" >'+list[i]+'</a></li>';
        }
        if (page == totalPage) {
            str+='<li class="footable-page-arrow disabled"><a href="#" ng-click="listFirstPage(page)">></a></li>';
            str+='<li class="footable-page-arrow disabled"><a href="#" ng-click="listFirstPage(totalPage)">>></a></li>';
        }
        else {
            str+='<li class="footable-page-arrow"><a href="#" ng-click="listFirstPage(page + 1)">></a></li>';
            str+='<li class="footable-page-arrow"><a href="#" ng-click="listFirstPage(totalPage)">>></a></li>';
        }

        //$compile(str)($scope);
        var pagination = document.getElementById("pagination");
        pagination.innerHTML=str;
        $compile($(pagination).contents())($scope);
    }
    $scope.listFirstPage($scope.page);
}

/**
 * List the item we sent to the stylist
 *
 * @param $scope
 * @param $cookies
 * @param utilService
 * @param httpService
 * @constructor
 */
function ListStylistItemsController($scope, $cookies, utilService, httpService,$compile) {
    $scope.items = [];
    $scope.offset = 0;

    const limit = 20;

    $scope.page = 1;
    $scope.total = 0;

    // List cooperation items the stylists selected
    $scope.listFirstPage = function (id) {
        id = parseInt(id);

        $scope.offset = (id - 1) * limit;

        const url = webUriPrefix + "/v2/stylists/" + $cookies.get("userId") + "/cooperationitems?limit=" + limit + "&offset=" + $scope.offset + "&status=1";

        httpService.GET(url, null, null)
            .then(function (response) {
                if (response.message.code == 0) {

                    $scope.page = id;
                    $scope.payload = response.payload;
                    $scope.total = response.payload.total;
                    $scope.items = $scope.payload.items;
                    $scope.totalPage = Math.ceil($scope.total / limit);
                    $scope.mod = $scope.total % limit;
                    page($scope.page, $scope.totalPage );
                } else {
                    utilService.toasterFail("No More Items!");
                }

            }, function () {
                console.log("Error");
            })
    };

    function page(page,totalPage){
        page=parseInt(page);
        var list=[];
        if(totalPage<5){
            for(i=1;i<=totalPage;i++){
                list.push(i);
            }
        } else {
            if(page <= 3 )list=[1,2,3,4,5];
            else if(page>= totalPage-3) list=[totalPage-4,totalPage-3,totalPage-2,totalPage-1,totalPage];
            else list=[page-2,page-1,page,page+1,page+2];
        }
        str="";
        if (page == 1) {
            str+='<li class="footable-page-arrow disabled"><a href="#" ng-click="listFirstPage(1)"><<</a></li>';
            str+='<li class="footable-page-arrow disabled"><a href="#" ng-click="listFirstPage(1)"><</a></li>';
        }
        else {
            str+='<li class="footable-page-arrow"><a href="#" ng-click="listFirstPage(1)"><<</a></li>';
            str+='<li class="footable-page-arrow"><a href="#" ng-click="listFirstPage(page - 1)"><</a></li>';
        }

        for(i=0;i<list.length;i++){
            if (list[i] == page)
                str+=' <li class = "footable-page active"><a href="#" ng-click="listFirstPage('+list[i]+')" >'+list[i]+'</a></li>';
            else
                str+=' <li class="footable-page"><a href="#" ng-click="listFirstPage('+list[i]+')" >'+list[i]+'</a></li>';
        }
        if (page == totalPage) {
            str+='<li class="footable-page-arrow disabled"><a href="#" ng-click="listFirstPage(page)">></a></li>';
            str+='<li class="footable-page-arrow disabled"><a href="#" ng-click="listFirstPage(totalPage)">>></a></li>';
        }
        else {
            str+='<li class="footable-page-arrow"><a href="#" ng-click="listFirstPage(page + 1)">></a></li>';
            str+='<li class="footable-page-arrow"><a href="#" ng-click="listFirstPage(totalPage)">>></a></li>';
        }

        //$compile(str)($scope);
        var pagination = document.getElementById("pagination");
        pagination.innerHTML=str;
        $compile($(pagination).contents())($scope);
    }

    $scope.listFirstPage($scope.page);
}

angular
    .module('inspinia')
    .controller('SelectItemController', SelectItemController)
    .controller("CartController", CartController)
    .controller("ListOrderController", ListOrderController)
    .controller("ListStylistItemsController", ListStylistItemsController)
    .controller("ItemDetailController", ItemDetailController);
