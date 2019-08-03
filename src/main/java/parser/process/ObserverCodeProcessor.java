package parser.process;

import lombok.NoArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import parser.entities.Measurement;
import parser.entities.Observer;

import java.util.Iterator;

import static parser.Constants.OBSERVER_CODE_ROW_SELECTOR;

@Component
@NoArgsConstructor
public class ObserverCodeProcessor implements Processor {
    @Override
    public void process(Measurement measurement, Document document) {
        Elements elements = document.select(OBSERVER_CODE_ROW_SELECTOR);
        Iterator<Observer> iterator = measurement.getObservers().iterator();
        for (Element element : elements) {
            iterator.next().setCode(element.text());
        }

    }
}
