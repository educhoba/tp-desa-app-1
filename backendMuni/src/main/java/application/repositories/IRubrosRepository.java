package application.repositories;

import application.models.Rubros;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRubrosRepository extends JpaRepository<Rubros,Long> {
    public Optional<Rubros> findByIdRubro(Integer integer);

}
