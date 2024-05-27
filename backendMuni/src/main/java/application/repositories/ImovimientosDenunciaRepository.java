package application.repositories;

import application.models.movimientosDenuncia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImovimientosDenunciaRepository extends JpaRepository<movimientosDenuncia,Long> {
    public Optional<movimientosDenuncia> findByIdMovimiento(Integer integer);

}
