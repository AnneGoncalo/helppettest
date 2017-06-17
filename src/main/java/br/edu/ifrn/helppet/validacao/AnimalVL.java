package br.edu.ifrn.helppet.validacao;

import br.edu.ifrn.helppet.dominio.Animal;
import br.edu.ifrn.helppet.persistencia.UsuariosCadastradosGambiarra;
import java.util.ArrayList;

/**
 *
 * @author camila
 */
public class AnimalVL {

    private final ArrayList<String> tipo = new ArrayList<>();
    private final ArrayList<String> especie = new ArrayList<>();
    private final ArrayList<String> idade = new ArrayList<>();
    private final ArrayList<String> sexo = new ArrayList<>();
    private final ArrayList<String> raca = new ArrayList<>();
    
    
    UsuariosCadastradosGambiarra dao;

    public AnimalVL() {
        // Tipos válidos
        tipo.add("Adoção");
        tipo.add("Perdido");
        tipo.add("Resgate");
        // Espécies válidas
        especie.add("Gato");
        especie.add("Cachorro");
        especie.add("Outra");
        // Idades válidas
        idade.add("0 a 6 meses");
        idade.add("6 a 12 meses");
        idade.add("1 a 2 anos");
        idade.add("2 a 5 anos");
        idade.add("Mais de 5 anos");
        // Sexo válido
        sexo.add("F");
        sexo.add("M");
        // Raças válidas
        raca.add("Viralata");
        raca.add("SRD");
        raca.add("Outra");

        dao = new UsuariosCadastradosGambiarra();
    }
    
    
    public String cadastrarAnimal(Animal a){
        if(a != null){
            if(!a.isEmpty()){
                if(validarResponsavel(a).equals("OK")){
                    if(validarLocal(a).equals("OK")){ 
                        if(validarDescricao(a).equals("OK")){
                            if(validarRaca(a).equals("OK")){
                                if(validarSexo(a).equals("OK")){
                                    if(validarIdade(a).equals("OK")){
                                        if(validarEspecie(a).equals("OK")){
                                            if(validarTipo(a).equals("OK")){
                                                if(validarNome(a).equals("OK")){
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
            } else {
                return "Campos vazios";
            }
        } else {
            return "Objeto nulo";
        }
    }

    
    private String validarResponsavel(Animal a) {
        if(a.getResponsavel() != null){
            if(dao.ListarUsuarios().contains(a.getResponsavel())){
                if(a.getResponsavel().getNomeUsuario() != null){
                    if(a.getResponsavel().getEmail() != null){
                        if(a.getResponsavel().getCpfcnpj() != null){
                            if(a.getResponsavel().getLocalizacao() != null){
                                if(a.getResponsavel().getTelefone() != null){
                                    return "OK";
                                } else {
                                    return "Telefone do responsável não pode ser nulo";
                                }
                            } else {
                                return "Localização do responsável não pode ser nulo";
                            }
                        } else {
                            return "CPF do responsável não pode ser nulo";
                        }
                    } else {
                        return "E-mail do responsável não pode ser nulo.";
                    }
                } else {
                    return "Nome do responsável não pode ser nulo";
                }
            } else {
                return "Usuário não cadastrado";
            }
        } else{
            return "O responsável não pode ser nulo";
        }
    }
    
    private String validarLocal(Animal a) {
        if (a.getLocalizacao() != null) {
            if (!a.getLocalizacao().isEmpty()) {
                if (a.getLocalizacao().length() <= 255) {
                    return "OK";
                } else {
                    return "Tamanho maior que o esperado: " + a.getLocalizacao().length();
                }
            } else {
                return "Localização vazia";
            }
        } else {
            return "Campo nulo";
        }
    }

    private String validarDescricao(Animal a) {
        if (a.getDescricaoAnimal() != null) {
            if (!a.getDescricaoAnimal().isEmpty()) {
                if (a.getDescricaoAnimal().length() <= 255) {
                    return "OK";
                } else {
                    return "Tamanho maior que o esperado: " + a.getDescricaoAnimal().length();
                }
            } else {
                return "Descrição vazio";
            }
        } else {
            return "Campo nulo";
        }
    }

    private String validarRaca(Animal a) {
        if (a.getRaca() != null) {
            if (!a.getRaca().isEmpty()) {
                if (raca.contains(a.getRaca())) {
                    return "OK";
                } else {
                    return "Campo inválido: " + a.getRaca();
                }
            } else {
                return "Campo vazio";
            }
        } else {
            return "Campo nulo";
        }
    }

    private String validarSexo(Animal a) {
        if (a.getSexo() != null) {
            if (!a.getSexo().isEmpty()) {
                if (sexo.contains(a.getSexo())) {
                    return "OK";
                } else {
                    return "Campo inválido: " + a.getSexo();
                }
            } else {
                return "Campo vazio";
            }
        } else {
            return "Campo nulo";
        }
    }

    private String validarIdade(Animal a) {
        if (a.getIdade() != null) {
            if (!a.getIdade().isEmpty()) {
                if (idade.contains(a.getIdade())) {
                    return "OK";
                } else {
                    return "Campo inválido: " + a.getIdade();
                }
            } else {
                return "Campo vazio";
            }
        } else {
            return "Campo nulo";
        }
    }

    private String validarEspecie(Animal a) {
        if (a.getEspecie() != null) {
            if (!a.getEspecie().isEmpty()) {
                if (especie.contains(a.getEspecie())) {
                    return "OK";
                } else {
                    return "Campo inválido: " + a.getEspecie();
                }
            } else {
                return "Campo vazio";
            }
        } else {
            return "Campo nulo";
        }
    }

    private String validarTipo(Animal a) {
        if (a.getTipoAnimal() != null) {
            if (!a.getTipoAnimal().isEmpty()) {
                if (tipo.contains(a.getTipoAnimal())) {
                    return "OK";
                } else {
                    return "Campo inválido: " + a.getTipoAnimal();
                }
            } else {
                return "Campo vazio";
            }
        } else {
            return "Campo nulo";
        }
    }

    private String validarNome(Animal a) {
        int cont = 0;
        if (a.getNomeAnimal() == null) {
            a.setNomeAnimal("Pet " + a.getIdAnimal());
            return "OK";
        } else {
            if (a.getNomeAnimal().length() >= 3 && a.getNomeAnimal().length() <= 30) {
                for (int i = 0; i < a.getNomeAnimal().length(); i++) {
                    if (Character.isLetter(a.getNomeAnimal().charAt(i))) {
                        cont++;
                    }
                }
                if (cont >= 3) {
                    return "OK";
                } else {
                    return "O nome do animal deve ter no mínimo 3 letras";
                }
            } else {
                if (a.getNomeAnimal().length() < 3) {
                    return "O nome do animal deve conter no mínimo 3 caracteres";
                } else {
                    return "O nome do animal deve conter no máximo 30 caracteres";
                }
            }
        }
    }

}
