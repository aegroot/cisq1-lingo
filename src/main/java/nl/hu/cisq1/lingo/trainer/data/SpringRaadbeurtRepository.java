package nl.hu.cisq1.lingo.trainer.data;

import nl.hu.cisq1.lingo.trainer.domain.raadBeurt.Raadbeurt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringRaadbeurtRepository extends JpaRepository<Raadbeurt,Long> {
}
