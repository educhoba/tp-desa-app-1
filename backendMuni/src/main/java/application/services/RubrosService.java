package application.services;

import application.models.Rubros;
import application.models.Sitios;
import application.repositories.IRubrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        Optional<Rubros> ret = iRepository.findByIdRubro(id);
        return ret.orElse(null);
    }

}