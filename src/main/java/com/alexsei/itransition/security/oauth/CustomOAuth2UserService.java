package com.alexsei.itransition.security.oauth;

import com.alexsei.itransition.model.User;
import com.alexsei.itransition.repository.RoleRepository;
import com.alexsei.itransition.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        String clientName = userRequest.getClientRegistration().getClientName();
        OAuth2User oAuth2User =  super.loadUser(userRequest);
        User user = userServiceImpl.getUserByEmail(oAuth2User.getAttribute("email"));
        System.out.println(user);
        return new CustomOAuth2User(oAuth2User, clientName, user,roleRepository);
    }
}
