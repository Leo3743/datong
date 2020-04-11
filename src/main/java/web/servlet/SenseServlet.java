package web.servlet;

import domain.PageBean;
import domain.Sense;
import service.Impl.SenseServiceImpl;
import service.SenseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sense/*")
public class SenseServlet extends BaseServlet{
    SenseService senseService = new SenseServiceImpl();

    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        //2.处理参数

        int currentPage = 0;//当前页码，如果不传递，则默认为第一页
        if(currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else{
            currentPage = 1;
        }

        int pageSize = 0;//每页显示条数，如果不传递，默认每页显示9条记录
        if(pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else{
            pageSize = 9;
        }
        //3. 调用service查询PageBean对象
        PageBean<Sense> pb = senseService.pageQuery(currentPage, pageSize);
        //4. 将pageBean对象序列化为json，返回
        writeValue(pb,response);
    }

    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.接收id
        String sid = request.getParameter("sid");
        //2.调用service查询route对象
        Sense sense = senseService.findOne(sid);
        //3.转为json写回客户端
        writeValue(sense,response);
    }
}
