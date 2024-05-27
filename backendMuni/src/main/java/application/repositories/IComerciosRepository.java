package application.repositories;

import application.models.Comercios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IComerciosRepository extends JpaRepository<Comercios,Long> {
    public Optional<Comercios> findByIdComercios(Integer integer);

}
