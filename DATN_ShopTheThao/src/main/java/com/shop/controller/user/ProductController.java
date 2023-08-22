package com.shop.controller.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.entity.Account;
import com.shop.entity.Favorite;
import com.shop.entity.Feedback;
import com.shop.entity.Order;
import com.shop.entity.OrderDetail;
import com.shop.entity.Product;
import com.shop.service.AccountService;
import com.shop.service.FavoriteService;
import com.shop.service.FeedbackService;
import com.shop.service.OrderService;
import com.shop.service.ProductService;
import com.shop.util.AuthenticationFacade;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private FeedbackService feedbackService;
	@Autowired
	private FavoriteService favoriteService;
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AuthenticationFacade af;
	
	@GetMapping("/list")
	public String list(
			Model model, 
			@RequestParam(value = "categoryId", defaultValue = "null") Optional<String> categoryId,
			@RequestParam(value = "subcategoryId", defaultValue = "null") Optional<String> subcategoryId,
			@RequestParam(value = "message", required = false) Integer message,
			@RequestParam("page") Optional<Integer> page  
			//@RequestParam(value = "sortBy", defaultValue = "null") Optional<String> sort
		) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		List<Favorite> FavList = favoriteService.findByUsername(userName);
		
		// Pageable
		Pageable pageable = PageRequest.of(page.orElse(0), 16);
		Page<Product> list = null;
		List<Boolean> favL = new ArrayList<>();
		//Sort sortOption = null;
		// sort by category
		if (!categoryId.get().equals("null")) {
			list = productService.findByCategoryID(categoryId.get(), pageable);
			model.addAttribute("products", list);
		} else if (!subcategoryId.get().equals("null")) {
			list = productService.findBySubcategoryID(subcategoryId.get(), pageable);
			model.addAttribute("products", list);
		} else {
			list = productService.findAllProduct(pageable);
			model.addAttribute("products", list);
		}
		
		if(list == null) {
			model.addAttribute("isProductNull", false);
		} else {
			model.addAttribute("isProductNull", true);
		}
		for (Product p : list.getContent()) {
			boolean sta =false;
			for (Favorite f : FavList) {
				if (f.getProduct().getProductId().equalsIgnoreCase(p.getProductId())) {
					sta= true;
				}
			}
			favL.add(sta);
		}
		int totalPages = list.getTotalPages();
		List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
		model.addAttribute("pageNumbers", pageNumbers);
		model.addAttribute("favL", favL);
		System.out.println(message);
		model.addAttribute("mess", message);
		return "user-page/product";
	}

	@GetMapping("/product-detail")
	public String getProductDetail(
			@RequestParam("id") String maSP,
			@RequestParam(value = "message", required = false) String message,
			@RequestParam(name = "sendFb", required = false, defaultValue = "0") String sendFb,
			Model model) {
		
		int status = 1;
		List<Feedback> feedBacks = feedbackService.findFeedBacksByPrdId(maSP);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!af.getUsername().equalsIgnoreCase("anonymousUser")) {
			System.out.println("true");
			String userName = authentication.getName();
			List<Product> list = purchasedProducts(userName);
			for (Product p : list) {
				if (p.getProductId().equalsIgnoreCase(maSP)) {
					status=1;
					break;
				}else {
					status=0;
				}
			}
			for (Feedback fb : feedBacks) {
				if (fb.getAccount().getUsername().equalsIgnoreCase(userName)) {
					status=0;
				}
			}
		} else {
			status=0;
		}
		
		Optional<Product> optionalProduct = productService.findProductById(maSP);
		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			System.out.println(product.getProductName());
			boolean fav = false;
			List<Favorite> favL = favoriteService.findByUsername(authentication.getName());
			for (Favorite f : favL) {
				if (f.getProduct().getProductId().equalsIgnoreCase(maSP)) {
					fav=true;
					break;
				}
			}
			model.addAttribute("feedBacks", feedBacks);
			model.addAttribute("status", status);
			model.addAttribute("fav", fav);
			model.addAttribute("product", product);
			model.addAttribute("mess", message);
			if (sendFb.equalsIgnoreCase("1")) {
				model.addAttribute("sendFb", sendFb);
			}
			return "user-page/product-detail";
		} else {
			System.out.println("Không tìm thấy product");
			return "redirect:/product/list-all-product";
		}
	}
	@GetMapping("/sendfeedback")
	public String sendFeedBack(
			@RequestParam("review") String review,
			@RequestParam("prdId") String prdId,
			RedirectAttributes redirectAttributes,
			Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Product prd= productService.findProductById(prdId).get();
		Account acc= accountService.findAccountById(authentication.getName()).get(); 
		Feedback fb = new Feedback();
		fb.setReviewContent(review);
		fb.setProduct(prd);
		fb.setAccount(acc);
		System.out.println(prd.getProductName());
		fb.setReviewDate(new Date());
		feedbackService.createFeedback(fb);
		redirectAttributes.addAttribute("sendFb", "1");
		return "redirect:/product/product-detail?id="+prd.getProductId();
	}
	
	//---------------------------function
	
	List<Product> purchasedProducts(String userName){
		List<Product> list = new ArrayList<>();
		List<Order> Orders =  orderService.findOrderByUserName(userName);
		for (Order o : Orders) {
			for (OrderDetail od : o.getOrderDetails()) {
				list.add(od.getProduct()); 
			}
		}
		
		return list;
	}

}