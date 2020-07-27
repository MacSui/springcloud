package com.springcloud.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springcloud.entiry.JwtUser;
import com.springcloud.module.LoginUser;
import com.springcloud.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2020/5/27
 **/
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginUser loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginUser.getUserName(), loginUser.getPassword()
            ));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验证成功后调用，并返回token
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        JwtUser jwtUser =(JwtUser) authResult.getPrincipal();
        System.out.println("jwtUser:"+jwtUser);
        String token = JwtTokenUtils.createToken(jwtUser.getUsername(), false);
        response.setHeader("token", JwtTokenUtils.TOKEN_PREFIX + token);

    }

    /**
     * 验证失败时调用，返回失败原因
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        response.getWriter().write("authentication failed, reason:"+failed.getMessage());
    }
}
