package com.shukalovich.web.servlet;

import com.shukalovich.database.dto.ProductFilter;
import com.shukalovich.database.entity.ProductEntity;
import com.shukalovich.service.ProductService;
import com.shukalovich.web.util.PagesUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;

import static java.lang.Double.*;
import static java.lang.Short.parseShort;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private final ProductService productService = ProductService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            String memorySize;
            String price;
            String ram;
            String limit;
            String page;
            if (req.getParameter("memorySize") == null || req.getParameter("memorySize").isEmpty()) {
                memorySize = "1000";
            } else {
                memorySize = req.getParameter("memorySize");
            }
            if (req.getParameter("price") == null || req.getParameter("price").isEmpty()) {
                price = "3000";
            } else {
                price = req.getParameter("price");
            }
            if (req.getParameter("ram") == null || req.getParameter("ram").isEmpty()) {
                ram = "10";
            } else {
                ram = req.getParameter("ram");
            }
            if (req.getParameter("limit") == null || req.getParameter("limit").isEmpty()) {
                limit = "6";
            } else {
                limit = req.getParameter("limit");
            }
            if (req.getParameter("page") == null || req.getParameter("page").isEmpty()) {
                page = "1";
            } else {
                page = req.getParameter("page");
            }
//            ProductFilter build = ProductFilter.builder()
//                    .memorySize((short) memorySize)
//                    .price(1234.0)
//                    .ram((short) 4)
//                    .limit(1)
//                    .page(1)
//                    .build();
            req.setAttribute("products", productService.findByFilter(
                    ProductFilter.builder()
                            .memorySize(Short.parseShort(memorySize))
                            .price(Double.parseDouble(price))
                            .ram(Short.parseShort(ram))
                            .limit(Integer.valueOf(limit))
                            .page(Integer.valueOf(page))
                            .build()

            ));
            req.getRequestDispatcher(PagesUtil.PRODUCTS).forward(req, resp);
        } else {
            redirectToProductPage(req, resp, productService.findById(Long.parseLong(id)));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String memorySize = req.getParameter("memory_size");
        String price = req.getParameter("price");
        String ram = req.getParameter("ram");
        ProductEntity.ProductEntityBuilder builder = ProductEntity.builder();
        builder.memorySize(parseShort(memorySize));
        builder.price(parseDouble(price));
        builder.ram(parseShort(ram));
        ProductEntity productForCreation = builder
                .build();
        productService.save(productForCreation)
                .ifPresentOrElse(
                        product -> redirectToProductPage(req, resp, product),
                        () -> onFailedCreation(req, resp)
                );
        super.doPost(req, resp);
    }

    @SneakyThrows
    private static void redirectToProductPage(HttpServletRequest req, HttpServletResponse resp,
                                              ProductEntity product) {
        req.setAttribute("product", product);
        req.getRequestDispatcher(PagesUtil.PRODUCT).forward(req, resp);
    }

    @SneakyThrows
    private static void onFailedCreation(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("error", true);
        req.getRequestDispatcher(PagesUtil.PRODUCT).forward(req, resp);
    }
}
