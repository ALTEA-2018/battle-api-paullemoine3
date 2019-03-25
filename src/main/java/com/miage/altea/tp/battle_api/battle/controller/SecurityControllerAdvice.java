package com.miage.altea.tp.battle_api.battle.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class SecurityControllerAdvice {

    @ModelAttribute("user")
    public Object principal(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object o = new Object();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            o = authentication.getPrincipal();
        }
        return o;
    }
}