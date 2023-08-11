package com.shop.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shop.entity.Brand;
import com.shop.entity.Color;
import com.shop.entity.Discount;
import com.shop.entity.Image;
import com.shop.entity.Product;
import com.shop.entity.Size;
import com.shop.entity.Subcategory;
import com.shop.service.BrandService;
import com.shop.service.ColorService;
import com.shop.service.DiscountService;
import com.shop.service.ImageService;
import com.shop.service.ProductService;
import com.shop.service.SizeService;
import com.shop.service.SubcategoryService;

@Controller
public class AdminProductController {
	
	@Autowired
	ProductService productService;
	@Autowired
	SubcategoryService subcategoryService;
	@Autowired
	BrandService brandService;
	@Autowired
	ColorService colorService;
	@Autowired
	SizeService sizeService;
	@Autowired
	ImageService imageService;
	@Autowired
	DiscountService discountService;
	String uploadDir = "C:/Users/Dung/Documents/GitHub/DoAnTotNghiep/DATN_ShopTheThao/src/main/resources/static/user-page/images/product/";
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
	@GetMapping("/admin/product")
	public String ProductList(@RequestParam(defaultValue = "0") int page, Model model) {
		
		int pageSize = 10; // Số phần tử trên mỗi trang
		Pageable pageable = PageRequest.of(page, pageSize);
		
		List<Product> list = productService.findAllProduct();
		Page<Product> pageProduct = productService.findAllProduct(pageable);
		model.addAttribute("list", list);
		model.addAttribute("page", pageProduct);
		return "admin-page/product-list";
	}
	
	@GetMapping("/admin/product/add")
	public String ProductAdd(Model model) {
		
		Product pro = new Product();
		
		List<Size> SizeL = sizeService.findAllSize();
		List<Color> ColorL = colorService.findAllColor();
		List<Brand> BrandL = brandService.findAllBrand();
		List<Discount> DiscountL = discountService.findAllDiscount();
		List<Subcategory> SubcategoryL = subcategoryService.findAllSubcategory();
		
		model.addAttribute("subcategoryL", SubcategoryL);
		model.addAttribute("sizeL", SizeL);
		model.addAttribute("colorL", ColorL);
		model.addAttribute("discountL", DiscountL);
		model.addAttribute("brandL", BrandL);
		model.addAttribute("product", pro);
		model.addAttribute("status", 1);
		return "admin-page/product-add";
}
	
	@PostMapping("/admin/product/add/save")
	public String ProductAddSave(Model model,@ModelAttribute("product") Product product,
			@RequestParam("image1") MultipartFile image1,
			@RequestParam("image2") MultipartFile image2,
			@RequestParam("image3") MultipartFile image3,
			@RequestParam("image4") MultipartFile image4) throws IOException {
		
		
		String filename1 = image1.getOriginalFilename();
		String filename2 = image2.getOriginalFilename();
		String filename3 = image3.getOriginalFilename();
		String filename4 = image4.getOriginalFilename();

		Image img = new Image();

		String fileExtension1 = filename1.substring(filename1.lastIndexOf("."));
		String new1 = UUID.randomUUID().toString() + fileExtension1;
		String fileExtension2 = filename2.substring(filename2.lastIndexOf("."));
		String new2 = UUID.randomUUID().toString() + fileExtension2;
		String fileExtension3 = filename3.substring(filename3.lastIndexOf("."));
		String new3 = UUID.randomUUID().toString() + fileExtension3;
		String fileExtension4 = filename4.substring(filename4.lastIndexOf("."));
		String new4 = UUID.randomUUID().toString() + fileExtension4;
		img.setImageName1(new1);
		img.setImageName2(new2);
		img.setImageName3(new3);
		img.setImageName4(new4);

		try {
		    System.out.println(product.getAvailable());
		    product.setImage(img);
		    imageService.createImage(img);
		    productService.createProduct(product);

		    // Sử dụng đối tượng File để xác định vị trí lưu trữ tệp tải lên
		    File file1 = new File(uploadDir + new1);
		    File file2 = new File(uploadDir + new2);
		    File file3 = new File(uploadDir + new3);
		    File file4 = new File(uploadDir + new4);
		    System.out.println(file1.getName());
		    // Thực hiện transfer tệp tải lên vào vị trí lưu trữ đã xác định
		    image1.transferTo(file1);
		    image2.transferTo(file2);
		    image3.transferTo(file3);
		    image4.transferTo(file4);
		} catch (IOException e) {
		    // Xử lý lỗi nếu có lỗi trong quá trình transfer
		    e.printStackTrace();
		}
        
		return "redirect:/admin/product";
	}
	
	//------------------------------------------------------------
	
