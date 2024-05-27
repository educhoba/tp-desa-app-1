package application.repositories;

import application.models.Vecinos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IVecinosRepository extends JpaRepository<Vecinos,Long> {
    public Optional<Vecinos> findByDocumento(String pk);

}
