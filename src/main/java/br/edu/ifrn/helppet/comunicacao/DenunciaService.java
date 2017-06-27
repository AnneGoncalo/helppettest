package br.edu.ifrn.helppet.comunicacao;

import br.edu.ifrn.helppet.dominio.Denuncia;
import br.edu.ifrn.helppet.servico.DenunciaServico;
import br.edu.ifrn.helppet.validacao.DenunciaVL;
import java.util.List;
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
//	Denuncia a = new Denuncia();
//        a.setTitulo("denuncia Teste Persistencia");
//        a.setTipo("Outro");
//        a.setDescricao("descrição da denuncia teste de persistencia");
//        a.setLocalizacao("Natal, RN");
	
	DenunciaServico dao = new DenunciaServico();
	//dao.save(a);
	return (List<Denuncia>) dao.findAll();
        //DenunciaVL servico = new DenunciaVL();
        //return servico.ListarDenuncia();
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
