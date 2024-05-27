package application.services;

import application.models.Reclamos;
import application.repositories.IReclamosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return null;
    }

    @Override
    public Reclamos buscarPorPK(String pk) {
        return null;
    }

    @Override
    public Reclamos buscarPorId(Integer id) {
        return null;
    }

}