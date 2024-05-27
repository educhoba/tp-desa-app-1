package application.services;

import application.models.Personal;
import application.repositories.IPersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonalService implements IService<Personal, Personal> {
    @Autowired
    private IPersonalRepository iRepository;

    @Autowired
    public PersonalService(IPersonalRepository iRepository){
        this.iRepository=iRepository;
    }

    @Override
    public List<Personal> listar() {
        return iRepository.findAll();
    }

    @Override
    public Personal guardar(Personal entity) {
        return null;
    }

    @Override
    public Personal buscarPorPK(String pk) {
        return null;
    }

    @Override
    public Personal buscarPorId(Integer id) {
        return null;
    }

}