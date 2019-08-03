package parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import parser.entities.Measurement;
import parser.process.DocumentProcessor;

import java.io.File;

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
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(new File(Constants.FILE_NAME), measurement);
	}

}
