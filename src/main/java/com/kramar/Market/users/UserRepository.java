package com.kramar.Market.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByNumber(String number);
    UserEntity findUserEntitiesByNumber(String number);
    UserEntity findUserById(Long id);
}
