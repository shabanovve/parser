package parser.process;

import lombok.NoArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import parser.entities.Measurement;
import parser.entities.Observer;

import static parser.Constants.OBSERVER_NAME_ROW_SELECTOR;

@Component
@NoArgsConstructor
public class ObserverNameProcessor implements Processor {
    @Override
    public void process(Measurement measurement, Document document) {
        Elements elements = document.select(OBSERVER_NAME_ROW_SELECTOR);
        for (Element element : elements) {
            Observer observer = new Observer();
            String observerName = element.text();
            observer.setName(observerName);
            measurement.getObservers().add(observer);
        }
    }
}
