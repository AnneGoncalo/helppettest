package br.edu.ifrn.helppet.comunicacao;

import br.edu.ifrn.helppet.dominio.Denuncia;
import br.edu.ifrn.helppet.validacao.DenunciaVL;
import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author anne
 */
@RestController
@RequestMapping("/rest/denuncia")
public class DenunciaService {

    @GET
    public List<Denuncia> Listar() {
        DenunciaVL servico = new DenunciaVL();
        return servico.ListarDenuncia();
    }

    @POST
    public String cadastrarDenuncia(String json) {
        Gson gson = new Gson();
        Denuncia a = gson.fromJson(json, Denuncia.class);
        DenunciaVL servico = new DenunciaVL();
        servico.CadastrarDenuncia(a);
        return "Ok";
    }
}
