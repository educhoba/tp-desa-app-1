package application.services;

import application.exceptions.PersonalException;
import application.exceptions.UsuarioException;
import application.models.Personal;
import application.models.Usuarios;
import application.repositories.IPersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public Personal buscarPorPK(String documento) {
        return null;
    }

    public Personal buscarPersonal(String documento) throws PersonalException {
        Optional<Personal> ret = iRepository.findByDocumento(documento);
        if(ret.isPresent())
        {
            return ret.get();
        }
        else
            throw new PersonalException("No existe un personal con ese documento.");
    }

    @Override
    public Personal buscarPorId(Integer id) {
        return null;
    }

}