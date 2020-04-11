package dao.impl;

import dao.NewsDao;
import domain.News;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

public class NewsDaoImpl implements NewsDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalCount() {
        String sql = "select count(*) from tab_news";
        return template.queryForObject(sql,Integer.class);
    }

    @Override
    public List<News> findByPage(int start, int pageSize) {
        String sql = "select * from tab_news limit ? , ?";
        return template.query(sql,new BeanPropertyRowMapper<News>(News.class),start,pageSize);
    }

    @Override
    public News findOne(int nid) {
        String sql = "select * from tab_news where nid = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<News>(News.class),nid);
    }
}
