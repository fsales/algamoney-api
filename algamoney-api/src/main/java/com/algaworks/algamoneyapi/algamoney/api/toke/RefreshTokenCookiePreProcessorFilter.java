package com.algaworks.algamoneyapi.algamoney.api.toke;

import org.apache.catalina.util.ParameterMap;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Stream;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RefreshTokenCookiePreProcessorFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        if ("/oauth/token".equalsIgnoreCase(req.getRequestURI())
                && "refresh_token".equals(req.getParameter("grant_type"))
                && req.getCookies() != null) {

            String refreshToken =
                    Stream.of(req.getCookies())
                            .filter(cookie -> "refreshToken".equals(cookie.getName()))
                            .findFirst()
                            .map(cookie -> cookie.getValue())
                            .orElse(null);

            req = new MyServletRequestWrapper(req, refreshToken);
        }

        filterChain.doFilter(req, response);
    }

    static class MyServletRequestWrapper extends HttpServletRequestWrapper{ //Após extender o HttpServletRequestWrapper criar o implemento

        private String refreshToken;

        public MyServletRequestWrapper(HttpServletRequest request, String refreshToken) {
            super(request);

            this.refreshToken = refreshToken;
        }

        @Override
        public Map<String, String[]> getParameterMap() { //criar o  o getParameterMap

            ParameterMap<String, String[]> map = new ParameterMap<>(getRequest().getParameterMap());
            map.put("refresh_token", new String[] { refreshToken }); //refresh_token é o nome que o spring irá buscar o token
            map.setLocked(true);//Trava o mapa

            return map;
        }

    }
}
