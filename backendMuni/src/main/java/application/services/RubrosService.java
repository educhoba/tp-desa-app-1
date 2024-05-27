package application.services;

import application.models.Rubros;
import application.repositories.IRubrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RubrosService implements IService<Rubros, Rubros> {
    @Autowired
    private IRubrosRepository iRepository;

    @Autowired
    public RubrosService(IRubrosRepository iRepository){
        this.iRepository=iRepository;
    }

    @Override
    public List<Rubros> listar() {
        return iRepository.findAll();
    }

    @Override
    public Rubros guardar(Rubros entity) {
        return null;
    }

    @Override
    public Rubros buscarPorPK(String pk) {
        return null;
    }

    @Override
    public Rubros buscarPorId(Integer id) {
        return null;
    }

}