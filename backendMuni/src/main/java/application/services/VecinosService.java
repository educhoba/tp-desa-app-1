package application.services;

import application.models.Vecinos;
import application.repositories.IVecinosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VecinosService implements IService<Vecinos, Vecinos> {
    @Autowired
    private IVecinosRepository iRepository;

    @Autowired
    public VecinosService(IVecinosRepository iRepository){
        this.iRepository=iRepository;
    }

    @Override
    public List<Vecinos> listar() {
        return iRepository.findAll();
    }

    @Override
    public Vecinos guardar(Vecinos entity) {
        return null;
    }

    @Override
    public Vecinos buscarPorPK(String pk) {
        return null;
    }

    @Override
    public Vecinos buscarPorId(Integer id) {
        return null;
    }

}