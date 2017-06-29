
package br.edu.ifrn.helppet.comunicacao;

import br.edu.ifrn.helppet.dominio.Denuncia;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Ana Gonçalo
 */
@Path("/teste")
public class TesteService {
    
    List<Denuncia> lista;
    
    public TesteService(){
        Denuncia a = Denuncia.builder().titulo("denuncia A").tipo("tipo A").build();
        lista.add(a);
    }
    
    @GET
    @Path("/buscar")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Denuncia> buscarTodos() {
        return lista;
    }
    
    @POST
    @Path("/cadastrar")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response cadastrar(Denuncia d) {
        try {
            Denuncia b = Denuncia.builder().titulo("denuncia B").tipo("tipo B").build();
            lista.add(b);
            return Response.status(200).entity("Contato Cadastrado").build();
        } catch (Exception e) {
            System.out.println("Erro: " + e.toString());
        }
        return Response.status(500).entity("Falha no Cadastro").build();
    }
    
}
