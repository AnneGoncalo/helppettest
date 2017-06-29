package br.edu.ifrn.helppet.dominio;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author anne
 */
@Getter
@Setter
@ToString(exclude = "foto")
@EqualsAndHashCode(exclude = {"id", "foto", "nascimento", "localizacao"})
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@SequenceGenerator(sequenceName = "seq_usuario", name = "ID_SEQUENCE", allocationSize = 1)
public class Usuario implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE")
    private Long id;
    
    private String nome;
    
    private String email;
    
    private String senha;
    
    private String foto;
    
    private String nascimento;
    
    private String localizacao;
    
    private String telefone;
    
    @ManyToOne
    private Permissao permissao;
    
    @OneToMany(mappedBy = "responsavel")
    private Set<Animal> animais;
    
    @OneToMany(mappedBy = "responsavel")
    private Set<Evento> eventos;
    
    @OneToMany(mappedBy = "responsavel")
    private Set<Anuncio> anuncios;
    
    @OneToMany(mappedBy = "usuario")
    private Set<Experiencia> experiencias;
    
}
