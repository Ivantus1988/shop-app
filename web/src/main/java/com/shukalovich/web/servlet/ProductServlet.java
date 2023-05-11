package com.shukalovich.web.servlet;

import com.shukalovich.database.dto.ProductFilter;
import com.shukalovich.database.entity.Product;
import com.shukalovich.database.entity.enam.Brand;
import com.shukalovich.service.ProductService;
import com.shukalovich.web.util.PagesUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import java.io.IOException;
import static com.shukalovich.database.entity.enam.Brand.valueOf;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private final ProductService productService = ProductService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            req.setAttribute("products", productService.findByFilter(new ProductFilter(
                    Brand.valueOf(req.getParameter("brand") != null ? req.getParameter("brand") : "APPLE"),
                    Double.parseDouble(req.getParameter("price") != null ? req.getParameter("price") : "400"),
                    Integer.parseInt(req.getParameter("ram") != null ? req.getParameter("ram") : "4"),
                    Integer.parseInt(req.getParameter("limit") != null ? req.getParameter("limit") : "4"),
                    Integer.parseInt(req.getParameter("page") != null ? req.getParameter("page") : "1")
            )));
            req.getRequestDispatcher(PagesUtil.PRODUCTS).forward(req, resp);
        } else {
            redirectToProductPage(req, resp, productService.findById(Long.parseLong(id)));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String brand = req.getParameter("brand");
        String price = req.getParameter("price");
        String ram = req.getParameter("ram");
        Product.ProductBuilder builder = Product.builder();
        builder.brand(valueOf(brand));
        builder.price(Double.parseDouble(price));
        builder.ram(Integer.parseInt(ram));
        Product productForCreation = builder
                .build();
        productService.save(productForCreation)
                .ifPresentOrElse(
                        product -> redirectToProductPage(req, resp, product),
                        () -> onFailedCreation(req, resp)
                );
        super.doPost(req, resp);
    }

    @SneakyThrows
    private static void redirectToProductPage(HttpServletRequest req, HttpServletResponse resp, Product product) {
        req.setAttribute("product", product);
        req.getRequestDispatcher(PagesUtil.PRODUCT).forward(req, resp);
    }

    @SneakyThrows
    private static void onFailedCreation(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("error", true);
        req.getRequestDispatcher(PagesUtil.PRODUCT).forward(req, resp);
    }
}
