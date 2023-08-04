const app = angular.module("shopping-cart-app",[]);
app.controller("shopping-cart-ctrl",function($scope,$http){
    /*
    * QUẢN LÝ GIỎ HÀNG
    */
    
	   $scope.quantity = 1;
	   
	   $scope.decreaseQuantity = function() {
		  if ($scope.quantity > 1) {
		    $scope.quantity--;
		  }
		};
		
		$scope.increaseQuantity = function() {
		  $scope.quantity++;
		};
		
		$scope.setQuantity = function() {
		  $scope.quantity = 1;
		};

		$scope.cart = {
			items:[],
			//Thêm sản phẩm vào giỏ hàng
			add(productId){
				var item = this.items.find(item => item.productId == productId);
				if(item){
					item.qty += $scope.quantity;
					this.saveToLocalStorage();
				} else {
					$http.get("/api/products/" + productId).then(resp =>{
						resp.data.qty = $scope.quantity;
						this.items.push(resp.data);
						this.saveToLocalStorage();
					})
				}
				$("#getProduct_" + productId).modal("hide");
			},

			//Xóa toàn bộ sản phẩm ra giỏ hàng
			clear(){
				this.items = [];
				this.saveToLocalStorage();
			},

			//Tính tổng các mặt hàng trong giỏ hàng
			get count() {
			  return this.items
			    .map(item => parseInt(item.qty) || 0) // Chuyển đổi thành số hoặc mặc định là 0 nếu không hợp lệ
			    .reduce((total, qty) => total += qty, 0);
			},
			
			//Tổng thành tiền các mặt hàng trong giỏ
			get amount(){
				return this.items
				.map(item => item.qty * item.sellingPrice)
				.reduce((total,qty)=> total += qty,0);
			},
			
			updateQty(productId, quantity){
				var item = this.items.find(item => item.productId == productId);
				if(item && quantity >= 1){
					item.qty = quantity;
					console.log(item.qty)
					this.saveToLocalStorage();
				}
			},
			
			decreaseQty(productId, quantity){
				var item = this.items.find(item => item.productId == productId);
				if(item && quantity > 1){
					item.qty--;
					console.log(quantity)
					this.saveToLocalStorage();
				}
			},
			
			increaseQty(productId, quantity){
				var item = this.items.find(item => item.productId == productId);
				if(item && quantity >= 1){
					item.qty++;
					console.log(item.qty)
					this.saveToLocalStorage();
				}
			},
			
			//Xóa sản phẩm ra giỏ hàng
			remove(productId){
				var index = this.items.findIndex(item => item.productId == productId);
				this.items.splice(index,1);
				this.saveToLocalStorage();
			},
			
			//Lưu giỏ hàng vào Local Storage
			saveToLocalStorage(){
				var json = JSON.stringify(angular.copy(this.items));
				localStorage.setItem("cart",json);
			},
			//Đọc giỏ hàng từ Local Storage
			loadFromLocalStorage(){
				var json = localStorage.getItem("cart");
				this.items = json ? JSON.parse(json) : [];
			}
		}
		$scope.cart.loadFromLocalStorage();
		
		// Hàm để lấy productId và qty từ $scope.cart.items và đưa vào một mảng khác
	    $scope.extractProductIdsAndQtys = function() {
	        var extractedData = [];
	        $scope.cart.items.forEach(function(item) {
	            extractedData.push({
	                productId: item.productId,
	                quantity: item.qty,
	                productPrice: item.sellingPrice
	            });
	        });
	        return extractedData;
	    };
	    
	    $scope.pushShoppingCart = function(){
			console.log($scope.extractProductIdsAndQtys())
			$http.post("/api/orders/getAllCart", $scope.extractProductIdsAndQtys())
            .then(function(response) {
                // Xử lý phản hồi từ API nếu cần thiết
            })
            .catch(function(error) {
                // Xử lý lỗi nếu cần thiết
                console.error("Error sending data to API:", error);
            });
		}
		
		//api địa chỉ
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
          console.log($scope.address);
        })
        .catch(function (error) {});
    };
})