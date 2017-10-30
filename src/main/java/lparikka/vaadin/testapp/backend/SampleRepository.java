package lparikka.vaadin.testapp.backend;

import lparikka.vaadin.testapp.backend.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample, Long> {
}
