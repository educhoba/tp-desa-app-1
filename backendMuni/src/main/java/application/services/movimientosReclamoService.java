package application.services;

import application.models.movimientosReclamo;
import application.repositories.ImovimientosReclamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class movimientosReclamoService implements IService<movimientosReclamo, movimientosReclamo> {
    @Autowired
    private ImovimientosReclamoRepository iRepository;

    @Autowired
    public movimientosReclamoService(ImovimientosReclamoRepository iRepository){
        this.iRepository=iRepository;
    }

    @Override
    public List<movimientosReclamo> listar() {
        return iRepository.findAll();
    }

    @Override
    public movimientosReclamo guardar(movimientosReclamo entity) {
        return null;
    }

    @Override
    public movimientosReclamo buscarPorPK(String pk) {
        return null;
    }

    @Override
    public movimientosReclamo buscarPorId(Integer id) {
        return null;
    }

}