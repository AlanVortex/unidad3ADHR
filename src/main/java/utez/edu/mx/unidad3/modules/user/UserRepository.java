package utez.edu.mx.unidad3.modules.user;

//Este paso ya va implicito en la creacion del proyecto

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<BeanUser, Long> {
    Optional<BeanUser> findByUsernameAndPassword(String username, String password);
    Optional<BeanUser> findByUsername(String username);
}
