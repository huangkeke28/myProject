package servlet;

import model.Response;
import util.JSONUtil;
import util.ThreadLocalHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public abstract class AbstractBaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");//设置请求数据的编码格式
        resp.setCharacterEncoding("UTF-8");//设置响应数据的编码格式
        resp.setContentType("application/json");//设置响应数据格式

        //reponse类  基于子类process方法的返回值  包装在data这个字段里面去
        Response response = new Response();
        try {
            Object o = process(req,resp);
            response.setSuccess(true);
            response.setData(o);
            response.setCode("200");
            response.setMessage("操作成功");
            response.setTotal(ThreadLocalHolder.get().get());//获取当前线程设置的count变量
        } catch (Exception e) {
            response.setCode("500");
            response.setMessage(e.getMessage());
            //设置堆栈日志 输出流往另外一个输出流里面打
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            //把堆栈日志打到输出流里面 之后sw就有堆栈信息;
            e.printStackTrace(pw);
            String stackTrace = sw.toString();
            System.err.println(stackTrace);
            response.setStackTrace(stackTrace);
        } finally {
            ThreadLocalHolder.get().remove();//使用完ThreadLocal在线程结束前一定要删除变量 否则会引起内存泄漏
        }
        //输出流的打印
        PrintWriter pw = resp.getWriter();
        pw.println(JSONUtil.write(response));
        pw.flush();

    }

    public abstract Object process (HttpServletRequest req, HttpServletResponse resp) throws Exception;


}
