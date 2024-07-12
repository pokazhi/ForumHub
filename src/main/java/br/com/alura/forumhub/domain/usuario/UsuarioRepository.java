package br.com.alura.forumhub.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<br.com.alura.forumhub.domain.usuario.Usuario, Long> {

    UserDetails findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("select u from Usuario u where u.email = :email")
    br.com.alura.forumhub.domain.usuario.Usuario getReferenceByEmail(@Param("email") String email);
}
