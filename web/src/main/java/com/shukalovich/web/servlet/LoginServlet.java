package com.shukalovich.web.servlet;

import com.shukalovich.database.entity.UserEntity;
import com.shukalovich.service.UserService;
import com.shukalovich.web.util.PagesUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@WebServlet("/login")
@Controller
public class LoginServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = (ApplicationContext) getServletContext().getAttribute("applicationContext");
        userService = context.getBean(UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(PagesUtil.LOGIN)
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        userService.findByEmailAndPass(email, password)
                .ifPresentOrElse(
                        user -> successLogin(req, resp, user),
                        () -> failLogin(req, resp)
                );

    }

    @SneakyThrows
    private static void successLogin(HttpServletRequest req, HttpServletResponse resp, UserEntity user) {
        req.getSession().setAttribute("user", user);
        resp.sendRedirect("/products");
    }

    @SneakyThrows
    private static void failLogin(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect("/login?error=true");
    }
}
