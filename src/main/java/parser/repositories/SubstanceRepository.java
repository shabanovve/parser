package parser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import parser.entities.Substance;

@Repository
public interface SubstanceRepository extends JpaRepository<Substance, Long> {

}
