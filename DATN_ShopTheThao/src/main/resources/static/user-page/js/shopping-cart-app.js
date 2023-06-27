const app = angular.module("shopping-cart-app",[]);
app.controller("shopping-cart-ctrl",function($scope,$http){
    /*
    * QUẢN LÝ GIỎ HÀNG
    */
    
	   $scope.quantity = 1,
	   
	   $scope.increaseQuantity = function() {
	  	$scope.quantity++;
		};
		
		$scope.decreaseQuantity = function() {
		  if ($scope.quantity > 1) {
		    $scope.quantity--;
		  }
		};

	   $scope.cart = {
	    items: [],
	
	    // Thêm sản phẩm vào giỏ hàng
	    add(productId) {
			var quantity = $scope.quantity;
			console.log(quantity)
		    var item = this.items.find(item => item.productId == productId);
		    if (item) {
		        item.qty += quantity;
		    } else {
		        $http.get(`/api/products/${productId}`).then(resp => {
		            resp.data.qty = quantity;
		            this.items.push(resp.data);
		        });
		    }
		    this.saveToLocalStorage();
		},

       //Xoá sản phẩm khỏi giỏ hàng
       remove(id){
           var index = this.items.findIndex(item=>item.id == id);
           this.items.splice(index, 1);
           this.saveToLocalStorage();
       },

       //Xoá sạch các mặt hàng trong giỏ
       clear(){
           this.items = [];
           this.saveToLocalStorage();
       },
       
       //Tính thành tiền của một sản phẩm
       amt_of(item){},

       //Tính tổng số lượng các mặt hàng trong giỏ
		get count() {
		  return this.items
		    .map(item => item.qty || 0) // Chuyển đổi thành số hoặc mặc định là 0 nếu không hợp lệ
		    .reduce((total, qty) => total += qty, 0);
		},

       //Tổng thành tiền các mặt hàng trong giỏ
       get amount(){
        return this.items
            .map(item=>item.qty * item.price)
            .reduce((total,qty)=> total+=qty,0);
       },
       //Lưu giỏ hàng vào local storage
       saveToLocalStorage(){
           //dùng angular để copy xong đổi các mặt hàng sang json
           var json = JSON.stringify(angular.copy(this.items));
           localStorage.setItem("cart",json);
       },

       //Đọc giỏ hàng từ local storage
       loadFromLocalStorage(){
           var json = localStorage.getItem("cart");
           this.items = json?JSON.parse(json) : [];
       },
   };
   $scope.cart.loadFromLocalStorage();

})