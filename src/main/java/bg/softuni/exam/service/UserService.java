package bg.softuni.exam.service;

import bg.softuni.exam.models.UserServiceModel;

public interface UserService {


    void registerModel(UserServiceModel map);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    String findByUsername(String username);
}
