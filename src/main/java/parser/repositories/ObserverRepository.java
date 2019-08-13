package parser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import parser.entities.Observer;

@Repository
public interface ObserverRepository extends JpaRepository<Observer, Long> {

}
