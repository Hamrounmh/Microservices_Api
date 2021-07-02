package fr.univ.dauphine.innov.message.repository;

import fr.univ.dauphine.innov.message.Models.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation,Long> {

}
