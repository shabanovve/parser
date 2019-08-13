package parser.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import parser.entities.Measurement;

@Repository
public interface MeasurementRepository extends PagingAndSortingRepository<Measurement, Long> {

}
