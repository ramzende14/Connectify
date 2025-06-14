package com.connectify.connectify10.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connectify.connectify10.entity.User;

public interface UserRepository extends JpaRepository<User,String> {

    
    //extra methods db related
    //custom query methods
    //custom finder methods
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email,String password);

    


  

    



}
