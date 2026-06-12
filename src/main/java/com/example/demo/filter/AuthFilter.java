package com.example.demo.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

import com.example.demo.entity.AuthToken;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthFilter implements Filter {
    Set<Pattern> excludeUrlPatterns = new HashSet<>();
    private String authLocation;

    public AuthFilter(String authLocation, String... excludeUrlPatterns) {
        this.authLocation = authLocation;

        for (String urlPattern : excludeUrlPatterns) {
            registerExcludeUrl(urlPattern);
        }
    }

    public void registerExcludeUrl(String url) {
        excludeUrlPatterns.add(Pattern.compile(url));
    }

    public boolean containExcludeUrlPatterns(String url) {
        for (Pattern excludeUrl : excludeUrlPatterns) {
            boolean isMatched = excludeUrl.matcher(url).find();
            log.info("find url pattern: %s == %s".formatted(excludeUrl.pattern(), url));

            if (isMatched) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request instanceof HttpServletRequest httpRequest
                && response instanceof HttpServletResponse httpResponse) {

            HttpSession session = httpRequest.getSession();

            Optional<AuthToken> auth = AuthToken.fromSession(session);

            if (auth.isPresent()) {
                log.info("auth: " + auth.get());
            } else if (!containExcludeUrlPatterns(httpRequest.getRequestURI())) {
                httpResponse.sendRedirect(authLocation);
                return;
            }
        }

        chain.doFilter(request, response);
    }

}