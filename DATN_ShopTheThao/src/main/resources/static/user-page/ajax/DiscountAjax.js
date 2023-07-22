$("#selectCategory").change(function() {
	// Lấy phần tử select bằng class
	const selectCategory = document.getElementById('selectCategory');
	const selectedValue = selectCategory.value;
	
	// Gọi controller bằng phương thức AJAX 
    $.ajax({
        type: "GET",
        url: "/admin/discount/add/soft?id=" + selectedValue,
        success: function(result) {
	        // Cập nhật danh sách sản phẩm
	        var optionsHtml = '<option value="0">Chọn sản phẩm</option>';
	        result.forEach(function(product) {
	            optionsHtml += '<option value="' + product.productId + '">' + product.productId + ' - ' + product.productName + '</option>';
	        });
	        $("#selectProduct").html(optionsHtml);
        },
        error: function(error) {
            console.log("Error:", error);
        }
    });
});

$("#selectProduct").change(function() {
	// Lấy phần tử select bằng class
	const selectProduct = document.getElementById('selectProduct');
	const selectedValue = selectProduct.value;
	
	// Gọi controller bằng phương thức AJAX 
    $.ajax({
        type: "GET",
        url: "/admin/discount/add/addprd?id=" + selectedValue,
        success: function(result) {
	        // Cập nhật danh sách sản phẩm
	        
	        var optionsHtml ='';
	        result.forEach(function(product, index) {
	              optionsHtml += '<tr>';
				  optionsHtml += '<td>';
				  optionsHtml += '<div class="form-check">';
				  optionsHtml += '<input class="form-check-input" type="checkbox" value="" id="defaultCheck2" />';
				  optionsHtml += '<label class="form-check-label" for="defaultCheck2"> </label>';
				  optionsHtml += '</div>';
				  optionsHtml += '</td>';
				  optionsHtml += '<td>' + (index+1) + '</td>';
				  optionsHtml += '<td>' + product.productName + '</td>';
				  optionsHtml += '<td>';
				  optionsHtml += '<button';
				  optionsHtml += ' onclick="deleteProduct(\'' + product.productId + '\')"';
				  optionsHtml += ' style="border:none;background-color:white;"';
				  optionsHtml += ' class="ps-2"';
				  optionsHtml += ' data-bs-toggle="modal"';
				  optionsHtml += ' data-bs-target="#modalToggle"';
				  optionsHtml += '><i class="bx bx-trash"></i></button>';
				  optionsHtml += '</td>';
				  optionsHtml += '</tr>';
			});
	        $("#tblPrd").html(optionsHtml);
        },
        error: function(error) {
            console.log("Error:", error);
        }
    });
});

function deleteProduct(id) {
  $.ajax({
        type: "GET",
        url: "/admin/discount/add/deleteprd?id=" + id,
        success: function(result) {
	        // Cập nhật danh sách sản phẩm
	        
	        var optionsHtml ='';
	        result.forEach(function(product, index) {
	              optionsHtml += '<tr>';
				  optionsHtml += '<td>';
				  optionsHtml += '<div class="form-check">';
				  optionsHtml += '<input class="form-check-input" type="checkbox" value="" id="defaultCheck2" />';
				  optionsHtml += '<label class="form-check-label" for="defaultCheck2"> </label>';
				  optionsHtml += '</div>';
				  optionsHtml += '</td>';
				  optionsHtml += '<td>' + (index+1) + '</td>';
				  optionsHtml += '<td>' + product.productName + '</td>';
				  optionsHtml += '<td>';
				  optionsHtml += '<button';
				  optionsHtml += ' style="border:none;background-color:white;"';
				  optionsHtml += ' onclick="deleteProduct(\'' + product.productId + '\')"';
				  optionsHtml += ' class="ps-2"';
				  optionsHtml += ' data-bs-toggle="modal"';
				  optionsHtml += ' data-bs-target="#modalToggle"';
				  optionsHtml += '><i class="bx bx-trash"></i></button>';
				  optionsHtml += '</td>';
				  optionsHtml += '</tr>';
			});
	        $("#tblPrd").html(optionsHtml);
        },
        error: function(error) {
            console.log("Error:", error);
        }
    });
}



