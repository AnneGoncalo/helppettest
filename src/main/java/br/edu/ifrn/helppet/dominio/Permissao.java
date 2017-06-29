package br.edu.ifrn.helppet.dominio;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

/**
 *
 * @author anne
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(of= "nome")
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@SequenceGenerator(sequenceName = "seq_permissao", name = "ID_SEQUENCE", allocationSize = 1)
public class Permissao implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE")
    private Long id;
    
    private String nome;
    
    private String descricao;
    
    @Singular
    @OneToMany(mappedBy = "permissao")
    private Set<Usuario> usuarios;
    
}
