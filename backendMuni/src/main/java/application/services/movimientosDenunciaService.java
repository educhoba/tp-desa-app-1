package application.services;

import application.models.movimientosDenuncia;
import application.repositories.ImovimientosDenunciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class movimientosDenunciaService implements IService<movimientosDenuncia, movimientosDenuncia> {
    @Autowired
    private ImovimientosDenunciaRepository iRepository;

    @Autowired
    public movimientosDenunciaService(ImovimientosDenunciaRepository iRepository){
        this.iRepository=iRepository;
    }

    @Override
    public List<movimientosDenuncia> listar() {
        return iRepository.findAll();
    }

    @Override
    public movimientosDenuncia guardar(movimientosDenuncia entity) {
        return null;
    }

    @Override
    public movimientosDenuncia buscarPorPK(String pk) {
        return null;
    }

    @Override
    public movimientosDenuncia buscarPorId(Integer id) {
        return null;
    }

}