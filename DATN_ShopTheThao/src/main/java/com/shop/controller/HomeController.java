package com.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.entity.Discount;
import com.shop.entity.Favorite;
import com.shop.entity.OrderDetail;
import com.shop.entity.Product;
import com.shop.service.DiscountService;
import com.shop.service.FavoriteService;
import com.shop.service.OrderDetailService;
import com.shop.service.ProductService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;

@Controller
public class HomeController {
<<<<<<< HEAD

=======
	
>>>>>>> update-entity
	@Autowired
	ProductService productService;

	@Autowired
	FavoriteService favoriteService;

	@Autowired
	DiscountService discountService;

	@Autowired
	OrderDetailService orderDetailService;

	@GetMapping("/home")
	public String index(Model model) {

		model.addAttribute("lPDiscount", productService.findProductsOnSale());
		model.addAttribute("lPDateDesc", productService.getProductsSortByDateDesc());
		model.addAttribute("lPSaleDesc", productService.findBestSellingProducts());
		model.addAttribute("lPFavorite", productService.findMostLikedProducts());
		return "user-page/home";
	}

	@Scheduled(fixedDelay = 60000) // Tải lại sau mỗi 1 phút (60 giây)
	public void updateProducts() {
		// Gọi phương thức loadProducts() định kỳ để tải sản phẩm khuyến mãi mới

	}
<<<<<<< HEAD
=======
	// Lấy sp khuyến mãi
	List<Product> getDiscountProducts(){
		
		List<Product> listPrd = productService.findAllProduct();
		List<Discount> listDC = discountService.findAllDiscount();
		List<Product> list = new ArrayList<>();
		
		//xắp xếp khuyến mãi theo tỉ lệ giảm giá với thứ tự giảm dần
		for (int i = 0; i < listDC.size(); i++) {
			for (int j = 1; j < listDC.size(); j++) {
				if (listDC.get(i).discountRate<listDC.get(j).discountRate) {
					Collections.swap(listDC, i, j);
				}
			}
		}
		for (int j = 0; j < listDC.size(); j++) {
			if (listDC.get(j).discountId.equalsIgnoreCase("DS04")) {
				listDC.remove(j);
			}
		}
		for (int i = 0; i < listDC.size(); i++) {
			for (int j = 0; j < listPrd.size(); j++) {
				if (listDC.get(i).discountId.equalsIgnoreCase(listPrd.get(j).discount.discountId )) {
					list.add(listPrd.get(j));
				}
			}
		}
		this.productsDC = list;
		
		return list;
	}
	
	
	
	//Lấy sản phầm có nhiều lượt yêu thích nhất
	
	public List<Product> ListProductLike() {
		
		List<Favorite> fav= favoriteService.findAllFavorites();
		List<Product> pro= new ArrayList<Product>();
		
		// Đếm số lần xuất hiện của mỗi sản phẩm
        Map<String, Integer> counts = new HashMap<>();
        for (Favorite student : fav) {
            counts.put(student.getProduct().productId, counts.getOrDefault(student, 0) + 1);
        }
        
        // Xây dựng danh sách mới chứa sản phẩm và số lần xuất hiện
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(counts.entrySet());

        // Sắp xếp danh sách mới theo số lần xuất hiện giảm dần
        Collections.sort(sortedList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        // Lọc danh sách mới để loại bỏ các sản phẩm trùng lặp
        Set<String> uniqueProducts = new HashSet<>();
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sortedList) {
        	String id = entry.getKey();
            if (!uniqueProducts.contains(id)) {
                result.add(id);
                uniqueProducts.add(id);
            }
        }

        // In danh sách kết quả
        for (String f : result) {
            System.out.println(f);
        }
        
        // Duyệt mảng và lấy sản phẩm nổi bật
        for (String f : result) {
			Product p = productService.findProductById(f).get();
			pro.add(p);
		}
        return pro;
	}
	
	@Scheduled(fixedDelay = 5000) // Tải lại sau mỗi 1 phút (60 giây)
    public void updateProducts() {
        // Gọi phương thức loadProducts() định kỳ để tải sản phẩm khuyến mãi mới
		getDiscountProducts();
        
    }
>>>>>>> update-entity
}
