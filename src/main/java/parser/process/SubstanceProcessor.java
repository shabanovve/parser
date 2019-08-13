package parser.process;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import parser.Constants;
import parser.entities.Measurement;
import parser.entities.Observer;
import parser.entities.Substance;

import java.util.Iterator;

@Component
public class SubstanceProcessor implements Processor{
    @Override
    public void process(Measurement measurement, Document document) {
        Elements rows = document.select("#center_column table tbody tr");
        int observerPointCount = measurement.getObservers().size();
        for (Element row : rows) {
            if (isItRowWithSubstance(row, observerPointCount)) {
                Elements cells = row.select("td");
                Iterator<Element> cellIterator = cells.iterator();

                String substanceName = cellIterator.next().text();
                String limitConcentration = cellIterator.next().text();

                Iterator<Observer> observerIterator = measurement.getObservers().iterator();
                while (cellIterator.hasNext()) {
                    Observer observer = observerIterator.next();
                    String concentration = cellIterator.next().text();
                    Substance substance = Substance.builder()
                            .name(substanceName)
                            .limitConcentration(limitConcentration)
                            .concentration(concentration)
                            .build();
                    observer.getSubstances().add(substance);
                }
            }
        }

    }

    private boolean isItRowWithSubstance(Element element, int observerPointCount) {
        Elements columns = element.select("td");
        return columns.size() == observerPointCount + Constants.COLUMN_COUNT_BEFORE_SUBSTANCE_CONCENTRATION;
    }
}
