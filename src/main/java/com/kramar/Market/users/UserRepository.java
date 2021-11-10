package com.kramar.Market.users;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findUserById(String id);
}
