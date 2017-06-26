package br.edu.ifrn.helppet.dominio;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 *
 * @author camila
 */
@Getter
@Setter
@ToString(exclude = "foto")
@EqualsAndHashCode(exclude = {"foto", "nascimento", "localizacao"})
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Usuario {

    private String nome;
    
    private String email;
    
    private String senha;
    
    private String foto;
    
    private String nascimento;
    
    private String localizacao;
    
    private String telefone;
    
    private Permissao permissao;
    
}
