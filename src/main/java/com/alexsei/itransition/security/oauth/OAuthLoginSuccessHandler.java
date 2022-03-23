package com.alexsei.itransition.security.oauth;

import com.alexsei.itransition.model.AuthenticationType;
import com.alexsei.itransition.model.Role;
import com.alexsei.itransition.model.User;
import com.alexsei.itransition.repository.RoleRepository;
import com.alexsei.itransition.repository.UserRepository;
import com.alexsei.itransition.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Component
public class OAuthLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        CustomOAuth2User oauth2User = (CustomOAuth2User) authentication.getPrincipal();
        String oauth2ClientName = oauth2User.getOauth2ClientName();
        String username = oauth2User.getName();
        String email = oauth2User.getEmail();


        User userFromDb = userRepository.getUserByEmail(email);

        if(userFromDb!=null){
            userService.updateAuthenticationType(email,oauth2ClientName);
        } else{
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setAuthType(AuthenticationType.valueOf(oauth2ClientName.toUpperCase(Locale.ROOT)));
            newUser.setEmail(email);
            newUser.addRole(new Role());
            newUser.setEnabled(true);
            userService.saveUser(newUser);
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }

}
