package com.stegnin.sbrfclient.vaadin.ui;

import com.stegnin.sbrfclient.repository.AuthRepository;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * @author Alexandr Stegnin
 */

@Route(value = LoginView.ROUTE)
@PageTitle("LOGIN")
public class LoginView extends VerticalLayout {
    public static final String ROUTE = "login";

    private final AuthRepository authRepository;

    public LoginView(AuthRepository authRepository) {
        this.authRepository = authRepository;
        init();
    }

    private void init() {
        LoginForm component = new LoginForm();
        setAlignItems(Alignment.CENTER);
        component.addLoginListener(event -> {
            if (authenticated(event.getUsername(), event.getPassword())) {
                this.getUI().ifPresent(ui -> ui.navigate(MainView.ROUTE));
            } else {
                component.setError(true);
            }
        });
        add(component);
    }

    private boolean authenticated(String login, String password) {
        return authRepository.authenticate(login, password).isAuthenticated();
    }

}
