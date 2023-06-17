package com.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.entity.Discount;
import com.shop.entity.OrderDetail;
import com.shop.entity.Product;
import com.shop.service.DiscountService;
import com.shop.service.OrderDetailService;
import com.shop.service.ProductService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Controller
public class HomeController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	DiscountService discountService;
	
	@Autowired
	OrderDetailService orderDetailService;

	@RequestMapping("/home")
	public String index(Model model) {
		
		//Lấy danh sách sản phẩm và xắp xếp theo trường ngày nhận với thứ thự giảm dần và thêm vào model
		List<Product> lPDateDesc =	productService.getProductsSortByDateDesc();
		List<Product> lPSaleDesc = softProductByCountSale();
		model.addAttribute("lPDateDesc", lPDateDesc);
		model.addAttribute("lPSaleDesc", lPSaleDesc);
		return "user-page/home";
	}
	
	@GetMapping("/promotion")
    @ResponseBody
    public List<Product> getPromotions() {
        // Lấy danh sách sản phẩm khuyến mãi từ cơ sở dữ liệu
        List<Product> promotions = getDiscountProducts();
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
            String name = orderDetail.toString();
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
    			if (listProductDef.get(id).productId == uniqueNames.get(i)) {
					Collections.swap(listProductDef, i, id);
				}
    		}
		}
        
		return listProductDef;
	
	}
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
		
		return list;
	}
}
