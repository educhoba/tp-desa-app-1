package old;

import application.services.IService;
import old.Profesionales;
import old.IProfesionalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProfesionalesService implements IService<Profesionales, Profesionales> {
    @Autowired
    private IProfesionalesRepository iRepository;

    @Autowired
    public ProfesionalesService(IProfesionalesRepository iRepository){
        this.iRepository=iRepository;
    }

    @Override
    public List<Profesionales> listar() {
        return iRepository.findAll();
    }

    @Override
    public Profesionales guardar(Profesionales entity) {
        return null;
    }

    @Override
    public Profesionales buscarPorPK(String pk) {
        return null;
    }

    @Override
    public Profesionales buscarPorId(Integer id) {
        return null;
    }

}