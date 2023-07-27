package com.shop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.entity.Category;
import com.shop.entity.Discount;
import com.shop.entity.Product;
import com.shop.entity.Subcategory;
import com.shop.service.CategoryService;
import com.shop.service.DiscountService;
import com.shop.service.ProductService;
import com.shop.service.SubcategoryService;


@Controller
public class DiscountController {
	
	@Autowired
	DiscountService discountService;
	
	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	SubcategoryService subCategoryService;
	
	@GetMapping("/admin/discount")
	public String index(@RequestParam(defaultValue = "0") int page,Model model) {
		
		int pageSize = 10; // Số phần tử trên mỗi trang
		Pageable pageable = PageRequest.of(page, pageSize);
		
		Page<Discount> listDiscount = discountService.findAllDiscount(pageable);
		
		model.addAttribute("page", listDiscount);
		
		return "admin-page/discount-list";
	}
	
	//----------------------------------------------------------------------------add
	@GetMapping("/admin/discount/add")
	public String add(Model model, HttpSession session) {
		
		Discount discount = new Discount();
		List<Product> productsOfDiscount = new ArrayList<>();
		List<Product> list = productService.findAllProduct();
		List<Category> listCategory = categoryService.findAllCategory();
		session.removeAttribute("productList");
		model.addAttribute("discount", discount);
		model.addAttribute("productsOfDiscount", productsOfDiscount);
		model.addAttribute("listCategory", listCategory);
		model.addAttribute("list", list);
		model.addAttribute("status", 0);
		
		return "admin-page/discount-add";
	}
	
	@GetMapping("/admin/discount/add/soft")
	@ResponseBody
	public List<Product> softadd(Model model, @RequestParam("id") String id) {
		if (id.equalsIgnoreCase("0")) {
			List<Product> list =productService.findAllProduct();
			return list;
		} else {
			List<Product> list = getProductByCategory(id);
			return list;
		}
	}
	
	@GetMapping("/admin/discount/add/addprd")
	@ResponseBody
	public List<Product> addProduct(Model model, @RequestParam("id") String id, HttpSession session) {
		
		if (session != null && session.getAttribute("productList") != null) {
			List<Product> list = (List<Product>) session.getAttribute("productList");
			for (Product p : list) {
				if (p.getProductId().equalsIgnoreCase(id)) {
					return list;
				}
			}
			Product prd = productService.findProductById(id).get();
			list.add(prd);
			session.removeAttribute("productList");
			session.setAttribute("productList", list);
			return list;
		}else {
			List<Product> list = new ArrayList<>();
			Product prd = productService.findProductById(id).get();
			list.add(prd);
			session.removeAttribute("productList");
			session.setAttribute("productList", list);
			return list;
        }
	}
	
	@GetMapping("/admin/discount/add/deleteprd")
	@ResponseBody
	public List<Product> deleteProduct( @RequestParam("id") String id, HttpSession session) {
		List<Product> list =(List<Product>) session.getAttribute("productList");
		int index=-1;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getProductId().equalsIgnoreCase(id)) {
				index=i;
				break;
			}
			
		}
		list.remove(index);
		session.removeAttribute("productList");
		session.setAttribute("productList",list);
		return list;
		
	}
	@PostMapping("/admin/discount/add/save")
	public String addSave(Model model, @RequestParam("discountId") String discountId,
			@RequestParam("discountRate") String discountRate,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam("description") String description,
			HttpSession session) {

		Optional<Discount> dck = discountService.findDiscountById(discountId);
		if (!dck.isEmpty()) {
			model.addAttribute("add", 1);
			return "redirect:/admin/discount";
		}
		List<Product> list =(List<Product>) session.getAttribute("productList");
		Discount discount = new Discount();
		discount.setDiscountId(discountId);
		discount.setDescription(description);
		discount.setDiscountRate(Integer.parseInt(discountRate));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d1 = dateFormat.parse(startDate);
			Date d2 = dateFormat.parse(endDate);
			discount.setStartDate(d1);
			discount.setEndDate(d2);
			System.out.println(12);
			discountService.createDiscount(discount);
			System.out.println(142);
			model.addAttribute("add", 2);
			if (list!=null) {
				for (Product l : list) {
					l.setDiscount(discount);
					productService.updateProduct(l);
				}
			}

			session.removeAttribute("productList");
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/admin/discount";
		
	}
	//-----------------------------------------------------------------------------update
	@GetMapping("/admin/discount/update")
	public String update(HttpSession session,Model model, @RequestParam("discountid") String id) {
		Discount discount = discountService.findDiscountById(id).get();
		List<Product> productsOfDiscount = discount.getProducts();
		List<Product> list = productService.findAllProduct();
		List<Category> listCategory = categoryService.findAllCategory();
		session.removeAttribute("productList");
		session.setAttribute("productList",productsOfDiscount);
		model.addAttribute("productsOfDiscount", productsOfDiscount);
		model.addAttribute("listCategory", listCategory);
		model.addAttribute("list", list);
		model.addAttribute("discount", discount);
		model.addAttribute("status", 1);
		return "admin-page/discount-add";
	}
	
	@PostMapping("/admin/discount/update/save")
	public String updateSave(Model model, @RequestParam("discountId") String discountId,
			@RequestParam("discountRate") String discountRate,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam("description") String description,
			HttpSession session) {

		Optional<Discount> dck = discountService.findDiscountById(discountId);
		if (dck.isEmpty()) {
			model.addAttribute("add", 1);
			return "redirect:/admin/discount";
		}
		List<Product> list =(List<Product>) session.getAttribute("productList");
		Discount discount = new Discount();
		discount.setDiscountId(discountId);
		discount.setDescription(description);
		discount.setDiscountRate(Integer.parseInt(discountRate));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d1 = dateFormat.parse(startDate);
			Date d2 = dateFormat.parse(endDate);
			discount.setStartDate(d1);
			discount.setEndDate(d2);
			System.out.println(12);
			discountService.createDiscount(discount);
			System.out.println(142);
			model.addAttribute("add", 2);
			if (list!=null) {
				for (Product l : list) {
					l.setDiscount(discount);
					productService.updateProduct(l);
				}
			}
			session.removeAttribute("productList");
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/admin/discount";
	}
	
	@GetMapping("/admin/discount/delete")
	public String DiscountDelete(Model model, @RequestParam("discountId") String discountId) {

		discountService.deleteDiscount(discountId);
		
		return "redirect:/admin/discount";
	}
	
	// function
	
	private List<Product> getProductByCategory(String id) {
		
		List<Product> list = new ArrayList<Product>();
		
		Category Cate= categoryService.findCategoryById(id).get();
		List<Subcategory> listSub = Cate.getSubcategories();
		for (Subcategory sub : listSub) {
			for (Product p : sub.getProducts()) {
				list.add(p);
			}
		}	
		return list;
	}
	
	
	
	
}
