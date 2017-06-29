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
import br.edu.ifrn.helppet.persistencia.PersistenciaGamb;

/**
 *
 * @author camila
 */
public class UsuarioVL {

    private final int nomeGrande = 30;
    private final int tamanhoFrase = 255;

    PersistenciaGamb dao;

    public UsuarioVL() {
        dao = new PersistenciaGamb();
    }

    public String cadastrarUsuario(Usuario u) {
        if (validarEmail(u).equals("OK")) {
            if (validarNome(u).equals("OK")) {
                if (validarSenha(u).equals("OK")) {
                    if (validarNascimento(u).equals("OK")) {
                        if (validarLocalizacao(u).equals("OK")) {
                            dao.cadastrarUsuario(u);
                            return "Cadastrado com sucesso";
                        } else {
                            return validarLocalizacao(u);
                        }
                    } else {
                        return validarNascimento(u);
                    }
                } else {
                    return validarSenha(u);
                }
            } else {
                return validarNome(u);
            }
        } else {
            return validarEmail(u);
        }
    }

    private String validarEmail(Usuario u) {
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

                            if (cont_ponto >= 1) {

                                if ((posicaoDoPonto - posicaoDoArroba) >= 2) {

                                    if (posicaoDoPonto != u.getEmail().length() - 1) {
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
                if (u.getSenha().length() >= 6) {
                    if (u.getSenha().length() <= 30) {
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

    // Formato: dd/MM/AAAA (10 caracteres na string)
    // Inventando a roda.. ¬¬
    public String validarNascimento(Usuario u) {
        if (u.getNascimento() != null && !u.getNascimento().equals("")) {
            if (u.getNascimento().length() == 10) {
                if ((u.getNascimento().charAt(0) == '1' || u.getNascimento().charAt(0) == '2' || u.getNascimento().charAt(0) == '3' || u.getNascimento().charAt(0) == '4' || u.getNascimento().charAt(0) == '5' || u.getNascimento().charAt(0) == '6' || u.getNascimento().charAt(0) == '7' || u.getNascimento().charAt(0) == '8' || u.getNascimento().charAt(0) == '9' || u.getNascimento().charAt(0) == '0') && (u.getNascimento().charAt(1) == '1' || u.getNascimento().charAt(1) == '2' || u.getNascimento().charAt(1) == '3' || u.getNascimento().charAt(1) == '4' || u.getNascimento().charAt(1) == '5' || u.getNascimento().charAt(1) == '6' || u.getNascimento().charAt(1) == '7' || u.getNascimento().charAt(1) == '8' || u.getNascimento().charAt(1) == '9' || u.getNascimento().charAt(1) == '0') && (u.getNascimento().charAt(3) == '1' || u.getNascimento().charAt(3) == '2' || u.getNascimento().charAt(3) == '3' || u.getNascimento().charAt(3) == '4' || u.getNascimento().charAt(3) == '5' || u.getNascimento().charAt(3) == '6' || u.getNascimento().charAt(3) == '7' || u.getNascimento().charAt(3) == '8' || u.getNascimento().charAt(3) == '9' || u.getNascimento().charAt(3) == '0') && (u.getNascimento().charAt(4) == '1' || u.getNascimento().charAt(4) == '2' || u.getNascimento().charAt(4) == '3' || u.getNascimento().charAt(4) == '4' || u.getNascimento().charAt(4) == '5' || u.getNascimento().charAt(4) == '6' || u.getNascimento().charAt(4) == '7' || u.getNascimento().charAt(4) == '8' || u.getNascimento().charAt(4) == '9' || u.getNascimento().charAt(4) == '0') && (u.getNascimento().charAt(6) == '1' || u.getNascimento().charAt(6) == '2' || u.getNascimento().charAt(6) == '3' || u.getNascimento().charAt(6) == '4' || u.getNascimento().charAt(6) == '5' || u.getNascimento().charAt(6) == '6' || u.getNascimento().charAt(6) == '7' || u.getNascimento().charAt(6) == '8' || u.getNascimento().charAt(6) == '9' || u.getNascimento().charAt(6) == '0') && (u.getNascimento().charAt(7) == '1' || u.getNascimento().charAt(7) == '2' || u.getNascimento().charAt(7) == '3' || u.getNascimento().charAt(7) == '4' || u.getNascimento().charAt(7) == '5' || u.getNascimento().charAt(7) == '6' || u.getNascimento().charAt(7) == '7' || u.getNascimento().charAt(7) == '8' || u.getNascimento().charAt(7) == '9' || u.getNascimento().charAt(7) == '0') && (u.getNascimento().charAt(8) == '1' || u.getNascimento().charAt(8) == '2' || u.getNascimento().charAt(8) == '3' || u.getNascimento().charAt(8) == '4' || u.getNascimento().charAt(8) == '5' || u.getNascimento().charAt(8) == '6' || u.getNascimento().charAt(8) == '7' || u.getNascimento().charAt(8) == '8' || u.getNascimento().charAt(8) == '9' || u.getNascimento().charAt(8) == '0') && (u.getNascimento().charAt(9) == '1' || u.getNascimento().charAt(9) == '2' || u.getNascimento().charAt(9) == '3' || u.getNascimento().charAt(9) == '4' || u.getNascimento().charAt(9) == '5' || u.getNascimento().charAt(9) == '6' || u.getNascimento().charAt(9) == '7' || u.getNascimento().charAt(9) == '8' || u.getNascimento().charAt(9) == '9' || u.getNascimento().charAt(9) == '0') && (u.getNascimento().charAt(2) == '/') && (u.getNascimento().charAt(5) == '/')) {
                    return "OK";
                } else {
                    return "Data de nascimento inválida";
                }
            } else {
                return "Data de nascimento inválida";
            }
        } else {
            return "OK";
        }
    }

    private String validarLocalizacao(Usuario u) {

        if (u.getLocalizacao() != null && !u.getLocalizacao().equals("")) {
            if (u.getLocalizacao().length() <= 255) {
                return "OK";
            } else {
                return "Localização muito grande";
            }
        } else {
            return "OK";
        }

    }

    public String editarUsuario(Usuario u) {

        if (validarNome(u).equals("OK")) {
            if (validarSenha(u).equals("OK")) {
                if (validarNascimento(u).equals("OK")) {
                    if (validarLocalizacao(u).equals("OK")) {
                        dao.cadastrarUsuario(u);
                        return "Editado com sucesso";
                    } else {
                        return validarLocalizacao(u);
                    }
                } else {
                    return validarNascimento(u);
                }
            } else {
                return validarSenha(u);
            }
        } else {
            return validarNome(u);
        }
    }
    
    
    // Verifica se a conta que será excluída pertence ao usuário que deseja excluí-la
    public String excluirConta(Usuario u1, Usuario u2){
        if(u1.equals(u2)){
            return "Conta excluída";
        } else {
            return "Conta não encontrada";
        }
    }
    
}


