package old;

import application.services.IService;
import old.Comercios;
import old.IComerciosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ComerciosService implements IService<Comercios, Comercios> {
    @Autowired
    private IComerciosRepository iRepository;

    @Autowired
    public ComerciosService(IComerciosRepository iRepository){
        this.iRepository=iRepository;
    }

    @Override
    public List<Comercios> listar() {
        return iRepository.findAll();
    }

    @Override
    public Comercios guardar(Comercios entity) {
        return null;
    }

    @Override
    public Comercios buscarPorPK(String pk) {
        return null;
    }

    @Override
    public Comercios buscarPorId(Integer id) {
        return null;
    }

}