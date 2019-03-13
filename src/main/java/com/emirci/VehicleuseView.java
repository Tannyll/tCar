package com.emirci;

import com.emirci.Model.VehicleUsage;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Responsive;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.FooterRow;
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Collection;
import java.util.Locale;

/**
 * Created by serdaremirci on 5/3/17.
 */

@ViewScope
public class VehicleuseView extends CssLayout implements View {
    Grid<VehicleUsage> grid;
    HeaderRow filteringHeader;

    ListDataProvider<VehicleUsage> dataProvider;
    Collection<VehicleUsage> vehicleUsages;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    public VehicleuseView() {
        addStyleName(ValoTheme.PANEL_BORDERLESS);
        setSizeFull();
        //MainUIEventBus.register(this);

        VerticalLayout root = new VerticalLayout();
        root.setMargin(true);
        addComponent(root);
        Responsive.makeResponsive(root);
        root.addComponent(buildHeader());
        root.addComponent(createGrid());
        //root.setComponentAlignment(tabs, Alignment.TOP_CENTER);

    }

    private Component buildHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.setSpacing(true);

        Label titleLabel = new Label("sadasdasdj kasdhjas kdhjas");
        titleLabel.setSizeUndefined();
        titleLabel.addStyleName(ValoTheme.LABEL_H1);
        titleLabel.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        titleLabel.addStyleName(ValoTheme.LABEL_BOLD);
        header.addComponent(titleLabel);

        return header;
    }

    private void setColumnFiltering(boolean filtered) {
        if (filtered && filteringHeader == null) {

            filteringHeader = grid.appendHeaderRow();

            String columnId = "driverName";
            TextField filter = getColumnFilterField(columnId);
            filteringHeader.getCell(columnId).setComponent(filter);
            filteringHeader.getCell(columnId).setStyleName("filter-header");
        } else if (!filtered && filteringHeader != null) {
            grid.removeHeaderRow(filteringHeader);
            filteringHeader = null;
        }
    }

    private TextField getColumnFilterField(final Object columnId) {
        TextField filterText = new TextField();


        filterText.addValueChangeListener(e -> {
            dataProvider.setFilter(VehicleUsage::getDriverName, name -> {
                String namelover = name == null ? ""
                        : name.toLowerCase(Locale.ENGLISH);

                String filterLover = e.getValue()
                        .toLowerCase(Locale.ENGLISH);
                return namelover.contains(filterLover);
            });
        });

        filterText.setWidth("100%");
        filterText.addStyleName(ValoTheme.TEXTFIELD_TINY);
        filterText.setPlaceholder("Filter");
        return filterText;
    }


    private Component createGrid() {
        dataProvider = DataProvider.ofCollection(vehicleUsages);
        dataProvider.setSortOrder(VehicleUsage::getId, SortDirection.DESCENDING);


        grid.setColumns("id");
        grid.addColumn(VehicleUsage::getDriverName).setCaption(Translator.toLocale("vehicle.driver")).setSortProperty("driverName");
        grid.addColumn(VehicleUsage::getLicensePlate).setCaption("PLAKA.");

        //Grid.Column<VehicleUsage, LocalDateTime> dateTimeFieldColumn = grid.addColumn(VehicleUsage::getEntDate);
        //dateTimeFieldColumn.setCaption("DÖ..NÜŞ ZAMANI");

        grid.addColumn(VehicleUsage::getOutDate).setCaption("ÇIKIŞ ZAMANI");
        grid.addColumn(VehicleUsage::getEntDate).setCaption("DÖNÜŞ ZAMANI");
        grid.addColumn(VehicleUsage::getVenicleOutKm).setCaption("ÇIKIŞ KM");
        grid.addColumn(VehicleUsage::getVenicleEntKm).setCaption("DÖNÜŞ KM");
        grid.addColumn(VehicleUsage::getComment).setCaption("AÇIKLAMA");
        grid.setFrozenColumnCount(1);
        setColumnFiltering(true);

        FooterRow footerRow = grid.appendFooterRow();
        HeaderRow headerRow = grid.appendHeaderRow();


        //footerRow.getCell("venicleOutKm").setText("avg:");

        grid.getDataProvider().refreshAll();
        grid.setColumnReorderingAllowed(true);

        grid.getColumns().stream().forEach(column -> column.setHidable(true));

        grid.setDataProvider(dataProvider);

        return grid;

    }
}
