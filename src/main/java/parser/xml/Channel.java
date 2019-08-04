package parser.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.List;

import static parser.Constants.URL_TO_PARSE;

@Data
public class Channel {
    public String title = "Архив данных росгидромета";
    public String link = URL_TO_PARSE;
    public String description = "Мониторинг загрязнения атмосферного воздуха";
    public String language = "ru";

    @JacksonXmlElementWrapper(localName = "items")
    @JacksonXmlProperty(localName = "item")
    private List<Item> items;
}
