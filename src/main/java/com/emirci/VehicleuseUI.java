package com.emirci;

import com.emirci.Model.Vehicle;
import com.emirci.Translator;
import com.emirci.Model.VehicleUsage;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.ui.renderers.DateRenderer;
import com.vaadin.ui.renderers.LocalDateRenderer;
import com.vaadin.ui.renderers.LocalDateTimeRenderer;
import com.vaadin.ui.renderers.NumberRenderer;
import com.vaadin.ui.themes.ValoTheme;
import org.apache.tomcat.util.descriptor.LocalResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Collection;
import java.util.Locale;

@Scope("prototype")
@Title("Hareketler")
@SpringUI(path = "Vehicleuse")

public class VehicleuseUI extends UI {

    private static final Logger logger = LoggerFactory.getLogger(VehicleuseUI.class);

    @Autowired
    private MessageSource messageSource;

    VerticalLayout root = new VerticalLayout();
    HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
    Grid<VehicleUsage> grid;
    //private VehicleUsageService service = VehicleUsageService.getInstance();
    VehicleuseEditor editor;
    VehicleuseView view;

    private Collection<VehicleUsage> vehicleUsages;
    private VehicleUsageRepository repository = null;
    private VehicleUsageService service;
    ListDataProvider<VehicleUsage> listDataProvider;
    private Grid.Column[] columns;

    @Autowired
    public VehicleuseUI(VehicleUsageRepository repository, VehicleuseEditor editor) {
        this.repository = repository;
        this.editor = editor;

        this.grid = new Grid<VehicleUsage>(VehicleUsage.class);


    }

/*    private void deleteVehicleUsage(VehicleUsage vehicleUsage) {
       repository.delete(vehicleUsage);
        updateList();
        selectedDefault();
    }*/

/*    private void editSelectedVehicleUsage() {
        VehicleUsage selected = grid.asSingleSelect().getValue();

        if (selected != null) {
            editor.setVehicleUsage(repository.getOne(selected.getId()));
        } else {

            selectedDefault();
        }

    }*/

/*    private void saveVehicleUsage(VehicleUsage vehicleUsage) {

     repository.save(vehicleUsage);
        updateList();
        //grid.select(vehicleUsageNew);
    }*/

    private void selectedDefault() {
        VehicleUsage vehicleUsage = new VehicleUsage();
        Pageable sdd = new PageRequest(1,1, Sort.Direction.DESC,"licensePlate");

        //String lastKM = repository.findVehicleUsageByLicensePlateAndOrderByIdDesc("06 BEG 159",sdd);

        //logger.warn("KM : " +lastKM);
        grid.select(vehicleUsage);

    }

    @PostConstruct
    void load() {


        grid.asSingleSelect().addValueChangeListener(evt -> {
            editor.setVehicleUsage(evt.getValue());
            editor.setVisible(true);
        });


        editor.setChangeHandler(() -> {
            editor.setVisible(false);


            updateList();
        });

        updateList();


        loadGrid();


        logger.info("VehicleUsageUI Alanındasınız.");


    }


    @Override
    protected void init(VaadinRequest request) {

        updateList();

        //Page.getCurrent().setTitle(messageSource.getMessage("page.title", new Object[]{}, Locale.getDefault()));

        //setLocale(new Locale("tr", "TR"));
        //UI.getCurrent().setLocale(new Locale("tr"));
        Page.getCurrent().setTitle(Translator.toLocale("page.title"));

        DefErrorHandler();

        splitPanel.setSplitPosition(70, Unit.PERCENTAGE);
        splitPanel.setFirstComponent(grid);
        splitPanel.setSecondComponent(editor);
        splitPanel.setSizeFull();

        root.setSizeFull();

        //root.addComponents(filterdriveName);
        root.addComponents(splitPanel);
        //view.vehicleUsages = repository.findAll();
        //root.addComponent(view);
        setContent(root);
    }


    private void loadGrid() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

        LocalDateTimeRenderer renderer = new LocalDateTimeRenderer(() -> DateTimeFormatter
                .ofPattern("dd.MM.yyyy hh:mm"));

        grid.setSizeFull();

        //grid.setColumns("id","licensePlate", "driverName", "outDate", "entDate", "venicleOutKm", "venicleEntKm", "comment");

        //SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:MM", new Locale("tr"));
        //DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:MM",new Locale("tr"));

