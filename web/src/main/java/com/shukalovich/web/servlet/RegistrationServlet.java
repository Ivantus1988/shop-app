package com.shukalovich.web.servlet;

import com.shukalovich.database.entity.UserEntity;
import com.shukalovich.database.entity.enam.Gender;
import com.shukalovich.service.UserService;
import com.shukalovich.web.util.PagesUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@WebServlet("/registration")
@Controller
public class RegistrationServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = (ApplicationContext) getServletContext().getAttribute("applicationContext");
        userService = context.getBean(UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(PagesUtil.REGISTRATION).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        userService.save(
                UserEntity.builder()
                        .email(req.getParameter("email"))
                        .password(req.getParameter("password"))
                        .name(req.getParameter("name"))
                        .surname(req.getParameter("surname"))
                        .gender(Gender.valueOf(req.getParameter("gender")))
                        .build());
        resp.sendRedirect("/login");
    }
}