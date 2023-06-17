package com.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.entity.OrderDetail;
import com.shop.entity.Product;
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
	
	
	// function
	
	List<Product> softProductByCountSale(){
		List<OrderDetail> listOrderDef = orderDetailService.findAllOrderDetails();
		List<Product> listProductDef=	productService.findAllProduct();
		
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
	
	
}
