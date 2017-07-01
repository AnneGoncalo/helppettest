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

/**
 *
 * @author camila
 */
public class ExperienciaVL {

    private final int tamanhoNome = 45;

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

}
