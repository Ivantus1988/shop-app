package com.shukalovich.web.controller;

import com.shukalovich.database.dto.ProductCreationDto;
import com.shukalovich.database.dto.ProductFilter;
import com.shukalovich.database.entity.ProductEntity;
import com.shukalovich.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

import static com.shukalovich.web.util.PagesUtil.PRODUCT;

@Controller
@RequestMapping(PRODUCT)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String getALLProductsPage(Model model, ProductFilter productFilter) {
        model.addAttribute("products", productService.findByFilter(productFilter));
        return "products";
    }

    @GetMapping(path = "/{id}")
    public String getProductPage(Model model, @PathVariable Long id) {
            model.addAttribute("product", productService.findById(id));
        return "product";
    }

    @GetMapping(path = "/create")
    public String createProductPage() {
        return "create-product";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping(path = "/create")
    public String createProduct(ProductCreationDto product) {
        Optional<ProductEntity> newProduct = productService.save(product);
        return "redirect:/product/" + newProduct.get().getId();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping(path = "/{id}/update")
    public String updateProduct(@PathVariable Long id, ProductCreationDto product) {
       return productService.update(id, product).map(
                        updateProduct -> "redirect:/product/" + id
                )
                .orElse("redirect:/products/?error=true");
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping(path = "/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/product";
    }
}