package rs.ac.uns.ftn.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.backend.model.Ingredient;
@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long>{

}
