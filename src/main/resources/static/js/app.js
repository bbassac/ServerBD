angular.module('myApp', [])
    .controller('home', function($scope,$http) {
        $scope.sortReverse  = false;
        $scope.sortType  = 'nom';
        $scope.toggleDetail = function($index) {
            $scope.activePosition = $scope.activePosition == $index ? -1 : $index;
        };

        $http.get('/collection').
        success(function(data) {
            $scope.collection = data;
        });

        $scope.getCouvUrl =  function (url){
                var filename = url.replace(/^.*[\\\/]/, '')
                return "img/couv/"+filename;
            };

    }

    )

