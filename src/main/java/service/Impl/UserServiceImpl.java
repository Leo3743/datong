package service.Impl;


import dao.UserDao;
import dao.impl.UserDaoImpl;
import domain.User;
import service.UserService;
import util.MailUtils;
import util.UuidUtil;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean regist(User user) {
        //1.查询用户
        User u = userDao.findByUsername(user.getUsername());
        if (u != null) {
            //用户名存在，注册失败
            return false;
        } else {
            //2.保存用户信息
            //设置激活码，唯一字符串
            user.setCode(UuidUtil.getUuid());
            //设置激活状态
            user.setStatus("N");
            userDao.save(user);
            //3.激活邮件发送
            //邮件内容
            String content = "<a href='http://localhost/datong/user/active?code=" + user.getCode() + "'>点击激活 北魏平城文化主题网站</a>";
            MailUtils.sendMail(user.getEmail(), content, "激活邮件");
            return true;
        }
    }

    /*
    激活用户
     */
    @Override
    public boolean acitve(String code) {
        //1.根据激活码查询用户
        User user = userDao.findByCode(code);
        if (user != null) {
            //2.激活用户修改status
            userDao.updateStatus(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
