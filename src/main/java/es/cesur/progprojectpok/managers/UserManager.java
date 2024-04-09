package es.cesur.progprojectpok.managers;

import es.cesur.progprojectpok.model.User;
import es.cesur.progprojectpok.services.UserService;

public class UserManager {

    private UserService userService;

    public UserManager(){
        userService = new UserService();
    }

    public Boolean login(String username, String password){
        Boolean result = false;
        User user = userService.findByUsernameAndPassword(username,password);
        if(user != null){
            return true;
        }
        return result;
    }

    public Boolean signIn(User user){
        Boolean result = false;
        result = userService.saveUser(user);
        if(user != null){
            return true;
        }
        return result;
    }
}
