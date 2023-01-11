angular.module('app', []).controller('indexController', function($scope, $http){
    const contextPath = 'http://localhost:8179/app';

    //запрос списка продуктов из репозитория
    $scope.loadProducts = function (pageIndex = 1) {
        $http({
              url: contextPath + '/api/v1/products',
              method: 'GET',
              params: {
                  name_part : $scope.product ? $scope.product.name_part : null,
                  min_price : $scope.product ? $scope.product.min_price : null,
                  max_price : $scope.product ? $scope.product.max_price : null
              }
        }).then(function (response) {
              console.log(response);
              $scope.ProductsList = response.data.content;
        });
    }

    //сбросить фильтр
        $scope.reload = function () {
            $scope.product = null;
            $scope.loadProducts();
        }

    //удаление продукта из репозитория по id
    $scope.deleteProduct = function (productId) {
        $http.delete(contextPath + '/api/v1/products/' + productId)
             .then(function (response) {
                $scope.loadProducts();
             });
    }

    //изменение количества продуктов по id
    $scope.changeQuantity = function (productId, delta) {
        $http({
            url: contextPath + '/api/v1/products',
            method: 'PUT',
            params: {
                id: productId,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadProducts();
        });
    }

    //добавить новый продукт
    $scope.addProduct = function () {
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
             .then(function(response) {
                $scope.loadProducts();
             });
    }

    $scope.loadProducts();
});