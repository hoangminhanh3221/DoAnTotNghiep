package com.shop.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.entity.Order;
import com.shop.entity.OrderDetail;
import com.shop.entity.Product;
import com.shop.service.OrderDetailService;
import com.shop.service.OrderService;
import com.shop.service.ProductService;

import model.NumProduct;
import model.RevenueProduct;

@Controller
public class StatisticController {
	
	@Autowired
	OrderService orderService;	
	@Autowired
	OrderDetailService orderDetailService;
	@Autowired
	ProductService productService;
	
	// Request trang thống kê doanh thu 
	@GetMapping("/admin/revenue-statistic")
	public String revenue( Model model,
			@RequestParam(value="year" , required = false) Integer year, 
			@RequestParam(value="month" , required = false) Integer month, 
			@RequestParam(value="firstDay" , required = false) 	String firstDayus,
			@RequestParam(value="lastDay" , required = false) String lastDayus
			) throws ParseException {
		
		int monthIdx =0;
		double RevFromTo =0;
		List<RevenueProduct> topRevenue = getTopRevenue();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		if (firstDayus == null) {
			model.addAttribute("revFromTo", RevFromTo);
		}else {
			Date date1 = sdf.parse(firstDayus);
			Date date2 = sdf.parse(lastDayus);
			List<Order> listOrderFromTo = orderService.getRevenueFromTo(date1, date2);
			List<OrderDetail> listOrderDetail = new ArrayList<>();
			// Lọc những đơn hàng có trong tháng được tham chiếu( để lấy thông tin đơn hàng)
			for (Order od : listOrderFromTo) {
				for (OrderDetail odd : od.getOrderDetails()) {
					listOrderDetail.add(odd);
				}
			}
			
			// Duyệt mảng và tính giá
			for (OrderDetail odd : listOrderDetail) {
				double cost = odd.getProduct().getCostPrice() * odd.getProductQuantity();
				RevFromTo = RevFromTo + odd.getProductPrice() - cost;
			}
	        model.addAttribute("revFromTo", RevFromTo);
		}
		
		// Nếu không có giá trị "year" trong request parameter, sử dụng năm hiện tại
		if (year == null) {
            year = LocalDate.now().getYear();
            monthIdx = LocalDate.now().getMonthValue();
        }else {
        	if (year ==LocalDate.now().getYear()) {
        		monthIdx = LocalDate.now().getMonthValue();
			}else {
				monthIdx = 12;
			}
        }
        
        Double[] listMonthRev = getListMonthRevenue(monthIdx, year);
        
        if (month == null) {
            // Nếu không có giá trị "month" trong request parameter, sử dụng tháng hiện tại
        	month = LocalDate.now().getMonthValue();
        }
        
        
        List<Integer> years = orderService.getYear();
        double totalRev = getTotalRevenue();
        double monthRev = getMonthRevenue(month, year);
        double yearRev = getYearRevenue(year);
        
        //Data biểu đồ top doanh thu
        List<String> dataNameChart2l=new ArrayList<>();
        List<Double> dataValueChart2l=new ArrayList<>();
        for (int i = 0; i < topRevenue.size(); i++) {
        	dataNameChart2l.add(topRevenue.get(i).getName());
        	dataValueChart2l.add(topRevenue.get(i).getRevenue());
		}
        // Chuyển ArrayList thành mảng kiểu int
        String[] dataNameChart2 = dataNameChart2l.toArray(new String[dataNameChart2l.size()]);
        Double[] dataValueChart2 = dataValueChart2l.toArray(new Double[dataValueChart2l.size()]);
        
        
        model.addAttribute("dataNameChart2", dataNameChart2);
        model.addAttribute("dataValueChart2", dataValueChart2);
        model.addAttribute("years", years);
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("totalRev", totalRev);
        model.addAttribute("monthRev", monthRev);
        model.addAttribute("listMonthRev", listMonthRev);
        model.addAttribute("topRevenue", topRevenue);
        model.addAttribute("yearRev", yearRev);
        
		return "admin-page/revenue-statistics";
	}
	
