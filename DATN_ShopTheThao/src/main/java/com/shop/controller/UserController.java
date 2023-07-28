package com.shop.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shop.entity.Account;
import com.shop.entity.Address;
import com.shop.entity.Customer;
import com.shop.entity.Order;
import com.shop.entity.OrderDetail;
import com.shop.service.AccountService;
import com.shop.service.AddressService;
import com.shop.service.CustomerService;
import com.shop.service.OrderService;
import com.shop.util.XDate;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private XDate xDate;
	String uploadDir = "D:/DoAnTotNghiep/DATN_ShopTheThao/src/main/resources/static/user-page/images/product";
	
	@RequestMapping("/info")
	public String getInfo(Model model) {
	    Account account = accountService.findAccountById("kiet").orElse(null);  
	    Customer customers = account.getCustomers().get(0);
	    model.addAttribute("account", account);
	    model.addAttribute("customer", customers);
	    return "user-page/user-info";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editInfo(
			@RequestParam("email") String email
			,@RequestParam("customerName") String name
			,@RequestParam("numberHome") String numberHome
			,@RequestParam("ward") String ward
			,@RequestParam("district") String district
			,@RequestParam("city") String city
			,@RequestParam("gender") boolean gender
			,@RequestParam("birthday") String birthdayStr
		) {
		Account account = accountService.findAccountById("kiet").orElse(null);
		Customer oldCustomer = customerService.findCustomerByAccount(account).get(0);
		System.out.println(oldCustomer.getCustomerImage());
		Address oldAddress = oldCustomer.getAddress();
		account.setEmail(email);
		oldCustomer.setAccount(account);
		oldAddress.setNumberHome(numberHome);
		oldAddress.setWard(ward);
		oldAddress.setDistrict(district);
		oldAddress.setCity(city);
		oldCustomer.setCustomerName(name);
		oldCustomer.setGender(gender);
		Date birthday = null;
	    try {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        birthday = dateFormat.parse(birthdayStr);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    oldCustomer.setBirthday(birthday);
		System.out.println("1");
		oldCustomer.setAddress(oldAddress);
	    accountService.updateAccount(account);
	    customerService.updateCustomer(oldCustomer);
	    return "redirect:/user/info";
	}
	
	@RequestMapping("/edit/address/{id}")
	public String getEditAddress( @PathVariable("id") Integer id,
			@RequestParam("numberHome") String numberHome
			,@RequestParam("ward") String ward
			,@RequestParam("district") String district
			,@RequestParam("city") String city){
		Address address = addressService.findAddressById(id).get();
		System.out.println("2");
		address.setNumberHome(numberHome);
		address.setWard(ward);
		address.setDistrict(district);
		address.setCity(city);
		
		return "redirect:/user/info";
	}
	
	@RequestMapping("/list-order")
	public String getListOrder(Model model) {
		Account account = accountService.findAccountById("kiet").orElse(null);
		List<Order> orders = account.getOrders();
	    model.addAttribute("account", account);
	    model.addAttribute("orders", orders);
		return "user-page/list-order";
	}
	@RequestMapping("/order-detail/{orderId}")
	public String getOrderDetail(@PathVariable Integer orderId, Model model) {
	    Order order = orderService.findOrderById(orderId).get();
	    List<OrderDetail> orderDetails = order.getOrderDetails();
	    model.addAttribute("order", order);	
	    model.addAttribute("orderDetails", orderDetails);
	    return "user-page/order-detail";
	}
	
	@PostMapping("/upload-avatar")
	public ResponseEntity<String> uploadAvatar(@RequestParam("file") MultipartFile file) {
	    try {
	        // Lưu hình đại diện vào cơ sở dữ liệu hoặc máy chủ
	        // Ví dụ: lưu hình vào thư mục trên máy chủ
	        String fileName = file.getOriginalFilename();
	        Path path = Paths.get(uploadDir + "/" + fileName);
	        Files.write(path, file.getBytes());

	        // Lưu tên hình đại diện vào cơ sở dữ liệu (ví dụ: lưu vào đối tượng customer)
	     
	        Customer customer = customerService.findCustomerById(5).orElse(null);
	        customer.setCustomerImage(fileName);
	        customerService.updateCustomer(customer);

	        return ResponseEntity.ok("Hình đại diện đã được tải lên thành công!");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi tải lên hình đại diện!");
	    }
	}

}
