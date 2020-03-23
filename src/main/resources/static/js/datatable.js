
      var app = angular.module('ShopApp', ['angularUtils.directives.dirPagination']);
      
      app.controller('ProductsController', function($scope, $http) {
    	  $scope.currentPage = 1;
    	  $scope.value="";
          $http.get('/rest/products/'+$scope.value).
              then(function(response) {
                  $scope.products = response.data;
              });
      });
      

      function PagingController($scope) {
        $scope.pageChangeHandler = function(num) {
        	
        };
      }

      app.controller('PagingController', PagingController);
      