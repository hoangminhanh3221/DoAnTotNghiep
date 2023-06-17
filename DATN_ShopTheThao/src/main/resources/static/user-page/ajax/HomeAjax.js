$(document).ready(function() {
	// Hàm để cập nhật danh sách sản phẩm khuyến mãi
	function updatePromotions() {
		$.ajax({
			url: '/promotion',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				// Xóa danh sách sản phẩm khuyến mãi hiện tại
				$('#promotionList').empty();

				// Thêm từng sản phẩm vào danh sách
				$.each(data, function(index, promotion) {
						$('#promotionList').append(`
					    <div class="item-slick2 p-l-15 p-r-15 p-t-15 p-b-15">
					        <!-- Block1 -->
					        <div class="block2" style="width:270px">
					            <div class="block2-pic hov-img0">
					                <img src="${promotion.imageName1}" alt="IMG-PRODUCT"/>
					                <a href="/product-detail?id=${promotion.productId}" class="block2-btn flex-c-m stext-103 cl12 size-102 bg1 bor2 hov-btn1 p-lr-15 trans-04 js-show-modal1">
					                    Chi Tiết
					                </a>
					            </div>
					            <div class="block2-txt flex-w flex-t p-t-14">
					                <div class="block2-txt-child1 flex-col-l">
					                    <a href="product-detail.html" class="stext-105 cl5 hov-cl2 trans-04 js-name-b2 p-b-6" style="height: 40px">
					                        ${promotion.productName}
					                    </a>
					                    <div>
					                        <span class="stext-105 cl10 fx1">${promotion.sellingPrice}</span><span>vnđ</span>
					                    </div> 
					                </div>
					                <div class="block2-txt-child2 flex-r p-t-3">
					                    <a href="#" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">
					                        <img class="icon-heart1 dis-block trans-04" src="/user-page/images/icons/icon-heart-01.png" alt="ICON" />
					                        <img class="icon-heart2 dis-block trans-04 ab-t-l" src="/user-page/images/icons/icon-heart-02.png" alt="ICON" />
					                    </a>
					                </div>
					            </div>
					        </div>
					    </div>
					`);
				});
			}
		});
	}
	// Cập nhật danh sách sản phẩm khuyến mãi khi trang được tải
	updatePromotions();

	// Cập nhật danh sách sản phẩm khuyến mãi mỗi 5 giây
	setInterval(updatePromotions, 50000);
});