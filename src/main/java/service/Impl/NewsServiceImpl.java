package service.Impl;


import dao.NewsDao;
import dao.impl.NewsDaoImpl;
import domain.News;
import domain.PageBean;
import service.NewsService;

import java.util.List;

public class NewsServiceImpl implements NewsService {
    NewsDao newsDao = new NewsDaoImpl();
    @Override
    public PageBean<News> pageQuery(int currentPage, int pageSize) {
        //封装PageBean
        PageBean<News> pb = new PageBean<>();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示条数
        pb.setPageSize(pageSize);
        //设置总记录数
        int totalCount = newsDao.findTotalCount();
        pb.setTotalCount(totalCount);

        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount/pageSize : (totalCount/pageSize) + 1;
        pb.setTotalPage(totalPage);
        //设置当前页显示的数据集合
        int start =(currentPage - 1)*pageSize;//开始的记录数
        List<News> list = newsDao.findByPage(start, pageSize);
        pb.setList(list);

        return pb;
    }

    @Override
    public News findOne(String nid) {
            //1.根据id去route表中查询route对象
            News route = newsDao.findOne(Integer.parseInt(nid));
            return route;

    }
}
