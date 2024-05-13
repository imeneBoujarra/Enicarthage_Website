package tn.enicarthage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.enicarthage.model.Experience;
import tn.enicarthage.model.Publication;


@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {
	List<Experience> findByUserId(Long userId);
	 Optional<Experience> findByIdAndUserId(Long id, Long userId);
}
