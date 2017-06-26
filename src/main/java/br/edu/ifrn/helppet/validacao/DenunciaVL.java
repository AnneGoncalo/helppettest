package br.edu.ifrn.helppet.validacao;

import br.edu.ifrn.helppet.dominio.Denuncia;
import br.edu.ifrn.helppet.persistencia.PersistenciaGamb;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tat
 */
public class DenunciaVL {

    private final int tamanhoFrase = 255;
    private final int tamanhoTitulo = 30;
    private final ArrayList<String> tipo = new ArrayList<>();
    
    PersistenciaGamb dao = new PersistenciaGamb();

    public DenunciaVL() {
        tipo.add("Abandono");         // Tipos válidos
        tipo.add("Maus Tratos");      // Tipos válidos
        tipo.add("Outro");           // Tipos válidos
    }
    
    public List<Denuncia> ListarDenuncia(){
	return dao.ListarDenuncias();
    }
    
    public String CadastrarDenuncia(Denuncia d) {
        if (d != null){
	    if(!d.isEmpty()){
		if (validarTitulo(d).equals("OK")) {
		    if (validarTipo(d).equals("OK")) {
			if (validarDescricao(d).equals("OK")) {
			    if (validarLocalizacao(d).equals("OK")) {
				dao.cadastrarDenuncia(d);
				return "Cadastrado com sucesso";
			    } else {
				return validarLocalizacao(d);
			    }
			} else {
			    return validarDescricao(d);
			}
		    } else {
			return validarTipo(d);
		    }
		} else {
		    return validarTitulo(d);
		}
	    } else {
		return "Objeto vazio";
	    }
	} else {
	    return "Objeto nulo";
	}
    }
    
    private String validarTitulo(Denuncia d) {
        if (d.getTitulo() != null) {
            if (!d.getTitulo().isEmpty()) {
                if (d.getTitulo().length() <= tamanhoTitulo) 
                    return "OK";
                else 
                    return "Tamanho maior que o esperado: " + d.getTitulo().length();
            } 
	    else 
                return "Titulo vazio";
        } 
	else
            return "Campo nulo";
    }

    private String validarDescricao(Denuncia d) {
        if (d.getDescricao() != null) {
            if (!d.getDescricao().isEmpty()) {
                if (d.getDescricao().length() <= tamanhoFrase) 
                    return "OK";
                else
                    return "Tamanho maior que o esperado: " + d.getDescricao().length();
            } 
	    else 
                return "Descrição vazio";
        } 
	else
            return "Campo nulo";
    }

    private String validarTipo(Denuncia d) {
	if(d.getTipo() != null) {
	    if (!d.getTipo().isEmpty()) {
		if (tipo.contains(d.getTipo()))
		    return "OK";
		else
		    return "Campo inválido: " + d.getTipo();
	    }
	    else
		return "Tipo vazio";
	    }
	else
	    return "Campo nulo";
    }

    private String validarLocalizacao(Denuncia d) {
        if (d.getLocalizacao() != null) {
            if (!d.getLocalizacao().isEmpty()) {
                if (d.getLocalizacao().length() <= tamanhoFrase) {
                    return "OK";
                } else {
                    return "Tamanho maior que o esperado: " + d.getLocalizacao().length();
                }
            } else {
                return "Localização vazio";
            }
        } else {
            return "Campo nulo";
        }
    }

   /* public static void main(String[] args) { // para teste
        Denuncia d = new Denuncia(0, "titulo", "frase", "foto", "Abandono", "frase");
        DenunciaBL den = new DenunciaBL();

        System.out.println(den.cadastrarDenuncia(d));
    }*/
}
