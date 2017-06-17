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
@ToString()
@EqualsAndHashCode()
@RequiredArgsConstructor
public class Usuario {

    private String nomeUsuario;
    private String email;
    private String senha;
    private String dataNascimento;
    private String foto;
    private String localizacao;
    private String telefone;
    private int idPermissao;
    
    
}
