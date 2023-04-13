package com.shukalovich.web.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PagesUtil {

    private static final String PREFIX = "/WEB-INF/jsp/";
    public static final String PRODUCTS = PREFIX + "products.jsp";
    public static final String PRODUCT = PREFIX + "product.jsp";
    public static final String LOGIN = PREFIX + "login.jsp";
    public static final String REGISTRATION = PREFIX + "registration.jsp";
}