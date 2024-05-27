package old;

import old.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IModelRepository extends JpaRepository<Model,Long> {
    public Optional<Model> findByPK(String pk);
    public Optional<Model> findById(Integer integer);

}
