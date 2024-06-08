package application.services;

import application.exceptions.UsuarioException;
import application.models.Usuarios;
import application.repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService implements IService<Usuarios, Usuarios> {
    @Autowired
    private IUsuarioRepository iRepository;

    @Autowired
    public UsuarioService(IUsuarioRepository iRepository){
        this.iRepository=iRepository;
    }

    @Override
    public List<Usuarios> listar() {
        return iRepository.findAll();
    }

    @Override
    public Usuarios guardar(Usuarios persona)  {
        System.out.println(persona);
        if(persona.getDocumento()==null || persona.getEmail() == null) {
            return null;
        }
        return iRepository.save(persona);
    }

    @Override
    public Usuarios buscarPorPK(String pk) {
        return null;
    }

    @Override
    public Usuarios buscarPorId(Integer id) {
        return null;
    }

    public Usuarios buscarPorDocumento(String id) throws UsuarioException{
        Optional<Usuarios> ret = iRepository.findByDocumento(id);
        if(ret.isPresent())
        {
            return ret.get();
        }
        else
            throw new UsuarioException("No existe un usuario con ese documento.");
    }


    public Usuarios buscarUsuario(String documento) throws UsuarioException {
        Optional<Usuarios> ret = iRepository.findByDocumento(documento);
        if(ret.isPresent())
        {
            return ret.get();
        }
        else
            throw new UsuarioException("No existe una usuario con ese documento.");
    }
    public void registrarUsuario(Usuarios usuarios) throws UsuarioException {
        Usuarios existe = buscarUsuario(usuarios.getDocumento());
        //no chequea si existe
        Usuarios existeDos = buscaPorEmail(usuarios.getEmail());
        if(existe != null || existeDos != null)
            throw new UsuarioException("Ya existe un usuario con ese email o documento.");

        guardar(usuarios);
    }
    public void cambiarContrasenia(Usuarios usuarios) throws UsuarioException {
        Usuarios p = buscarUsuario(usuarios.getDocumento());
        p.setContrasenia(usuarios.getContrasenia().trim());
        guardar(p);
    }

    public void agregarUsuario(Usuarios persona) throws UsuarioException{

        try {
            Usuarios existe = buscarUsuario(persona.getDocumento());
            Usuarios existeDos = buscaPorEmail(persona.getEmail());
            if(existe != null || existeDos != null)
                throw new UsuarioException("Ya existe un vecino con ese documento o email.");
        }
        catch (UsuarioException ex){
            System.out.println("entra al catch");
            guardar(persona);

        }
    }

    public Usuarios buscaPorEmail(String mail) throws UsuarioException {
        Optional<Usuarios> ret = iRepository.findByEmail(mail);
        return ret.orElse(null);
    }

}