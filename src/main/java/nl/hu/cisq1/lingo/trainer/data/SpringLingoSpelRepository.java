package nl.hu.cisq1.lingo.trainer.data;

import nl.hu.cisq1.lingo.trainer.domain.LingoSpel.LingoSpel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringLingoSpelRepository extends JpaRepository<LingoSpel,Long> {
}
