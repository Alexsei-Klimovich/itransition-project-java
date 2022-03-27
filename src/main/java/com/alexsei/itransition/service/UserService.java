package com.alexsei.itransition.service;

import com.alexsei.itransition.model.AuthenticationType;
import com.alexsei.itransition.model.User;
import com.alexsei.itransition.repository.UserRepository;
import com.alexsei.itransition.security.oauth.CustomOAuth2User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }

    public void updateAuthenticationType(String username, String oauth2ClientName) {
        AuthenticationType authType = AuthenticationType.valueOf(oauth2ClientName.toUpperCase());
        userRepository.updateAuthenticationType(username, authType);
        System.out.println("Updated user's authentication type to " + authType);
    }

    public User getUserById(Long id){
        return userRepository.getUserById(id);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserByName(String name){
        return userRepository.getUserByUsername(name);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public User getUserByAuthentication(Authentication authentication){
        CustomOAuth2User oauth2User = (CustomOAuth2User) authentication.getPrincipal();
        return userRepository.getUserByEmail(oauth2User.getEmail());
    }

    public void incrementTotalLikes(Long userId){
        User userFromDb = getUserById(userId);
        userFromDb.setTotalLikes(userFromDb.getTotalLikes()+1);
        userRepository.save(userFromDb);
    }


}
