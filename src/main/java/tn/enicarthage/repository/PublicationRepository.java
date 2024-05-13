package tn.enicarthage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.enicarthage.model.Formation;
import tn.enicarthage.model.Publication;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {

	List<Publication> findByUserId(Long userId);
	 Optional<Publication> findByIdAndUserId(Long id, Long userId);
}
