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
 * @author camila
 */
@Getter
@Setter
@ToString(exclude = "fotoAnimal")
@EqualsAndHashCode(exclude = "fotoAnimal")
@RequiredArgsConstructor
public class Animal {

    private int idAnimal;
    private String nomeAnimal;
    private String especie;
    private String raca;
    private String idade;
    private String sexo;
    private String descricaoAnimal;
    private String fotoAnimal;
    private String tipoAnimal;
    private Date cadastro;
    private boolean statusAnimal;
    private Usuario responsavel;
    private String localizacao;

   
    
}
