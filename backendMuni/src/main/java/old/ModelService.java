package old;

import application.services.IService;
import old.Model;
import old.IModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ModelService implements IService<Model, Model> {
    @Autowired
    private IModelRepository iRepository;

    @Autowired
    public ModelService(IModelRepository iRepository){
        this.iRepository=iRepository;
    }

    @Override
    public List<Model> listar() {
        return iRepository.findAll();
    }

    @Override
    public Model guardar(Model entity) {
        return null;
    }

    @Override
    public Model buscarPorPK(String pk) {
        return null;
    }

    @Override
    public Model buscarPorId(Integer id) {
        return null;
    }

}