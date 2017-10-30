package lparikka.vaadin.testapp.backend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class SampleService {


    public Map loadSamples(SampleRepository repository) {

        HashMap<String, Sample> samplesMap = new HashMap<>();
        List<Sample> samples = repository.findAll();
        for (Sample sample : samples) {
            String locationId = sample.getRow() + String.valueOf(sample.getColumn());
            samplesMap.put(locationId, sample);
        }
        return samplesMap;
    }
}
