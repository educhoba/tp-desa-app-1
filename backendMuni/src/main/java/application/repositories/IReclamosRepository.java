package application.repositories;

import application.models.Reclamos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IReclamosRepository extends JpaRepository<Reclamos,Long> {
    public Optional<Reclamos> findByIdReclamo(Integer integer);

}
