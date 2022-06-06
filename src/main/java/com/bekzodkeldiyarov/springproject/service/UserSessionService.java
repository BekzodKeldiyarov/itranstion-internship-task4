package com.bekzodkeldiyarov.springproject.service;

import java.util.List;

public interface UserSessionService {
    void expireSessionForNonActiveUsers();
}
