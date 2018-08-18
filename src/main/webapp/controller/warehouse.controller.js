/**
 * Created by haris on 8/18/2018.
 */
app = angular.module("mainApp", []);

app.controller('warehouseController', function ($scope, $http) {
    $scope.fetch = function() {
        $http.get("/warehouse/list").success(function (response) {
            $scope.warehouseList = response;
        });
    };
    $scope.reset = function () {
        $scope.warehouse = {
            name: ""
        };
        $scope.warehouseList = null;
    };

    $scope.submit = function () {
        $http.post("/warehouse/save", $scope.warehouse).success(function (response) {
            $scope.warehouse = response;
            $scope.fetch();
            $scope.reset();
        });
    };
    $scope.fetch();
    $scope.reset();
});

