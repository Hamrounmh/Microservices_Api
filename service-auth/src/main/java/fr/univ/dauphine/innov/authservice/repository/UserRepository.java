package fr.univ.dauphine.innov.authservice.repository;

import fr.univ.dauphine.innov.authservice.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
