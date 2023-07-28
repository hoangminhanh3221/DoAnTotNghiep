package com.shop.controller.adminController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.entity.Account;
import com.shop.entity.Address;
import com.shop.entity.Brand;
import com.shop.entity.Customer;
import com.shop.entity.Employee;
import com.shop.service.AccountService;
import com.shop.service.AddressService;
import com.shop.service.EmployeeService;

@RequestMapping("/admin")
@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	private AccountService accountService;
	private AddressService addressService;
	
	@RequestMapping("/employeeList")
	public String getEmployeeList(Model model,@RequestParam("page") Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.orElse(0), 10);
		Page<Employee> listEmployee = employeeService.findAllEmployee(pageable);
		model.addAttribute("employees", listEmployee);
		int totalPages = listEmployee.getTotalPages();
		List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
		model.addAttribute("pageNumbers", pageNumbers);
			return "/admin-page/employee-list";
	}
	@RequestMapping("/employeeDetail/{id}")
	public String getemployeeDetail(Model model, @PathVariable("id") Integer id) {
		Employee employee = employeeService.findEmployeeById(id).get();
		model.addAttribute("employee", employee);
		return "/admin-page/employee-detail";
	}
	
	@RequestMapping("/employeeAdd")
	public String getEmployeeAdd() {
		return "/admin-page/employee-add";
	}
	
	@RequestMapping(value = "/employeeAdd/create", method = RequestMethod.POST)
    public String createEmployeeAdd(
            @RequestParam("employeeId") Integer Id,
            @RequestParam("name") String name,
            @RequestParam("gender") Boolean gender,
            @RequestParam("avatar") String avatar,
            @RequestParam("dob") String birthday,
            @RequestParam("phone") String phoneNumber,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("IDAr") Integer addressId,
            @RequestParam("numberHome") String numberHome,
            @RequestParam("ward") String ward,
            @RequestParam("district") String district,
            @RequestParam("province") String province) {
        System.out.println(Id+","+name+","+gender+","+avatar+","+birthday);
        // Tạo đối tượng Employee từ dữ liệu nhận được từ form HTML
        Employee employee = new Employee();
        employee.setEmployeeId(Id);
        employee.setEmployeeName(name);
        employee.setGender(gender);
        employee.setEmployeeImage(avatar);
        employee.setPhoneNumber(phoneNumber);
        Date dob = null;
	    try {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        dob = dateFormat.parse(birthday);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
        // Tạo đối tượng Account từ dữ liệu nhận được từ form HTML
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        // Thiết lập các giá trị khác cho đối tượng Account (createDate, email, role) nếu cần thiết.

        // Tạo đối tượng Address từ dữ liệu nhận được từ form HTML
        Address address = new Address();
        address.setAddressId(addressId);
        address.setNumberHome(numberHome);
        address.setWard(ward);
        address.setDistrict(district);
        address.setCity(province);

        // Thiết lập quan hệ giữa các đối tượng
        employee.setAccount(account);
        employee.setAddress(address);

        employeeService.createEmployee(employee);
        // Chuyển hướng người dùng về trang danh sách nhân viên (employeeList)
        return "redirect:/employeeList";
    }
}
