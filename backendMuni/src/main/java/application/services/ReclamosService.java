package application.services;

import application.exceptions.ReclamoException;
import application.exceptions.UsuarioException;
import application.models.Reclamos;
import application.models.Usuarios;
import application.models.Vecinos;
import application.repositories.IReclamosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReclamosService implements IService<Reclamos, Reclamos> {
    @Autowired
    private IReclamosRepository iRepository;

    @Autowired
    public ReclamosService(IReclamosRepository iRepository){
        this.iRepository=iRepository;
    }

    @Override
    public List<Reclamos> listar() {
        return iRepository.findAll();
    }

    @Override
    public Reclamos guardar(Reclamos entity) {
        return iRepository.save(entity);
    }

    @Override
    public Reclamos buscarPorPK(String pk) {
        return null;
    }

    @Override
    public Reclamos buscarPorId(Integer id) {
        Optional<Reclamos> ret = iRepository.findByIdReclamo(id);
        return ret.orElse(null);
    }
    public Reclamos registrar(Reclamos reclamos) throws ReclamoException {
        if(false)
            throw new ReclamoException("Error al registrar.");
        //si entro a registrar nunca deberia tener id
        reclamos.setIdReclamo(null);
        if(reclamos.getLegajo() != null && reclamos.getLegajo() == 0)
            reclamos.setLegajo(null);
        if(reclamos.getIdReclamoUnificado() != null && reclamos.getIdReclamoUnificado() == 0)
            reclamos.setIdReclamoUnificado(null);
        if(reclamos.getIdDesperfecto() != null && reclamos.getIdDesperfecto() == 0)
            reclamos.setIdDesperfecto(null);

        guardar(reclamos);
        return reclamos;
    }
    public void actualizar(Reclamos reclamos) throws ReclamoException {
        //wip
        if(false)
            throw new ReclamoException("Error al actualizar.");

        guardar(reclamos);
    }


    public List<Reclamos> listarPorDocumento(String documento) {
        return iRepository.findByDocumento(documento);
    }

    public List<Reclamos> listarPorLegajo(Integer legajo) {
        return iRepository.findByLegajo(legajo);
    }

    //public List<Reclamos> listarPorRubro(String rubro) {
    //    return iRepository.findByRubro(rubro);
    //}
}