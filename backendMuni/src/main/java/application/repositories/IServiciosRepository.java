package application.repositories;

import application.models.Rubros;
import application.models.Servicios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IServiciosRepository extends JpaRepository<Servicios,Long> {
    public Optional<Servicios> findByIdServicios(Integer idServicios);
    public List<Servicios> findByTipo(String tipo);
    public List<Servicios> findByTipoAndRubro(String tipo,String rubro);

}