	// Request trang thống kê sản phẩm
	@GetMapping("/admin/product-statistic")
	public String product(Model model,
			@RequestParam(value="year" , required = false) Integer year, 
			@RequestParam(value="month" , required = false) Integer month) {
		
		
		int monthIdx =0;
		int allNumPrdSold=0;
		List<Integer> years = orderService.getYear();
		List<NumProduct> listNumProductSold = getDataChartDonut3();
		List<NumProduct> topNumProductSold = new ArrayList<>();
		
        List<Product> listAllPrd = productService.findAllProduct();

        // Nếu không có giá trị "year" trong request parameter, sử dụng năm hiện tại
		if (year == null) {
            year = LocalDate.now().getYear();
            monthIdx = LocalDate.now().getMonthValue();
        }else {
        	if (year ==LocalDate.now().getYear()) {
        		monthIdx = LocalDate.now().getMonthValue();
			}else {
				monthIdx = 12;
			}
        	
        }
        // Nếu không có giá trị "month" trong request parameter, sử dụng tháng hiện tại
        if (month == null) {
        	month = LocalDate.now().getMonthValue();
        }

		int numPrdSoldOfYear=getNumprdSoldOfYear(year);
		int numPrdSoldOfMonth=getNumprdSoldOfMonth(month, year);
		Integer[] dataChartLine = getDataChartLine(monthIdx, year);
        
		//Duyệt mảng và tính số sản phẩm đã bán
        for (Product p : listAllPrd) {
        	//tổng sl trừ sl còn lại
        	int numx1 = p.getTotalQuantity() -p.getQuantityLeft();
        	allNumPrdSold = allNumPrdSold + numx1;
		}
        
        //Lấy datachartdonut3
        List<String> listName = new ArrayList<>();
        List<Integer> listNum = new ArrayList<>();
     // Sắp xếp theo số lượng giảm dần
        Collections.sort(listNumProductSold, new Comparator<NumProduct>() {
            @Override
            public int compare(NumProduct p1, NumProduct p2) {
                return Integer.compare(p2.getNum(), p1.getNum());
            }
        });

        NumProduct numPrd = new NumProduct("", 0);

        for (int i = 0; i < listNumProductSold.size(); i++) {
        	NumProduct numPrd2 = new NumProduct();
            if (i == 4) {
                numPrd.setName("Khác");
                numPrd.setNum(listNumProductSold.get(i).getNum());
            } else if (i > 4) {
                numPrd.setNum(numPrd.getNum() + listNumProductSold.get(i).getNum());
            } else {
                listName.add(listNumProductSold.get(i).getName());
                listNum.add(listNumProductSold.get(i).getNum());
                numPrd2.setName(listNumProductSold.get(i).getName());
                numPrd2.setNum(listNumProductSold.get(i).getNum());
                topNumProductSold.add(numPrd2);
            }
            if (i == listNumProductSold.size() - 1) {
                listName.add(numPrd.getName());
                listNum.add(numPrd.getNum());
                topNumProductSold.add(numPrd);
            }
        }

        // Chuyển ArrayList thành mảng String
        String[] dataNameChartDonut3 = listName.toArray(new String[listName.size()]);
        // Chuyển ArrayList thành mảng Integer
        Integer[] dataNumChartDonut3 = listNum.toArray(new Integer[listNum.size()]);

        // In mảng đã chuyển
        for (String name : dataNameChartDonut3) {
            System.out.println(name);
        }

        for (Integer num : dataNumChartDonut3) {
            System.out.println(num);
        }
        
        model.addAttribute("years", years);
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("dataNameChartDonut3", dataNameChartDonut3);
        model.addAttribute("dataNumChartDonut3", dataNumChartDonut3);
        model.addAttribute("allNumPrdSold", allNumPrdSold);
        model.addAttribute("dataChartLine", dataChartLine);
        model.addAttribute("numPrdSoldOfYear", numPrdSoldOfYear);
        model.addAttribute("numPrdSoldOfMonth", numPrdSoldOfMonth);
        model.addAttribute("topNumProductSold", topNumProductSold);
        
		return "admin-page/product-statistics";
	}
	
	
	//------------------- function
	
	// Lấy tổng doanh thu
	public double getTotalRevenue() {
		
		double totalRev = 0;
		List<OrderDetail> listOrderDetail = orderDetailService.findAllOrderDetails();
		
		// Duyệt mảng và tính giá
		for (OrderDetail odd : listOrderDetail) {
			double cost = odd.getProduct().getCostPrice() * odd.getProductQuantity();
			totalRev = totalRev + odd.getProductPrice() - cost;
		}
		
		return totalRev;
	}
	
	// Lấy doanh thu của năm được tham chiếu
	public double getYearRevenue(int year) {
		double yearRev =0;
		List<Order> listOrder = orderService.getOrdersByYear(year);
		List<OrderDetail> listOrderDetail = new ArrayList<>();
		
		// Lọc những đơn hàng có trong năm được tham chiếu( để lấy thông tin đơn hàng)
		for (Order od : listOrder) {
			for (OrderDetail odd : od.getOrderDetails()) {
				listOrderDetail.add(odd);
			}
		}
		
		// Duyệt mảng và tính giá
		for (OrderDetail odd : listOrderDetail) {
			double cost = odd.getProduct().getCostPrice() * odd.getProductQuantity();
			yearRev = yearRev + odd.getProductPrice() - cost;
		}
		return yearRev;
	}
	
