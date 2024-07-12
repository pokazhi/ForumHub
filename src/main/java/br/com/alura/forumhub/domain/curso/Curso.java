package br.com.alura.forumhub.domain.curso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private br.com.alura.forumhub.domain.curso.Categoria categoria;

    public Curso(CursoDTO cursoDTO) {
        this.id = null;
        this.nome = cursoDTO.nome();
        this.categoria = cursoDTO.categoria();
    }
}
