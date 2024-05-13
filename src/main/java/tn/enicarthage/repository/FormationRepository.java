package tn.enicarthage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.enicarthage.model.Formation;

@Repository
public interface FormationRepository extends JpaRepository<Formation, Long> {
	List<Formation> findByUserId(Long userId);
	 Optional<Formation> findByIdAndUserId(Long id, Long userId);

}
