package dao;

import domain.Sense;

import java.util.List;

public interface SenseDao {

    /**
     * 查询纪录数
     */
    public int findTotalCount();

    /**
     * 查询分页条数
     */
    public List<Sense> findByPage(int start, int pageSize);

    Sense findOne(int sid);
}
