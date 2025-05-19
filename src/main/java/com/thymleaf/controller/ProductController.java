package com.thymleaf.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thymleaf.model.Product;
import com.thymleaf.repo.ProductRepository;

@Controller
public class ProductController {

	private final int PAGE_SIZE = 5;
	private final ProductRepository productrepo;
	public ProductController(ProductRepository productrepo) {
		this.productrepo=productrepo;
	}
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("message","Welcome to out Product Managment System");
		return "index";
	}
	
	 @GetMapping("/products")
	    public String listProducts(
	            @RequestParam(defaultValue = "0") int page,
	            Model model) {
	        
	        Page<Product> productPage = productrepo.findAll(
	            PageRequest.of(page, PAGE_SIZE, Sort.by("name").ascending())
	        );
	        
	        model.addAttribute("products", productPage.getContent());
	        model.addAttribute("currentPage", page);
	        model.addAttribute("totalPages", productPage.getTotalPages());
	        model.addAttribute("totalItems", productPage.getTotalElements());
	        
	        return "products";
	    }
	@GetMapping("/add-product")
	public String showAddForm(Model model) {
		model.addAttribute("product", new Product());
		return "add-product";
	}
	@PostMapping("/add-product")
	public String addProduct(@ModelAttribute Product product) {
		productrepo.save(product);
		return "redirect:/products";
	}
	@GetMapping("/about")
	public String about() {
		return "about";
	}
}
