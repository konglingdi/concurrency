package com.mmal.concurrency.threadLocal;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
public class HttpFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Long id = Thread.currentThread().getId();
        ThreadLocalDemo.set(id);
        log.info("httpFilter执行了，id是{}",id);
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
