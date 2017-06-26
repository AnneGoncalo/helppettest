package br.edu.ifrn.helppet.validacao;

import br.edu.ifrn.helppet.dominio.Encontro;
import br.edu.ifrn.helppet.persistencia.PersistenciaGamb;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author camila
 */
public class EncontroVL {

    PersistenciaGamb dao;

    public EncontroVL() {

        dao = new PersistenciaGamb();
    }

    
    public String cadastrarEncontro(Encontro e){
        if(e != null){
            if(!e.isEmpty()){
                if(validarAnimal(e).equals("OK")){
                    if(validarData(e).equals("OK")){
                        if(validacaoLocal(e).equals("OK")){
                            if(validarAdotante(e).equals("OK")){
                                dao.cadastrarEncontro(e);
                                return "Cadastrado com sucesso";
                            } else {
                                return validarAdotante(e);
                            }
                        } else {
                            return validacaoLocal(e);
                        }
                    } else {
                        return validarData(e);
                    }
                } else {
                    return validarAnimal(e);
                }
            } else {
                return "Campos vazios";
            }
        } else {
            return "Objeto nulo";
        }
    }
    
    private String validarAnimal(Encontro e) {
        if (e.getAnimal() != null) {
            if (dao.ListarAnimais().contains(e.getAnimal())) {
                return "OK";
            } else {
                return "Animal não encontrado ou não existe";
            }
        } else {
            return "Animal não encontrado ou não existe";
        }
    }

    private String validarData(Encontro e) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        // setLenient serve pra indicar que a conversão não deve dar margem a erros 
        //na String ("31/06/2013" será convertido para um Date apontando para "01/07/2013", por exemplo).
        formato.setLenient(false);
        try {
            String dataFormatada = formato.format(e.getDataHorario());
            Date dataAtual = new Date();
            if (e.getDataHorario().after(dataAtual)) {
                return "OK";
            } else {
                return "Por favor, insira uma data futura";
            }
        } catch (Exception ex) {
            return "Data inválida. Por favor, insira data válida";
        }
    }

    private String validacaoLocal(Encontro e) {
        if (e.getLocalizacao() != null) {
            if (!e.getLocalizacao().isEmpty()) {
                if (e.getLocalizacao().length() <= 255) {
                    return "OK";
                } else {
                    return "Tamanho maior que o esperado: " + e.getLocalizacao().length();
                }
            } else {
                return "Localização está vazia";
            }
        } else {
            return "Local do encontro é um campo obrigatório";
        }
    }

    private String validarAdotante(Encontro e) {
        if (e.getAdotante() != null) {
            if (dao.ListarPF().contains(e.getAdotante())) {
                if (e.getAdotante().getUsuario().getNome() != null) {
                    if (e.getAdotante().getUsuario().getEmail() != null) {
                        if (e.getAdotante().getCpf() != null) {
                            if (e.getAdotante().getUsuario().getLocalizacao() != null) {
                                if (e.getAdotante().getUsuario().getTelefone() != null) {
                                    return "OK";
                                } else {
                                    return "O adotante deve informar o número de telefone";
                                }
                            } else {
                                return "O adotante tem que ter uma localização";
                            }
                        } else {
                            return "CPF do adotante não pode ser nulo";
                        }
                    } else {
                        return "E-mail do adotante não pode ser nulo";
                    }
                } else {
                    return "Nome do adotante não pode ser nulo";
                }
            } else {
                return "Usuário não cadastrado";
            }
        } else {
            return "Adotante não pode ser nulo";
        }
    }

}
