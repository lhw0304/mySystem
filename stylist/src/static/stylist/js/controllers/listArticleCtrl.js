function listArticleController($scope, $http, $cookies, $state, $compile, toaster, $rootScope,SweetAlert){
    $scope.userId = $cookies.get('userId');
    $scope.pageName = document.getElementById("pageName").value;
    $rootScope.toasterSuccess = function(message) {
        toaster.success({ body:message});
    };
    $rootScope.toasterFail = function(message){
        toaster.pop({
            type: 'error',
            body: message,
            showCloseButton: true,
            timeout: 6000
        });
    };

    $scope.deleteit = function (id) {
        SweetAlert.swal({
                title: "Are you sure?",
                text: "Your will not be able to recover this imaginary file!",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Yes, delete it!",
                cancelButtonText: "No, cancel delete!",
                closeOnConfirm: false,
                closeOnCancel: false },
            function (isConfirm) {
                if (isConfirm) {
                    var req = {
                        method: "DELETE",
                        url: webUriPrefix + '/v2/stylistDrafts/' + id,
                    };
                    $http(req)
                        .success(function (response) {
                            if (response.message.code == 0) {
                                SweetAlert.swal("Deleted!", "Your imaginary file has been deleted.", "success");
                                if($scope.mod == 1 && $scope.page == $scope.totalPage)
                                    $scope.listFirstPage($scope.page - 1);
                                else
                                    $scope.listFirstPage($scope.page);
                            }
                            else {
                                SweetAlert.swal("Error", "Your delete operation  is fail!", "error");
                                return;
                            }
                        });
                } else {
                    SweetAlert.swal("Cancelled", "Your Article draft is safe :)", "error");
                }
            });
    }

    $scope.submit = function (id,status) {
        SweetAlert.swal({
                title: "Are you sure?",
                text: "Your will submit the article!",
                type: "info",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Yes, submit it!",
                cancelButtonText: "No, cancel !",
                closeOnConfirm: false,
                closeOnCancel: false },
            function (isConfirm) {
                if (isConfirm) {
                    var req={
                        method:"POST",
                        url:webUriPrefix+"/v2/stylistDrafts/"+id,
                        headers :{
                            'status':status,
                    }
                    };
                    $http(req)
                        .success(function(response){
                            if (response.message.code == 0) {
                                SweetAlert.swal("success!", "Your have submitted the article.", "success");
                                $state.go("pages.submitted",{}, {reload: true});
                                // if($scope.mod == 1 && $scope.page == $scope.totalPage)
                                //         $scope.listFirstPage($scope.page-1);
                                //     else 
                                //         $scope.listFirstPage($scope.page);
                            } else {
                                SweetAlert.swal("Error", "Submitted Fail!", "error");
                                return;
                            }
                        });
                } else {
                    SweetAlert.swal("Cancelled", "Your cancel the submit", "error");
                }
            });
    }

    //turn to page
    $scope.turnToViewPage = function (id) {
        var table = document.getElementById("table").value;
        if (table == "headline") {
            $rootScope.table = "headline";
        } else {
            $rootScope.table = "stylistDrafts";
        }
        $cookies.put('articleId', id);
        $state.go("pages.viewArticle",{}, {reload: true});
    }

    $scope.turnToStatsPage = function (id) {
        $cookies.put('articleId', id);
        $state.go("statistics.articleDaily",{}, {reload: true});
    }

    $scope.turnToEditPage = function (id) {
        $cookies.put('articleId', id);
        var req = {
            method: "GET",
            url: webUriPrefix + '/v2/stylistDrafts/' + id,
        }
        $http(req)
            .success(function(response) {
                if(response.message.code == 0) {
                    $rootScope.editArticleContent = response.payload.stylistDraft.content;
                    $state.go("app.write.step_one",{}, {reload: true});
                }
            });

    }


    if ($rootScope.page == null)
        $scope.page = 1;
    else {
        if ($rootScope.goBack == "goBack") {
            $scope.page = $rootScope.page;
            $rootScope.goBack = null;
        } else
            $scope.page = 1;
    }

    $scope.listFirstPage = function (id) {
        id = parseInt(id);

        $scope.offset = (id - 1) * PAGE_SIZE;
        if ($scope.pageName == 'articleApproving')
            url = webUriPrefix + '/v2/stylistDrafts?limit=' + PAGE_SIZE
                + '&offset=' + $scope.offset+"&stylistId="+$scope.userId
                + "&status=2";
        else if ($scope.pageName == 'articleDrafts')
            url = webUriPrefix + '/v2/stylistDrafts?limit=' + PAGE_SIZE
                + '&offset=' + $scope.offset+"&stylistId="+$scope.userId
                + "&status=1";
        else if ($scope.pageName == 'articleApproved')
            url = webUriPrefix + '/v2/headlines?limit=' + PAGE_SIZE
                + '&offset=' + $scope.offset+"&stylistId="+$scope.userId;
        else if ($scope.pageName == 'articleStats')
            url = webUriPrefix + '/v2/stats/listStylistArticleStats?limit=' + PAGE_SIZE
                + '&offset=' + $scope.offset+"&stylistId="+$scope.userId;
        else if ($scope.pageName == 'articleRejected')
            url = webUriPrefix + '/v2/stylistDrafts?limit=' + PAGE_SIZE
                + '&offset=' + $scope.offset+"&stylistId="+$scope.userId
                + "&status=4";
        var req = {
            method: 'GET',
            url: url,
        }
        $http(req)
            .success(function (response) {
                if (response.payload == null) {
                    $rootScope.toasterFail("No results!");
                    $scope.list = null;
                    return;
                }
                $scope.page = id;
                $scope.payload = response.payload;
                $scope.total = response.payload.total;
                $scope.list = $scope.payload.list;
                $scope.totalPage = Math.ceil($scope.total / PAGE_SIZE);
                $scope.mod = $scope.total % PAGE_SIZE;
                for (var i = 0; i < $scope.list.length; i++) {
                    $scope.list[i].content = jQuery.parseJSON($scope.list[i].content);
                }
                $rootScope.page = $scope.page;
                page($scope.page, $scope.totalPage );
            })
            .error(function (response) {
                $rootScope.toasterFail("No results!");
            });

    };

    $scope.updateHeadline = function(id, status) {
        SweetAlert.swal({
                title: "Are you sure?",
                text: "Your will switch your article status!",
                type: "info",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Yes, do it!",
                cancelButtonText: "No, cancel !",
                closeOnConfirm: false,
                closeOnCancel: false },
            function (isConfirm) {
                if (isConfirm) {
                    var req={
                        method:"POST",
                        url:webUriPrefix + "/v2/headlines/" + id + "?status=" + status + "&userId=" + $scope.userId,
                    };
                    $http(req)
                        .success(function(response) {
                            if (response.message.code == 0) {
                                SweetAlert.swal("success!", "Your article status  has been switched.", "success");
                                $scope.listFirstPage($scope.page);
                            } else {
                                SweetAlert.swal("Error", "Your operation of the article fail!", "error");
                                $scope.listFirstPage($scope.page);
                                return;
                            }
                        });
                } else {
                    SweetAlert.swal("Cancelled", "Your cancel the operation:)", "error");
                    $scope.listFirstPage($scope.page);
                }
            });
    }


    $scope.listFirstPage($scope.page);

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

    $scope.flag = 0;
    $scope.listComments = function(id) {
        var panel = document.getElementById("panel" + id);
        var flag = panel.getAttribute("collapse");
        if (flag == "false") {

            document.getElementById("panel" + id).setAttribute("collapse", "true");
            document.getElementById("panel" + id).setAttribute("class", "panel-collapse collapse");
            document.getElementById("panel" + id).setAttribute("style", "height:0px;");

        }
        else {
            var node = document.getElementsByTagName("tr");
            for (var i = 0; i < node.length; i ++) {
                if (node[i].getAttribute("collapse") == "false") {
                    node[i].setAttribute("collapse", "true");
                    node[i].setAttribute("class", "panel-collapse collapse");
                    node[i].setAttribute("style", "height:0px;");
                }
            }
            document.getElementById("panel" + id).setAttribute("collapse", "false");
            document.getElementById("panel" + id).setAttribute("class", "panel-collapse collapse in");
            document.getElementById("panel" + id).setAttribute("style", "height:auto;");
            $rootScope.id = id;
            var str = '<td colspan="10" ><div ng-include="\'views/list_article/articleComments.html\'"></div></td>';
            panel.innerHTML=str;
            $compile($(panel).contents())($scope);
        }
    }
}
function listCommentController($rootScope, $scope, $cookies, toaster, $http, $state) {
    $scope.stylistId = $cookies.get('userId');
    $scope.articleId = $rootScope.id;
    $rootScope.toasterSuccess = function(message) {
        toaster.success({ body:message});
    };
    $rootScope.toasterFail = function(message){
        toaster.pop({
            type: 'error',
            body: message,
            showCloseButton: true,
            timeout: 6000
        });
    };
    $scope.loadData = function () {
        var id = $scope.page;
        $scope.offset = (id - 1) * PAGE_SIZE;
        url = webUriPrefix + '/v2/stylists/articles/' + $scope.articleId + '/comments?limit=' + PAGE_SIZE
            + '&offset=' + $scope.offset;
        var req = {
            method: 'GET',
            url: url,
        }
        $http(req)
            .success(function (response) {
                if (response.payload == null) {
                    //$rootScope.toasterFail("No results!");
                    return;
                }
                $scope.payload = response.payload;
                //$scope.total = response.payload.total;
                $scope.list = $scope.list.concat($scope.payload.list);
                $scope.more = $scope.payload.list.length === PAGE_SIZE;
            });
    };

    $scope.sendMessage = function() {
        var message = document.getElementById("message").value;
        if (message == "") {
            $rootScope.toasterFail("Message can't be empty!");
            return;
        }
        var req = {
            method: "POST",
            url: webUriPrefix + '/v2/stylists/' + $scope.stylistId + '/comment/articles/' + $scope.articleId,
            headers: {
                'Content-Type': undefined,
            },
            data: message,
            transformRequest: function (data, headersGetterFunction) {
                return data;
            }
        };
        $http(req)
            .success(function (response) {
                if (response.message.code == 0) {
                    document.getElementById("message").value = "";
                    $scope.page = 1;
                    $scope.more = false;
                    $scope.list = [];
                    $scope.loadData();
                }
                else {
                    $rootScope.toasterFail("System error!");
                    return;
                }
            });
    }

    $scope.show_more = function () {
        $scope.page += 1;
        $scope.loadData();
    }

    $scope.has_more = function () {
        return $scope.more;
    }

    $scope.page = 1;
    $scope.more = false;
    $scope.list = [];
    $scope.loadData();
}

