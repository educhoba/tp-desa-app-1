package application.repositories;

import application.models.Personal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPersonalRepository extends JpaRepository<Personal,Long> {
    public Optional<Personal> findByLegajo(Integer integer);

}
