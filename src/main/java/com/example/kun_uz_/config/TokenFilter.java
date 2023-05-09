package com.example.kun_uz_.config;
import com.example.kun_uz_.dto.JwtDTO.JwtDTO;
import com.example.kun_uz_.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
public class TokenFilter extends GenericFilterBean {


        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            System.out.println("doFilter method");
            final HttpServletRequest request = (HttpServletRequest) servletRequest;
            final HttpServletResponse response = (HttpServletResponse) servletResponse;

            final String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setHeader("Message", "Token Not Found.");
                return;
            }

            String token = authHeader.substring(7);
            JwtDTO jwtDto;
            try {
                jwtDto = JwtUtil.decode(token);
            } catch (JwtException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setHeader("Message", "Token Not Valid");
                return;
            }

            request.setAttribute("id", jwtDto.getId());
            request.setAttribute("role", jwtDto.getRole());
            filterChain.doFilter(request, response);
        }
    }

