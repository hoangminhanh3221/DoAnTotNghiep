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
	
	@Autowired
	ProductService productService;
	
	@Autowired
	FavoriteService favoriteService;
	
	@Autowired
	DiscountService discountService;
	
	@Autowired
	OrderDetailService orderDetailService;
	
	private List<Product> productsDC = new ArrayList<>();
	
	

	@GetMapping("/home")
	public String index(Model model) {
		
		//Lấy danh sách sản phẩm và xắp xếp theo trường ngày nhận với thứ thự giảm dần và thêm vào model
		List<Product> lPDateDesc =	productService.getProductsSortByDateDesc();
		List<Product> lPSaleDesc = softProductByCountSale();
		List<Product> lPFavorite = ListProductLike();
		getDiscountProducts();
		model.addAttribute("lPDiscount", productsDC);
		model.addAttribute("lPDateDesc", lPDateDesc);
		model.addAttribute("lPSaleDesc", lPSaleDesc);
		model.addAttribute("lPFavorite", lPFavorite);
		return "user-page/home";
	}
	
	@GetMapping("/promotion")
    @ResponseBody
    public List<Product> getPromotions() {
        // Lấy danh sách sản phẩm khuyến mãi từ cơ sở dữ liệu
		getDiscountProducts();
        List<Product> promotions = productsDC;
        return promotions;
    }

    // Định nghĩa một lớp Promotion đại diện cho một sản phẩm khuyến mãi
	
	// ---------------------------function
	
	//Lấy và sắp xếp danh sách sản phẩm theo số lượng đã bán 
	List<Product> softProductByCountSale(){
		List<OrderDetail> listOrderDef = orderDetailService.findAllOrderDetails();
		List<Product> listProductDef=	productService.findAllProduct();
		
		//tạo map có tên sản phẩm và số lần bán
		Map<String, Integer> countMap = new HashMap<>();
		for (OrderDetail orderDetail : listOrderDef) {
		    String name = orderDetail.getProduct().getProductId(); // Lấy productId từ sản phẩm liên quan
		    countMap.put(name, countMap.getOrDefault(name, 0) + 1);
		}

        // Tạo danh sách phụ không trùng lặp
        List<String> uniqueNames = new ArrayList<>(countMap.keySet());

        // Sắp xếp danh sách phụ theo số lần xuất hiện giảm dần
        Collections.sort(uniqueNames, new Comparator<String>() {
            @Override
            public int compare(String name1, String name2) {
                return countMap.get(name2) - countMap.get(name1);
            }
        });
        
     
		
        // Xắp xếp mảng sản phẩm
        for (int i = 0; i < uniqueNames.size(); i++) {
        	for (int id = 0; id < uniqueNames.size(); id++) {
    			if (listProductDef.get(id).getProductId() == uniqueNames.get(i)) {
					Collections.swap(listProductDef, i, id);
				}
    		}
		}
        
		return listProductDef;
	
	}
	// Lấy sp khuyến mãi
	void getDiscountProducts(){
		
		List<Product> listPrd = productService.findAllProduct();
		List<Discount> listDC = discountService.findAllDiscount();
		
		//xắp xếp khuyến mãi theo tỉ lệ giảm giá với thứ tự giảm dần
		for (int i = 0; i < listDC.size(); i++) {
			for (int j = 1; j < listDC.size(); j++) {
				if (listDC.get(i).getDiscountRate()<listDC.get(j).getDiscountRate()) {
					Collections.swap(listDC, i, j);
				}
			}
		}

		for (int i = 0; i < listDC.size(); i++) {
			for (int j = 0; j < listPrd.size(); j++) {
				if (listDC.get(i).getDiscountId().equalsIgnoreCase(listPrd.get(j).getDiscount().getDiscountId() )) {
					productsDC.add(listPrd.get(j));
				}
			}
		}
		
	}
	
	
	
	//Lấy sản phầm có nhiều lượt yêu thích nhất
	
	public List<Product> ListProductLike() {
		
		List<Favorite> fav= favoriteService.findAllFavorites();
		List<Product> pro= new ArrayList<Product>();
		
		// Đếm số lần xuất hiện của mỗi sản phẩm
        Map<String, Integer> counts = new HashMap<>();
        for (Favorite student : fav) {
            counts.put(student.getProduct().getProductId(), counts.getOrDefault(student, 0) + 1);
        }
        
     // Xây dựng danh sách mới chứa sản phẩm và số lần xuất hiện
        List<Map.Entry<Product, Integer>> sortedList = new ArrayList<Map.Entry<Product, Integer>>();

        // Sắp xếp danh sách mới theo số lần xuất hiện giảm dần
        Collections.sort(sortedList, new Comparator<Map.Entry<Product, Integer>>() {
            @Override
            public int compare(Map.Entry<Product, Integer> o1, Map.Entry<Product, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        // Lọc danh sách mới để loại bỏ các sản phẩm trùng lặp
        Set<Product> uniqueProducts = new HashSet<>();
        List<Product> result = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : sortedList) {
            Product product = entry.getKey();
            if (!uniqueProducts.contains(product)) {
                result.add(product);
                uniqueProducts.add(product);
            }
        }

        
        // Duyệt mảng và lấy sản phẩm nổi bật
        for (Product f : result) {
			Product p = productService.findProductById(f.getProductId()).get();
			pro.add(p);
		}
        return pro;
	}
	
	@Scheduled(fixedDelay = 5000) // Tải lại sau mỗi 1 phút (60 giây)
    public void updateProducts() {
        // Gọi phương thức loadProducts() định kỳ để tải sản phẩm khuyến mãi mới
		getDiscountProducts();
        
    }
}
