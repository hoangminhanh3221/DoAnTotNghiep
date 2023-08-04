angular.module('shopping-cart-app', [])
    .controller('shopping-cart-ctrl', function ($scope, $http) {
	const hostLocation = "https://provinces.open-api.vn/api/";
    $scope.provinces = [];
    $scope.districts = [];
    $scope.wards = [];
    $scope.provinceId = null;
	$scope.districtId = null;
	$scope.wardId = null;
    
    $scope.callApiGetAll = function (api) {
      $http
        .get(api)
        .then(function (response) {
          $scope.provinces = response.data;
        })
        .catch(function (error) {});
    };

    $scope.callApiGetAll(hostLocation + "?depth=1");

    $scope.callApiDistrict = function (api) {
      $http
        .get(api)
        .then(function (response) {
          $scope.districts = response.data.districts;
        })
        .catch(function (error) {});
    };

    $scope.callApiWard = function (api) {
      $http
        .get(api)
        .then(function (response) {
          $scope.wards = response.data.wards;
        })
        .catch(function (error) {});
    };

    $scope.updateProvince = function () {
      // Gọi hàm để lấy dữ liệu quận/huyện của thành phố đã chọn
      $scope.callApiDistrict(hostLocation + "p/" + $scope.provinceId + "?depth=2");
    };

    $scope.updateDistrict = function () {
      // Gọi hàm để lấy dữ liệu Xã/Phường của quận/huyện đã chọn
      $scope.callApiWard(hostLocation + "d/" + $scope.districtId + "?depth=2");
    };

    $scope.updateWard = function () {
      $scope.address = {
		  ward:$("#ward option:selected").text(),
		  district:$("#district option:selected").text(),
		  city:$("#province option:selected").text()
	  }
	  $http.post("/api/orders/address", $scope.address)
        .then(function (response) {
          console.log(response.data);
        })
        .catch(function (error) {});
    };
    });