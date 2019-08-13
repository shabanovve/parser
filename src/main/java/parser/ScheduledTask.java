package parser;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import parser.entities.Measurement;
import parser.process.DocumentProcessor;

import java.io.IOException;

import static parser.Constants.URL_TO_PARSE;

@Component
@Slf4j
@AllArgsConstructor
public class ScheduledTask {

    private final DocumentProcessor documentProcessor;
    private final MeasurementService service;


    @Scheduled(fixedRate = 5000)
    public void runTask() throws IOException {
        log.info("Try to get page " + URL_TO_PARSE);
        Document document = Jsoup.connect(URL_TO_PARSE).get();
        log.info("Html received. Try to parse.");
        Measurement measurement = documentProcessor.process(document);
        log.info(String.format("Html parsed with %s observation point", measurement.getObservers().size()));

        service.save(measurement);
    }
}
