package tn.enicarthage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.enicarthage.model.Enseignant;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {
	Optional<Enseignant> findByEmail(String email);

}
