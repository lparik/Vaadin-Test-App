package lparikka.vaadin.testapp.ui;

import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class PlateItem extends Panel{

    private String panelName;

    public String getPanelName() {
        return panelName;
    }

    public void setPanelName(String panelName) {
        this.panelName = panelName;
    }

    public PlateItem(String description, String labelText){

        this.panelName = panelName;
        VerticalLayout vLayout = new VerticalLayout();
        setContent(vLayout);
        vLayout.setMargin(true);
        vLayout.setSizeFull();
        Label lblA = new Label(labelText);
        lblA.setWidth("30%");
        this.setDescription(description);

        vLayout.addComponent(lblA);

    }

}