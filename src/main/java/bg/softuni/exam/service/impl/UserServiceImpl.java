package bg.softuni.exam.service.impl;

import bg.softuni.exam.entity.User;
import bg.softuni.exam.models.UserServiceModel;
import bg.softuni.exam.repository.UserRepository;
import bg.softuni.exam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerModel(UserServiceModel userServiceModel) {
        userRepository.save(modelMapper.map(userServiceModel, User.class));
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        return userRepository
                .findByUsernameAndPassword(username,password)
                .map(user->modelMapper.map(user,UserServiceModel.class))
                        .orElse(null);
    }

    @Override
    public String findByUsername(String username) {
        return userRepository
                .findByUsername(username);

    }
}