	@GetMapping("/admin/product/update")
	public String ProductUpdate(Model model, @RequestParam("id") String id) {
		
		Product pro = productService.findProductById(id).get();
		
		String imgPath1= uploadDir + pro.getImage().getImageName1();
		String imgPath2= uploadDir + pro.getImage().getImageName2();
		String imgPath3= uploadDir + pro.getImage().getImageName3();
		String imgPath4= uploadDir + pro.getImage().getImageName4();
		
		List<Size> SizeL = sizeService.findAllSize();
		List<Color> ColorL = colorService.findAllColor();
		List<Brand> BrandL = brandService.findAllBrand();
		List<Discount> DiscountL = discountService.findAllDiscount();
		List<Subcategory> SubcategoryL = subcategoryService.findAllSubcategory();
		
		model.addAttribute("image1", imgPath1);
		model.addAttribute("image2", imgPath2);
		model.addAttribute("image3", imgPath3);
		model.addAttribute("image4", imgPath4);
		
		model.addAttribute("subcategoryL", SubcategoryL);
		model.addAttribute("sizeL", SizeL);
		model.addAttribute("colorL", ColorL);
		model.addAttribute("discountL", DiscountL);
		model.addAttribute("brandL", BrandL);
		model.addAttribute("product", pro);
		model.addAttribute("status", 0);
		return "admin-page/product-add";
}
	
	
	@PostMapping("/admin/product/update/save")
	public String ProductUpdateSave(Model model,@ModelAttribute("product") Product product, 
			@RequestParam(value="image1", required = false) MultipartFile image1,
			@RequestParam(value="image2", required = false) MultipartFile image2,
			@RequestParam(value="image3", required = false) MultipartFile image3,
			@RequestParam(value="image4", required = false) MultipartFile image4) throws IOException {
		
		Product pro = productService.findProductById(product.getProductId()).get();
		Image img = pro.getImage();
		
		if (image1 != null && !image1.isEmpty()) {
		    // Xử lý khi có tệp hình ảnh được gửi lên
		    String imagePath = uploadDir+img.getImageName1(); // Đường dẫn đến tệp ảnh cần xóa
	        // Tạo đối tượng Path từ đường dẫn tệp ảnh
	        Path imagePathObj = Paths.get(imagePath);
	        try {
	            // Sử dụng Files.delete() để xóa tệp
	            Files.delete(imagePathObj);
	            System.out.println("Tệp ảnh đã được xóa.");
	        } catch (IOException e) {
	            System.out.println("Không thể xóa tệp ảnh: " + e.getMessage());
	        }
			
			String filename1 = image1.getOriginalFilename();
			String fileExtension1 = filename1.substring(filename1.lastIndexOf("."));
			String new1 = UUID.randomUUID().toString() + fileExtension1;
			img.setImageName1(new1);
		    // Sử dụng đối tượng File để xác định vị trí lưu trữ tệp tải lên
		    File file1 = new File(uploadDir + new1);
		    image1.transferTo(file1);
		}
		if (image2 != null && !image2.isEmpty()) {
		    // Xử lý khi có tệp hình ảnh được gửi lên
		    String imagePath = uploadDir+img.getImageName2(); // Đường dẫn đến tệp ảnh cần xóa
	        // Tạo đối tượng Path từ đường dẫn tệp ảnh
	        Path imagePathObj = Paths.get(imagePath);
	        try {
	            // Sử dụng Files.delete() để xóa tệp
	            Files.delete(imagePathObj);
	            System.out.println("Tệp ảnh đã được xóa.");
	        } catch (IOException e) {
	            System.out.println("Không thể xóa tệp ảnh: " + e.getMessage());
	        }
			
			String filename2 = image2.getOriginalFilename();
			String fileExtension2 = filename2.substring(filename2.lastIndexOf("."));
			String new2 = UUID.randomUUID().toString() + fileExtension2;
			img.setImageName1(new2);
		    // Sử dụng đối tượng File để xác định vị trí lưu trữ tệp tải lên
		    File file2 = new File(uploadDir + new2);
		    image2.transferTo(file2);
		}
		if (image3 != null && !image3.isEmpty()) {
		    // Xử lý khi có tệp hình ảnh được gửi lên
		    String imagePath = uploadDir+img.getImageName3(); // Đường dẫn đến tệp ảnh cần xóa
	        // Tạo đối tượng Path từ đường dẫn tệp ảnh
	        Path imagePathObj = Paths.get(imagePath);
	        try {
	            // Sử dụng Files.delete() để xóa tệp
	            Files.delete(imagePathObj);
	            System.out.println("Tệp ảnh đã được xóa.");
	        } catch (IOException e) {
	            System.out.println("Không thể xóa tệp ảnh: " + e.getMessage());
	        }
			
			String filename3 = image3.getOriginalFilename();
			String fileExtension3 = filename3.substring(filename3.lastIndexOf("."));
			String new3 = UUID.randomUUID().toString() + fileExtension3;
			img.setImageName1(new3);
		    // Sử dụng đối tượng File để xác định vị trí lưu trữ tệp tải lên
		    File file3 = new File(uploadDir + new3);
		    image3.transferTo(file3);
		}
		if (image4 != null && !image4.isEmpty()) {
		    // Xử lý khi có tệp hình ảnh được gửi lên
		    String imagePath = uploadDir+img.getImageName4(); // Đường dẫn đến tệp ảnh cần xóa
	        // Tạo đối tượng Path từ đường dẫn tệp ảnh
	        Path imagePathObj = Paths.get(imagePath);
	        try {
	            // Sử dụng Files.delete() để xóa tệp
	            Files.delete(imagePathObj);
	            System.out.println("Tệp ảnh đã được xóa.");
	        } catch (IOException e) {
	            System.out.println("Không thể xóa tệp ảnh: " + e.getMessage());
	        }
			
			String filename4 = image4.getOriginalFilename();
			String fileExtension4 = filename4.substring(filename4.lastIndexOf("."));
			String new4 = UUID.randomUUID().toString() + fileExtension4;
			img.setImageName1(new4);
		    // Sử dụng đối tượng File để xác định vị trí lưu trữ tệp tải lên
		    File file4 = new File(uploadDir + new4);
		    image4.transferTo(file4);
		}

		Product product2 = productService.findProductById(product.getProductId()).get();
	    product.setImage(img);
	    imageService.deleteImage(product2.getImage().getImageId());
	    imageService.createImage(img);
	    productService.createProduct(product);
        
		return "redirect:/admin/product";
	}
	@GetMapping("/admin/product/delete")
	public String ProductDelete(Model model, @RequestParam("id") String id) {
		
		Product product =  productService.findProductById(id).get();
		productService.deleteProduct(id);
		imageService.deleteImage(product.getImage().getImageId());
		
		return "redirect:/admin/product";
	}
}
