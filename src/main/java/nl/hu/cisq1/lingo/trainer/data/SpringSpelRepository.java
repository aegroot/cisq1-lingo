package nl.hu.cisq1.lingo.trainer.data;

import nl.hu.cisq1.lingo.trainer.domain.Spel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringSpelRepository extends JpaRepository<Spel, Long> {
}
