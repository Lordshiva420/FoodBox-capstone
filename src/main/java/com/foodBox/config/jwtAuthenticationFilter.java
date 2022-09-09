package com.foodBox.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.foodBox.service.userDetaliServiceImpl;

import net.bytebuddy.asm.MemberSubstitution.Substitution.Chain;

@Component
public class jwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private userDetaliServiceImpl userDetaliServiceImpl;
	
	@Autowired
	private jwtUtil jwtutil;

	
//for anyother requests otherthan permitall will passthrough this filter first.
	
	// we will come here ,after generating token for subsequent requests.
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String requestTokenHeader=request.getHeader("Authorization");
		
		System.out.println(requestTokenHeader);
		
		String userName = null;
		String jwtToken = null;
		
		// step:1
		// here we take request and extract username and token.
		
			if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")) {
				try {
					
					jwtToken=requestTokenHeader.substring(7);
					
				   userName=this.jwtutil.extractUsername(jwtToken);
				
					
				} catch (Exception e) {
					e.printStackTrace();
					
				}
				
						
			}else {
				System.out.println("invalide token , not start with Bearer");
			}

			
	// step:2
	//here we check the token, is it really belongs to user, who send it.
		if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			final UserDetails userDetails=this.userDetaliServiceImpl.loadUserByUsername(userName);
			if(this.jwtutil.validateToken(jwtToken, userDetails)) {
				
				//if token valid then we will set security context with his request and his authorities.
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken( userDetails, null,userDetails.getAuthorities());
			usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}else {
				System.out.println("token is not valide");
			}
		}
		
		//when security context has something , we forward the request.
	 filterChain.doFilter(request, response);
		
	}

}
