function groupCtrl($http,$scope,utilService,$cookies) {
	$scope.userId = $cookies.get("userId");
	$scope.createGroup = function() {
		var req = {
			method: "GET",
			url : webUriPrefix + "/system/group/create",
			headers : {
				userId : $scope.userId,
				checkCount: $scope.check,
				completionCount:$scope.completion,
				singleSelectCount: $scope.single,
				multiSelectCount:$scope.multi,
				shortAnswerCount:$scope.short
			}
		}
		// var req = {
		// 	method: "GET",
		// 	url : webUriPrefix + "/system/group/create",
		// 	headers : {
		// 		userId : $scope.userId,
		// 		checkCount: 3,
		// 		completionCount:3,
		// 		singleSelectCount: 3,
		// 		multiSelectCount: 3,
		// 		shortAnswerCount: 3
		// 	}
		// }
		$http(req)
		.success(function(response){
			if (response.message.code == 0) {
				$scope.checkList = response.payload.checkList;
				$scope.completionList = response.payload.completionList;
				$scope.singleSelectList = response.payload.singleSelectList;
				$scope.multiSelectList = response.payload.multiSelectList;
				$scope.shortAnswerList = response.payload.shortAnswerList;
			} else {
				utilService.toasterFail("失败！");
			}
		})
		.error(function(reject){
			utilService.toasterFail("失败！");
		})
	}
	//$scope.createGroup();
}
angular
    .module('inspinia')
    .controller('groupCtrl', groupCtrl)