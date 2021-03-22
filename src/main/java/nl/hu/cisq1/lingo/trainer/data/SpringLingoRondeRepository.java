package nl.hu.cisq1.lingo.trainer.data;

import nl.hu.cisq1.lingo.trainer.domain.lingoRonde.LingoRonde;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringLingoRondeRepository extends JpaRepository<LingoRonde,Long> {
}
