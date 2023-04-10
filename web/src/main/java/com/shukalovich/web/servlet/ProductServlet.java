package com.shukalovich.web.servlet;

import com.shukalovich.database.entity.Product;
import com.shukalovich.service.ProductService;
import com.shukalovich.web.util.PagesUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private final ProductService productService = ProductService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        List<Product> products = productService.findAll();
        if (id == null) {
            req.setAttribute("products", products);
            req.getRequestDispatcher(PagesUtil.PRODUCTS).forward(req, resp);
        } else {
            req.setAttribute("product", productService.findById(Integer.getInteger(id)));
            req.getRequestDispatcher(PagesUtil.PRODUCT).forward(req, resp);
        }
    }
}
