app = angular.module("mainApp", []);

app.controller('productController', function ($scope,$http) {
    $scope.fetch = function() {
        $http.get("/product/list/inventory?warehouseId="+$scope.selectedWarehouse.id).success(function (response) {
            $scope.productList = response;
        });
    };
    $scope.fetchWarehouse = function() {
        $http.get("/warehouse/list").success(function (response) {
            $scope.warehouseList = response;
            $scope.selectedWarehouse = $scope.warehouseList[0];
            $scope.fetch();

            sessionStorage.setItem("warehouse", $scope.selectedWarehouse);
            //console.log(sessionStorage.getItem("uid"));
        });
    };
    $scope.reset = function() {
        $scope.product = {
            productName: "",
            inventoryAmt: 0
        };
        if(sessionStorage.getItem("warehouse")) $scope.selectedWarehouse = sessionStorage.getItem("warehouse");
    };

    $scope.submit = function(){
        $http.post("/product/save", $scope.product).success( function(response) {
            $scope.product = response;
            $scope.fetch();
            $scope.reset();
        });
    };

    $scope.submitEnter = function($event){
        if($event.keyCode==13) $scope.submit();
    };

    $scope.reset();
    $scope.fetchWarehouse();

});
