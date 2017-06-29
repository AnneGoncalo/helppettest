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
                                        return "Tem que haver um caractere após o ponto";
                                    }
                                    
                                } else {
                                    return "Não pode haver um ponto logo após o arroba";
                                }
                                
                            } else {
                                return "Tem que haver 1 ponto após o arroba";
                            }

                        } else {
                            return "O primeiro caractere não pode ser o arroba";
                        }
                    } else {
                        return "O email não tem arroba ou tem mais te 1";
                    }
                } else {
                    return "E-mail não pode ter espaço.";
                }
            } else {
                return "Informe um email válido";
            }
        } else {
            return "E-mail não pode ser nulo";
        }
    }
    
}
