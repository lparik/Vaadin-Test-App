package lparikka.vaadin.testapp;

import lparikka.vaadin.testapp.backend.Sample;
import lparikka.vaadin.testapp.ui.PlateView;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import java.util.Map;
import lparikka.vaadin.testapp.backend.SampleRepository;
import lparikka.vaadin.testapp.backend.SampleService;
import org.springframework.beans.factory.annotation.Autowired;


@SpringUI
@Theme("mytheme")
public class VaadinTestAppUI extends UI {

    private PlateView plate;

    private Map<String, Sample> samplesMap;

    private SampleService sampleService;

    @Autowired
    SampleRepository repository;

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        sampleService = new SampleService();
        samplesMap = sampleService.loadSamples(repository);
        plate = new PlateView(samplesMap);
        Component plateComponent = plate.constructPlate();
        this.setContent(plateComponent);

    }


}
