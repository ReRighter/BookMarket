package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class EncodeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        //将所有的请求的编码都设置为utf-8
        req.setCharacterEncoding("utf-8");
        chain.doFilter(req,resp);
    }

    @Override
    public void destroy() {

    }
}
