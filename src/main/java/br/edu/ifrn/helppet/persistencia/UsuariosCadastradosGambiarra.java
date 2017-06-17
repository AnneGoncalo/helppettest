package br.edu.ifrn.helppet.persistencia;

import br.edu.ifrn.helppet.dominio.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author camila
 */
public class UsuariosCadastradosGambiarra {

    List<Usuario> lista = new ArrayList();
    
    public UsuariosCadastradosGambiarra() {
        UsuariosCadastrados();
    }
    
    public List<Usuario> ListarUsuarios(){
	return lista;
    }
   
    
    public void UsuariosCadastrados(){
        Usuario usuario1 = new Usuario();
        usuario1.setNomeUsuario("Camila Jordana");
        usuario1.setEmail("camila@email.com");
        usuario1.setSenha("1234");
        usuario1.setDataNascimento("00/00/0000");
        usuario1.setIdUsuario(1);
        usuario1.setCpfcnpj("11111");
            
        lista.add(usuario1);
        
        Usuario usuario2 = new Usuario();
        usuario2.setNomeUsuario("Ana");
        usuario2.setEmail("ana@email.com");
        usuario2.setSenha("1234");
        usuario2.setDataNascimento("00/00/0000");
        usuario2.setIdUsuario(2);
        usuario2.setCpfcnpj("22222");
        
        
        lista.add(usuario2);
        
        
        Usuario usuario3 = new Usuario();
        usuario3.setNomeUsuario("Anne");
        usuario3.setEmail("anne@email.com");
        usuario3.setSenha("1234");
        usuario3.setDataNascimento("00/00/0000");
        usuario3.setIdUsuario(3);
        usuario3.setCpfcnpj("33333");
        
        
        lista.add(usuario3);
        
        
        Usuario usuario4 = new Usuario();
        usuario4.setNomeUsuario("Tatiana");
        usuario4.setEmail("tat@email.com");
        usuario4.setSenha("1234");
        usuario4.setDataNascimento("00/00/0000");
        usuario4.setIdUsuario(4);
        usuario4.setCpfcnpj("44444");
        
        
        lista.add(usuario4);
        
        
    }
    
}
