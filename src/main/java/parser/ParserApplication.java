package parser;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import parser.entities.Measurement;
import parser.process.DocumentProcessor;
import parser.xml.Channel;
import parser.xml.Item;
import parser.xml.RssRoot;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static parser.Constants.FILE_NAME;
import static parser.Constants.URL_TO_PARSE;

@SpringBootApplication
@Slf4j
@AllArgsConstructor
public class ParserApplication implements CommandLineRunner {

	private final DocumentProcessor documentProcessor;

	public static void main(String[] args) {
		SpringApplication.run(ParserApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Try to get page " + URL_TO_PARSE);
		Document document = Jsoup.connect(URL_TO_PARSE).get();
		log.info("Html received. Try to parse.");
		Measurement measurement = documentProcessor.process(document);
		log.info(String.format("Html parsed with %s observation point", measurement.getObservers().size()));

		log.info("Write result to " + FILE_NAME);

		XmlMapper xmlMapper = new XmlMapper();
		xmlMapper.configure( ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true );
		RssRoot rssRoot = new RssRoot();
		rssRoot.setChannel(new Channel());

		List<Item> items = measurement.getObservers().stream()
				.map(observer -> {
					Item item = new Item();
					item.setPubDate(observer.getDate());
					item.setTitle(observer.getName());
					item.setDescription(observer.getName());
					item.setSubstances(observer.getSubstances());
					return item;
				})
				.collect(Collectors.toList());
		rssRoot.getChannel().setItems(items);
		xmlMapper.writeValue(new File(FILE_NAME), rssRoot);

	}

}
