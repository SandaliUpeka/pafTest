package com.crud.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.crud.demo.model.Product;
import com.crud.demo.repository.ProductRepository;

@Controller
public class ProductController {
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/signup")
    public String showSignUpForm(Product product) {
        return "add-product";
    }
     
    @PostMapping("/addproduct")
    public String addUser(@Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
         
        productRepository.save(product);
        model.addAttribute("products", productRepository.findAll());
        return "index";
    }
    
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Product product = productRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
         
        model.addAttribute("product", product);
        return "update-product";
    }
    
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid Product product, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
        	product.setId(id);
            return "update-product";
        }
             
        productRepository.save(product);
        model.addAttribute("products", productRepository.findAll());
        return "index";
    }
         
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
    	Product product = productRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid Product Id:" + id));
        productRepository.delete(product);
        model.addAttribute("users", productRepository.findAll());
        return "index";
    }
}
