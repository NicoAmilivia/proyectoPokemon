package es.cesur.progprojectpok.services;

import es.cesur.progprojectpok.daos.UserDAO;
import es.cesur.progprojectpok.model.User;

import java.util.List;

public class UserService {

    private UserDAO userDao;

    public UserService() {
        userDao = new UserDAO();
    }

    public User findUser(int id) {
        return userDao.findById(id);
    }

    public Boolean saveUser(User user) {
        return userDao.save(user);
    }

/*    public void deleteUser(User user) {
        userDao.delete(user);
    }

    public void updateUser(User user) {
        userDao.update(user);
    }

    public List<User> findAllUsers() {
        return userDao.findAll();
    }

    public User findAutoById(int id) {
        return userDao.findAutoById(id);
    }*/

    public User findByUsernameAndPassword(String username, String password){
        return userDao.findByUsernameAndPassword(username,password);
    }

}
