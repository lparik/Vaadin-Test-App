package lparikka.vaadin.testapp.ui;

import lparikka.vaadin.testapp.ui.PlateItem;
import com.vaadin.event.Transferable;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.ui.Component;
import com.vaadin.ui.DragAndDropWrapper;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import lparikka.vaadin.testapp.backend.Sample;

public class PlateView implements DropHandler {

    private static final Logger logger  = Logger.getLogger(PlateView.class.getName());
    private static final String[] ROWLABELS = { "A", "B", "C", "D", "E", "F", "G", "H" };
    private static final int PLATE_COLUMN_COUNT = 13;
    private static final int PLATE_ROW_COUNT = 9;
    private Map<String, Sample> samples;
    private GridLayout platePanelLayout;
    private Label plateItemDetailsLabel;

    public PlateView(Map<String, Sample> samples) {
        this.samples = samples;

    }

    public Component constructPlate() {

        VerticalLayout layout = new VerticalLayout();
        platePanelLayout = new GridLayout(13, 9);
        platePanelLayout.setStyleName("csstag");

        for (int row = 0; row < PLATE_ROW_COUNT; row++) {

            for (int column = 0; column < PLATE_COLUMN_COUNT; column++) {

                if (row == 0 && column == 0) {

                    PlateItem emptyPlateItem = new PlateItem("", "");
                    emptyPlateItem.setSizeFull();
                    platePanelLayout.addComponent(emptyPlateItem);

                }
                if (row == 0 && column > 0) {

                    PlateItem plateItem = new PlateItem("", String.valueOf(column));
                    plateItem.setSizeFull();
                    platePanelLayout.addComponent(plateItem);

                }
                else if (row > 0 && column == 0) {

                    PlateItem plateItem = new PlateItem("", ROWLABELS[row - 1]);
                    plateItem.setSizeFull();
                    platePanelLayout.addComponent(plateItem);

                }
                else if (column > 0 && row > 0) {

                    PlateItem plateItem;
                    DragAndDropWrapper dragAndDropWrapper;

                    if (samples.containsKey(getSampleKey(row, column))) {

                        Sample plateSample = samples.get(getSampleKey(row, column));
                        plateItem = new PlateItem(this.getSampleDescription(plateSample), "");
                        plateItem.addClickListener((clickEvent-> {
                            plateItemDetailsLabel.setCaption(getSampleDescription(plateSample));
                        }));

                        plateItem.setSizeFull();
                        dragAndDropWrapper = new DragAndDropWrapper(plateItem);
                        dragAndDropWrapper.setDropHandler(this);
                        dragAndDropWrapper.setDragStartMode(DragAndDropWrapper.DragStartMode.WRAPPER);
                        plateItem.addStyleName("border-gridcell");

                    }
                    else {

                        plateItem = new PlateItem("", "");
                        plateItem.setSizeFull();
                        dragAndDropWrapper = new DragAndDropWrapper(plateItem);
                        dragAndDropWrapper.setDropHandler(this);
                        plateItem.addStyleName("border-empty-gridcell");
                        dragAndDropWrapper.setDragStartMode(DragAndDropWrapper.DragStartMode.NONE);
                    }
                    dragAndDropWrapper.setSizeFull();
                    dragAndDropWrapper.setId("Wrapper_EmptyPanel_" + row + "_" + column);
                    platePanelLayout.addComponent(dragAndDropWrapper, column, row);


                    logger.log(Level.FINEST, "Added item at location={0},{1}", new Object[]{row,
                                                                                            column});

                }
            }


        }
        platePanelLayout.setSizeFull();
        layout.setWidth("860");
        layout.setHeight("700");
        layout.addComponent(platePanelLayout);
        plateItemDetailsLabel = new Label("");
        layout.addComponent(plateItemDetailsLabel);
        layout.setExpandRatio(platePanelLayout,
                              9);
        layout.setExpandRatio(plateItemDetailsLabel,
                              1);
        return layout;
    }


    @Override
    public void drop(DragAndDropEvent event) {

        Transferable t = event.getTransferable();
        Component from = t.getSourceComponent();

        logger.log(Level.INFO, "wrapper component ={0}", from);
        DragAndDropWrapper dragAndDropWrapper = (DragAndDropWrapper) from;
        logger.log(Level.INFO, "dragged component count ={0} with ID={1}", new Object[]{dragAndDropWrapper.getComponentCount(),
                                                                                        dragAndDropWrapper.getId()});

        DragAndDropWrapper.WrapperTargetDetails details = (DragAndDropWrapper.WrapperTargetDetails) event.getTargetDetails();
        logger.log(Level.INFO, "target component ={0}", details.getTarget());
        DragAndDropWrapper dragAndDropTargetWrapper = (DragAndDropWrapper) details.getTarget();
        logger.log(Level.INFO, "target component count ={0} with ID={1}", new Object[]{dragAndDropTargetWrapper.getComponentCount(),
                                                                                       dragAndDropTargetWrapper.getId()});

        platePanelLayout.replaceComponent(from, dragAndDropTargetWrapper);

    }

    @Override
    public AcceptCriterion getAcceptCriterion() {
        return AcceptAll.get();
    }

    private String getSampleDescription(Sample sample) {

        return "sampleID: " + sample.getSampleID() +
               " , volume (ml): " + String.valueOf(sample.getVolume());
    }

    private String getSampleKey(int row, int column) {

        return ROWLABELS[row - 1].concat(String.valueOf(column));

    }
}
