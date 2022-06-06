package com.bekzodkeldiyarov.springproject.service;

import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSessionImpl implements UserSessionService {

    private final SessionRegistry sessionRegistry;
    private final MyUserDetailsService myUserDetailsService;

    public UserSessionImpl(SessionRegistry sessionRegistry, MyUserDetailsService myUserDetailsService) {
        this.sessionRegistry = sessionRegistry;
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    public List<MyUserPrincipal> getAllActiveUsers() {
        List<Object> principals = sessionRegistry.getAllPrincipals();
        List<MyUserPrincipal> userDetailsList = new ArrayList<>();
        for (Object principal : principals) {
            if (principal instanceof MyUserPrincipal) {
                userDetailsList.add((MyUserPrincipal) principal);
            }
        }
        return userDetailsList;
    }

    public void expireSessionForNonActiveUsers() {
        List<Object> principals = sessionRegistry.getAllPrincipals();
        for (Object principal : principals) {
            if (principal instanceof MyUserPrincipal) {
                MyUserPrincipal myUserPrincipal = (MyUserPrincipal) myUserDetailsService.loadUserByUsername(((MyUserPrincipal) principal).getUsername());
                if (!myUserPrincipal.isEnabled()) {
                    List<SessionInformation> sessionInformations = sessionRegistry.getAllSessions(principal, false);
                    for (SessionInformation sessionInformation : sessionInformations) {
                        sessionInformation.expireNow();
                    }
                }
            }
        }
    }
}
