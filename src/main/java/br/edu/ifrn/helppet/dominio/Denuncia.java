package br.edu.ifrn.helppet.dominio;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Ana Gonçalo
 */
@Getter
@Setter
@ToString(exclude = "foto")
@EqualsAndHashCode(exclude = {"tipo", "foto"})
@RequiredArgsConstructor
public class Denuncia {
    
    private String titulo;

    private String descricao;
    
    private String tipo;
    
    private String foto;
    
    private Date data;
    
    private String localizacao;
    
    public boolean isEmpty(){
	return !(this.titulo != null && this.descricao != null && this.tipo != null && this.localizacao != null);
    }
}
