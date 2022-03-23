package com.alexsei.itransition.repository;


import com.alexsei.itransition.model.AuthenticationType;
import com.alexsei.itransition.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByEmail(String email);

    User getUserById(Long id);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User getUserByUsername(@Param("username") String username);

    @Modifying
    @Query("UPDATE User u SET u.authType = ?2 WHERE u.email = ?1")
    void updateAuthenticationType(String email, AuthenticationType authType);
}
