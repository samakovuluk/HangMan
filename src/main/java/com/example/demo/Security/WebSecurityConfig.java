package com.example.demo.Security;

import com.example.demo.Entities.Users;
import org.apache.tomcat.util.codec.binary.Base64;
import org.bouncycastle.crypto.generators.OpenBSDBCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Component
public class WebSecurityConfig implements Filter {
    private final EntityManager entityManager;
    @Autowired
    public WebSecurityConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Query query = entityManager.createNativeQuery("SELECT username,password FROM users WHERE username = :username");

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            StringTokenizer st = new StringTokenizer(authHeader);
            if (st.hasMoreTokens()) {
                String basic = st.nextToken();

                if (basic.equalsIgnoreCase("Basic")) {
                    try {
                        String credentials = new String(Base64.decodeBase64(st.nextToken()), "UTF-8");
                        int p = credentials.indexOf(":");
                        if (p != -1) {
                            String _username = credentials.substring(0, p).trim();
                            String _password = credentials.substring(p + 1).trim();
                            query.setParameter("username", _username);
                            List<Object[]> rows =query.getResultList();
                            List<Users> users = new ArrayList<>(rows.size());
                            if (rows.size()!=0){
                                for (Object[] row : rows){


                                    if (!OpenBSDBCrypt.checkPassword(row[1].toString(), _password.toCharArray())) {
                                        unauthorized(response, "Bad credentials");

                                    } else {
                                        filterChain.doFilter(servletRequest, servletResponse);
                                    }
                                }}
                            else {
                                unauthorized(response, "Bad credentials");
                            }

                        } else {
                            unauthorized(response, "Invalid authentication token");
                        }
                    } catch (UnsupportedEncodingException e) {
                        throw new Error("Couldn't retrieve authentication", e);
                    }
                }
            }
        } else {
            unauthorized(response);
        }

    }

    @Override
    public void destroy() {

    }

    private void unauthorized(HttpServletResponse response, String message) throws IOException {
        response.setHeader("WWW-Authenticate", "Basic realm=\"" + "protected" + "\"");
        response.sendError(401, message);
    }

    private void unauthorized(HttpServletResponse response) throws IOException {
        unauthorized(response, "Unauthorized");
    }
}
