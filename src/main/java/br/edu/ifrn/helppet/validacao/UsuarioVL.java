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

import br.edu.ifrn.helppet.dominio.Usuario;

/**
 *
 * @author camila
 */
public class UsuarioVL {
    
    private final int nomeGrande = 30;
    
    private String validarEmail(Usuario u){
        boolean temEspaco = false;
        boolean temArroba = false;
        int posicaoDoArroba = 0;
        int posicaoDoPonto = 0;

        if (u.getEmail() != null) {
            String palavraSemEspaco = u.getEmail().trim();
            if (!u.getEmail().isEmpty() && (palavraSemEspaco.length() != 0)) {
                for (int i = 0; i < u.getEmail().length(); i++) {
                    if (u.getEmail().charAt(i) == ' ') {
                        temEspaco = true;
                        break;
                    }
                }
                if (!temEspaco) {
                    int cont_arroba = 0;
                    for (int i = 0; i < u.getEmail().length(); i++) {
                        if (u.getEmail().charAt(i) == '@') {
                            cont_arroba++;
                            posicaoDoArroba = i;
                        }
                    }
                    int cont_ponto = 0;
                    if (cont_arroba == 1) {
                        if (posicaoDoArroba > 0) {
                            for (int i = posicaoDoArroba; i < u.getEmail().length(); i++) {
                                if (u.getEmail().charAt(i) == '.') {
                                    cont_ponto++;
                                    posicaoDoPonto = i;
                                }
                            }

                            if (cont_ponto == 1) {
                                
                                if((posicaoDoPonto-posicaoDoArroba) >= 2){
                                    
                                    if(posicaoDoPonto != u.getEmail().length()-1){
                                        return "OK";
                                    } else {
                                        return "Informe um e-mail válido";
                                    }
                                    
                                } else {
                                    return "Informe um e-mail válido";
                                }
                                
                            } else {
                                return "Informe um e-mail válido";
                            }

                        } else {
                            return "Informe um e-mail válidoa";
                        }
                    } else {
                        return "Informe um e-mail válido";
                    }
                } else {
                    return "Informe um e-mail válido";
                }
            } else {
                return "Informe um e-mail válido";
            }
        } else {
            return "E-mail é um campo obrigatório";
        }
    }
    
    private String validarNome(Usuario u) {
        if (u.getNome() != null) {
            String palavraSemEspaco = u.getNome().trim();
            if (!u.getNome().isEmpty() && (palavraSemEspaco.length() != 0)) {
                if (u.getNome().length() <= nomeGrande) {
                    return "OK";
                } else {
                    return "Nome muito grande";
                }
            } else {
                return "Informe um nome válido";
            }
        } else {
            return "Nome é um campo obrigatório";
        }
    }
    
    
    private String validarSenha(Usuario u) {
        if (u.getSenha() != null) {
            String palavraSemEspaco = u.getNome().trim();
            if (!u.getSenha().isEmpty() && (palavraSemEspaco.length() != 0)) {
                if(u.getSenha().length() >= 6){
                    if(u.getSenha().length() <= 30){
                        return "OK";
                    } else {
                        return "Senha muito grande";
                    }
                } else {
                    return "Senha muito pequena";
                }
            } else {
                return "Informe uma senha válida";
            }
        } else {
            return "Senha é um campo obrigatório";
        }
    }
    
    
}
