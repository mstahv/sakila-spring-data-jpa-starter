package com.example.demo;

import com.example.demo.vaadinui.Layout;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.ServiceInitEvent;
import in.virit.entityexplorer.EntityEditorView;
import in.virit.entityexplorer.EntityExplorer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		// TODO either configure your "production database" or start the TestDemoApplication class from the test sources
		// that automatically starts a Docker container with DB configured for testing and development
		SpringApplication.run(DemoApplication.class, args);
	}

	@EventListener
	void registerEERoutes(ServiceInitEvent evt) {
		RouteConfiguration configuration = RouteConfiguration
				.forApplicationScope();
		if(!configuration.getRoute("entityexplorer").isPresent())  {
			configuration.setRoute("entityexplorer", EntityExplorer.class, Layout.class);
			configuration.setRoute("entityeditor", EntityEditorView.class, Layout.class);
		};
	}
}
