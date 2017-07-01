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

import br.edu.ifrn.helppet.dominio.Experiencia;
import br.edu.ifrn.helppet.persistencia.PersistenciaGamb;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author camila
 */
public class ExperienciaVL {

    private final int tamanhoNome = 45;
    private final int tamanhoTexto = 5000;

    private final List<String> tipos = new ArrayList();
    PersistenciaGamb dao;

    // QUAIS OS TIPOS DE EXPERIÊNCIAS?
    public ExperienciaVL() {
        // Tipos válidos
        tipos.add("tipo1");
        tipos.add("tipo2");
        tipos.add("tipo3");
        tipos.add("outro");

        dao = new PersistenciaGamb();
    }
    
    
    public String cadastrarExperiencia(Experiencia e){
        if(validarTitulo(e).equals("OK")){
            if(validarTipo(e).equals("OK")){
                if(validarTexto(e).equals("OK")){
                    if(validarResponsavel(e).equals("OK")){
                        dao.cadastrarExperiencia(e);
                        return "OK";
                    } else {
                        return validarResponsavel(e);
                    }
                } else {
                    return validarTexto(e);
                }
            } else {
                return validarTipo(e);
            }
        }else {
            return validarTitulo(e);
        }
    }

    private String validarTitulo(Experiencia e) {
        if (e.getTitulo() != null) {
            String palavraSemEspaco = e.getTitulo().trim();
            if (!e.getTitulo().isEmpty() && (palavraSemEspaco.length() != 0)) {
                if (e.getTitulo().length() < tamanhoNome) {
                    return "OK";
                } else {
                    return "Título muito grande";
                }
            } else {
                return "Informe um título válido";
            }
        } else {
            return "Título da experiência é um campo obrigatório";
        }
    }

    private String validarTipo(Experiencia e) {
        if (e.getTipo() != null) {
            if (!e.getTipo().isEmpty()) {
                if (tipos.contains(e.getTipo())) {
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

    private String validarTexto(Experiencia e) {
        if (e.getTexto() != null) {
            String palavraSemEspaco = e.getTexto().trim();
            if (!e.getTexto().isEmpty() && (palavraSemEspaco.length() != 0)) {
                if (e.getTitulo().length() < tamanhoTexto) {
                    return "OK";
                } else {
                    return "Texto muito grande";
                }
            } else {
                return "Informe um texto válido";
            }
        } else {
            return "O texto é um campo obrigatório";
        }
    }

    private String validarResponsavel(Experiencia e) {

        if (e.getUsuario() != null) {
            if (dao.ListarUsuarios().contains(e.getUsuario())) {
                if (e.getUsuario().getNome() != null) {
                    if (e.getUsuario().getEmail() != null) {
                        return "OK";

                    } else {
                        return "E-mail do usuário não pode ser nulo.";
                    }
                } else {
                    return "Nome do usuário não pode ser nulo";
                }
            } else {
                return "Responsável não cadastrado";
            }
        } else {
            return "A experiência tem que ter um responsável";
        }
    }

}
