package application.services;

import application.models.Imagenes;
import application.repositories.IImagenesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ImagenesService implements IService<Imagenes, Imagenes> {
    @Autowired
    private IImagenesRepository iRepository;

    @Autowired
    public ImagenesService(IImagenesRepository iRepository){
        this.iRepository=iRepository;
    }

    @Override
    public List<Imagenes> listar() {
        return iRepository.findAll();
    }

    @Override
    public Imagenes guardar(Imagenes entity) {
        return null;
    }

    @Override
    public Imagenes buscarPorPK(String pk) {
        return null;
    }

    @Override
    public Imagenes buscarPorId(Integer id) {
        return null;
    }

}