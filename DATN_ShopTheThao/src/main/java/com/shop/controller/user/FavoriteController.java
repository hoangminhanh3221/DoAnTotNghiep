package com.shop.controller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

import com.shop.entity.Product;
import com.shop.entity.Favorite;
import com.shop.service.AccountService;
import com.shop.service.FavoriteService;
import com.shop.service.ProductService;

@Controller
@RequestMapping("/user")
public class FavoriteController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private FavoriteService favoriteService;
	
	@GetMapping("/favorite")
	public String Favorite (
			Model model,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam(value = "message", required = false) Integer message
			//@RequestParam(value = "sortBy", defaultValue = "null") Optional<String> sort
		) {
		List<Product> listPrd = new ArrayList<>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		
		List<Favorite> FavList = favoriteService.findByUsername(userName);
		
		for (Favorite f : FavList) {
			listPrd.add(f.getProduct());
		}
		
		// Pageable
		Pageable pageable = PageRequest.of(page.orElse(0), 16);
		Page<Product> list = new PageImpl<>(listPrd, pageable, listPrd.size());
		model.addAttribute("products", list);
		
		if(FavList == null) {
			model.addAttribute("isProductNull", false);
		} else {
			model.addAttribute("isProductNull", true);
		}
		
		int totalPages = list.getTotalPages();
		List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();

		model.addAttribute("mess", message);
		model.addAttribute("pageNumbers", pageNumbers);
		return "user-page/favorite";
	}
	@GetMapping("/favorite/add")
	public String add (
			Model model,
			@RequestParam("id") String id,
			RedirectAttributes redirectAttributes,
			HttpServletRequest request
		) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		
		Favorite fav = new Favorite();
		fav.setAccount(accountService.findAccountById(userName).get());
		fav.setProduct(productService.findProductById(id).get());
		favoriteService.createFavorite(fav);
		redirectAttributes.addAttribute("message", 0);
		
		// Lưu trang trước đó trong session
        String referrer = request.getHeader("referer");
        request.getSession().setAttribute("referrer", referrer);
        String url = referrer.split("&message")[0]; 
        // Thực hiện xong và chuyển hướng trở lại trang trước đó
        return "redirect:" + url;
	}
	@GetMapping("/favorite/remove")
	public String remove (
			Model model,
			@RequestParam("id") String id,
			RedirectAttributes redirectAttributes,
			HttpServletRequest request
		) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		
		Favorite fav = new Favorite();
		
		List<Favorite> FavList = favoriteService.findByUsername(userName);
		for (Favorite f : FavList) {
			if (f.getProduct().getProductId().equalsIgnoreCase(id)) {
				fav = f;
				break;
			}
		}
		favoriteService.deleteFavorite(fav.getFavoriteId());
		redirectAttributes.addAttribute("message", 1);
		
		// Lưu trang trước đó trong session
        String referrer = request.getHeader("referer");
        request.getSession().setAttribute("referrer", referrer);
        String url = referrer.split("&message")[0]; 
        // Thực hiện xong và chuyển hướng trở lại trang trước đó
        return "redirect:" + url;
	}
}
