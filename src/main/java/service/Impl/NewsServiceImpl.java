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
        //��װPageBean
        PageBean<News> pb = new PageBean<>();
        //���õ�ǰҳ��
        pb.setCurrentPage(currentPage);
        //����ÿҳ��ʾ����
        pb.setPageSize(pageSize);
        //�����ܼ�¼��
        int totalCount = newsDao.findTotalCount();
        pb.setTotalCount(totalCount);

        //������ҳ�� = �ܼ�¼��/ÿҳ��ʾ����
        int totalPage = totalCount % pageSize == 0 ? totalCount/pageSize : (totalCount/pageSize) + 1;
        pb.setTotalPage(totalPage);
        //���õ�ǰҳ��ʾ�����ݼ���
        int start =(currentPage - 1)*pageSize;//��ʼ�ļ�¼��
        List<News> list = newsDao.findByPage(start, pageSize);
        pb.setList(list);

        return pb;
    }

    @Override
    public News findOne(String nid) {
            //1.����idȥroute���в�ѯroute����
            News route = newsDao.findOne(Integer.parseInt(nid));
            return route;

    }
}
