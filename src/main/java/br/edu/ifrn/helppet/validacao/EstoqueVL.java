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

import br.edu.ifrn.helppet.dominio.Estoque;
import br.edu.ifrn.helppet.dominio.PessoaJuridica;
import br.edu.ifrn.helppet.persistencia.PersistenciaGamb;
import java.util.ArrayList;

/**
 *
 * @author camila
 */
public class EstoqueVL {

    private final ArrayList<String> tipos = new ArrayList<>();
    private final int tamanhoNome = 45;
    PersistenciaGamb dao;

    public EstoqueVL() {
        // Tipos válidos
        tipos.add("limpeza");
        tipos.add("alimento");
        tipos.add("medicamento");
        tipos.add("outros");
        dao = new PersistenciaGamb();
    }

    public String cadastrarEstoque(Estoque e) {
        if (validarNome(e).equals("OK")) {
            if (validarTipo(e).equals("OK")) {
                if (validarQtdAtual(e).equals("OK")) {
                    if (validarQtdIdeal(e).equals("OK")) {
                        if (validarONG(e).equals("OK")) {
                            dao.cadastrarEstoque(e);
                            return "Cadastrado com sucesso";
                        } else {
                            return validarONG(e);
                        }
                    } else {
                        return validarQtdIdeal(e);
                    }
                } else {
                    return validarQtdAtual(e);
                }
            } else {
                return validarTipo(e);
            }
        } else {
            return validarNome(e);
        }
    }

    private String validarNome(Estoque e) {
        if (e.getProduto() != null) {
            String palavraSemEspaco = e.getProduto().trim();
            if (!e.getProduto().isEmpty() && (palavraSemEspaco.length() != 0)) {
                if (e.getProduto().length() < tamanhoNome) {
                    return "OK";
                } else {
                    return "Nome muito grande";
                }
            } else {
                return "Informe um nome válido";
            }
        } else {
            return "Nome do produto é um campo obrigatório";
        }
    }

    private String validarTipo(Estoque e) {
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

    private String validarQtdAtual(Estoque e) {
        if (e.getQtdAtual() >= 0) {
            String campoVazio = String.valueOf(e.getQtdAtual());
            if (!campoVazio.isEmpty()) {
                return "OK";
            } else {
                return "Valor inválido";
            }
        } else {
            return "Valor inválido";
        }
    }

    private String validarQtdIdeal(Estoque e) {
        if (e.getQtdIdeal() >= 0) {
            String campoVazio = String.valueOf(e.getQtdIdeal());
            if (!campoVazio.isEmpty()) {
                return "OK";
            } else {
                return "Valor inválido";
            }
        } else {
            return "Valor inválido";
        }
    }

    /*
     OBSERVAÇÃO!!!!!!!!!!!!!!
     Fiquei na dúvida se para cadastar um estoque a ONG precisaria do
     CNPJ, então NÃO COLOQUEI O CNPJ COMO REQUISITO. 
     */
    private String validarONG(Estoque estoque) {
        if (estoque.getUsuario() != null) {
            if (dao.ListarPJ().contains(estoque.getUsuario())) {
                if (estoque.getUsuario().getUsuario().getNome() != null) {
                    if (estoque.getUsuario().getUsuario().getEmail() != null) {
                        if (estoque.getUsuario().getUsuario().getLocalizacao() != null) {
                            if (estoque.getUsuario().getUsuario().getTelefone() != null) {
                                return "OK";
                            } else {
                                return "Telefone da ONG não pode ser nulo";
                            }
                        } else {
                            return "Localização da ONG não pode ser nulo";
                        }
                    } else {
                        return "E-mail da ONG não pode ser nulo";
                    }
                } else {
                    return "Nome da ONG não pode ser nulo";
                }
            } else {
                return "Usuário não cadastrado";
            }
        } else {
            return "ONG não pode ser um valor nulo";
        }
    }

    // Método pra verificar se o estoque pertence a ONG 
    //** dúvida ** 
    public String listarEncontrosUsuario(Estoque estoque, PessoaJuridica pj) {
        boolean testeUser = false, testeEnc = false;
        for (PessoaJuridica u : dao.ListarPJ()) {
            if (u.equals(pj)) {
                testeUser = true;
            }
        }
        for (Estoque e : dao.ListarEstoques()) {
            if (e.equals(estoque)) {
                if (testeUser == true) {
                    if (estoque.getUsuario().equals(pj)) {
                        // pega encontro no bd
                        testeEnc = true;
                        return "OK";
                    }
                }
            }
        }
        if (testeUser == false) {
            return "Vínculo não corresponde";
        } else {
            if (testeEnc == true) {
                return "OK";
            } else {
                return "Vínculo não corresponde";
            }
        }
    }

    public String editarEstoque(Estoque e) {
        if (validarNome(e).equals("OK")) {
            if (validarTipo(e).equals("OK")) {
                if (validarQtdAtual(e).equals("OK")) {
                    if (validarQtdIdeal(e).equals("OK")) {
                        dao.editarEstoque(e);
                        return "Editado com sucesso";
                    } else {
                        return validarQtdIdeal(e);
                    }
                } else {
                    return validarQtdAtual(e);
                }
            } else {
                return validarTipo(e);
            }
        } else {
            return validarNome(e);
        }
    }
    
    public String excluirEstoque(Estoque estoque, PessoaJuridica pj) {

        for (PessoaJuridica u : dao.ListarPJ()) {
            if (u.equals(pj)) {
                for (Estoque e : dao.ListarEstoques()) {
                    if (e.equals(estoque)) {
                        if (estoque.getUsuario().equals(pj)) {
                            dao.excluirEstoque(e);
                            return "Estoque excluído";
                        }
                    }
                }
            }
        }

        return "Estoque não encontrado";

    }

}
