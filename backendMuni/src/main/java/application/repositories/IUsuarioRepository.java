package application.repositories;

import application.models.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsuarioRepository extends JpaRepository<Usuarios,Long> {
    public Optional<Usuarios> findByDocumento(String documento);
    public Optional<Usuarios>  findByEmail(String mail);

}
