function loginCtrl($scope,$http,$state,$cookies, toaster, $rootScope){
    $scope.token = $cookies.get('X-Auth-Token');
    $scope.expires = $cookies.get('expires');
    var expireDate = new Date();

    //Automatic login when current time smaller than expires
    if($scope.token != null && $scope.expires != null){
        var myDate = new Date();
        var time=Date.parse(myDate);
        if(time < $scope.expires){
            $state.go("home.profile",{}, {reload: true});
        }
    }

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

    $scope.loginAction = function () {
        $scope.loginPwd = $scope.loginPassword;
        $scope.loginPwd = md5($scope.loginPwd);
        var req = {
            method : "POST",
            url: webUriPrefix+"/v2/login",
            headers:{
                'username':$scope.loginName,
                'password':$scope.loginPwd,
            },
        }
        $http(req)
            .success(function(response){
                if(response.message.code == 401){
                    $rootScope.toasterFail("username or password error!");
                }
                else if(response.message.code == 0){
                    $scope.payload = response.payload;
                    $scope.loginUser = $scope.payload.loginUser;

                    if($scope.loginUser.role.indexOf("STYLIST")<0 ){
                        $rootScope.toasterFail("your account role error");
                        return;
                    }

                    //check if remeber token and expires
                    var check = document.getElementById("checkbox").checked;
                    if (check == true) {
                        var expireDate = new Date();
                        expireDate.setDate(expireDate.getDate() + 30);
                        $cookies.put('userId', $scope.loginUser.userId, {'expires': expireDate});
                        $cookies.put('X-Auth-Token', $scope.loginUser.token, {'expires': expireDate});
                        $cookies.put('username', $scope.loginUser.username, {'expires': expireDate});
                        $cookies.put('expires', $scope.loginUser.expires, {'expires': expireDate});
                    } else {
                        $cookies.put('userId', $scope.loginUser.userId);
                        $cookies.put('username', $scope.loginUser.username);
                        $cookies.put('X-Auth-Token', $scope.loginUser.token);
                        $cookies.put('expires', $scope.loginUser.expires);
                    }
                    $state.go("home.profile",{}, {reload: true});
                }
                else if(response.message.code == 4){
                    $rootScope.toasterFail("This user does not exist!");
                } else {
                    $rootScope.toasterFail("error");
                }
            })
    }
}
angular
    .module('inspinia')
    .controller('loginCtrl', loginCtrl);