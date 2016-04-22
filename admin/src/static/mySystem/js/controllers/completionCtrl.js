function completionUploadCtrl($http,$scope,$cookies,utilService) {
	$scope.userId = $cookies.get("userId");
	$scope.completionUpload = function() {
		var formData = new FormData();
		formData.append("content",$scope.content);
		formData.append("answer",$scope.answer);
		var req = {
			method: "POST",
			url : webUriPrefix + "/system/completion/create",
			headers : {
				'Content-Type': undefined,
				'userId' : $scope.userId,
			},
			data: formData,
            transformRequest: function (data, headersGetterFunction) {
                return data;
            }
		}
		$http(req)
		.success(function(response){
			if (response.message.code ==0) {
				utilService.toasterSuccess("上传成功！");
			} else{
				utilService.toasterFail("上传失败！")
			}
		})
		.error(function(reject){
			utilService.toasterFail("上传失败！")
		})

	}
    $scope.getKnowledge = function() {
        var req = {
            method : "GET",
            url : webUriPrefix + "/system/knowledge/list",
            headers : {
                userId : $scope.userId
            }
        }
        $http(req)
        .success(function(response) {
            if (response.message.code == 0) {
                $scope.knowledges = response.payload.list;
            } else {
                utilService.toasterFail("获取知识点失败");
            }
        })
        .error(function(){
            utilService.toasterFail("获取知识点失败");
        })
    }
    $scope.getKnowledge();
}
function completionListCtrl($http, utilService, $scope, $cookies,SweetAlert,$compile,$state,$rootScope) {
	$scope.userId = $cookies.get("userId");
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
            	'userId':$scope.userId,
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
    $scope.deleteit = function (id) {
        SweetAlert.swal({
                title: "Are you sure?",
                text: "Your will not be able to recover this completion !",
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
                        method: "POST",
                        url: webUriPrefix + '/system/completion/delete/' + id,
                    };
                    $http(req)
                        .success(function (response) {
                            if (response.message.code == 0) {
                                SweetAlert.swal("Deleted!", "Your completion has been deleted.", "success");
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
                    SweetAlert.swal("Cancelled", "Your completion is safe :)", "error");
                }
            });
    }
    $scope.updateCheck = function(id) {
    	$cookies.put("completionId", id);
    	$state.go("completion.update")
    }
}
function completionUpdateCtrl($http,$scope,$cookies,utilService) {
	$scope.userId = $cookies.get("userId");
	$scope.completionId = $cookies.get("completionId");
	$scope.getCompletion = function() {
		var req = {
			method : "GET",
			url : webUriPrefix + "/system/completion/fetch/" + $scope.completionId,
		}
		$http(req)
		.success(function(response){
			if (response.message.code == 0) {
				$scope.content = response.payload.completion.content;
				$scope.answer = response.payload.completion.answer;
			};
		})
	}
	$scope.getCompletion();

	$scope.updateCompletion = function() {
		var formData = new FormData();
		formData.append("content",$scope.content);
		formData.append("answer",$scope.answer);
		var req = {
			method: "POST",
			url : webUriPrefix + "/system/completion/update/" + $scope.completionId,
			headers : {
				'Content-Type': undefined,
				'userId' : $scope.userId,
			},
			data: formData,
            transformRequest: function (data, headersGetterFunction) {
                return data;
            }
		}
		$http(req)
		.success(function(response){
			if (response.message.code == 0) {
				utilService.toasterSuccess("上传成功");
			} else {
				utilService.toasterFail("上传失败");
			}
		})
		.error(function(reject){
			utilService.toasterFail("上传失败");
		})
	}
}
angular.module('inspinia')
    .controller('completionUploadCtrl', completionUploadCtrl)
    .controller('completionUpdateCtrl', completionUpdateCtrl)
    .controller('completionListCtrl', completionListCtrl);