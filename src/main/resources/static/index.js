angular.module('app', []).controller('indexController', function($scope, $http){
    const contextPath = 'http://localhost:8179/app';

    //запрос списка продуктов из репозитория
    $scope.loadProducts = function () {
        $http.get(contextPath + '/products')
             .then(function (response) {
                 console.log(response);
                 $scope.ProductsList = response.data;
             });
    };

    //удаление продукта из репозитория по id
    $scope.deleteProduct = function (productId) {
        $http.get(contextPath + '/products/delete/' + productId)
             .then(function (response) {
                $scope.loadProducts();
             });
    }

    //изменение количества продуктов по id
    $scope.changeQuantity = function (productId, delta) {
        $http({
            url: contextPath + '/products/change_cost',
            method: 'GET',
            params: {
                id: productId,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadProducts();
        });
    }

    $scope.loadProducts();
});