const app = angular.module("shopping-cart-app",[]);
app.controller("shopping-cart-ctrl",function($scope,$http){
    /*
    * QUẢN LÝ GIỎ HÀNG
    */
    
	   $scope.quantity = 1;
	   
	   $scope.increaseQuantity = function() {
	  	$scope.quantity++;
		};
		
		$scope.decreaseQuantity = function() {
		  if ($scope.quantity > 1) {
		    $scope.quantity--;
		  }
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
			},
			//Xóa sản phẩm ra giỏ hàng
			remove(id){
				var index = this.items.findIndex(item => item.id == id);
				this.items.splice(index,1);
				this.saveToLocalStorage();
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
})