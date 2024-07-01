package application.services;

import application.models.Reclamos;
import application.models.Sitios;
import application.repositories.ISitiosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SitiosService implements IService<Sitios, Sitios> {
    @Autowired
    private ISitiosRepository iRepository;

    @Autowired
    public SitiosService(ISitiosRepository iRepository){
        this.iRepository=iRepository;
    }

    @Override
    public List<Sitios> listar() {
        return iRepository.findAll();
    }

    @Override
    public Sitios guardar(Sitios entity) {
        return null;
    }

    @Override
    public Sitios buscarPorPK(String pk) {
        return null;
    }

    @Override
    public Sitios buscarPorId(Integer id) {
        Optional<Sitios> ret = iRepository.findByIdSitio(id);
        return ret.orElse(null);
    }

}