        grid.setColumns("id");
        grid.addColumn(VehicleUsage::getDriverName).setCaption(Translator.toLocale("vehicle.driver")).setSortProperty("DriverName").setId("DriverName");
        grid.addColumn(VehicleUsage::getLicensePlate).setCaption(Translator.toLocale("vehicle.LicensePlate")).setId("LicensePlate");


//        Grid.Column<VehicleUsage, LocalDateTime> dateTimeFieldColumn = grid.addColumn(VehicleUsage::getEntDate);
//        dateTimeFieldColumn.setCaption("DÖ..NÜŞ ZAMANI");

        Grid.Column<VehicleUsage, LocalDateTime> OutDate =
                grid.addColumn(
                        VehicleUsage::getOutDate)
                        .setRenderer(new LocalDateTimeRenderer())
                        .setCaption(Translator.toLocale("vehicle.OutDate"))
                        .setId("OutDate");


        Grid.Column<VehicleUsage, LocalDateTime> EntDate =
                grid.addColumn(
                        VehicleUsage::getEntDate)
                        .setRenderer(new LocalDateTimeRenderer())
                        .setCaption(Translator.toLocale("vehicle.EntDate"))
                        .setId("EntDate");

        grid.addColumn(VehicleUsage::getVenicleOutKm).setCaption(Translator.toLocale("vehicle.OutKm")).setId("VenicleOutKm");
        grid.addColumn(VehicleUsage::getVenicleEntKm).setCaption(Translator.toLocale("vehicle.EntKm")).setId("VenicleEntKm");
        grid.addColumn(VehicleUsage::getDestination).setCaption(Translator.toLocale("vehicle.Destination")).setId("Destination");
        grid.addColumn(VehicleUsage::getComment).setCaption(Translator.toLocale("vehicle.Comment")).setId("Comment");
        grid.setLocale(new Locale("tr"));

        //setColumnFiltering(grid,true);
        addGridFilters(true);

        //setColumnFiltering(grid, "DriverName", true);
        //setColumnFiltering(grid, "LicensePlate", true);

        //footerRow.getCell("venicleOutKm").setText("avg:");


        grid.getDataProvider().refreshAll();
        grid.setColumnReorderingAllowed(true);

