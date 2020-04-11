package dao.impl;

import dao.SenseImgDao;
import domain.SenseImg;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

public class SenseImgDaoImpl implements SenseImgDao {

    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<SenseImg> findBySid(int sid) {
        String sql = "select * from tab_sense_img where sid = ?";
        return template.query(sql,new BeanPropertyRowMapper<SenseImg>(SenseImg.class),sid);

    }
}
