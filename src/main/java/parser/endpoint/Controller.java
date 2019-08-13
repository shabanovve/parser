package parser.endpoint;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import parser.MeasurementService;
import parser.entities.Measurement;

@RestController
@AllArgsConstructor
public class Controller {

    private final MeasurementService service;

    @GetMapping
    public Measurement getLast() {
        return service.findLast();
    }
}
