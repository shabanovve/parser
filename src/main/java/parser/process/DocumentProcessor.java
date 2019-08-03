package parser.process;

import lombok.AllArgsConstructor;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import parser.Constants;
import parser.entities.Measurement;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
@AllArgsConstructor
public class DocumentProcessor {

    private final ObserverNameProcessor observerNameProcessor;
    private final ObserverCodeProcessor observerCodeProcessor;
    private final SubstanceProcessor substanceProcessor;

    public Measurement process(Document document) {
        Measurement measurement = new Measurement();
        observerNameProcessor.process(measurement, document);
        observerCodeProcessor.process(measurement, document);
        substanceProcessor.process(measurement, document);
        measurement.setDate(new Timestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(Constants.TIME_ZONE)) * 1000));
        return measurement;
    }
}
