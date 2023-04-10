package com.shukalovich.web.servlet;

import com.shukalovich.database.dao.UserDao;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")

public class UserServlet extends HttpServlet {
    private final UserDao userService = UserDao.getInstance();


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try (var writer = resp.getWriter()) {
            writer.write("User name - ".concat(userService.getUser().getName())
                    .concat(", surname - ")
                    .concat(userService.getUser().getSurname())
                    .concat(", email - ")
                    .concat(userService.getUser().getEmail())
                    .concat("."));
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

}
