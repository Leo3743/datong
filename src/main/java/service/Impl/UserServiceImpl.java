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
        //1.��ѯ�û�
        User u = userDao.findByUsername(user.getUsername());
        if (u != null) {
            //�û������ڣ�ע��ʧ��
            return false;
        } else {
            //2.�����û���Ϣ
            //���ü����룬Ψһ�ַ���
            user.setCode(UuidUtil.getUuid());
            //���ü���״̬
            user.setStatus("N");
            userDao.save(user);
            //3.�����ʼ�����
            //�ʼ�����
            String content = "<a href='http://localhost/datong/user/active?code=" + user.getCode() + "'>������� ��κƽ���Ļ�������վ</a>";
            MailUtils.sendMail(user.getEmail(), content, "�����ʼ�");
            return true;
        }
    }

    /*
    �����û�
     */
    @Override
    public boolean acitve(String code) {
        //1.���ݼ������ѯ�û�
        User user = userDao.findByCode(code);
        if (user != null) {
            //2.�����û��޸�status
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
