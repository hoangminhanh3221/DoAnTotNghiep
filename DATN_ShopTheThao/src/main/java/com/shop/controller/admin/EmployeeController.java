package com.shop.controller.admin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shop.entity.Account;
import com.shop.entity.Address;
import com.shop.entity.Brand;
import com.shop.entity.Customer;
import com.shop.entity.Employee;
import com.shop.repository.EmployeeRepository;
import com.shop.service.AccountService;
import com.shop.service.AddressService;
import com.shop.service.EmployeeService;
import com.shop.util.AddressAPI;
import com.shop.util.AuthenticationFacade;
import com.shop.util.ImageUtil;
import com.shop.util.SessionService;

@RequestMapping("/admin")
@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private AccountService accountService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private SessionService sessionService;
	@Autowired
	private AuthenticationFacade af;
	@Autowired
	private ImageUtil imageUtil;

	@RequestMapping("/employeeList")
	public String getEmployeeList(Model model, @RequestParam("page") Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.orElse(0), 10);
		Page<Employee> listEmployee = employeeService.findAllEmployee(pageable);
		model.addAttribute("employees", listEmployee);
		int totalPages = listEmployee.getTotalPages();
		List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
		model.addAttribute("pageNumbers", pageNumbers);
		return "admin-page/employee-list";
	}

	@RequestMapping("/employeeDetail/{id}")
	public String getemployeeDetail(Model model, @PathVariable("id") Integer id) {
		Employee employee = employeeService.findEmployeeById(id).get();
		model.addAttribute("employee", employee);
		return "admin-page/employee-detail";
	}

	@RequestMapping("/employeeAdd")
	public String getEmployeeAdd(Model model, @ModelAttribute Employee employee) {
		model.addAttribute("title", "Thêm Nhân Viên");
		return "admin-page/employee-add";
	}

	@RequestMapping(value = "/employeeAdd/create")
	public String createEmployeeAdd(@ModelAttribute Employee employee, 
			@RequestParam("homeNumber") String homeNumber,
			@RequestParam("avatar") MultipartFile avatarFile) {

		AddressAPI addressAPI = sessionService.get("addressAPI");
		Address address = new Address();
		address.setNumberHome(homeNumber);
		address.setCity(addressAPI.getCity());
		address.setDistrict(addressAPI.getDistrict());
		address.setWard(addressAPI.getWard());
		addressService.createAddress(address);
		
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date birthday = dateFormat.parse(employee.getBirthdayString());
			employee.setBirthday(birthday);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Account account = employee.getAccount();
		account.setCreateDate(new Date());
		accountService.createAccount(account);
		
		employee.setAddress(address);
		employee.setAddress(address);
		try {
			if(!avatarFile.getOriginalFilename().equals("")) {
				Random random = new Random();
			    int randomSuffix = random.nextInt(1000);
				employee.setEmployeeImage(imageUtil.saveImage(avatarFile, "employee"+randomSuffix, "avatars"));
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		employeeService.createEmployee(employee);
		sessionService.remove("addressAPI");
		return "redirect:/admin/employeeList";
	}
	
	@RequestMapping(value = "/employeeUpdate/update/{id}")
	public String updateEmployee(@ModelAttribute Employee employee,
			@RequestParam("avatar") MultipartFile avatarFile,
			@PathVariable("id") Integer id) {
		System.out.println(employee.getEmployeeId());
		Employee currentEmployee = employeeService.findEmployeeById(id).orElse(null);
		currentEmployee.setEmployeeName(employee.getEmployeeName());
		currentEmployee.setGender(employee.getGender());
		currentEmployee.setPhoneNumber(employee.getPhoneNumber());
		
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date birthday = dateFormat.parse(employee.getBirthdayString());
			currentEmployee.setBirthday(birthday);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		try {
			if(!avatarFile.getOriginalFilename().equals("")) {
				String new1 = UUID.randomUUID().toString();
				currentEmployee.setEmployeeImage(imageUtil.saveImage(avatarFile,new1 , "avatars"));
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Account currentAccount = currentEmployee.getAccount();
		currentAccount.setPassword(employee.getAccount().getPassword());
		currentAccount.setEmail(employee.getAccount().getEmail());
		currentAccount.setRole(employee.getAccount().getRole());
		currentEmployee.setAccount(currentAccount);
		
		addressService.updateAddress(currentEmployee.getAddress());
		accountService.updateAccount(currentAccount);
		employeeService.updateEmployee(currentEmployee);
		
		sessionService.remove("addressAPI");
		return "redirect:/admin/employeeList";
	}

	@RequestMapping("/employeeEdit/{id}")
	public String getEmployeeEdit(Model model, @PathVariable("id") Integer id) {
		Employee employee = employeeService.findEmployeeById(id).get();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String birthdayString = dateFormat.format(employee.getBirthday());
		employee.setBirthdayString(birthdayString);
		model.addAttribute("image", "/user-page/images/avatars/" + employee.getEmployeeImage());
		model.addAttribute("title", "Sửa Nhân Viên");
		model.addAttribute("address", employee.getAddress());
		model.addAttribute("role", employee.getAccount().getRole());
		model.addAttribute("employee", employee);
		model.addAttribute("employeeId", id);
		return "admin-page/employee-update";
	}

	@RequestMapping("/employeeDelete/{id}")
	public String getDeleteEmployee(@PathVariable("id") Integer id) {
		employeeService.deleteEmployee(id);
		return "redirect:/admin/employeeList";
	}
	
	@RequestMapping("/employee/changeAddress/{id}")
	public String changeAddress(@RequestParam("homeNumber")String homeNumber, @PathVariable("id") Integer id) {
		Employee employee = employeeService.findEmployeeById(id).orElse(null);
		
		AddressAPI addressAPI = sessionService.get("addressAPI");
		
		Address address = employee.getAddress();
		address.setCity(addressAPI.getCity());
		address.setNumberHome(homeNumber);
		address.setDistrict(addressAPI.getDistrict());
		address.setWard(addressAPI.getWard());
		addressService.updateAddress(address);
		
		sessionService.remove("addressAPI");
		return "redirect:/admin/employeeEdit/"+id;
	}
}
