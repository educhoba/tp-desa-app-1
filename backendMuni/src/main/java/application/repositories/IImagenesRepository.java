package application.repositories;

import application.models.Imagenes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IImagenesRepository extends JpaRepository<Imagenes,Long> {
    public Optional<Imagenes> findByIdImagen(Integer integer);
    public List<Imagenes> findByIdDenuncia(Integer integer);
    public List<Imagenes> findByIdReclamo(Integer integer);
    public List<Imagenes> findByIdServicio(Integer integer);

}
