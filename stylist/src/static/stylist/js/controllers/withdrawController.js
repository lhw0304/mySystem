function withdrawCtrl($scope,$http,$modal,$cookies,toaster,$rootScope, $state){
    $scope.token = $cookies.get("X-Auth-Token");
    $scope.userId = $cookies.get('userId');
    str="";
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

    $scope.openWithdrawWindow = function () {
        var modalInstance = $modal.open({
            templateUrl: 'views/account/modal.html',
            controller: ModalInstanceCtrl,
            windowClass: "animated fadeIn"
        });
    };
    $scope.listWithdraw = function(){
        $.ajax({
            type: "get",
            headers: {
                'X-Auth-Token': $scope.token,
            },
            async: false,
            dataType: "json",
            url: webUriPrefix+"/v2/listCommissionSettlement?stylistId="+$scope.userId,
            success: function (response) {
                if(response.message.code == 0){
                    $scope.list = response.payload.list;
                    $scope.banlance = response.payload.balance;
                    $scope.banlance = parseFloat($scope.banlance).toFixed(2);
                    for(var i=0;i<$scope.list.length;i++){
                        if($scope.list[i].status == 0){
                            $scope.list[i].status = "<span class='label label-primary'>Processing</span>";
                        } else if($scope.list[i].status == 1){
                            $scope.list[i].status = "<span class='label label-success'>Done</span>";
                        } else if($scope.list[i].status == 2){
                            $scope.list[i].status = "<span class='label label-danger'>Reject</span>";
                        } else if($scope.list[i].status == null){
                            $scope.list[i].status = "<span class='label label-success'>Done</span>";
                        }
                        if($scope.list[i].comment == null) $scope.list[i].comment = "";
                        $scope.list[i].balance = parseFloat($scope.list[i].balance).toFixed(2);
                        $scope.list[i].usd = parseFloat($scope.list[i].usd).toFixed(2);
                        $scope.list[i].date = $scope.list[i].date + "";
                        $scope.list[i].date = $scope.list[i].date.substring(0,4)+"-"+$scope.list[i].date.substring(4,6)+"-"+$scope.list[i].date.substring(6,8);
                    }
                    for(var i=0;i<$scope.list.length;i++){
                        str+="<tr><td>"+$scope.list[i].type+"</td><td>"+$scope.list[i].usd+"</td><td>"+$scope.list[i].balance+"</td><td>"+$scope.list[i].status+"</td><td>"+$scope.list[i].date+"</td><td>"+$scope.list[i].comment+"</td></tr>";
                    }
                    document.getElementById("list").innerHTML=str;
                } else {
                    $scope.banlance = 0;
                    $rootScope.toasterFail("No results!")
                }
            },
            error: function (e) {
                $rootScope.toasterFail("Error,Please check your account!")
            }
        });

    }
    $scope.listWithdraw();
}

function ModalInstanceCtrl ($scope, $modalInstance,$http,$cookies,$rootScope) {
    $scope.userId = $cookies.get('userId');
    $scope.withdrawAction = function() {
        if($scope.usd == null || $scope.usd.length == 0 || isNaN($scope.usd) || $scope.usd < 5){
            $rootScope.toasterFail("Please input a number, and the number must larger than 5");
            return;
        }
        var req= {
            method:"POST",
            url:webUriPrefix+"/v2/withdraw?stylistId="+$scope.userId+"&usd="+$scope.usd,
        }
        $http(req)
            .success(function(response) {
                if(response.message.code == 0) {
                    $rootScope.toasterSuccess("Submit the application for success! Please wait for the audit! Jump to the last page after 5 seconds.");
                    $scope.cancel();
                    setTimeout('window.location.reload()',5000);
                } else if (response.message.code == 43) {
                    $rootScope.toasterFail("Balance is insufficient, please re - enter!");
                } else {
                    $rootScope.toasterFail("Settlement failed!");
                }
            })
    }
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
};


angular
    .module('inspinia')
    .controller('withdrawCtrl', withdrawCtrl);
