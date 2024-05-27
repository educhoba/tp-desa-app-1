package application.services;

import application.models.Barrios;
import application.repositories.IBarriosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BarriosService implements IService<Barrios, Barrios> {
    @Autowired
    private IBarriosRepository iRepository;

    @Autowired
    public BarriosService(IBarriosRepository iRepository){
        this.iRepository=iRepository;
    }

    @Override
    public List<Barrios> listar() {
        return iRepository.findAll();
    }

    @Override
    public Barrios guardar(Barrios entity) {
        return null;
    }

    @Override
    public Barrios buscarPorPK(String pk) {
        return null;
    }

    @Override
    public Barrios buscarPorId(Integer id) {
        return null;
    }

}