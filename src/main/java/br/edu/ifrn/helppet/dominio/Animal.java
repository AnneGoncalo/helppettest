package br.edu.ifrn.helppet.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;

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
@ToString(exclude = {"foto", "cadastro"})
@EqualsAndHashCode(exclude = {"id", "descricao", "foto", "cadastro", "localizacao"})
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@SequenceGenerator(sequenceName = "seq_animal", name = "ID_SEQUENCE", allocationSize = 1)
public class Animal implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE")
    private Long id;

    private String nome;
    
    private String tipo;
    
    private String especie;
    
    private String raca;
    
    private String idade;
    
    private String sexo;
    
    private String descricao;
    
    private String foto;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date cadastro;
    
    private boolean status;
    
    private String localizacao;
    
    @ManyToOne
    private Usuario responsavel;

    public boolean isEmpty(){
	return !(this.nome != null && this.tipo != null && this.especie != null && this.raca != null && this.idade != null && this.sexo != null && this.localizacao != null);
    }
    
}
