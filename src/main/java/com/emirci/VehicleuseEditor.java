package com.emirci;

import com.emirci.Model.VehicleUsage;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import java.util.Locale;

@SpringComponent
@UIScope
public class VehicleuseEditor extends VerticalLayout {


    private final VehicleUsageRepository repository;
    Binder<VehicleUsage> binder = new Binder<>(VehicleUsage.class);
    BeanValidationBinder<VehicleUsage> binderValidate = new BeanValidationBinder<>(VehicleUsage.class);
    //private VehicleUsage vehicleUsage;
    private int version = 0;
    private TextField driverName;
    private TextField licensePlate;
    private TextField destination;
    private DateTimeField outDate;
    private DateTimeField entDate;
    private TextField venicleOutKm;
    private TextField venicleEntKm;
    private TextArea comment;
    private Button save;
    private Button delete;


    @Autowired
    public VehicleuseEditor(VehicleUsageRepository repository) {

        this.repository = repository;

        HorizontalLayout footerButton = new HorizontalLayout();

        driverName = new TextField(Translator.toLocale("vehicle.driver"));
        driverName.setSizeFull();
        driverName.setRequiredIndicatorVisible(true);
        driverName.setMaxLength(30);
        driverName.addValueChangeListener(e -> {
            int len = e.getValue().length();
            driverName.setValue(e.getValue().toUpperCase(Locale.getDefault()));
        });
        driverName.setTabIndex(1);

        destination = new TextField(Translator.toLocale("vehicle.Destination"));
        destination.setSizeFull();
        destination.setValueChangeMode(ValueChangeMode.LAZY);
        destination.addValueChangeListener(e -> {
            int len = e.getValue().length();
            destination.setValue(e.getValue().toLowerCase());
        });
        destination.setTabIndex(2);

        licensePlate = new TextField(Translator.toLocale("vehicle.LicensePlate"));
        licensePlate.setSizeFull();
        licensePlate.setRequiredIndicatorVisible(true);
        licensePlate.setMaxLength(10);
        licensePlate.setComponentError(new UserError("Hatalı Değer"));
        licensePlate.setValueChangeMode(ValueChangeMode.LAZY);
        licensePlate.addValueChangeListener(e -> {
            int len = e.getValue().length();
            licensePlate.setValue(e.getValue().toUpperCase());
        });
        licensePlate.setTabIndex(3);




/*        Cookie driveNameCookie = getCookieByName("driverName");

        if (driveNameCookie != null) {
            String oldName = driveNameCookie.getValue();
            driveNameCookie.setValue(driverName.getValue());
            //Notification.show("Updated Drivename in cookie from " + oldName + " to " + driverName.getValue());
        } else {
            driveNameCookie = new Cookie("driverName", driverName.getValue());
            driveNameCookie.setComment("Cookie for strong the name of the user");
            //Notification.show("Strong name " + driverName + " in cookie");
        }

        driveNameCookie.setMaxAge(1200);
        driveNameCookie.setPath(VaadinService.getCurrentRequest().getContextPath());
        VaadinService.getCurrentResponse().addCookie(driveNameCookie);*/

        outDate = new DateTimeField(Translator.toLocale("vehicle.OutDate"));
        outDate.setSizeFull();
        outDate.setShowISOWeekNumbers(true);
        outDate.setRequiredIndicatorVisible(true);
        outDate.setTabIndex(4);


        entDate = new DateTimeField(Translator.toLocale("vehicle.EntDate"));
        entDate.setSizeFull();
        entDate.setShowISOWeekNumbers(true);
        entDate.setTabIndex(5);

        venicleOutKm = new TextField(Translator.toLocale("vehicle.OutKm"));
        venicleOutKm.setSizeFull();
        venicleOutKm.setMaxLength(6);
        venicleOutKm.setTabIndex(6);


        venicleEntKm = new TextField(Translator.toLocale("vehicle.EntKm"));
        venicleEntKm.setSizeFull();
        venicleEntKm.setMaxLength(6);
        venicleEntKm.setTabIndex(7);

        comment = new TextArea(Translator.toLocale("vehicle.Comment"));
        comment.setSizeFull();
        comment.setPlaceholder(Translator.toLocale("vehicle.placeholder"));
        comment.addValueChangeListener(e -> {
            int len = e.getValue().length();
            comment.setValue(e.getValue().toLowerCase());
        });
        comment.setTabIndex(8);

        save = new Button(Translator.toLocale("save"));
        save.setId("btnSave");
        save.setSizeFull();
        save.addStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setIcon(VaadinIcons.CHECK_CIRCLE);
        save.setDescription("Formda verilen bilgilerin veritabanına kayıt işlemni gerçekleştiri.");


        delete = new Button(Translator.toLocale("delete"));
        delete.setId("btnDelete");
        delete.setSizeFull();
        delete.addStyleName(ValoTheme.BUTTON_DANGER);
        delete.setIcon(VaadinIcons.CLOSE_CIRCLE);
        delete.setDescription("Seçili kaydın veritabanından tamamen siler.", ContentMode.HTML);


        footerButton.addComponents(save, delete);

        addComponents(driverName, destination, licensePlate, outDate, entDate, venicleOutKm, venicleEntKm, comment);
        addComponents(footerButton);

        setDefaultComponentAlignment(Alignment.TOP_LEFT);

        binder.bindInstanceFields(this);

        //binder.bind(driverName,VehicleUsage::getDriverName,VehicleUsage::setDriverName);

        binder.forField(venicleOutKm)
                .withValidator(new StringLengthValidator("Kilometre yanlış", 1, 9999999))
                .bind(VehicleUsage::getVenicleOutKm, VehicleUsage::setVenicleOutKm);


        //save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        save.addClickListener(e -> {
            try {

                repository.save(binder.getBean());
                save.focus();

                Notification notify = new Notification(
                        Translator.toLocale("notify.title.info"),
                        Translator.toLocale("successful"), Notification.Type.TRAY_NOTIFICATION);
                notify.setPosition(Position.TOP_RIGHT);
                notify.show(Page.getCurrent());

            } catch (Exception ex) {

                Notification notify = new Notification(
                        Translator.toLocale("notify.title.warning"),
                        Translator.toLocale("failed"), Notification.Type.ERROR_MESSAGE);
                notify.setPosition(Position.TOP_RIGHT);
                notify.show(Page.getCurrent());
            }

        });


        //delete.setClickShortcut(ShortcutAction.KeyCode.DELETE);
       //); //com.vaadin.ui.JavaScript.getCurrent()
        //Page.getCurrent().getJavaScript().execute("confirm('Are you sure?') ? true: false;")
        delete.addClickListener(e -> {
            if (binder.getBean().getId() != null) {
                repository.delete(binder.getBean());
                delete.focus();

            } else {
                Notification notify = new Notification(
                        Translator.toLocale("notify.title.info"),
                        Translator.toLocale("failed"), Notification.Type.ERROR_MESSAGE);
                notify.setPosition(Position.TOP_RIGHT);
                notify.show(Page.getCurrent());
            }
        });

        setVehicleUsage(binder.getBean());

    }

    public static Cookie getCookieByName(String name) {
        Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();

        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie;
            }
        }

        return null;
    }

    public static Cookie updateCookieValue(final String name, final String value) {
        // Create a new cookie
        Cookie cookie = getCookieByName(name);

        cookie.setValue(value);

        // Save cookie
        VaadinService.getCurrentResponse().addCookie(cookie);

        return cookie;
    }

    public static Cookie createCookie(final String name, final String value, final int maxAge) {
        // Create a new cookie
        Cookie cookie = new Cookie(name, value);

        cookie.setMaxAge(maxAge);

        // Set the cookie path.
        cookie.setPath(VaadinService.getCurrentRequest().getContextPath());

        // Save cookie
        VaadinService.getCurrentResponse().addCookie(cookie);

        return cookie;
    }

    public static void destroyCookieByName(final String name) {
        Cookie cookie = getCookieByName(name);

        if (cookie != null) {
            cookie.setValue(null);
            // By setting the cookie maxAge to 0 it will deleted immediately
            cookie.setMaxAge(0);
            cookie.setPath("/");
            VaadinService.getCurrentResponse().addCookie(cookie);
        }
    }

    public void setVehicleUsage(VehicleUsage selectedRow) {
        binder.setBean(selectedRow);
    }

    public void setChangeHandler(ChangeHandler h) {

        save.addClickListener(e -> {
            h.onChange();
        });
        delete.addClickListener(e -> h.onChange());

    }

    public interface ChangeHandler {
        void onChange();


    }
}
