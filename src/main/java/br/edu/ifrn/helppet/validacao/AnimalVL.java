package br.edu.ifrn.helppet.validacao;

import br.edu.ifrn.helppet.dominio.Animal;
import br.edu.ifrn.helppet.dominio.Usuario;
import br.edu.ifrn.helppet.persistencia.PersistenciaGamb;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author camila
 */
public class AnimalVL {

    private final int nomeGrande = 30;
    private final int nomePequeno = 3;
    private final int tamanhoFrase = 255;
    private final List<String> tipo = new ArrayList();
    private final List<String> especies = new ArrayList();
    private final List<String> idades = new ArrayList();
    private final List<String> sexos = new ArrayList();
    private final List<String> racas = new ArrayList();

    PersistenciaGamb dao;

    public AnimalVL() {
        // Tipos válidos
        tipo.add("adoção");
        tipo.add("perdido");
        tipo.add("resgate");
        // Espécies válidas
        especies.add("gato");
        especies.add("cachorro");
        especies.add("outra");
        // Idades válidas
        idades.add("0 a 6 meses");
        idades.add("6 a 12 meses");
        idades.add("1 a 2 anos");
        idades.add("2 a 5 anos");
        idades.add("mais de 5 anos");
        // Sexo válido
        sexos.add("f");
        sexos.add("m");
        // Raças válidas
        racas.add("viralata");
        racas.add("srd");
        racas.add("outra");

        dao = new PersistenciaGamb();
    }

    public String cadastrarAnimal(Animal a) {

        if (validarResponsavel(a).equals("OK")) {
            if (validarLocal(a).equals("OK")) {
                if (validarDescricao(a).equals("OK")) {
                    if (validarRaca(a).equals("OK")) {
                        if (validarSexo(a).equals("OK")) {
                            if (validarIdade(a).equals("OK")) {
                                if (validarEspecie(a).equals("OK")) {
                                    if (validarTipo(a).equals("OK")) {
                                        if (validarNome(a).equals("OK")) {
                                            dao.cadastrarAnimal(a);
                                            return "Cadastrado com sucesso";
                                        } else {
                                            return validarNome(a);
                                        }
                                    } else {
                                        return validarTipo(a);
                                    }
                                } else {
                                    return validarEspecie(a);
                                }
                            } else {
                                return validarIdade(a);
                            }
                        } else {
                            return validarSexo(a);
                        }
                    } else {
                        return validarRaca(a);
                    }
                } else {
                    return validarDescricao(a);
                }
            } else {
                return validarLocal(a);
            }
        } else {
            return validarResponsavel(a);
        }

    }

    private String validarResponsavel(Animal a) {
              
        if (a.getResponsavel() != null) {
            if (dao.ListarUsuarios().contains(a.getResponsavel())) {
                if (a.getResponsavel().getNome() != null) {
                    if (a.getResponsavel().getEmail() != null) {
                        if (a.getResponsavel().getLocalizacao() != null) {
                            if (a.getResponsavel().getTelefone() != null) {
                                return "OK";
                            } else {
                                return "Telefone do responsável não pode ser nulo";
                            }
                        } else {
                            return "Localização do responsável não pode ser nulo";
                        }
                    } else {
                        return "E-mail do responsável não pode ser nulo.";
                    }
                } else {
                    return "Nome do responsável não pode ser nulo";
                }
            } else {
                return "Responsável não cadastrado";
            }
        } else {
            return "O animal tem que ter um responsável";
        }
    }

