package dao;

import domain.SenseImg;

import java.util.List;

public interface SenseImgDao {
    public List<SenseImg> findBySid(int sid);
}
