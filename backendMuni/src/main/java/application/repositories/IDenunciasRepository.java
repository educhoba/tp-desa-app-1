package application.repositories;

import application.models.Denuncias;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IDenunciasRepository extends JpaRepository<Denuncias,Long> {
    public Optional<Denuncias> findByIdDenuncias(Integer integer);

    public List<Denuncias> findByDocumento(String documento);
}
