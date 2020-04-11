package service;


import domain.User;

public interface UserService {
    boolean regist(User user);

    boolean acitve(String code);

    User login(User user);
}
