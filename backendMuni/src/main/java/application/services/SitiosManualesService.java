package application.services;

import application.exceptions.SitioManualException;
import application.models.SitiosManuales;
import application.repositories.ISitiosManualesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SitiosManualesService implements IService<SitiosManuales, SitiosManuales> {
    @Autowired
    private ISitiosManualesRepository iRepository;

    @Autowired
    public SitiosManualesService(ISitiosManualesRepository iRepository){
        this.iRepository=iRepository;
    }

    @Override
    public List<SitiosManuales> listar() {
        return iRepository.findAll();
    }

    @Override
    public SitiosManuales guardar(SitiosManuales entity) {
        return iRepository.save(entity);
    }

    @Override
    public SitiosManuales buscarPorPK(String pk) {
        return null;
    }

    @Override
    public SitiosManuales buscarPorId(Integer id) {
        return null;
    }

    public void registrarSitio(SitiosManuales sitioManual) throws SitioManualException {
        if(false)//wip
            throw new SitioManualException("bla bla");
        guardar(sitioManual);
    }
}