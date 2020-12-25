package com.project.qrsis.security;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CORSAllowFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String cookie = response.getHeader("Set-Cookie");
        if (cookie != null) {
            cookie += "; secure; SameSite=None";
            response.setHeader("Set-Cookie", cookie);
        }

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Headers",
                "access-control-allow-origin," +
                        "access-control-allow-credentials, " +
                        "access-control-allow-headers, " +
                        "authorization," +
                        "content-type");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        chain.doFilter(req, response);
    }
}
