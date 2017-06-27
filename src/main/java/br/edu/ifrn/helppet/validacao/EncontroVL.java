package br.edu.ifrn.helppet.validacao;

import br.edu.ifrn.helppet.dominio.Encontro;
import br.edu.ifrn.helppet.dominio.Usuario;
import br.edu.ifrn.helppet.persistencia.PersistenciaGamb;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author camila
 */
public class EncontroVL {

    PersistenciaGamb dao;
    private final int tamanhoFrase = 255;

    public EncontroVL() {

        dao = new PersistenciaGamb();
    }

    public String cadastrarEncontro(Encontro e) {

        if (validarAnimal(e).equals("OK")) {
            if (validarData(e).equals("OK")) {
                if (validarLocalizacao(e).equals("OK")) {
                    if (validarAdotante(e).equals("OK")) {
                        dao.cadastrarEncontro(e);
                        return "Cadastrado com sucesso";
                    } else {
                        return validarAdotante(e);
                    }
                } else {
                    return validarLocalizacao(e);
                }
            } else {
                return validarData(e);
            }
        } else {
            return validarAnimal(e);
        }

    }

    private String validarAnimal(Encontro e) {
        if (e.getAnimal() != null) {
            if (dao.ListarAnimais().contains(e.getAnimal())) {
                return "OK";
            } else {
                return "Animal não encontrado";
            }
        } else {
            return "Animal não encontrado";
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

    private String validarLocalizacao(Encontro e) {
        if (e.getLocalizacao() != null) {
            String palavraSemEspaco = e.getLocalizacao().trim();
            if (!e.getLocalizacao().isEmpty() && (palavraSemEspaco.length() != 0)) {
                if (e.getLocalizacao().length() <= tamanhoFrase) {
                    return "OK";
                } else {
                    return "Localização muito grande";
                }
            } else {
                return "Informe uma localização válida";
            }
        } else {
            return "Local do encontro é obrigatório";
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
                                    return "Telefone do adotante não pode ser nulo";
                                }
                            } else {
                                return "Localização do adotante não pode ser nulo";
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

    // Método pra verificar se o encontro pertence ao usuario 
    //** dúvida **
    public String listarEncontrosUsuario(Encontro encontro, Usuario usuario) {
        boolean testeUser = false, testeEnc = false;
        for (Usuario u : dao.ListarUsuarios()) {
            if (u.equals(usuario)) {
                testeUser = true;
            }
        }
        for (Encontro e : dao.ListarEncontros()) {
            if (e.equals(encontro)) {
                if (testeUser == true) {
                    if (encontro.getAdotante().equals(usuario)) {
                        // pega encontro no bd
                        testeEnc = true;
                        return "Ok";
                    }
                }
            }
        }
        if (testeUser == false) {
            return "Vínculo não corresponde";
        } else {
            if (testeEnc == true) {
                return "Ok";
            } else {
                return "Vínculo não corresponde";
            }
        }
    }

    public String editarEncontro(Encontro e) {

        if (validarData(e).equals("OK")) {
            if (validarLocalizacao(e).equals("OK")) {
                // Alteraçao da flague 'editado' no BD
                dao.cadastrarEncontro(e);
                return "Editado com sucesso";
            } else {
                return validarLocalizacao(e);
            }
        } else {
            return validarData(e);
        }

    }

    public String excluirEncontro(Encontro encontro, Usuario usuario) {

        for (Usuario u : dao.ListarUsuarios()) {
            if (u.equals(usuario)) {
                for (Encontro e : dao.ListarEncontros()) {
                    if (e.equals(encontro)) {
                        if (encontro.getAdotante().equals(usuario)) {
                            dao.excluirEncontro(e);
                            return "Encontro excluído";
                        }
                    }
                }
            }
        }

        return "Encontro não encontrado";

    }

}
