package com.alexsei.itransition.security.oauth;

import com.alexsei.itransition.model.Role;
import com.alexsei.itransition.model.User;
import com.alexsei.itransition.repository.RoleRepository;
import com.alexsei.itransition.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

public class CustomOAuth2User implements OAuth2User {
    private String oauth2ClientName;
    private OAuth2User oauth2User;
    private User user;

    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;

    public CustomOAuth2User(OAuth2User oauth2User, String oauth2ClientName, User user, RoleRepository roleRepository) {
        this.roleRepository=roleRepository;
        this.user=user;
        this.oauth2User = oauth2User;
        this.oauth2ClientName = oauth2ClientName;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = new HashSet<>();
        if(user != null){
            roles = user.getRoles();
        }
        else {
            Role role = roleRepository.findRoleByName("ROLE_USER");
//            if(role.getName()==null){
//                role.setName("ROLE_USER");
//            }
            roles.add(role);
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return authorities;
    }

    @Override
    public String getName() {
        System.out.println(oauth2User.<String>getAttribute("email"));
        return oauth2User.getAttribute("name");
    }

    public String getEmail() {
        return oauth2User.<String>getAttribute("email");
    }

    public String getOauth2ClientName() {
        return this.oauth2ClientName;
    }
}
