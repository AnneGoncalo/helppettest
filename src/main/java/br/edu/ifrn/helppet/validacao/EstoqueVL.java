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
import java.util.ArrayList;

/**
 *
 * @author camila
 */
public class EstoqueVL {

    private final ArrayList<String> tipo = new ArrayList<>();

    public EstoqueVL() {
        // Tipos válidos
        tipo.add("Limpeza");
        tipo.add("Alimento");
        tipo.add("Medicamento");
        tipo.add("Outros");
        
    }

    private String validarNome(Estoque e) {
        if (e.getProduto() != null) {
            if (!e.getProduto().isEmpty()) {
                if (e.getProduto().length() < 45) {
                    return "OK";
                } else {
                    return "Tamanho maior que o esperado: " + e.getProduto().length();
                }
            } else {
                return "Nome do produto esta vazio";
            }
        } else {
            return "Nome do produto é um campo obrigatório";
        }
    }
    
    private String validarTipo(Estoque e){
        if (e.getTipo() != null) {
            if (!e.getTipo().isEmpty()) {
                if (tipo.contains(e.getTipo())) {
                    return "OK";
                } else {
                    return "Campo inválido: " + e.getTipo();
                }
            } else {
                return "Campo vazio";
            }
        } else {
            return "Campo nulo";
        }
    }
    
    
    private String validarQtdAtual(Estoque e){
        if(e.getQtdAtual() < 0){
            String campoVazio = String.valueOf(e.getQtdAtual());
            if(!campoVazio.isEmpty()){
                return "OK";
            } else {
                return "O campo Quantidade Atual não pode estar vazio";
            }
        } else {
            return "O campo Quantidade Atual não pode ser negativo";
        }
    }
    
      
    

}
