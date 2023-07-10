package com.shop.intercreptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.shop.entity.Category;
import com.shop.entity.Subcategory;
import com.shop.service.CategoryService;
import com.shop.service.SubcategoryService;


@Component
public class GlobalInterceptor implements HandlerInterceptor{
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SubcategoryService subcategoryService;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//header
		List<Category> lists = categoryService.findAllCategory();
		request.setAttribute("categories", lists);
		for(int i = 0 ; i < lists.size(); i++) {
			List<Subcategory> listSub  = subcategoryService.findSubcategoryByCategoryId(lists.get(i).getCategoryId());
			request.setAttribute(lists.get(i).getCategoryId(), listSub);
		}

	}
}
