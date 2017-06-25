package br.edu.ifrn.helppet.comunicacao;

import br.edu.ifrn.helppet.dominio.Denuncia;
import br.edu.ifrn.helppet.validacao.DenunciaVL;
import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author anne
 */
@RestController
@RequestMapping("/rest/denuncia")
public class DenunciaService {

    @RequestMapping(method = RequestMethod.GET)
    public List<Denuncia> Listar() {
        DenunciaVL servico = new DenunciaVL();
        return servico.ListarDenuncia();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String cadastrarDenuncia(@RequestBody String json) {
//        Gson gson = new Gson();
//        Denuncia a = gson.fromJson(json, Denuncia.class);
//        DenunciaVL servico = new DenunciaVL();
//        servico.CadastrarDenuncia(a);
          JSONParser parser = new JSONParser();
        return "Ok";
    }
}
