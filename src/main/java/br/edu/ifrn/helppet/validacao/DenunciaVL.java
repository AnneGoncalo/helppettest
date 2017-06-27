package br.edu.ifrn.helppet.validacao;

import br.edu.ifrn.helppet.dominio.Denuncia;
import br.edu.ifrn.helppet.persistencia.PersistenciaGamb;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author camila
 */
public class DenunciaVL {

    private final int tamanhoFrase = 255;
    private final int tamanhoTitulo = 30;
    private final ArrayList<String> tipos = new ArrayList<>();

    PersistenciaGamb dao;

    public DenunciaVL() {
        tipos.add("abandono");
        tipos.add("maus tratos");
        tipos.add("outro");
        dao = new PersistenciaGamb();
    }
    
    public List<Denuncia> ListarDenuncia(){
	return dao.ListarDenuncias();
    }

    public String cadastrarDenuncia(Denuncia d) {

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

    }
    

    private String validarTitulo(Denuncia d) {
        if (d.getTitulo() != null) {
            String palavraSemEspaco = d.getTitulo().trim();
            if (!d.getTitulo().isEmpty() && (palavraSemEspaco.length() != 0)) {
                if (d.getTitulo().length() <= tamanhoTitulo) {
                    return "OK";
                } else {
                    return "Título muito grande";
                }
            } else {
                return "Informe um título válido";
            }
        } else {
            return "Título é um campo obrigatório";
        }
    }

    private String validarDescricao(Denuncia d) {
        if (d.getDescricao() != null) {
            String palavraSemEspaco = d.getDescricao().trim();
            if (!d.getDescricao().isEmpty() && (palavraSemEspaco.length() != 0)) {
                if (d.getDescricao().length() <= tamanhoFrase) {
                    return "OK";
                } else {
                    return "Descrição muito grande";
                }
            } else {
                return "Informe uma descrição válida";
            }
        } else {
            return "Descrição é um campo obrigatório";
        }
    }

    private String validarTipo(Denuncia d) {
        if (d.getTipo() != null) {
            if (!d.getTipo().isEmpty()) {
                if (tipos.contains(d.getTipo())) {
                    return "OK";
                } else {
                    return "Tipo inválido";
                }
            } else {
                return "Tipo inválido";
            }
        } else {
            return "Tipo inválido";
        }
    }

    private String validarLocalizacao(Denuncia d) {
        if (d.getLocalizacao() != null) {
            String palavraSemEspaco = d.getLocalizacao().trim();
            if (!d.getLocalizacao().isEmpty() && (palavraSemEspaco.length() != 0)) {
                if (d.getLocalizacao().length() <= tamanhoFrase) {
                    return "OK";
                } else {
                    return "Localização muito grande";
                }
            } else {
                return "Informe uma localização válida";
            }
        } else {
            return "Localização é um campo obrigatório";
        }
    }
    
    public String listarPorTipo(String tipo){
        if(tipos.contains(tipo)){
            dao.listarPorTipo(tipo);
            return "OK";
        } else{
            return "Tipo inválido";
        }
    }
    
    public String editarDenuncia(Denuncia d) {

        if (validarTitulo(d).equals("OK")) {
            if (validarTipo(d).equals("OK")) {
                if (validarDescricao(d).equals("OK")) {
                    if (validarLocalizacao(d).equals("OK")) {
                        dao.editarDenuncia(d);
                        return "Editado com sucesso";
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

    }
    

}