	// Lấy doanh thu của Tháng được tham chiếu
		public double getMonthRevenue(int monthInput,int year) {
			double monthRev =0;
			List<Order> listOrder = orderService.getOrdersByMonth(monthInput, year);
			System.out.println(listOrder.size());
			List<OrderDetail> listOrderDetail = new ArrayList<>();
			// Lọc những đơn hàng có trong tháng được tham chiếu( để lấy thông tin đơn hàng)
			for (Order od : listOrder) {
				for (OrderDetail odd : od.getOrderDetails()) {
					listOrderDetail.add(odd);
				}
			}
			
			// Duyệt mảng và tính giá
			for (OrderDetail odd : listOrderDetail) {
				double cost = odd.getProduct().getCostPrice() * odd.getProductQuantity();
				monthRev = monthRev + odd.getProductPrice() - cost;
			}
			return monthRev;
		}

		// Lấy doanh thu của Tháng được tham chiếu
		public Double[] getListMonthRevenue(int monthIdx,int year) {
			
			Double[] list = new Double[monthIdx];
			double monthRev =0;
			
			for (int i = 0; i < monthIdx; i++) {
				monthRev = getMonthRevenue(i+1, year);
				list[i] = monthRev;
			}
			
			return list;
		}
		
		// Lấy top doanh thu của cửa hàng
		public List<RevenueProduct> getTopRevenue() {
		    List<RevenueProduct> listTop5 = new ArrayList<>();
		    List<String> listPrd = orderDetailService.getListproductsold();            
		    List<OrderDetail> listOrder = orderDetailService.findAllOrderDetails();
		    
		    for (String productName : listPrd) {
		        RevenueProduct rp = new RevenueProduct();
		        rp.setName(productName);
		        double revenue = 0.0;
		        
		        for (OrderDetail orderDetail : listOrder) {
		            if (productName.equalsIgnoreCase(orderDetail.getProduct().getProductName())) {
		                revenue += orderDetail.getProductPrice();
		            }
		        }
		        
		        rp.setRevenue(revenue);
		        listTop5.add(rp);
		    }

		    listTop5.sort((rp1, rp2) -> Double.compare(rp2.getRevenue(), rp1.getRevenue()));

		    List<RevenueProduct> finalListTop5 = new ArrayList<>();
		    double otherRevenue = 0.0;
		    
		    for (int i = 0; i < listTop5.size(); i++) {
		        if (i < 4) {
		            finalListTop5.add(listTop5.get(i));
		        } else {
		            otherRevenue += listTop5.get(i).getRevenue();
		        }
		    }
		    
		    RevenueProduct otherProduct = new RevenueProduct();
		    otherProduct.setName("Khác");
		    otherProduct.setRevenue(otherRevenue);
		    finalListTop5.add(otherProduct);
		    
		    return finalListTop5;
		}
		
		//Lấy số sản phẩm đã bán theo năm
		public int getNumprdSoldOfYear(Integer year) {
			int numPrd=0;
			List<Order> listOrder = orderService.getOrdersByYear(year);
			
			for (Order o : listOrder) {
				for (OrderDetail od : o.getOrderDetails()) {
					numPrd = numPrd + od.getProductQuantity();
				}
			}
			
			return numPrd;
		}
		//Lấy số sản phẩm đã bán theo 1 tháng trong năm đã chọn
		public int getNumprdSoldOfMonth(Integer month, Integer year) {
			int numPrd=0;
			List<Order> listOrder = orderService.getOrdersByMonth(month, year);
			
			for (Order o : listOrder) {
				for (OrderDetail od : o.getOrderDetails()) {
					numPrd = numPrd + od.getProductQuantity();
				}
			}
			
			return numPrd;
		}
		//Lấy datachart doanh số của năm
		public Integer[] getDataChartLine(Integer monthidx, Integer year) {
			Integer[] data = new Integer[monthidx];
			int numPrd=0;
			for (int i = 1; i < monthidx+1; i++) {
				List<Order> listOrder = orderService.getOrdersByMonth(i, year);
				
				for (Order o : listOrder) {
					for (OrderDetail od : o.getOrderDetails()) {
						numPrd = numPrd + od.getProductQuantity();
					}
				}
				data[i-1]=numPrd;
				numPrd=0;
			}
			
			return data;
		}
		//Lấy datachart2 Danh sach
		public List<NumProduct> getDataChartDonut3() {
		    List<NumProduct> list = new ArrayList<>();
		    List<Product> listPrd = productService.findAllProduct();
		    for (Product p : listPrd) {
		    	System.out.println(p.getTotalQuantity());
		        NumProduct obj = new NumProduct(); // Tạo một đối tượng mới trong mỗi lần lặp
		        obj.setName(p.getProductName());
		        obj.setNum(Integer.valueOf(p.getTotalQuantity() - p.getQuantityLeft()));
		        list.add(obj);
		    }
		    
		    return list;
		}
}
