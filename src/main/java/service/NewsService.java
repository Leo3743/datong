package service;


import domain.News;
import domain.PageBean;

public interface NewsService {
    public PageBean<News> pageQuery(int currentPage, int pageSize);

    News findOne(String nid);
}
