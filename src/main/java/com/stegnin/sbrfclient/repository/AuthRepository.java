package com.stegnin.sbrfclient.repository;

import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@VaadinSessionScope
public interface AuthRepository {

    Authentication authenticate(String login, String password);

    void logout();
}
