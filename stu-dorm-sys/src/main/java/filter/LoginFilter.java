package filter;

import model.Response;
import util.JSONUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

//过滤器:http请求的url匹配过滤器路径规则才会过滤(调用filter中的方法)
@WebFilter("/*")//所有路径都进行拦截
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        /**
         *页面的静态资源,后台服务
         *需要处理的敏感资源:
         * 1.首页:/public/page/main.html没有登录重定向到登录页面
         * 2.后端的服务资源:除登录接口/user/login以外 其他接口 没有登录 返回没有登录的json信息
         */
        //获取session信息
        HttpSession session = req.getSession(false);//没有session时 返回null
        if (session == null) {
            //没有登录
            //获取当前http请求的路径
            String uri = req.getServletPath();
            if ("/public/page/main.html".equals(uri)) {
                //首页没有登录重定向
                //重定向不能写相对路径 需要写全路径
                String schema = req.getScheme();//获取http
                String host = req.getServerName();//服务器域名或ip
                int port = req.getServerPort();//服务器端口号
                String contextPath = req.getContextPath();//项目部署名
                String basePath = schema + "://" + host + ":" + port + contextPath;
                res.sendRedirect(basePath + "/public/index.html");
                return;
            } else if (!"/user/login".equals(uri) && !uri.startsWith("/public/")
                    && !uri.startsWith("/static/")) {
                req.setCharacterEncoding("UTF-8");
                res.setCharacterEncoding("UTF-8");
                res.setContentType("application/json");
                Response r = new Response();
                r.setCode("301");//不是响应的状态吗 是响应体的字段
                r.setMessage("未授权的http请求");
                //输出流进行打印内容
                PrintWriter pw = res.getWriter();
                pw.println(JSONUtil.write(r));
                pw.flush();
                return;
            }
        }
        chain.doFilter(request, response);//过滤器向下调用再次过滤
    }

    @Override
    public void destroy() {

    }
}
