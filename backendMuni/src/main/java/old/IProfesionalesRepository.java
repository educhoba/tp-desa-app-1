package old;

import old.Profesionales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IProfesionalesRepository extends JpaRepository<Profesionales,Long> {
    public Optional<Profesionales> findByIdProfesional(Integer integer);

}
