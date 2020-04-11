package dao.impl;

import dao.SenseDao;
import domain.Sense;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

public class SenseDaoImpl implements SenseDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public int findTotalCount() {
        String sql = "select count(*) from tab_sense";
        return template.queryForObject(sql,Integer.class);
    }

    @Override
    public List<Sense> findByPage(int start, int pageSize) {
        String sql = "select * from tab_sense limit ? , ?";
        return template.query(sql,new BeanPropertyRowMapper<Sense>(Sense.class),start,pageSize);
    }

    @Override
    public Sense findOne(int sid) {
        String sql = "select * from tab_sense where sid = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Sense>(Sense.class),sid);
    }
}
