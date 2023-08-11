package com.shop.controller.adminController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shop.entity.Account;
import com.shop.entity.Address;
import com.shop.entity.Customer;
import com.shop.entity.Employee;
import com.shop.entity.Order;
import com.shop.entity.OrderDetail;
import com.shop.repository.EmployeeRepository;
import com.shop.service.AccountService;
import com.shop.service.AddressService;
import com.shop.service.EmployeeService;
import com.shop.service.OrderService;
import com.shop.util.AddressAPI;
import com.shop.util.ImageService;
import com.shop.util.SessionService;

@Controller
public class MyProfileController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private SessionService sessionService;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@RequestMapping("/admin/info")
	public String getInfo(Model model, @ModelAttribute Employee employee) {
		employee = employeeRepository.findEmployeeByUsername("anh").get(0);

		model.addAttribute("image", "/user-page/images/Avatar/" + employee.getEmployeeImage());
		// Chuyển đổi birthday sang chuỗi định dạng yyyy-MM-dd
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String birthdayString = dateFormat.format(employee.getBirthday());
		employee.setBirthdayString(birthdayString);
		
		if (employee.getAddress().getNumberHome() == null) {
			model.addAttribute("isAddress", true);
		} else {
			model.addAttribute("isAddress", false);
		}
		model.addAttribute("employee", employee);
		return "admin-page/myProfile";
	}
	@RequestMapping("/admin/info/edit")
	public String postInfo(
			@ModelAttribute Employee employee,
			@RequestParam("avatar") MultipartFile avatarFile
			) {
		Employee currentEmployee = employeeRepository.findEmployeeByUsername("anh").get(0);
		System.out.println(employee.getEmployeeImage());
		Address currentAddress = currentEmployee.getAddress();
		if(currentAddress.getNumberHome() == null) {
			AddressAPI addressAPI = sessionService.get("addressAPI");
			currentAddress.setNumberHome(employee.getAddress().getNumberHome());
			currentAddress.setCity(addressAPI.getCity());
			currentAddress.setDistrict(addressAPI.getDistrict());
			currentAddress.setWard(addressAPI.getWard());
			sessionService.remove("addressAPI");
		}
		
		Account currentAccount = currentEmployee.getAccount();
		currentAccount.setEmail(employee.getAccount().getEmail());
		
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date birthday = dateFormat.parse(employee.getBirthdayString());
			currentEmployee.setBirthday(birthday);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		currentEmployee.setEmployeeName(employee.getEmployeeImage());
		currentEmployee.setGender(employee.getGender());
		currentEmployee.setPhoneNumber(employee.getPhoneNumber());
		try {
			if(avatarFile.getOriginalFilename().equals("")) {
				currentEmployee.setEmployeeImage(employee.getEmployeeImage());
			} else {
//				currentEmployee.setCustomerImage(imageService.saveImage(avatarFile, "customer"+currentCustomer.getCustomerId(), "avatars"));
				currentEmployee.setEmployeeImage(imageService.saveImage(avatarFile, "employee" +currentEmployee.getEmployeeId(), "Avatar"));
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		currentEmployee.setAddress(currentAddress);
		currentEmployee.setAccount(currentAccount);
		
		try {
			accountService.updateAccount(currentAccount);
			addressService.updateAddress(currentAddress);
			employeeService.updateEmployee(currentEmployee);
		} catch (Exception e) {
			System.out.println(e);
		}

		return "redirect:/admin/info";
	}

}
