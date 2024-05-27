package application.repositories;

import application.models.movimientosReclamo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImovimientosReclamoRepository extends JpaRepository<movimientosReclamo,Long> {
    public Optional<movimientosReclamo> findByIdMovimiento(Integer integer);

}
