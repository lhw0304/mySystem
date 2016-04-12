function articleStatsController($scope, $cookies, $http, toaster, $state, $rootScope) {
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
    $scope.articleId = $cookies.get('articleId');
    $scope.listArticleStatsDaily = function(id) {
        var req = {
            method: "GET",
            url: webUriPrefix + '/v2/stats/listStylistArticleStatsByDate?articleId=' + id,
            headers: {
                'X-Auth-Token': $scope.token
            },
        };
        $http(req)
            .success(function (response) {
                if (response.payload == null) {
                    $rootScope.toasterFail("No results!");
                    return;
                }
                var list = response.payload.list;
                generateCharts(list);
            });

    }

    $scope.goBack = function() {
        //$rootScope.goBack = "goBack";
        $state.go("statistics.article",{}, {reload: false});
    }

    function generateCharts(list) {
        var split = [];
        var viewData = [];
        var likeData = [];
        var saveData = [];
        var shareData = [];
        var purchaseData = [];
        var date = null;

        for (var i = 0; i < list.statsList.length; i ++) {
            split = list.statsList[i].date.split("-");
            date = Date.UTC(split[0], parseInt(split[1]) - 1, split[2]);
            viewData.push([date, list.statsList[i].view]);
            likeData.push([date, list.statsList[i].like]);
            saveData.push([date, list.statsList[i].save]);
            shareData.push([date, list.statsList[i].share]);
            purchaseData.push([date, list.statsList[i].purchase]);

        }

        var viewDataSet = [
            {
                label: "Views",
                grow:{stepMode:"linear"},
                data: viewData,
                //yaxis: 2,
                color: "#1C84C6",
                lines: {
                    lineWidth: 1,
                    show: true,
                    fill: true,
                    fillColor: {
                        colors: [
                            {
                                opacity: 0.2
                            },
                            {
                                opacity: 0.2
                            }
                        ]
                    }
                }
            }
        ];
        var likeDataSet = [
            {
                label: "Likes",
                grow:{stepMode:"linear"},
                data: likeData,
                //yaxis: 2,
                color: "#1C84C6",
                lines: {
                    lineWidth: 1,
                    show: true,
                    fill: true,
                    fillColor: {
                        colors: [
                            {
                                opacity: 0.2
                            },
                            {
                                opacity: 0.2
                            }
                        ]
                    }
                }
            }
        ];
        var saveDataSet = [
            {
                label: "Saves",
                grow:{stepMode:"linear"},
                data: saveData,
                //yaxis: 2,
                color: "#1C84C6",
                lines: {
                    lineWidth: 1,
                    show: true,
                    fill: true,
                    fillColor: {
                        colors: [
                            {
                                opacity: 0.2
                            },
                            {
                                opacity: 0.2
                            }
                        ]
                    }
                }
            }
        ];
        var shareDataSet = [
            {
                label: "Shares",
                grow:{stepMode:"linear"},
                data: shareData,
                //yaxis: 2,
                color: "#1C84C6",
                lines: {
                    lineWidth: 1,
                    show: true,
                    fill: true,
                    fillColor: {
                        colors: [
                            {
                                opacity: 0.2
                            },
                            {
                                opacity: 0.2
                            }
                        ]
                    }
                }
            }
        ];
        var purchaseDataSet = [
            {
                label: "Buys",
                grow:{stepMode:"linear"},
                data: purchaseData,
                //yaxis: 2,
                color: "#1C84C6",
                lines: {
                    lineWidth: 1,
                    show: true,
                    fill: true,
                    fillColor: {
                        colors: [
                            {
                                opacity: 0.2
                            },
                            {
                                opacity: 0.2
                            }
                        ]
                    }
                }
            }
        ];

        var options = {
            grid: {
                hoverable: true,
                clickable: true,
                tickColor: "#d5d5d5",
                borderWidth: 0,
                color: '#d5d5d5'
            },
            colors: ["#1ab394"],
            tooltip: true,
            tooltipOpts: {
                content: "date: %x, times: %y"
            },
            xaxis: {
                mode: "time",
                tickSize: [3, "day"],
                tickLength: 0,
                axisLabel: "Date",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Arial',
                axisLabelPadding: 10,
                color: "#d5d5d5"
            },
            yaxes: [
                {
                    position: "left",
                    color: "#d5d5d5",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: ' Arial',
                    axisLabelPadding: 67
                }
            ],
            legend: {
                noColumns: 1,
                labelBoxBorderColor: "#d5d5d5",
                position: "nw"
            }

        };

        $scope.flotViewData = viewDataSet;
        $scope.flotLikeData = likeDataSet;
        $scope.flotSaveData = saveDataSet;
        $scope.flotShareData = shareDataSet;
        $scope.flotPurchaseData = purchaseDataSet;
        $scope.flotOptions = options;

    }

    $scope.listArticleStatsDaily($scope.articleId);
}

