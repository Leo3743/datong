package service;

import domain.PageBean;
import domain.Sense;

public interface SenseService {
    public PageBean<Sense> pageQuery(int currentPage, int pageSize);

    Sense findOne(String sid);
}
