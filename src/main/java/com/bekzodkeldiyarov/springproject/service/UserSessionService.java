package com.bekzodkeldiyarov.springproject.service;

import java.util.List;

public interface UserSessionService {
    List<MyUserPrincipal> getAllActiveUsers();

    void expireSessionForNonActiveUsers();
}
