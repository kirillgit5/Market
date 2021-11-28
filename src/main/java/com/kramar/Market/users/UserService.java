package com.kramar.Market.users;

import com.kramar.Market.exception.UserAlreadyExistException;
import com.kramar.Market.exception.UserNotExistException;
import com.kramar.Market.rest.dto.User;

public interface UserService {
    void addUser(User user) throws UserAlreadyExistException;
    UserEntity getUserBy(Long id) throws UserNotExistException ;
}
