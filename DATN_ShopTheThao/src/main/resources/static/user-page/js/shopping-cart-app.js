const app = angular.module("shopping-cart-app",[]);
app.controller("shopping-cart-ctrl",function($scope,$http){
	//Quản lí giỏ hàng
	$scope.cart = {
		items:[],
		//Thêm sản phẩm vào giỏ hàng
		add(id, number){
			var item = this.items.find(item => item.id == id);
			if(item){
				item.qty++;
				this.saveToLocalStorage();
			} else {
				$http.get(`/rest/products/${id}`).then(resp =>{
					resp.data.qty = 1;
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
		//Tính thành tiền của 1 sản phẩm
		amt_of(item){},
		//Tính tổng các mặt hàng trong giỏ hàng
		get count(){
			return this.items
			.map(item=>item.qty)
			.reduce((total,qty)=> total +=qty,0);
		},
		//Tổng thành tiền các mặt hàng trong giỏ
		get amount(){
			return this.items
			.map(item => item.qty * item.price)
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
	
<<<<<<< HEAD
	    // Thêm sản phẩm vào giỏ hàng
	    add(productId) {
			var quantity = $scope.quantity;
		    var item = this.items.find(item => item.productId == productId);
		    if (item) {
		        item.qty += quantity;
		    } else {
		        $http.get('/api/products/${productId}').then(resp => {
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
           console.log(this.items)
           var json = JSON.stringify(angular.copy(this.items));
           localStorage.setItem("cart",json);
       },

       //Đọc giỏ hàng từ local storage
       loadFromLocalStorage(){
           var json = localStorage.getItem("cart");
           this.items = json?JSON.parse(json) : [];
           console.log(this.items)
       },
   };
   $scope.cart.loadFromLocalStorage();

=======
	$scope.cart.loadFromLocalStorage();

	$scope.order = {
		createDate : new Date(),
		address:"",
		account: {username: $("#username").text()},
		get orderDetail(){
			return $scope.cart.items.map(item => {
				return {
					product:{id: item.id},
					price: item.price,
					quantity: item.qty 
				}
			})
		},
		purchase(){
			var order = angular.copy(this);
			//Thực hiện đặt hàng
			$http.post("/rest/orders", order).then(resp =>{
				alert("Đặt hàng thành công!");
				$scope.cart.clear();
				location.href = "/order/detail/" + resp.data.id;
			}).catch(error => {
				alert("Đặt hàng lỗi!")
				console.log(error)
			})
		}
	}
>>>>>>> parent of edb4097 (doing)
})