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

import br.edu.ifrn.helppet.dominio.Anuncio;
import br.edu.ifrn.helppet.persistencia.PersistenciaGamb;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author camila
 */
public class AnuncioVL {
    
    private final int tamanhoNome = 45;
    private final List<String> tipos = new ArrayList();
    PersistenciaGamb dao;
    
    
    public AnuncioVL(){
        // Tipos válidos
        tipos.add("alimentação");
        tipos.add("higiene");
        tipos.add("acessórios");
        tipos.add("outro");
        
    }
    
    private String validarTitulo(Anuncio a) {
        if (a.getTitulo() != null) {
            String palavraSemEspaco = a.getTitulo().trim();
            if (!a.getTitulo().isEmpty() && (palavraSemEspaco.length() != 0)) {
                if (a.getTitulo().length() < tamanhoNome) {
                    return "OK";
                } else {
                    return "Título muito grande";
                }
            } else {
                return "Informe um título válido";
            }
        } else {
            return "Título do anúncio é um campo obrigatório";
        }
    }
    
    private String validarTipo(Anuncio a) {
        if (a.getTipo() != null) {
            if (!a.getTipo().isEmpty()) {
                if (tipos.contains(a.getTipo())) {
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
    
}
