package tn.enicarthage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.enicarthage.model.Emploi;

@Repository
public interface EmploiRepository extends JpaRepository<Emploi, Long> {

}
