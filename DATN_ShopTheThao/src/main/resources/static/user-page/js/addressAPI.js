const app = angular.module("addressAPI-app",[]);
app.controller("addressAPI-ctrl",function($scope,$http){
// Location
    const hostLocation = "https://provinces.open-api.vn/api/";
    $scope.selectedDistrict = false;
    $scope.selectedWard = false;
    $scope.provinces = [];
    $scope.province = {};
    $scope.districts = [];
    $scope.wards = [];
    var provinceId;
    var wardId;
    var districtId;

    $scope.callApiGetAll = function (api) {
        $http.get(api)
            .then(function (response) {
                $scope.provinces = response.data;
            }).catch(function (error) {
        });
    };

    $scope.callApiGetAll(hostLocation + "?depth=1");

    $scope.callApiDistrict = function (api) {
        $http.get(api)
            .then(function (response) {
                $scope.districts = response.data.districts;
            }).catch(function (error) {
        });
    };

    $scope.callApiWard = function (api) {
        $http.get(api)
            .then(function (response) {
                $scope.wards = response.data.wards;
            }).catch(function (error) {
        });
    };
})