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

import br.edu.ifrn.helppet.dominio.PessoaFisica;
import br.edu.ifrn.helppet.dominio.Usuario;
import br.edu.ifrn.helppet.persistencia.PersistenciaGamb;
import java.util.InputMismatchException;

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
                            if (validarTelefone(u).equals("OK")) {
                                dao.cadastrarUsuario(u);
                                return "Cadastrado com sucesso";
                            } else {
                                return validarTelefone(u);
                            }
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
    public String validarNascimento(Usuario u) {
        if (u.getNascimento() != null && !u.getNascimento().equals("")) {
            if (u.getNascimento().length() == 10) {
                char[] t = u.getNascimento().toCharArray();
                if (t[2] == '/' && t[5] == '/' && Character.isDigit(t[0]) && Character.isDigit(t[1]) && Character.isDigit(t[3]) && Character.isDigit(t[4]) && Character.isDigit(t[6]) && Character.isDigit(t[7]) && Character.isDigit(t[8]) && Character.isDigit(t[9])) {
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

    // Formato: (00)00000-0000 OU (00) 00000-0000
    public String validarTelefone(Usuario u) {

        if (u.getTelefone() != null && !u.getTelefone().equals("")) {
            if (u.getTelefone().length() == 14 || u.getTelefone().length() == 15) {

                char[] t = u.getTelefone().toCharArray();
                if (u.getTelefone().length() == 14) {
                    if (Character.isDigit(t[1]) && Character.isDigit(t[2]) && Character.isDigit(t[4]) && Character.isDigit(t[5]) && Character.isDigit(t[6]) && Character.isDigit(t[7]) && Character.isDigit(t[8]) && Character.isDigit(t[10]) && Character.isDigit(t[11]) && Character.isDigit(t[12]) && Character.isDigit(t[13]) && t[0] == '(' && t[3] == ')' && t[9] == '-') {
                        return "OK";
                    } else {
                        return "Informe um telefone válido";
                    }
                } else {
                    if (t[0] == '(' && t[3] == ')' && t[10] == '-' && Character.isDigit(t[1]) && Character.isDigit(t[2]) && t[4] == ' ' && Character.isDigit(t[5]) && Character.isDigit(t[6]) && Character.isDigit(t[7]) && Character.isDigit(t[8]) && Character.isDigit(t[9]) && Character.isDigit(t[11]) && Character.isDigit(t[12]) && Character.isDigit(t[13]) && Character.isDigit(t[14])) {
                        return "OK";
                    } else {
                        return "Informe um telefone válido";
                    }
                }

            } else {
                return "Informe um telefone válido";
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
                        if (validarTelefone(u).equals("OK")) {
                            dao.cadastrarUsuario(u);
                            return "Editado com sucesso";
                        } else {
                            return validarTelefone(u);
                        }
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
    // Dúvida
    public String excluirConta(Usuario u1, Usuario u2) {
        if (u1.equals(u2)) {
            return "Conta excluída";
        } else {
            return "Conta não encontrada";
        }
    }
    
    public String validarCPF(PessoaFisica pf) {
        String cpfFormatado = null;
        if (pf.getCpf() != null && !pf.getCpf().equals("")) {
            if (pf.getCpf().length() == 11) {
                if (pf.getCpf().equals("00000000000") || pf.getCpf().equals("11111111111")
                        || pf.getCpf().equals("22222222222") || pf.getCpf().equals("33333333333")
                        || pf.getCpf().equals("44444444444") || pf.getCpf().equals("55555555555")
                        || pf.getCpf().equals("66666666666") || pf.getCpf().equals("77777777777")
                        || pf.getCpf().equals("88888888888") || pf.getCpf().equals("99999999999")) {
                    return "Informe um CPF válido";
                } else {
                    char dig10, dig11;
                    int sm, i, r, num, peso;

                    try { // protege o codigo para eventuais erros de conversao de tipo (int)
                        // Cálculo do 1º dígito verificador
                        sm = 0;
                        peso = 10;
                        for (i = 0; i < 9; i++) {
                            // converte o i-esimo caractere do CPF em um numero:
                            // por exemplo, transforma o caractere '0' no inteiro 0         
                            // (48 eh a posicao de '0' na tabela ASCII)         
                            num = (int) (pf.getCpf().charAt(i) - 48);
                            sm = sm + (num * peso);
                            peso = peso - 1;
                        }

                        r = 11 - (sm % 11);
                        if ((r == 10) || (r == 11)) {
                            dig10 = '0';
                        } else {
                            dig10 = (char) (r + 48); // converte no respectivo caractere numerico
                        }
                        // Cálculo do 2º dígito verificador
                        sm = 0;
                        peso = 11;
                        for (i = 0; i < 10; i++) {
                            num = (int) (pf.getCpf().charAt(i) - 48);
                            sm = sm + (num * peso);
                            peso = peso - 1;
                        }

                        r = 11 - (sm % 11);
                        if ((r == 10) || (r == 11)) {
                            dig11 = '0';
                        } else {
                            dig11 = (char) (r + 48);
                        }

                        // Verifica se os digitos calculados conferem com os digitos informados.
                        if ((dig10 == pf.getCpf().charAt(9)) && (dig11 == pf.getCpf().charAt(10))) {
                            return "OK";
                        } else {
                            return "Informe um CPF válido";
                        }
                    } catch (InputMismatchException erro) {
                        return "Informe um CPF válido";
                    }
                }
            } else if (pf.getCpf().length() == 14) {

                char[] c = pf.getCpf().toCharArray();
                String cpf_ = "";
                int cont = 0;
                if (c[3] == '.' && c[7] == '.' && c[11] == '-') {

                    for (int i = 0; i < c.length; i++) {
                        if (Character.isDigit(c[i])) {
                            cpf_ = cpf_ + c[i];
                        }
                    }

                    char dig10, dig11;
                    int sm, i, r, num, peso;

                    try { // protege o codigo para eventuais erros de conversao de tipo (int)
                        // Cálculo do 1º dígito verificador
                        sm = 0;
                        peso = 10;
                        for (i = 0; i < 9; i++) {
                            // converte o i-esimo caractere do CPF em um numero:
                            // por exemplo, transforma o caractere '0' no inteiro 0         
                            // (48 eh a posicao de '0' na tabela ASCII)         
                            num = (int) (cpf_.charAt(i) - 48);
                            sm = sm + (num * peso);
                            peso = peso - 1;
                        }

                        r = 11 - (sm % 11);
                        if ((r == 10) || (r == 11)) {
                            dig10 = '0';
                        } else {
                            dig10 = (char) (r + 48); // converte no respectivo caractere numerico
                        }
                        // Cálculo do 2º dígito verificador
                        sm = 0;
                        peso = 11;
                        for (i = 0; i < 10; i++) {
                            num = (int) (cpf_.charAt(i) - 48);
                            sm = sm + (num * peso);
                            peso = peso - 1;
                        }

                        r = 11 - (sm % 11);
                        if ((r == 10) || (r == 11)) {
                            dig11 = '0';
                        } else {
                            dig11 = (char) (r + 48);
                        }

                        // Verifica se os digitos calculados conferem com os digitos informados.
                        if ((dig10 == cpf_.charAt(9)) && (dig11 == cpf_.charAt(10))) {
                            return "OK";
                        } else {
                            return "Informe um CPF válido";
                        }
                    } catch (InputMismatchException erro) {
                        return "Informe um CPF válido";
                    }

                } else {
                    return "Informe um CPF válido";
                }

            } else {
                return "Informe um CPF válido";
            }
        } else {
            return "OK";
        }
    }

    public static String imprimeCPF(String CPF) {
        return (CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "."
                + CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
    }

    

}
