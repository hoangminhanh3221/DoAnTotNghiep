package com.shop.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.entity.Color;
import com.shop.entity.Customer;
import com.shop.service.CustomerService;

@RequestMapping("/admin")
@Controller
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/customerList")
	public String getCustomerList(Model model,@RequestParam("page") Optional<Integer> page) {
		
		Pageable pageable = PageRequest.of(page.orElse(0), 10);
		Page<Customer> listCustomer = customerService.findAllCustomer(pageable);
		model.addAttribute("customers", listCustomer);
		int totalPages = listCustomer.getTotalPages();
		List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
		model.addAttribute("pageNumbers", pageNumbers);
			return "/admin-page/customer-list";
	}
	@RequestMapping("/customerDetail/{id}")
	public String getcustomerDetail(Model model, @PathVariable("id") Integer id) {
		Customer customer = customerService.findCustomerById(id).get();
		System.out.println(customer.getCustomerName());
		model.addAttribute("customer", customer);
		return "/admin-page/customer-detail";
	}
}