function viewArticleCtrl($rootScope, $scope, $cookies, toaster, $http, $compile, $q, $modal, $state) {
    $scope.stylistId = $cookies.get('userId');
    $scope.articleId = $cookies.get('articleId');
    var url = null;
    if ($rootScope.table == "headline") {
        url = webUriPrefix + '/v2/headlines/' + $scope.articleId;
    } else {
        url = webUriPrefix + '/v2/stylistDrafts/' + $scope.articleId;
    }

    $scope.getArticle = function() {
        var req = {
            method: "GET",
            url: url,
        }
        $http(req)
            .success(function(response) {
                if(response.message.code == 0) {
                    if ($rootScope.table == "headline") {
                        $scope.article = jQuery.parseJSON(response.payload.headline.content);
                        $scope.articleContentWithTaggify = $scope.jsonToHtml(response.payload.headline.content);
                    } else {
                        $scope.article = jQuery.parseJSON(response.payload.stylistDraft.content);
                        $scope.articleContentWithTaggify = $scope.jsonToHtml(response.payload.stylistDraft.content);
                    }

                    var articleContent = document.getElementById("theNewArticle");
                    articleContent.innerHTML = $scope.articleContentWithTaggify;
                    $compile(articleContent)($scope);
                }
            });
    }
    $scope.getArticle();
    $scope.itemInfoPopUp = function ($event){
        // Get item infomation
        var labels = $event.target.getElementsByTagName("label");
        var itemId = labels[0].id;
        var link = labels[1].id;

        if(itemId == 'undefined'){
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
        myPromise.then(function(resolve){
            $scope.message = resolve.message;

            if($scope.message.code == 0){
                var modalInstance = $modal.open({
                    templateUrl: 'views/write_steps/iteminfo.html',
                    size : "lg2",
                    controller: CustomItemInfoModalInstanceCtrl,
                    resolve: {
                        item: function(){return resolve.payload.item;},
                        link: function(){return link;}
                    }
                });
            }
            return;
        }, function(reject){
            return reject;
        });
    }
    $scope.getItemRequest = function(url){
        var deferred = $q.defer();

        var url = url;

        var req = {
            method: 'GET',
            url: url,
        }
        $http(req)
            .success(function(response){
                deferred.resolve(response);
            })
            .error(function(data,status,headers,config){
                deferred.reject("Error");
            });
        return deferred.promise;
    }
    $scope.jsonToHtml = function(jsonString){
        //var jsonArticle = '{"coverImage":"http://img.cdn.whatsmode.com/images/test/e2f84ea548770cad75c50171bb52e67b.jpg","author":"","title":"Test","subtitle":"Test","content":[{"type":"image","defaultImage":"http://img.cdn.whatsmode.com/images/test/8ada872f8648550c0a55d6da14e6c44f.jpg","url":"","items":"15000","points":[{"left":"19.1705%","top":"32%","itemId":"15000","link":"http://www.baidu.com"}]},{"type":"text","content":"alskjd"},{"type":"text","content":"<b>asdasd</b>"},{"type":"text","content":"asdasds"}]}';
        var article = JSON.parse(jsonString);

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
        subtitleWrapper.className = "text-center";
        var articleSubtitle = document.createElement("h3");
        articleSubtitle.innerHTML = subtitle;
        articleTitleWrapper.appendChild(articleTitle);
        subtitleWrapper.appendChild(articleSubtitle);
        articleTitleWrapper.appendChild(subtitleWrapper);

        var articleContentWrapper = document.createElement("div");

        for(var i = 0; i < content.length; i++){
            if(content[i].type == "text"){
                var text = null;
                if (content[i].bold == "") {
                    text = document.createElement("p");
                } else {
                    text = document.createElement("h3");
                }
                text.innerHTML = content[i].content;
                articleContentWrapper.appendChild(text.cloneNode(true));
            } else if(content[i].type == "image"){
                var pointedImageWrapper = document.createElement("div");

                var pointedImageContainer = document.createElement("div");
                pointedImageContainer.style.position = "relative";
                pointedImageContainer.style.width = "100%";
                pointedImageContainer.style.height = "100%";
                pointedImageContainer.style.overflow = "hidden";
                pointedImageContainer.style.marginRight = "auto";
                pointedImageContainer.style.marginLeft = "auto";
                pointedImageContainer.style.borderRadius = "5px";

                var pointedImage = document.createElement("img");
                pointedImage.src = content[i].defaultImage;
                pointedImage.setAttribute("width","100%");
                pointedImage.setAttribute("height","100%");

                pointedImageContainer.appendChild(pointedImage);

                var points = content[i].points;
                if(points != null){
                    for(var j = 0; j < points.length; j++){
                        var pointWrapper = document.createElement("div");
                        pointWrapper.id = "point" + j;
                        pointWrapper.style.left = points[j].left;
                        pointWrapper.style.top = points[j].top;
                        pointWrapper.style.position = "absolute";

                        var clickWrapper = document.createElement("div");
                        clickWrapper.setAttribute("ng-click","itemInfoPopUp($event)");
                        clickWrapper.id = "pointClick" + j;
                        clickWrapper.className = "sk-spinner sk-spinner-pulse";

                        var labelWrapper = document.createElement("div");
                        var labelItem = document.createElement("label");
                        labelItem.id = points[j].itemId;
                        var labelLink = document.createElement("label");
                        labelLink.id = points[j].link;

                        labelWrapper.appendChild(labelItem);
                        labelWrapper.appendChild(labelLink);
                        clickWrapper.appendChild(labelWrapper);
                        pointWrapper.appendChild(clickWrapper);

                        pointedImageContainer.appendChild(pointWrapper);
                    }
                }

                pointedImageWrapper.appendChild(pointedImageContainer.cloneNode(true));

                articleContentWrapper.appendChild(pointedImageWrapper);
            }
        }

        article.appendChild(articleCoverImage.cloneNode(true));
        article.appendChild(articleTitleWrapper.cloneNode(true));
        article.appendChild(articleContentWrapper.cloneNode(true));

        articleWrapper.appendChild(article.cloneNode(true));

        return articleWrapper.innerHTML;
    }
}


angular
    .module('inspinia')
    .controller('listCommentController', listCommentController)
    .controller('listArticleController', listArticleController)
    .controller('viewArticleCtrl', viewArticleCtrl);