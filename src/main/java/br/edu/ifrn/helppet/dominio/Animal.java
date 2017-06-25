package br.edu.ifrn.helppet.dominio;

import java.util.Date;
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
@EqualsAndHashCode(exclude = {"descricao", "foto", "cadastro", "localizacao"})
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Animal {

    private String nome;
    
    private String tipo;
    
    private String especie;
    
    private String raca;
    
    private String idade;
    
    private String sexo;
    
    private String descricao;
    
    private String foto;
    
    private Date cadastro;
    
    private boolean status;
    
    private String localizacao;

    public boolean isEmpty(){
	return !(this.nome != null && this.tipo != null && this.especie != null && this.raca != null && this.idade != null && this.sexo != null && this.localizacao != null);
    }
    
}