        grid.getColumns().stream().forEach(column -> column.setHidable(true));

    }

    private void addGridFilters(boolean filtering) {

        final HeaderRow filterRow = grid.appendHeaderRow();

        if (filtering) {


            for (final Grid.Column<VehicleUsage, ?> column : grid.getColumns()) {
                final HeaderCell headerCell = filterRow.getCell(column);

                if ("DriverName".equals(column.getId())) {
                    headerCell.setComponent(createFilterTextField(column));
                } else if ("LicensePlate".equals(column.getId())) {
                    headerCell.setComponent(createFilterTextField(column));
                } else if ("OutDate".equals(column.getId())) {
                    headerCell.setComponent(createFilterTextField(column));
                } else if ("EntDate".equals(column.getId())) {
                    headerCell.setComponent(createFilterTextField(column));
                } else if ("Destination".equals(column.getId())) {
                    headerCell.setComponent(createFilterTextField(column));
                } else if ("Comment".equals(column.getId())) {
                    headerCell.setComponent(createFilterTextField(column));
                }
            }
        }
    }

    private TextField createFilterTextField(final Grid.Column<VehicleUsage, ?> column) {

        TextField filter = new TextField();
        filter.setId("filter_" + column.getId());
        filter.setWidth("100%");
        filter.addStyleName(ValoTheme.TEXTFIELD_TINY);
        filter.setPlaceholder(Translator.toLocale("vehicle.filter"));

        filter.addValueChangeListener(e -> {
            //final ValueProvider<VehicleUsage, String> valueProvider = (ValueProvider<VehicleUsage, String>) column.getParent().getDataProvider();

            listDataProvider.clearFilters();
            ListDataProvider<VehicleUsage> listDataProviderTemp;


            if ("DriverName".equals(column.getId())) {
                listDataProvider.addFilter(VehicleUsage::getDriverName, evt -> {

                    if (evt == null) {
                        return false;
                    }
                    String valueLower = evt.toLowerCase();
                    String filterLover = e.getValue().toLowerCase();
                    return valueLower.contains(filterLover);

                    //return pevt.toLowerCase().contains(event.getValue().toLowerCase());
                });

                listDataProviderTemp = listDataProvider;

            } else if ("LicensePlate".equals(column.getId())) {
                listDataProvider.addFilter(VehicleUsage::getLicensePlate, evt -> {

                    if (evt == null) {
                        return false;
                    }
                    String valueLower = evt.toLowerCase();
                    String filterLover = e.getValue().toLowerCase();
                    return valueLower.contains(filterLover);

                    //return pevt.toLowerCase().contains(event.getValue().toLowerCase());
                });

                listDataProviderTemp = listDataProvider;


            } else if ("OutDate".equals(column.getId())) {
                listDataProvider.addFilter(VehicleUsage::getOutDate, evt -> {

                    if (evt == null) {
                        return false;
                    }

                    String valueLower = evt.toString();
                    return valueLower.contains(evt.toString());

                    //return pevt.toLowerCase().contains(event.getValue().toLowerCase());
                });

                listDataProviderTemp = listDataProvider;

            } else if ("EntDate".equals(column.getId())) {
                listDataProvider.addFilter(VehicleUsage::getEntDate, evt -> {

                    if (evt == null) {
                        return false;
                    }
                    String valueLower = evt.toString();
                    return valueLower.contains(evt.toString());

                    //return pevt.toLowerCase().contains(event.getValue().toLowerCase());
                });

                listDataProviderTemp = listDataProvider;

            } else if ("Destination".equals(column.getId())) {
                listDataProvider.addFilter(VehicleUsage::getDestination, evt -> {

                    if (evt == null) {
                        return false;
                    }
                    String valueLower = evt.toLowerCase();
                    String filterLover = e.getValue().toLowerCase();
                    return valueLower.contains(filterLover);

                    //return pevt.toLowerCase().contains(event.getValue().toLowerCase());
                });

                listDataProviderTemp = listDataProvider;

            } else if ("Comment".equals(column.getId())) {
                listDataProvider.addFilter(VehicleUsage::getComment, evt -> {

                    if (evt == null) {
                        return false;
                    }
                    String valueLower = evt.toLowerCase();
                    String filterLover = e.getValue().toLowerCase();
                    return valueLower.contains(filterLover);

                    //return pevt.toLowerCase().contains(event.getValue().toLowerCase());
                });

                listDataProviderTemp = listDataProvider;

            }


        });

        return filter;
    }

    private void setColumnFiltering(Grid grid, boolean filtering) {
        //https://vaadin.com/forum/#!/thread/14870720/15023336

        HeaderRow filterRow = grid.appendHeaderRow();


        if (filtering && filterRow == null) {
            filterRow = grid.appendHeaderRow();


            TextField filteringField = getColumnFilterField();


            filteringField.addValueChangeListener(e -> {

                listDataProvider.setFilter(VehicleUsage::getDriverName, evt -> {
                    if (evt == null) {
                        return false;
                    }
                    String valueLower = evt.toLowerCase();
                    String filterLover = e.getValue().toLowerCase();
                    return valueLower.contains(filterLover);
                });
            });

            filterRow.getCell("DriverName").setComponent(filteringField);

        } else if (!filtering && filterRow != null) {
            listDataProvider.clearFilters();
            grid.removeHeaderRow(filterRow);

            filterRow = null;
        }
    }

    private TextField getColumnFilterField() {
        TextField filter = new TextField();
        filter.setWidth("100%");
        filter.addStyleName(ValoTheme.TEXTFIELD_TINY);
        filter.setPlaceholder("Filter");
        return filter;
    }


    private void DefErrorHandler() {
        UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {

            @Override
            public void error(com.vaadin.server.ErrorEvent event) {


                String cause = "";
                cause = "\"<b> Sunucu Hatası : </b>(Hata kodu : 500)<br/>\"";

                for (Throwable t = event.getThrowable(); t != null; t = t.getCause())

                    if (t.getCause() == null)
                        cause += t.getClass().getName() + "<br/>";


                root.addComponent(new Label(cause, ContentMode.HTML));

                doDefault(event);
            }
        });
    }

    private void updateList() {
        vehicleUsages = repository.findAll();

        listDataProvider = DataProvider.ofCollection(vehicleUsages);
        listDataProvider.setSortOrder(VehicleUsage::getId, SortDirection.DESCENDING);

        grid.setDataProvider(listDataProvider);
        //grid.setItems(vehicleUsages);


        logger.info(String.valueOf(vehicleUsages.size()));
        selectedDefault();


    }

    public Grid.Column[] getColumns() {
        return columns;
    }
}
