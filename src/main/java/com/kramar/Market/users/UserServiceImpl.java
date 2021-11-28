package com.kramar.Market.users;

import com.kramar.Market.exception.UserAlreadyExistException;
import com.kramar.Market.exception.UserNotExistException;
import com.kramar.Market.rest.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity getUserBy(Long id) throws UserNotExistException {
        if (userRepository.existsById(id)) {
            throw new UserNotExistException("User Not exist");
        }
        return userRepository.findUserById(id);
    }

    @Override
    public void addUser(User user) throws UserAlreadyExistException {
        if(userRepository.existsByNumber(user.getPhoneNumber())) {
            throw  new UserAlreadyExistException("user already exist");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setName(user.getName());
        userEntity.setNumber((user.getPhoneNumber()));
        userRepository.saveAndFlush(userEntity);
    }
}
