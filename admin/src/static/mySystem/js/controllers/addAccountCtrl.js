function addAccountCtrl($http,$scope,utilService,$cookies) {
	$scope.userId = $cookies.get("userId");
	$scope.addAccount = function() {
		var formData = new FormData();
		formData.append("nickname",$scope.nickname);
		formData.append("password",md5($scope.password));
		formData.append("description",$scope.description);
		var req = {
			method: "POST",
			url : webUriPrefix + "/system/add/account",
			headers : {
				'Content-Type': undefined,
				'username' : $scope.username,
			},
			data: formData,
            transformRequest: function (data, headersGetterFunction) {
                return data;
            }
		}
		$http(req)
		.success(function(response){
			if (response.message.code == 0) {
				utilService.toasterSuccess("添加成功！")
			} else if(response.message.code == 3){
				utilService.toasterFail("用户已存在！")
			}
		})
		.error(function(reject){
			utilService.toasterFail("添加失败！")
		})
	}
}
function resetPasswordCtrl($http,$scope,utilService) {
	$scope.resetPassword = function() {
		var req = {
			method: "POST",
			url : webUriPrefix + "/system/reset",
			headers : {
				username : $scope.username,
				password : md5($scope.password)
			}
		}
		$http(req)
		.success(function(response){
			if (response.message.code == 0) {
				utilService.toasterSuccess("重置成功！");
			} else if (response.message.code == 4) {
				utilService.toasterFail("用户不存在！");
			} else {
				utilService.toasterFail("重置失败！");
			}
		})
		.error(function(reject){
			utilService.toasterFail("服务器错误！");
		})
	}
}
function checkListAdminCtrl($http,$scope,utilService,$cookies, $rootScope,$compile) {
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
        url = webUriPrefix + "/system/check/list"
        var req = {
            method: 'GET',
            url: url,
            headers : {
            	'limit': PAGE_SIZE,
            	'offset' : $scope.offset,
            }
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
                $scope.list = $scope.payload.list;
                $scope.totalPage = Math.ceil($scope.total / PAGE_SIZE);
                $scope.mod = $scope.total % PAGE_SIZE;
                $rootScope.page = $scope.page;
                page($scope.page, $scope.totalPage );
            })
            .error(function (response) {
                utilService.toasterFail("No results!");
            });

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
function completionListAdminCtrl($http,$scope,utilService,$cookies, $rootScope,$compile) {
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
        url = webUriPrefix + "/system/completion/fetch/list"
        var req = {
            method: 'GET',
            url: url,
            headers : {
            	'limit': PAGE_SIZE,
            	'offset' : $scope.offset,
            }
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
                $scope.list = $scope.payload.list;
                $scope.totalPage = Math.ceil($scope.total / PAGE_SIZE);
                $scope.mod = $scope.total % PAGE_SIZE;
                $rootScope.page = $scope.page;
                page($scope.page, $scope.totalPage );
            })
            .error(function (response) {
                utilService.toasterFail("No results!");
            });

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
function singleListAdminCtrl($http,$scope,utilService,$cookies, $rootScope,$compile) {
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
        url = webUriPrefix + "/system/single/fetch/list"
        var req = {
            method: 'GET',
            url: url,
            headers : {
            	'limit': PAGE_SIZE,
            	'offset' : $scope.offset,
            }
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
                $scope.list = $scope.payload.list;
                $scope.totalPage = Math.ceil($scope.total / PAGE_SIZE);
                $scope.mod = $scope.total % PAGE_SIZE;
                $rootScope.page = $scope.page;
                page($scope.page, $scope.totalPage );
            })
            .error(function (response) {
                utilService.toasterFail("No results!");
            });

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
function multiListAdminCtrl($http,$scope,utilService,$cookies, $rootScope,$compile) {
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
        url = webUriPrefix + "/system/multi/fetch/list"
        var req = {
            method: 'GET',
            url: url,
            headers : {
            	'limit': PAGE_SIZE,
            	'offset' : $scope.offset,
            }
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
                $scope.list = $scope.payload.list;
                $scope.totalPage = Math.ceil($scope.total / PAGE_SIZE);
                $scope.mod = $scope.total % PAGE_SIZE;
                $rootScope.page = $scope.page;
                page($scope.page, $scope.totalPage );
            })
            .error(function (response) {
                utilService.toasterFail("No results!");
            });

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
function shortListAdminCtrl($http,$scope,utilService,$cookies, $rootScope,$compile) {
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
        url = webUriPrefix + "/system/short/fetch/list"
        var req = {
            method: 'GET',
            url: url,
            headers : {
            	'limit': PAGE_SIZE,
            	'offset' : $scope.offset,
            }
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
                $scope.list = $scope.payload.list;
                $scope.totalPage = Math.ceil($scope.total / PAGE_SIZE);
                $scope.mod = $scope.total % PAGE_SIZE;
                $rootScope.page = $scope.page;
                page($scope.page, $scope.totalPage );
            })
            .error(function (response) {
                utilService.toasterFail("No results!");
            });

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
function deleteAccountCtrl($http,$cookies,$scope,utilService) {
    $scope.deleteAccount = function() {
        var req = {
            method : "POST",
            url : webUriPrefix + "/system/delete",
            headers : {
                username : $scope.username
            }
        }
        $http(req)
        .success(function(response) {
            if (response.message.code == 0) {
                utilService.toasterSuccess("删除成功！");
            } else {
                utilService.toasterFail("删除失败！");
            }
        })
        .error(function(reject) {
            utilService.toasterFail("删除失败！");
        })
    }
}
angular
    .module('inspinia')
    .controller('addAccountCtrl', addAccountCtrl)
    .controller('resetPasswordCtrl', resetPasswordCtrl)
    .controller('checkListAdminCtrl', checkListAdminCtrl)
    .controller('completionListAdminCtrl', completionListAdminCtrl)
    .controller('singleListAdminCtrl', singleListAdminCtrl)
    .controller('multiListAdminCtrl', multiListAdminCtrl)
    .controller('shortListAdminCtrl', shortListAdminCtrl)
    .controller('deleteAccountCtrl', deleteAccountCtrl)