package application.repositories;

import application.models.SitiosManuales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ISitiosManualesRepository extends JpaRepository<SitiosManuales,Long> {
    public Optional<SitiosManuales> findByIdSitioManual(Integer integer);

}
