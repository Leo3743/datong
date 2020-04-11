package web.servlet;



import domain.News;
import domain.PageBean;
import service.Impl.NewsServiceImpl;
import service.NewsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/news/*")
public class NewsServlet extends BaseServlet {
    NewsService newsService = new NewsServiceImpl();


    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.���ܲ���
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        //2.�������

        int currentPage = 0;//��ǰҳ�룬��������ݣ���Ĭ��Ϊ��һҳ
        if(currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else{
            currentPage = 1;
        }

        int pageSize = 0;//ÿҳ��ʾ��������������ݣ�Ĭ��ÿҳ��ʾ5����¼
        if(pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else{
            pageSize = 5;
        }
        //3. ����service��ѯPageBean����
        PageBean<News> pb = newsService.pageQuery(currentPage, pageSize);
        //4. ��pageBean�������л�Ϊjson������
        writeValue(pb,response);
    }

    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.����id
        String nid = request.getParameter("nid");
        //2.����service��ѯroute����
        News news = newsService.findOne(nid);
        //3.תΪjsonд�ؿͻ���
        writeValue(news,response);
    }


}
