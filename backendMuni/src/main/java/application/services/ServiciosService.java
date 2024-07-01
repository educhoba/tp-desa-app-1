package application.services;

import application.exceptions.ReclamoException;
import application.models.Reclamos;
import application.models.Servicios;
import application.repositories.IServiciosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ServiciosService implements IService<Servicios, Servicios> {
    @Autowired
    private IServiciosRepository iRepository;

    @Autowired
    public ServiciosService(IServiciosRepository iRepository){
        this.iRepository=iRepository;
    }

    @Override
    public List<Servicios> listar() {
        return iRepository.findAll();
    }

    @Override
    public Servicios guardar(Servicios entity) {
        return iRepository.save(entity);
    }

    @Override
    public Servicios buscarPorPK(String pk) {
        return null;
    }

    @Override
    public Servicios buscarPorId(Integer id) {
        Optional<Servicios> ret = iRepository.findByIdServicios(id);
        return ret.orElse(null);
    }

    public List<Servicios> listarTipo(String tipo){
        //checkTipo(tipo);
        return iRepository.findByTipo(tipo);
    }

    public List<Servicios> listarTipoYRubro(String tipo, String rubro){
        //checkTipo(tipo);
        return iRepository.findByTipoAndRubro(tipo,rubro);
    }

    public void registrar(Servicios servicio) throws ReclamoException{
        //wip
        checkTipo(servicio.getTipo().trim());
        guardar(servicio);
    }

    private static void checkTipo(String tipo) throws ReclamoException {
        if(!(Objects.equals(tipo, "Comercio") || Objects.equals(tipo, "Profesional")))
            throw new ReclamoException("Error: " +
                    ">La propiedad 'Tipo' debe ser:" +
                    ">>'Comercio'" +
                    ">>'Profesional'");
    }
}