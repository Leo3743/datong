package service.Impl;

import dao.SenseDao;
import dao.SenseImgDao;
import dao.impl.SenseDaoImpl;
import dao.impl.SenseImgDaoImpl;
import domain.PageBean;
import domain.Sense;
import domain.SenseImg;
import service.SenseService;

import java.util.List;

public class SenseServiceImpl implements SenseService {
    SenseDao senseDao = new SenseDaoImpl();
    SenseImgDao senseImgDao = new SenseImgDaoImpl();
    @Override
    public PageBean<Sense> pageQuery(int currentPage, int pageSize) {
        //封装PageBean
        PageBean<Sense> pb = new PageBean<>();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示条数
        pb.setPageSize(pageSize);
        //设置总记录数
        int totalCount = senseDao.findTotalCount();
        pb.setTotalCount(totalCount);

        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount/pageSize : (totalCount/pageSize) + 1;
        pb.setTotalPage(totalPage);
        //设置当前页显示的数据集合
        int start =(currentPage - 1)*pageSize;//开始的记录数
        List<Sense> list = senseDao.findByPage(start, pageSize);
        pb.setList(list);

        return pb;
    }

    @Override
    public Sense findOne(String sid) {
        //1.根据id去sense表中查询sense对象
        Sense sense = senseDao.findOne(Integer.parseInt(sid));
        //2.根据sense的id查询图片集合信息
        List<SenseImg> senseImgList = senseImgDao.findBySid(sense.getSid());
        //2.2将集合设置到sense对象
        sense.setSenseImgList(senseImgList);
        return sense;
    }
}
