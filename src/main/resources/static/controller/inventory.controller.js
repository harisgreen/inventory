app = angular.module("mainApp", []);

app.controller('inventoryController', function ($scope,$http,$filter) {

    $scope.gap = 15;
    $scope.filteredItems = [];
    $scope.groupedItems = [];
    $scope.itemsPerPage = 15;
    $scope.pagedItems = [];
    $scope.currentPage = 0;
    $scope.query = "";

    $scope.fetchProducts = function() {
        $http.get("/product/list/inventory?warehouseId="+$scope.selectedWarehouse.id).success(function (response) {
            $scope.productList = response;
        });
    };
    $scope.fetch = function() {
        $http.get("/inventory/list?warehouseId="+$scope.selectedWarehouse.id).success(function (response) {
            $scope.inventoryList = response;
            $scope.search();
        });
        $scope.fetchProducts();
    };
    $scope.fetchWarehouse = function() {
        $http.get("/warehouse/list").success(function (response) {
            $scope.warehouseList = response;
            $scope.selectedWarehouse = $scope.warehouseList[0];
            $scope.fetch();
        });
    };
    $scope.reset = function() {
        $scope.selectedProduct = {
            inventoryAmt: 0
        };
        $scope.inventory = {};
    };

    $scope.submit = function(){
        console.log($scope.selectedProduct);
        console.log($scope.inventory);
        $scope.inventory.product = $scope.selectedProduct;
        $scope.inventory.warehouse = $scope.selectedWarehouse;
        if($scope.inventory.receivedStock == null) $scope.inventory.receivedStock = 0;
        if($scope.inventory.issuedStock == null) $scope.inventory.issuedStock = 0;

        $http.post("/inventory/save", $scope.inventory).success( function(response) {
            $scope.inventory = response;
            $scope.fetch();
            $scope.fetchProducts();
            $scope.reset();
        });
    };

    $scope.isAnySelected = function() {
        var anyDelete=false;
        angular.forEach($scope.pagedItems[$scope.currentPage], function (v) {
            if (v.isDelete) {
                anyDelete = true;
            }
        });
        return !anyDelete;
    };

    $scope.remove = function() {
        var ids = [];
        angular.forEach($scope.pagedItems[$scope.currentPage], function (v) {
            if (v.isDelete) {
                ids.push(v.id);
            }
        });
        if(ids.length > 0) {
            $http.post("/inventory/remove?warehouseId="+$scope.selectedWarehouse.id, ids).success(function (response) {
                $scope.inventoryList = response;
                $scope.search();
            });
        }
    };

    $scope.calculateNewBalance = function($event){
        var inv = $scope.inventory;
        var newBalance = $scope.selectedProduct.inventoryAmt - (inv.issuedStock == null ? 0 : inv.issuedStock)
            + (inv.receivedStock == null ? 0 : inv.receivedStock);
        inv.inventoryBalance = 0;
        inv.inventoryBalance = newBalance;
    };

    var searchMatch = function (haystack, needle) {
        if (!needle) {
            return true;
        }
        return haystack.toLowerCase().indexOf(needle.toLowerCase()) !== -1;
    };

    // init the filtered items
    $scope.search = function () {
        $scope.filteredItems = $filter('filter')($scope.inventoryList, function (item) {
            for(var attr in item) {
                if (searchMatch(item[attr], $scope.query))
                    return true;
            }
            return false;
        });
        $scope.currentPage = 0;
        // now group by pages
        $scope.groupToPages();
    };

    $scope.groupToPages = function () {
        $scope.pagedItems = [];

        for (var i = 0; i < $scope.filteredItems.length; i++) {
            if (i % $scope.itemsPerPage === 0) {
                $scope.pagedItems[Math.floor(i / $scope.itemsPerPage)] = [ $scope.filteredItems[i] ];
            } else {
                $scope.pagedItems[Math.floor(i / $scope.itemsPerPage)].push($scope.filteredItems[i]);
            }
        }
    };

    $scope.prevPage = function () {
        if ($scope.currentPage > 0) {
            $scope.currentPage--;
        }
    };

    $scope.range = function (size,start, end) {
        var ret = [];
        // console.log(size,start, end);

        if (size < end) {
            end = size;
            if(size<$scope.gap){
                start = 0;
            }else{
                start = size-$scope.gap;
            }

        }
        for (var i = start; i < end; i++) {
            ret.push(i);
        }
        // console.log(ret);
        return ret;
    };

    $scope.nextPage = function () {
        if ($scope.currentPage < $scope.pagedItems.length - 1) {
            $scope.currentPage++;
        }
    };

    $scope.setPage = function () {
        $scope.currentPage = this.n;
    };

    $scope.reset();
    $scope.fetchWarehouse();

});