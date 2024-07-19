package com.Ecom.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.Ecom.Model.Category;
import com.Ecom.Model.Product;
import com.Ecom.Service.CategoryService;
import com.Ecom.Service.ProductService;




@Controller
public class HomeController 
{
	@Autowired
	private CategoryService categoryservice;
	
	@Autowired
	private ProductService productservice;
	
	 @GetMapping
     public String index()
     {
    	 return "INDEX";
     }
	
	@GetMapping("/login")
    public String login()
    {
   	 return "LOGIN";
    }
	
	@GetMapping("/register")
    public String register()
    {
   	 return "REGISTER";
    }
	
	@GetMapping("/products")
    public String products(Model m, @RequestParam (value="category",defaultValue = "") String category)
    {
		//System.out.println(category+"=category");
		List<Category> categories=categoryservice.getAllActiveCategory();
		List<Product> products=productservice.getAllActiveProducts(category);
		m.addAttribute("categories",categories);
		m.addAttribute("products",products);
		m.addAttribute("paramValue",category);
		
   	 return "product";
    }
	
	@GetMapping("/product/{id}")
    public String product(@PathVariable int id, Model m)
    {
		Product productById=productservice.getProductById(id);
		m.addAttribute("product", productById);
   	 return "VIEW_PRODUCT";
    }
    
}