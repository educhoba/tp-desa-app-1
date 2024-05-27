package application.services;

import application.models.Desperfectos;
import application.repositories.IDesperfectosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DesperfectosService implements IService<Desperfectos, Desperfectos> {
    @Autowired
    private IDesperfectosRepository iRepository;

    @Autowired
    public DesperfectosService(IDesperfectosRepository iRepository){
        this.iRepository=iRepository;
    }

    @Override
    public List<Desperfectos> listar() {
        return iRepository.findAll();
    }

    @Override
    public Desperfectos guardar(Desperfectos entity) {
        return null;
    }

    @Override
    public Desperfectos buscarPorPK(String pk) {
        return null;
    }

    @Override
    public Desperfectos buscarPorId(Integer id) {
        return null;
    }

}