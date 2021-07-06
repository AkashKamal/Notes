package com.notes.Notes.Security.jwt;

import com.notes.Notes.Security.services.UserDetailsImpl;
import com.notes.Notes.exception.AuthorizationException;
import com.notes.Notes.exception.ErrorCode;
import com.notes.Notes.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//This filters all the requests and sets the authentication details in Spring securiity context which can be further accessed in all places with
//UserDetails userDetails =
//        (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    protected JwtUtils jwtUtils;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = parseJwt(httpServletRequest);
            if (jwt != null && jwt != "null" ) {
                if (jwtUtils.validateJWTToken(jwt)) {
                    String userMail = jwtUtils.getUserMailFromJWTToken(jwt);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(userMail);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }

            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
        catch (AuthorizationException e)
        {
            httpServletResponse.setContentType("application/json");
            httpServletResponse.setStatus(e.getCode());
            httpServletResponse.getWriter().write(e.getMessage().toString());
        }

    }

    private String parseJwt(HttpServletRequest request)
    {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }

        return null;
    }
}
