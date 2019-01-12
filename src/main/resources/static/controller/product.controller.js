app = angular.module("mainApp", []);

app.controller('productController', function ($scope,$http) {
    $scope.fetch = function () {
        $http.get("/product/list/inventory?warehouseId=" + $scope.selectedWarehouse.id).success(function (response) {
            $scope.productList = response;
        });
    };
    $scope.fetchWarehouse = function () {
        $http.get("/warehouse/list").success(function (response) {
            $scope.warehouseList = response;
            $scope.selectedWarehouse = $scope.warehouseList[0];
            $scope.fetch();

            //sessionStorage.setItem("warehouse", $scope.selectedWarehouse);
            //console.log(sessionStorage.getItem("uid"));
        });
    };
    $scope.reset = function () {
        $scope.product = {};
        $scope.addBtnShow = true;
        $scope.editBtnShow = false;
    };

    $scope.edit = function (item) {
        var itemToEdit = item;
        console.log(itemToEdit);
        $scope.product = itemToEdit;
        $scope.addBtnShow = false;
        $scope.editBtnShow = true;
    };
    $scope.saveUpdateProduct = function() {
        var warehouseId = $scope.selectedWarehouse.id;
        $http.post("/product/save?warehouseId=" + warehouseId, $scope.product).success(function (response) {
            $scope.product = response;
            $scope.fetch();
            $scope.reset();
        });
    };

    $scope.submit = function(){
        $scope.saveUpdateProduct();
    };

    $scope.editSubmit = function(){
        $scope.saveUpdateProduct();
    };

    $scope.submitEnter = function($event){
        if($event.keyCode==13) $scope.submit();
    };

    $scope.reset();
    $scope.fetchWarehouse();
    $scope.addBtnShow = true;
    $scope.editBtnShow = false;

});