    private String validarLocal(Animal a) {
        if (a.getLocalizacao() != null) {
            String palavraSemEspaco = a.getLocalizacao().trim();
            if (!a.getLocalizacao().isEmpty() && (palavraSemEspaco.length() != 0)) {
                if (a.getLocalizacao().length() <= tamanhoFrase) {
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

    private String validarDescricao(Animal a) {
        if (a.getDescricao() != null) {
            String palavraSemEspaco = a.getDescricao().trim();
            if (!a.getDescricao().isEmpty() && (palavraSemEspaco.length() != 0)) {
                if (a.getDescricao().length() <= tamanhoFrase) {
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

    private String validarRaca(Animal a) {
        if (a.getRaca() != null) {
            if (!a.getRaca().isEmpty()) {
                if (racas.contains(a.getRaca())) {
                    return "OK";
                } else {
                    return "Raça inválida";
                }
            } else {
                return "Raça inválida";
            }
        } else {
            return "Raça inválida";
        }
    }

    private String validarSexo(Animal a) {
        if (a.getSexo() != null) {
            if (!a.getSexo().isEmpty()) {
                if (sexos.contains(a.getSexo())) {
                    return "OK";
                } else {
                    return "Sexo inválido";
                }
            } else {
                return "Sexo inválido";
            }
        } else {
            return "Sexo inválido";
        }
    }

    private String validarIdade(Animal a) {
        if (a.getIdade() != null) {
            if (!a.getIdade().isEmpty()) {
                if (idades.contains(a.getIdade())) {
                    return "OK";
                } else {
                    return "Idade inválida";
                }
            } else {
                return "Idade inválida";
            }
        } else {
            return "Idade inválida";
        }
    }

    private String validarEspecie(Animal a) {
        if (a.getEspecie() != null) {
            if (!a.getEspecie().isEmpty()) {
                if (especies.contains(a.getEspecie())) {
                    return "OK";
                } else {
                    return "Espécie inválida";
                }
            } else {
                return "Espécie inválida";
            }
        } else {
            return "Espécie inválida";
        }
    }

    private String validarTipo(Animal a) {
        if (a.getTipo() != null) {
            if (!a.getTipo().isEmpty()) {
                if (tipo.contains(a.getTipo())) {
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

    private String validarNome(Animal a) {
        int cont = 0;
        if (a.getNome() == null) {
            a.setNome("Pet ");
            return "OK";
        } else {
            if (a.getNome().length() >= nomePequeno && a.getNome().length() <= nomeGrande) {
                for (int i = 0; i < a.getNome().length(); i++) {
                    if (Character.isLetter(a.getNome().charAt(i))) {
                        cont++;
                    }
                }
                if (cont >= nomePequeno) {
                    return "OK";
                } else {
                    return "O nome do animal deve ter, no mínimo, 3 letras";
                }
            } else {
                if (a.getNome().length() < nomePequeno) {
                    return "Nome muito pequeno";
                } else {
                    return "Nome muito grande";
                }
            }
        }
    }
    
    private String validarEspecieListagem(String especie){
        if(especies.contains(especie) || especie == null){
            return "OK";
        } else{
            return "Espécie inválida";
        }
    }
    
    private String validarIdadeListagem(String idade){
        if(idades.contains(idade) || idade == null){
            return "OK";
        } else{
            return "Idade inválida";
        }
    }
    
    private String validarRacaListagem(String raca){
        if(racas.contains(raca) || raca == null){
            return "OK";
        } else{
            return "Raça inválida";
        }
    }
    
    private String validarSexoListagem(String sexo){
        if(sexos.contains(sexo) || sexo == null){
            return "OK";
        } else{
            return "Sexo inválido";
        }
    }
    
    
    public String validacaoListagem(String especie, String idade, String raca, String sexo){
        if(validarSexoListagem(sexo).equals("OK")){
            if(validarRacaListagem(raca).equals("OK")){
                if(validarIdadeListagem(idade).equals("OK")){
                    if(validarEspecieListagem(especie).equals("OK")){
                        dao.filtragemAnimal(especie, idade, raca, sexo);
                        return "OK";
                    } else {
                        return validarEspecieListagem(especie);
                    }
                } else {
                    return validarIdadeListagem(idade);
                }
            } else {
                return validarRacaListagem(raca);
            }
        } else {
            return validarSexoListagem(sexo);
        }
        
    }
    
}
