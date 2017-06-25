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
        usuario1.setNome("Camila Jordana");
        usuario1.setEmail("camila@email.com");
        usuario1.setSenha("1234");
        usuario1.setNascimento("00/00/0000");
            
        lista.add(usuario1);
        
        Usuario usuario2 = new Usuario();
        usuario2.setNome("Ana");
        usuario2.setEmail("ana@email.com");
        usuario2.setSenha("1234");
        usuario2.setNascimento("00/00/0000");
            
        lista.add(usuario2);
   
	Usuario usuario3 = new Usuario();
        usuario3.setNome("Anne");
        usuario3.setEmail("anne@email.com");
        usuario3.setSenha("1234");
        usuario3.setNascimento("00/00/0000");
        
        lista.add(usuario3);
        
        Usuario usuario4 = new Usuario();
        usuario4.setNome("Tatiana");
        usuario4.setEmail("tat@email.com");
        usuario4.setSenha("1234");
        usuario4.setNascimento("00/00/0000");

        lista.add(usuario4);
        
    }
    
}
