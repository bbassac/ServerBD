angular.module('hello', [])
    .controller('home', function($scope,$http) {
        //$scope.greeting = {id: 'xxx', content: 'Hello World!'}
        $http.get('http://rest-service.guides.spring.io/greeting').
        success(function(data) {
            $scope.greeting = data;
        });
    })