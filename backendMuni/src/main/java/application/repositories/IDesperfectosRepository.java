package application.repositories;

import application.models.Desperfectos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDesperfectosRepository extends JpaRepository<Desperfectos,Long> {
    public Optional<Desperfectos> findByIdDesperfecto(Integer integer);

}
