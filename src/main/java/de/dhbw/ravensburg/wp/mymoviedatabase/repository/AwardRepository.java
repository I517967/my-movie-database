package de.dhbw.ravensburg.wp.mymoviedatabase.repository;

import de.dhbw.ravensburg.wp.mymoviedatabase.model.Award;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AwardRepository extends JpaRepository<Award, Long> {
}
