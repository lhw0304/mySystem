function utilService(toaster) {

    this.toasterSuccess = function (message) {
        toaster.success({body: message});
    };
    this.toasterFail = function (message) {
        toaster.pop({
            type: 'error',
            body: message,
            showCloseButton: true,
            timeout: 6000
        });
    };
    this.toasterWarning = function(message){
        toaster.warning({body: message});
    };
}

function httpService($http, $q, $rootScope) {
    // Sync http GET
    this.GET = function (url, headers, data){
        var deferred = $q.defer();
        var req = {
            method: "GET",
            url: url,
            headers: headers,
            data: data
        };
        $http(req)
            .success(function(response){
                $rootScope.responseMessage = response.message;
                deferred.resolve(response);
            })
            .error(function(){
                deferred.resolve("error");
            });
        return deferred.promise;
    };

    // Sync http POST
    this.POST = function (url, headers, data){
        var deferred = $q.defer();
        var req = {
            method: "POST",
            url: url,
            headers: headers,
            data: data
        };
        $http(req)
            .success(function(response){
                $rootScope.responseMessage = response.message;
                deferred.resolve(response);
            })
            .error(function(){
                deferred.resolve("error");
            });
        return deferred.promise;
    };
}

angular.module("inspinia")
    .service("utilService", utilService)
    .service("httpService", httpService);
