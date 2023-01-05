package com.example.demo;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.List;

@Route("")
class ChatView extends VerticalLayout {

    public ChatView(ChatService service) {
        var list = new MessageList();
        var userName = new TextField();
        var input = new MessageInput();

        input.addSubmitListener(e -> {
            service.send(e.getValue(), userName.getValue());
        });

        var ui = UI.getCurrent();
        service.join().subscribe(message -> {
            var items = new ArrayList<>(list.getItems());
            items.add(new MessageListItem(message.message(), message.time(), message.userName()));
            ui.access(() -> list.setItems(items));
        });


        var inputLayout = new HorizontalLayout(userName, input);
        inputLayout.setWidthFull();
        inputLayout.setAlignItems(Alignment.BASELINE);
        inputLayout.expand(input);
        userName.setPlaceholder("Username");

        setSizeFull();
        add(list, inputLayout);
        expand(list);
    }
}
