package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.servlet.http.HttpServletRequest;

@Getter
@Setter
@ToString
public class Page {
    private String searchText;//搜索的内容
    private String sortOrder;//排序的方式
    private Integer pageSize;//每页的数量
    private Integer pageNumber;//当前页码


    /**
     * 为什么不使用json的方式进行解析
     * 方式一:request输入流只能获取请求体中的数据,依赖程序自己解析
     * 方式二:request.getParameter可以获取url和请求体中的数据:k1=v1&k2=v2...
     * @param req
     * @return
     */
    public static Page parse(HttpServletRequest req) {
        Page p = new Page();
        p.searchText = req.getParameter("searchText");
        p.sortOrder = req.getParameter("sortOrder");
        p.pageSize = Integer.parseInt(req.getParameter("pageSize"));
        p.pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
        return p;
    }

}
