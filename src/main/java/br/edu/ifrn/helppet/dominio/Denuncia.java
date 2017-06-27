package br.edu.ifrn.helppet.dominio;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author Ana Gonçalo
 */
@Getter
@Setter
@ToString(exclude = "foto")
@EqualsAndHashCode(exclude = {"descricao", "foto"})
@Builder
@Entity
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Denuncia implements Serializable, Comparable<Denuncia> {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String titulo;

    private String descricao;
    
    private String tipo;
    
    private String foto;
    
    private Date data;
    
    private String localizacao;
    
    public boolean isEmpty(){
	return !(this.titulo != null && this.descricao != null && this.tipo != null && this.localizacao != null);
    }

    @Override
    public int compareTo(Denuncia o) {
	int result = 0;
	if(this.titulo != null && o.titulo != null){
	    result = this.titulo.compareTo(o.titulo);
	}
	else if(this.titulo == null && o.titulo == null){
	    result = 0;
	}
	else if(this.titulo == null){
	    result = -1;
	}
	else {
	    result = 1;
	}
	return result;
    }
    
}
