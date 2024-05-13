package tn.enicarthage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.enicarthage.model.Cours;
import tn.enicarthage.model.Experience;

@Repository
public interface CoursRepository extends JpaRepository<Cours, Long> {
	List<Cours> findByUserId(Long userId);
	 Optional<Cours> findByIdAndUserId(Long id, Long userId);
}
