package com.example.demo;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.security.AuthenticationContext;
import jakarta.annotation.security.PermitAll;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Route("")
@PermitAll
class ChatView extends VerticalLayout {

    public ChatView(ChatService service, AuthenticationContext authContext) {

        var logout = new Button("Log out", e -> authContext.logout());
        var list = new MessageList();
        var input = new MessageInput();
        var ui = UI.getCurrent();

        setSizeFull();
        add(logout, list, input);
        expand(list);
        input.setWidthFull();
        setAlignSelf(Alignment.END, logout);

        input.addSubmitListener(e -> {
            service.send(e.getValue(), authContext.getPrincipalName().orElse("Anonymous"));
        });

        service.join().subscribe(message -> {
            var items = new ArrayList<>(list.getItems());
            items.add(new MessageListItem(message.message(), message.time(), message.userName()));
            ui.access(() -> list.setItems(items));
        });

    }
}
