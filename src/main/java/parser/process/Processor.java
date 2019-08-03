package parser.process;

import org.jsoup.nodes.Document;
import parser.entities.Measurement;

public interface Processor {

    void process(Measurement measurement, Document document);
}
