package com.emirci;


//import com.vaadin.cdi.CDIUI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;


/**
 * Created by serdaremirci on 5/3/17.
 */
//@CDIUI("hello")
public class MainView extends CustomComponent implements View {



    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);

        Button button =new Button("Click me");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                layout.addComponent(new Label(" Main view clicked"));
            }
        });
        layout.addComponent(button);
        setCompositionRoot(layout);
    }
}
