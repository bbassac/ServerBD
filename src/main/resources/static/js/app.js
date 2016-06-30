angular.module('myApp', [])
    .controller('home', function($scope,$http) {
        $scope.toggleDetail = function($index) {
            $scope.activePosition = $scope.activePosition == $index ? -1 : $index;
        };

        $http.get('/collection').
        success(function(data) {
            $scope.collection = data;
        });

    })