package com.example.demo;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Push
@Theme(variant = Lumo.DARK)
public class DemoApplication implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
