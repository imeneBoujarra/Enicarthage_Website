package tn.enicarthage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.enicarthage.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
