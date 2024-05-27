package application.services;

import application.models.Denuncias;
import application.repositories.IDenunciasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return null;
    }

    @Override
    public Denuncias buscarPorPK(String pk) {
        return null;
    }

    @Override
    public Denuncias buscarPorId(Integer id) {
        return null;
    }

}