function MainCtrl($scope,$cookies,$state,$rootScope, $http) {
    $scope.logout = function() {
        $cookies.remove("X-Auth-Token");
        $cookies.remove("expires");
        $state.go("login");
    }
};

function contentCtrl($scope,$http,$cookies){
    $scope.userId = $cookies.get("userId");
    $scope.getNavProfile = function() {
        var req= {
            method:"GET",
            url:webUriPrefix+"/v2/profiles/users/"+$scope.userId,
        }
        $http(req)
            .success(function(response){
                if(response.message.code == 0){
                    $scope.profile = response.payload.profile;
                    $scope.avatar = $scope.profile.avatar;
                    $scope.nickname = $scope.profile.nickname;

                    $scope.navAvatar = $scope.avatar;
                    $cookies.put('nickname', $scope.nickname);
                    $scope.navNickname = $scope.nickname;
                } else {

                }
            })
            .error(function(data,status){
                //$rootScope.toasterFail("Get profile message error!");
            })
    }
    //$scope.getNavProfile();
}
/**
 * translateCtrl - Controller for translate
 */
function translateCtrl($translate, $scope) {
    $scope.changeLanguage = function (langKey) {
        $translate.use(langKey);
    };
}

/**
 * wizardCtrl - Controller for wizard functions
 * used in Wizard view
 */
function wizardCtrl($scope, $rootScope) {
    // All data will be store in this object
    $scope.formData = {};

    // After process wizard
    $scope.processForm = function() {
        alert('Wizard completed');
    };
}

function getProfileCtrl($scope,$http,$state,$cookies,$rootScope, toaster){
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
    $scope.userId = $cookies.get('userId');
    $scope.getProfile = function() {
        var req= {
            method:"GET",
            url:webUriPrefix+"/system/profile/"+$scope.userId,
        }
        $http(req)
            .success(function(response){
                if(response.message.code == 0){
                    $scope.profile = response.payload.profile;
                    $scope.nickname = $scope.profile.nickname;
                    $scope.description = $scope.profile.description;
                    $scope.checkCount = response.payload.checkCount;
                    $scope.completionCount = response.payload.completionCount;
                    $scope.singleCount = response.payload.singleCount;
                    $scope.multiCount = response.payload.multiCount;
                    $scope.shortCount = response.payload.shortCount;
                    document.getElementById("description").innerHTML = $scope.description;
                    $cookies.put('nickname', $scope.nickname);
                    //alert($scope.usd)
                } else {
                    $rootScope.toasterFail("Get profile message error!");
                }
            })
            .error(function(data,status){
                $rootScope.toasterFail("Get profile message error!");
            })
    }
    $scope.getProfile();
}

function updatePasswordCtrl($scope,$http,$state,$cookies,utilService, $rootScope) {

    $scope.username = $cookies.get('username');
    $scope.changePassword = function(){
        document.getElementById("submit").style.display='none';
        document.getElementById("submitSuccess").style.display='none';
        document.getElementById("submitError").style.display='none';
        document.getElementById("submitSpinner").style.display='inline';
        if($scope.currentPassword == null || $scope.newPassword == null || $scope.confirmNewPassword == null) {
            document.getElementById("submit").style.display='inline';
            document.getElementById("submitSpinner").style.display='none';
            utilService.toasterFail("Please enter your password.")
            return;
        } else if($scope.newPassword != $scope.confirmNewPassword) {
            document.getElementById("submit").style.display='inline';
            document.getElementById("submitSpinner").style.display='none';
            utilService.toasterFail("Please check that your passwords match and try again.")
            return;
        } else if($scope.newPassword.length < 8 ) {
            document.getElementById("submit").style.display='inline';
            document.getElementById("submitSpinner").style.display='none';
            utilService.toasterFail("Please enter a case-sensitive password that is at least 8 characters long.");
            return;
        }
        $scope.md5NewPassword = md5($scope.newPassword);
        $scope.md5CurrentPassword = md5($scope.currentPassword);
        var req = {
            method:"POST",
            url:webUriPrefix+"/system/changePassword",
            headers: {
                'userId' :  $scope.userId,
                'oldPassword' : $scope.md5CurrentPassword,
                'newPassword' : $scope.md5NewPassword,
            },
        }
        $http(req)
            .success(function(response){
                if(response.message.code == 0){
                    document.getElementById("submitSpinner").style.display='none';
                    document.getElementById("submitSuccess").style.display='inline';
                    utilService.toasterSuccess("Operate Succeed!");
                } else if(response.message.code == 401) {
                    document.getElementById("submitSpinner").style.display='none';
                    document.getElementById("submitError").style.display='inline';
                    utilService.toasterFail("CurrentPassword Error!");
                }
            })
            .error(function(data,status){
                utilService.toasterFail("Operation Failed!");
            })
    }
}

function updateProfileCtrl($scope,$http,$state,$cookies,$rootScope,utilService){
    $scope.userId = $cookies.get('userId');
    $scope.getProfile = function(){
        var req= {
            method:"GET",
            url:webUriPrefix+"/system/profile/"+$scope.userId,
        }
        $http(req)
            .success(function(response){
                if(response.message.code == 0){
                    $scope.profile = response.payload.profile;
                    $scope.nickname = $scope.profile.nickname;
                    $scope.description = $scope.profile.description;
                    $scope.description = $scope.description.replace(/<\/br>/g,"\n");
                } else {
                    utilService.toasterFail("getProfile message error!");
                }
            })
            .error(function(data,status){
                utilService.toasterFail("getProfile message error!");
            })
    }
    $scope.$watch('description', function(newVal, oldVal) {
        if (newVal && newVal != oldVal) {
            $scope.num = 0;
            if (newVal.length >= 255) {
                $scope.description = newVal.substr(0, 255);
            }
            for(var i=0; i<newVal.length; i++) {
                if(newVal[i] == '\n')
                    $scope.num++;
            }
        }
    })
    $scope.getProfile();
    $scope.updateProfile = function() {
        document.getElementById("submit").style.display='none';
        document.getElementById("submitSuccess").style.display='none';
        document.getElementById("submitError").style.display='none';
        document.getElementById("submitSpinner").style.display='inline';
        $scope.descriptionString = $scope.description.replace(/\n|\r\n/g,"</br>");
        var formData = new FormData();
        formData.append("nickname", $scope.nickname);
        formData.append("description", $scope.descriptionString);
        var req={
            method:"POST",
            url:webUriPrefix+"/system/profile/"+$scope.userId,
            headers: {
                'Content-Type': undefined,
            },
            data: formData,
            transformRequest: function (data, headersGetterFunction) {
                return data;
            }
        }
        $http(req)
            .success(function(response){
                if(response.message.code == 0){
                    document.getElementById("submitSpinner").style.display='none';
                    document.getElementById("submitSuccess").style.display='inline';
                    utilService.toasterSuccess("operate succeed!");
                } else {
                    document.getElementById("submitSpinner").style.display='none';
                    document.getElementById("submitError").style.display='inline';
                    utilService.toasterFail("operation failed");
                }
            })
            .error(function(data,status){
                utilService.toasterFail("operation failed");
            })
    }
}


angular
    .module('inspinia')
    .controller('MainCtrl', MainCtrl)
    .controller('contentCtrl', contentCtrl)
    .controller('wizardCtrl', wizardCtrl)
    .controller('translateCtrl', translateCtrl)
    .controller('getProfileCtrl', getProfileCtrl)
    .controller('updatePasswordCtrl', updatePasswordCtrl)
    .controller('updateProfileCtrl', updateProfileCtrl);
    