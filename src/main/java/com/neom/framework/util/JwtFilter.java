package com.neom.framework.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import com.neom.framework.constants.FrameworkConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) servletRequest;
		final HttpServletResponse response = (HttpServletResponse) servletResponse;
		final String authHeader = request.getHeader(FrameworkConstants.AUTHORIZATION);
		if (FrameworkConstants.OPTIONS.equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(request, response);
		} else {
			if (authHeader == null || !authHeader.startsWith(FrameworkConstants.BEARER)) {
				throw new ServletException(FrameworkConstants.EXCEPTION);
			}
		}
		final String token = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();
		request.setAttribute(FrameworkConstants.CLAIMS, claims);
		chain.doFilter(request, response);
	}

}
