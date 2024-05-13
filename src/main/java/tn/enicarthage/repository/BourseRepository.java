package tn.enicarthage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.enicarthage.model.Bourse;

@Repository
public interface BourseRepository extends JpaRepository<Bourse, Long> {
}
