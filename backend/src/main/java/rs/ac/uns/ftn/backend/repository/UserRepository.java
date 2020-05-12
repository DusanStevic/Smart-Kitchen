package rs.ac.uns.ftn.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.backend.model.User;


public interface UserRepository extends JpaRepository<User,Long> {

	User findByUsername(String username);


}
