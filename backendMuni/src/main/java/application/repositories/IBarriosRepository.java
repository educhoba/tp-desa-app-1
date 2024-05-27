package application.repositories;

import application.models.Barrios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBarriosRepository extends JpaRepository<Barrios,Long> {
    public Optional<Barrios> findByIdBarrio(Integer integer);

}
