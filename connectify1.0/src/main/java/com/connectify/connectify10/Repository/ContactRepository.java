package com.connectify.connectify10.Repository;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.connectify.connectify10.entity.Contact;
import com.connectify.connectify10.entity.User;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {

    // Find the contact by user
    Page<Contact> findByUser(User user, Pageable pageable);

    // Custom query method
    @Query("SELECT c FROM Contact c WHERE c.user.id = :userId")
    List<Contact> findByUserId(@Param("userId") String userId);

  

    Page<Contact> findByUserAndNameContaining(User user,String nameKeyword,Pageable pageable);
    Page<Contact> findByUserAndEmailContaining(User user,String emailKeyword,Pageable pageable);

    Page<Contact> findByUserAndPhoneNumberContaining(User user,String phoneKeyword,Pageable pageable);
     // Count total contacts
     long count();

     // Count contacts by user ID
     @Query("SELECT COUNT(c) FROM Contact c WHERE c.user.id = :userId")
     long countByUserId(@Param("userId") String userId);

    

}
