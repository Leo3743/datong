package dao;


import domain.User;

public interface UserDao {
    /**
     * �����û�����ѯ�û�
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * �����û�
     * @param user
     */
    public void save(User user);

    User findByCode(String code);

    void updateStatus(User user);

    User findByUsernameAndPassword(String username, String password);
}
