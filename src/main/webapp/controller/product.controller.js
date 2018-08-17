var mainApp = angular.module("mainApp", []);

mainApp.controller('productController', function ($scope,$http) {
    $scope.fetch = function() {
        $http.get("/product/list/inventory").success(function (response) {
            $scope.productList = response;
        });
    };
    $scope.reset = function() {
        $scope.product = {
            productName: "",
            inventoryAmt: 0
        };
    };

    $scope.fetch();
    $scope.reset();

    $scope.submit = function(){
        $http.post("/product/save", $scope.product).success( function(response) {
            $scope.product = response;
            $scope.fetch();
            $scope.reset();
        });
    };

    $scope.submitEnter = function($event){
        if($event.keyCode==13) $scope.submit();
    }

});