function httpInterceptor($location,$cookies,$q) {
    return {
        request: function(request) {
            if($cookies.get("X-Auth-Token")) {
                request.headers['X-Auth-Token'] = $cookies.get("X-Auth-Token");
            }
            return request;
        },
        response: function(response) {
            if (response.data.message != undefined && response.data.message.code != 0) {
                console.log(response.data.message);
            }
            return response;
        },
        responseError: function(response) {
            if (response.status === 401) {
                $location.path('/login');
            };
            return $q.reject(response);
        }
    };
}
angular.module('inspinia')
    .factory('httpInterceptor', httpInterceptor);