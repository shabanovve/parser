package parser.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import parser.entities.Substance;

import java.util.List;

@Data
public class Item {
    private String pubDate;
    private String title;
    private String description;

    @JacksonXmlElementWrapper(localName = "substances")
    @JacksonXmlProperty(localName = "substance")
    private List<Substance> substances;
}
