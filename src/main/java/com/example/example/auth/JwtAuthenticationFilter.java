package com.example.example.auth;


import com.example.example.helper.JwtHelper;
import com.example.example.helper.UserDetailsCacheHelper;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author fucheng on 2023/12/4
 */
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 从请求头中获取token
        String token = getTokenFromRequest(request);

        // 验证token是否为空
        if (StringUtils.isNotBlank(token)) {
            // 从token中获取用户信息
            String username = JwtHelper.parseToken(token);
            if (StringUtils.isNotBlank(username)) {
                // 从缓存中获取用户信息
                UserDetails payload = UserDetailsCacheHelper.getUserCache(username);
                // 如果用户信息不为空，将用户信息放入SecurityContextHolder中
                if (null != payload && null == SecurityContextHolder.getContext().getAuthentication()) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(payload, null,
                                    payload.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else {
                    // 无效token移除
                    UserDetailsCacheHelper.removeUserCache(username);
                }
            }
        }
        chain.doFilter(request, response);
    }

    // 从Request头信息中获取token
    private String getTokenFromRequest(HttpServletRequest request) {
        return request.getHeader("token");
    }
}
