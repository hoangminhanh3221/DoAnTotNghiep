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
		List<Product> lPDateDesc=	productService.getProductsSortByDateDesc();
		softProductByCountSale();
		model.addAttribute("lPDateDesc", lPDateDesc);
		return "user-page/home";
	}
	
	
	// function
	
	List<Product> softProductByCountSale(){
		List<OrderDetail> listOrderDef = orderDetailService.findAllOrderDetails();
		List<Product> listProductDef=	productService.findAllProduct();
		
		// Đếm số lần xuất hiện của mỗi order trong mảng ban đầu
		Map<OrderDetail, Integer> counts = new HashMap<>();
        for (OrderDetail order : listOrderDef) {
            counts.put(order, counts.getOrDefault(order, 0) + 1);
        }
        // Xác định các số lần xuất hiện duy nhất
        List<Integer> uniqueCounts = new ArrayList<>(new HashSet<>(counts.values()));
        // Sắp xếp các số lần xuất hiện duy nhất theo thứ tự giảm dần
        Collections.sort(uniqueCounts, Collections.reverseOrder());
        // Tạo một mảng mới với các số lần xuất hiện duy nhất đã được sắp xếp
        List<OrderDetail> orderSoft = new ArrayList<>(); 
        for (Integer count : uniqueCounts) {
            for (Map.Entry<OrderDetail, Integer> entry : counts.entrySet()) {
                if (entry.getValue() == count) {
                	orderSoft.add(entry.getKey());
                    break;
                }
            }
        }
		
        // Xắp xếp mảng sản phẩm
        for (int i = 0; i < orderSoft.size(); i++) {
        	for (int id = 0; id < listProductDef.size(); id++) {
        		
    			/*if (listProductDef.get(id).productId == orderSoft.get(i). {
					
				}*/
        		System.out.println(orderSoft.get(i));
        		System.out.println(listProductDef.get(i));
    		}
		}
        
		List<Product> list=	null;
		return list;
	}
	
	
}
