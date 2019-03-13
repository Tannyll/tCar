package com.emirci;

import com.vaadin.annotations.Title;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.servlet.annotation.WebServlet;
import java.util.Locale;

@SpringUI() // At Spring servlet root
public class HomeUI extends UI {

    private static final Logger logger = LoggerFactory.getLogger(HomeUI.class);

    @PostConstruct
    void load() {

        setLocale(new Locale("tr", "TR"));

        logger.info("HomeUI Alanındasınız.");

    }

    @Override
    protected void init(VaadinRequest request) {

        setLocale(new Locale("tr", "TR"));

        // The root of the component hierarchy
        VerticalLayout content = new VerticalLayout();
        content.setSizeFull(); // Use entire window
        setContent(content);   // Attach to the UI

        final Label selection = new Label("-");
        MenuBar menuBar = new MenuBar();
        MenuBar.MenuItem previous = null;

        MenuBar.Command vehicleuse = new MenuBar.Command() {

            @Override
            public void menuSelected(MenuBar.MenuItem menuItem) {

                Page.getCurrent().open("Vehicleuse", null);

                //selection.setValue("Arac kullanımları" + selectedItem.getText() + " from menu.");

            }
        };

        MenuBar.Command commandVehicle = new MenuBar.Command() {

            @Override
            public void menuSelected(MenuBar.MenuItem menuItem) {

                Page.getCurrent().open("Vehicle", null);

            }
        };

        getPage().setTitle(Translator.toLocale("app"));

        MenuBar.MenuItem prs = menuBar.addItem(Translator.toLocale("page.title"), VaadinIcons.GRID, vehicleuse);
        prs.setEnabled(true);
        //prs.addItem("Ekle", VaadinIcons.PLUS, vehicleuse);
        //prs.addSeparator();


        MenuBar.MenuItem arc = menuBar.addItem(Translator.toLocale("page.settings"), VaadinIcons.CAR, commandVehicle);
        arc.setEnabled(false);
        //arc.addItem("Ekle", null);
        //prs.addSeparator();


        MenuBar.MenuItem abo = menuBar.addItem(Translator.toLocale("page.about"), VaadinIcons.EXCLAMATION_CIRCLE_O, commandVehicle);
        abo.setEnabled(false);
        //arc.addItem("Ekle", null);
        //prs.addSeparator();

        MenuBar.MenuItem sts = menuBar.addItem(Translator.toLocale("page.settings"), VaadinIcons.COG_O, commandVehicle);
        sts.setEnabled(false);
        //arc.addItem("Ekle", null);
        //prs.addSeparator();


        // Add some component
        content.addComponents(menuBar, new Label("Araç hareket takip sistemine hoşgeldiniz. Bu sistem ile garajınızda bulunan araçların kimler tarafından hangi zamanlarda kullanıldığının takibini ve raporlamasını yapabileceksiniz."));

        // Layout inside layout
        HorizontalLayout hor = new HorizontalLayout();
        hor.setSizeFull(); // Use all available space
        content.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        content.setMargin(true);
        content.setMargin(true);

        content.addComponent(hor);
        content.setExpandRatio(hor, 1); // Expand to fill
    }

    @WebServlet(name = "vaadinServlet", urlPatterns = {"/iHeyo/*", "/VAADIN/*"})
    public class MySpringServlet extends SpringVaadinServlet {

    }
}
