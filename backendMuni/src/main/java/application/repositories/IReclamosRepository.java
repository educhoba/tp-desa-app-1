package application.repositories;

import application.models.Reclamos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IReclamosRepository extends JpaRepository<Reclamos,Long> {
    public Optional<Reclamos> findByIdReclamo(Integer integer);

    List<Reclamos> findByDocumento(String documento);

    List<Reclamos> findByLegajo(Integer legajo);

    //List<Reclamos> findByRubro(String rubro);
}
