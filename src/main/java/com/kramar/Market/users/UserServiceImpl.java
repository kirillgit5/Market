package com.kramar.Market.users;

import com.kramar.Market.exception.UserAlreadyExistException;
import com.kramar.Market.exception.UserNotExistException;
import com.kramar.Market.rest.dto.User;
import com.kramar.Market.rest.dto.order.OrderAdapter;
import com.kramar.Market.rest.dto.userUI.UserUIAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<UserUIAdapter> getUsers() {
        return userRepository.findAll().stream().map(entity -> {
            UserUIAdapter adapter = new UserUIAdapter();
            adapter.setPhoneNumber(entity.getNumber());
            adapter.setName(entity.getName());
            return  adapter;
        }).collect(Collectors.toList());
    }
}
