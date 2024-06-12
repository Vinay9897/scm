package com.scm.scm.config;

import com.scm.scm.entities.Providers;
import com.scm.scm.entities.User;
import com.scm.scm.repositories.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.util.*;

import java.io.IOException;

import static com.scm.scm.helpers.AppConstants.ROLE_USER;


@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);

    public OAuthAuthenticationSuccessHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {



        var oauth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;

        String authorizedClientRegistrationid = oauth2AuthenticationToken.getAuthorizedClientRegistrationId();

        logger.info(authorizedClientRegistrationid);

        var oauth2User = (DefaultOAuth2User) oauth2AuthenticationToken.getPrincipal();

        oauth2User.getAttributes().forEach((key,value) ->{
            logger.info("{}:{}", key, value);
        });

        User newUser = new User();
        newUser.setProviderUserId(oauth2User.getName());
        newUser.setRoleList(List.of(ROLE_USER));
        newUser.setEmailVerified(true);
        newUser.setAbout("This account is created using google");

        if(authorizedClientRegistrationid.equalsIgnoreCase("google")){

            newUser.setName(oauth2User.getAttribute("name").toString());
            newUser.setEmailId(oauth2User.getAttribute("email").toString());
            newUser.setProfilePic(oauth2User.getAttribute("picture").toString());
            newUser.setProviderUserId(authorizedClientRegistrationid);
            newUser.setProvider(Providers.GOOGLE);
        }
       else if(authorizedClientRegistrationid.equalsIgnoreCase("github")){

           String email  = oauth2User.getAttribute("email") != null ? oauth2User.getAttribute("email").toString() : oauth2User.getAttribute("login").toString() + "@gmail.com";
           String picture = oauth2User.getAttribute("avatar_url").toString();
           String name = oauth2User.getAttribute("name").toString();
           String providedUserId = oauth2User.getName();

            newUser.setName(name);
            newUser.setEmailId(email);
            newUser.setProfilePic(picture);
            newUser.setProviderUserId(providedUserId);
            newUser.setProvider(Providers.GITHUB);
        }
       else if(authorizedClientRegistrationid.equalsIgnoreCase("linkedin")){

       }
       else if(authorizedClientRegistrationid.equalsIgnoreCase("facebook")){

       }





        userRepository.save(newUser);

        new DefaultRedirectStrategy().sendRedirect(request, response, "user/dashboard");
    }
}
