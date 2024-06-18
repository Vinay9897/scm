package com.scm.scm.helpers;


import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.security.Principal;

public class Helper {

    public static String getEmailOfLoggedInUser(Authentication authentication) {

//        Principal principal = (Principal) authentication.getPrincipal();

            String username = "";
        //login in with OAuth
        if(authentication instanceof OAuth2AuthenticationToken){

            var oauth2AuthenticationToken = (OAuth2AuthenticationToken)authentication;
            var clientId = oauth2AuthenticationToken.getAuthorizedClientRegistrationId();

            var oauth2User = (OAuth2User) oauth2AuthenticationToken.getPrincipal();

            if(clientId.equalsIgnoreCase("google")){
                System.out.println("");
              username =  oauth2User.getAttribute("email").toString();
            }
            else if (clientId.equalsIgnoreCase("github")){

               username  = oauth2User.getAttribute("email") != null ? oauth2User.getAttribute("email").toString() : oauth2User.getAttribute("login").toString() + "@gmail.com";
            }
            else if(clientId.equalsIgnoreCase("facebook")){

            }
            else if(clientId.equalsIgnoreCase("linkedIn")){

            }
        }
        else{
            return username;
        }
        return username;
    }
}
