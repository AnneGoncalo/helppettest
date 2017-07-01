/*
 * Copyright 2017 HelpPet.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.edu.ifrn.helppet.validacao;

import br.edu.ifrn.helppet.dominio.Evento;
import br.edu.ifrn.helppet.dominio.Usuario;
import br.edu.ifrn.helppet.persistencia.PersistenciaGamb;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author camila
 */
public class EventoVL {

    private final int tamanhoNome = 45;
    private final int tamanhoFrase = 255;
    PersistenciaGamb dao;

    public EventoVL() {
        dao = new PersistenciaGamb();
    }

    public String cadastrarEvento(Evento e) {
        if (validarNome(e).equals("OK")) {
            if (validarData(e).equals("OK")) {
                if (validarLocalizacao(e).equals("OK")) {
                    if (validarDescricao(e).equals("OK")) {
                        if (validarResponsavel(e).equals("OK")) {
                            dao.cadastrarEvento(e);
                            return "Cadastrado com sucesso";
                        } else {
                            return validarResponsavel(e);
                        }
                    } else {
                        return validarDescricao(e);
                    }
                } else {
                    return validarLocalizacao(e);
                }
            } else {
                return validarData(e);
            }
        } else {
            return validarNome(e);
        }
    }

    private String validarNome(Evento e) {
        if (e.getNome() != null) {
            String palavraSemEspaco = e.getNome().trim();
            if (!e.getNome().isEmpty() && (palavraSemEspaco.length() != 0)) {
                if (e.getNome().length() < tamanhoNome) {
                    return "OK";
                } else {
                    return "Nome muito grande";
                }
            } else {
                return "Informe um nome válido";
            }
        } else {
            return "Nome do evento é um campo obrigatório";
        }
    }

    private String validarData(Evento e) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        // setLenient serve pra indicar que a conversão não deve dar margem a erros 
        //na String ("31/06/2013" será convertido para um Date apontando para "01/07/2013", por exemplo).
        formato.setLenient(false);
        try {
            String dataFormatada = formato.format(e.getData());
            Date dataAtual = new Date();
            if (e.getData().after(dataAtual)) {
                return "OK";
            } else {
                return "Por favor, insira uma data futura";
            }
        } catch (Exception ex) {
            return "Data inválida. Por favor, insira data válida";
        }
    }

    private String validarLocalizacao(Evento e) {
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
            return "Local é um campo obrigatório";
        }
    }

    private String validarDescricao(Evento e) {
        if (e.getDescricao() != null && !e.getData().equals("")) {
            if (e.getDescricao().length() <= tamanhoFrase) {
                return "OK";
            } else {
                return "Descrição muito grande";
            }
        } else {
            return "OK";
        }
    }

    private String validarResponsavel(Evento e) {

        if (e.getResponsavel() != null) {
            if (dao.ListarUsuarios().contains(e.getResponsavel())) {
                if (e.getResponsavel().getNome() != null) {
                    if (e.getResponsavel().getEmail() != null) {
                        if (e.getResponsavel().getLocalizacao() != null) {
                            if (e.getResponsavel().getTelefone() != null) {
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
            return "O evento tem que ter um responsável";
        }
    }

    public String editarEvento(Evento e) {
        if (validarNome(e).equals("OK")) {
            if (validarData(e).equals("OK")) {
                if (validarLocalizacao(e).equals("OK")) {
                    if (validarDescricao(e).equals("OK")) {

                        dao.cadastrarEvento(e);
                        return "Editado com sucesso";

                    } else {
                        return validarDescricao(e);
                    }
                } else {
                    return validarLocalizacao(e);
                }
            } else {
                return validarData(e);
            }
        } else {
            return validarNome(e);
        }
    }
    
    public String excluirEvento(Evento evento, Usuario usuario) {
        for (Usuario u : dao.ListarUsuarios()) {
            if (u.equals(usuario)) {
                for (Evento e : dao.ListarEventos()) {
                    if (e.equals(evento)) {
                        if (e.getResponsavel().equals(usuario)) {
                            dao.excluirEvento(e);
                            return "Evento excluído";
                        }
                    }
                }
            }
        }
        return "Evento não encontrado";

    }

}
