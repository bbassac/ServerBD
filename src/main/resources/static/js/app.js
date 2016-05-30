angular.module('myApp', [])
    .controller('home', function($scope,$http) {
        //$scope.collection = {id: 'xxx', content: 'Hello World!'}
        $http.get('/collection').
        success(function(data) {
            $scope.collection = data;
        });
    })