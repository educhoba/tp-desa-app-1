package application.services;

import application.exceptions.DenunciaException;
import application.exceptions.ReclamoException;
import application.models.Denuncias;
import application.models.Reclamos;
import application.repositories.IDenunciasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DenunciasService implements IService<Denuncias, Denuncias> {
    @Autowired
    private IDenunciasRepository iRepository;

    @Autowired
    public DenunciasService(IDenunciasRepository iRepository){
        this.iRepository=iRepository;
    }

    @Override
    public List<Denuncias> listar() {
        return iRepository.findAll();
    }

    @Override
    public Denuncias guardar(Denuncias entity) {
        return iRepository.save(entity);
    }

    @Override
    public Denuncias buscarPorPK(String pk) {
        return null;
    }

    @Override
    public Denuncias buscarPorId(Integer id) {
        Optional<Denuncias> ret = iRepository.findByIdDenuncias(id);
        return ret.orElse(null);
    }

    public Denuncias registrar(Denuncias denuncias) throws DenunciaException {

        if(false) //wip
            throw new DenunciaException("Error al registrar.");

        guardar(denuncias);
        return denuncias;
    }

    public void actualizar(Denuncias denuncias) throws DenunciaException  {

        if(denuncias.getIdDenuncias() == null) //wip
            throw new DenunciaException("Error al actualizar, id = null");

        guardar(denuncias);
    }

    public List<Denuncias> listarPorDocumento(String documento) {
        return iRepository.findByDocumento(documento);
    }

    public List<Denuncias> listarPorDenunciado(String documento) {
        return iRepository.findByDenunciado(documento);
    }
}