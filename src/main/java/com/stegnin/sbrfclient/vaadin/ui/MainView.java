package com.stegnin.sbrfclient.vaadin.ui;

import com.stegnin.sbrfclient.repository.AuthRepository;
import com.stegnin.sbrfclient.service.RestService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Alexandr Stegnin
 */

@Route(MainView.ROUTE)
public class MainView extends VerticalLayout {

    public static final String ROUTE = "main";

    private final AuthRepository authRepository;

    public MainView(@Autowired RestService restService, @Autowired AuthRepository authRepository) {
        this.authRepository = authRepository;
        VerticalLayout apiResponse = new VerticalLayout();
        apiResponse.setAlignItems(Alignment.CENTER);
        Button getResponseButton = new Button("GET API RESPONSE");
        getResponseButton.addClickListener(click -> {
            apiResponse.removeAll();
            apiResponse.add(restService.getUserInfo());
        });

        Button logoutButton = new Button("LOGOUT");
        logoutButton.addClickListener(event -> logout());

        add(
                new H1("USER INFO"),
                apiResponse,
                new HorizontalLayout(
                        getResponseButton,
                        logoutButton
                )
        );
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }

    private void logout() {
        authRepository.logout();
        this.getUI().ifPresent(ui -> ui.navigate(LoginView.class));
    }

}
