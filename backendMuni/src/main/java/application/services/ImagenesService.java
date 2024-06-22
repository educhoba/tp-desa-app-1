package application.services;

import application.exceptions.ImagenException;
import application.exceptions.UsuarioException;
import application.models.Imagenes;
import application.models.Usuarios;
import application.repositories.IImagenesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        return iRepository.save(entity);
    }

    @Override
    public Imagenes buscarPorPK(String pk) {
        return null;
    }

    @Override
    public Imagenes buscarPorId(Integer id) {
        Optional<Imagenes> ret = iRepository.findByIdImagen(id);
        return ret.orElse(null);
    }

    public List<Imagenes> listarPorIdDenuncia(Integer idDenuncia) {
        return iRepository.findByIdDenuncia(idDenuncia);
    }

    public List<Imagenes> listarPorIdReclamo(Integer idDenuncia) {
        return iRepository.findByIdReclamo(idDenuncia);
    }
    public List<Imagenes> listarPorIdServicio(Integer idServicio) {
        return iRepository.findByIdServicio(idServicio);
    }

    public void guardarImagen(Imagenes imagen) throws ImagenException {

        if(false) //wip
            throw new ImagenException("bla bla");

        guardar(imagen);
    }
}