function commissionStatsController($scope, $cookies, $http, toaster, $rootScope, $state) {
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
    $scope.stylistId = $cookies.get('userId');
    $scope.listCommissionStats = function() {
        var req = {
            method: "GET",
            url: webUriPrefix + '/v2/stats/listStylistCommissionStats?stylistId=' + $scope.stylistId,
            headers: {
                'X-Auth-Token': $scope.token
            },
        };
        $http(req)
            .success(function (response) {
                if (response.payload == null) {
                    $rootScope.toasterFail("No results!");
                    return;
                }
                var list = response.payload.list;
                generateCharts(list);
            });

    }

    $scope.listConfigs = function() {
        var req = {
            method: "GET",
            url: webUriPrefix + '/v2/stats/listConfigs/'+$scope.stylistId,
            headers: {
                'X-Auth-Token': $scope.token
            },
        };
        $http(req)
            .success(function (response) {
                if (response.payload == null) {
                    $rootScope.toasterFail("No More Commission Rules Results!");
                    return;
                }
                var list = response.payload.list;
                generateConfigs(list);
            });

    }

    function generateConfigs(list) {
        var attributeName = "";
        var attributeValue = "";
        var str = "";
        for (var i = 0; i < list.length; i ++) {
            attributeName = list[i].attributeName;
            attributeValue = list[i].attributeValue;
            if (attributeValue == null || attributeValue == '')
                attributeValue = "0";
            if (attributeName.indexOf("view.") == 0)
                str += '<tr><td><img src="img/view-01.png" width="20"></td><td>$' + attributeValue + '</td></tr>';
            else if (attributeName.indexOf("like.") == 0)
                str += '<tr><td><img src="img/like-01.png" width="20"></td><td>$' + attributeValue + '</td></tr>';
            else if (attributeName.indexOf("save.") == 0)
                str += '<tr><td><img src="img/save-01.png" width="20"></td><td>$' + attributeValue + '</td></tr>';
            else if (attributeName.indexOf("share.") == 0)
                str += '<tr><td><img src="img/share-01.png" width="20"></td><td>$' + attributeValue + '</td></tr>';
            else if (attributeName.indexOf("purchase.") == 0)
                str += '<tr><td><img src="img/purchase-01.png" width="20"></td><td>$' + attributeValue + '</td></tr>';
        }
        var configs = document.getElementById("configs");
        configs.innerHTML=str;
    }

    function generateCharts(list) {
        var year = null;
        var month = null;
        var day = null;
        var dateString = null;
        var date = null;
        var usd = null;
        var data = [];
        for (var i = list.length - 1; i >= 0; i --) {
            dateString = "" + list[i].date;
            usd = list[i].usd;
            year = dateString.substring(0,4);
            month = dateString.substring(4,6);
            day = dateString.substring(6,8);
            date = Date.UTC(year, month - 1, day);
            data.push([date, usd]);
        }

        var dataSet = [
            {
                label: "USD",
                grow:{stepMode:"linear"},
                data: data,
                //yaxis: 2,
                color: "#1C84C6",
                lines: {
                    lineWidth: 1,
                    show: true,
                    fill: true,
                    fillColor: {
                        colors: [
                            {
                                opacity: 0.2
                            },
                            {
                                opacity: 0.2
                            }
                        ]
                    }
                }
            }
        ];
        var options = {
            grid: {
                hoverable: true,
                clickable: true,
                tickColor: "#d5d5d5",
                borderWidth: 0,
                color: '#d5d5d5'
            },
            colors: ["#1ab394"],
            tooltip: true,
            tooltipOpts: {
                content: "date: %x, USD: %y"
            },
            xaxis: {
                mode: "time",
                tickSize: [3, "day"],
                tickLength: 0,
                axisLabel: "Date",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Arial',
                axisLabelPadding: 10,
                color: "#d5d5d5"
            },
            yaxes: [
                {
                    position: "left",
                    color: "#d5d5d5",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: ' Arial',
                    axisLabelPadding: 67
                }
            ],
            legend: {
                noColumns: 1,
                labelBoxBorderColor: "#d5d5d5",
                position: "nw"
            }

        };

        $scope.dataSet = dataSet;
        $scope.flotOptions = options;
    }
    $scope.listCommissionStats();
    $scope.listConfigs();
}


/**
 *
 * Pass all functions into module
 */
angular
    .module('inspinia')
    .controller('articleStatsController', articleStatsController)
    .controller('commissionStatsController', commissionStatsController);