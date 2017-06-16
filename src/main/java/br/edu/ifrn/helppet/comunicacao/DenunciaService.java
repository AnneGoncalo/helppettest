package br.edu.ifrn.helppet.comunicacao;

import br.edu.ifrn.helppet.dominio.Denuncia;
import br.edu.ifrn.helppet.validacao.DenunciaVL;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author anne
 */
@RestController
public class DenunciaService {
    
    @RequestMapping("/rest/denuncia")
    public List<Denuncia> Listar(){
	DenunciaVL servico = new DenunciaVL();
	return servico.ListarDenuncia();
    }
}
