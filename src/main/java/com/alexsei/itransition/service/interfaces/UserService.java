package com.alexsei.itransition.service.interfaces;

import com.alexsei.itransition.model.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    User getUserByEmail(String email);

    void updateAuthenticationType(String username, String oauth2ClientName);

    User getUserById(Long id);

    List<User> getAllUsers();

    User getUserByName(String name);

    void saveUser(User user);

    User getUserByAuthentication(Authentication authentication);

    public void incrementTotalLikes(Long userId);
}
