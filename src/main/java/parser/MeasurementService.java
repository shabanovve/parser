package parser;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import parser.entities.Measurement;
import parser.repositories.MeasurementRepository;
import parser.repositories.ObserverRepository;
import parser.repositories.SubstanceRepository;

@Service
@AllArgsConstructor
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final ObserverRepository observerRepository;
    private final SubstanceRepository substanceRepository;

    public Measurement findLast() {
        return measurementRepository.findAll(
                PageRequest.of(0, 1, Sort.by("date").descending())

        ).get().findFirst().get();

    }

    public Measurement save(Measurement measurement) {
        measurement.getObservers().stream()
                .forEach(
                        observer -> observer.getSubstances().stream()
                                .forEach(substance -> substanceRepository.save(substance))
                );


        measurement.getObservers().stream().forEach(observer -> observerRepository.save(observer));
        return measurementRepository.save(measurement);
    }
}
