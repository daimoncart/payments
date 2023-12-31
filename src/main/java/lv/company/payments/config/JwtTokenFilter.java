//package lv.company.payments.config;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.AllArgsConstructor;
//import lv.company.payments.util.JwtUtil;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Collections;
//
//@Component
//@AllArgsConstructor
//public class JwtTokenFilter extends OncePerRequestFilter {
//
//    private final JwtUtil util;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        final String header = request.getHeader("Authorization");
//
//        if (header != null && header.startsWith("Bearer ")) {
//            String token = header.substring(7);
//            if (!util.isTokenExpired(token)) {
//                String username = util.extractUsername(token);
//
//                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                    Authentication auth = new UsernamePasswordAuthenticationToken(
//                            username, null,
//                            Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
//                    );
//
//                    SecurityContextHolder.getContext().setAuthentication(auth);
//                }
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}