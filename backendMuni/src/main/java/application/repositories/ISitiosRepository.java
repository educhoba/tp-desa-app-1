package application.repositories;

import application.models.Sitios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ISitiosRepository extends JpaRepository<Sitios,Long> {
    public Optional<Sitios> findByIdSitio(Integer integer);

}
