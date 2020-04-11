package dao;

import domain.News;

import java.util.List;

public interface NewsDao {

    /**
     * 查询纪录数
     */
    public int findTotalCount();

    /**
     * 查询分页条数
     */
    public List<News> findByPage(int start, int pageSize);

    News findOne(int nid);
}
