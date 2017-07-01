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
package br.edu.ifrn.helppet.persistencia;

import br.edu.ifrn.helppet.dominio.Animal;
import br.edu.ifrn.helppet.dominio.Denuncia;
import br.edu.ifrn.helppet.dominio.Encontro;
import br.edu.ifrn.helppet.dominio.Estoque;
import br.edu.ifrn.helppet.dominio.Evento;
import br.edu.ifrn.helppet.dominio.PessoaFisica;
import br.edu.ifrn.helppet.dominio.PessoaJuridica;
import br.edu.ifrn.helppet.dominio.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author camila
 */
public class PersistenciaGamb {

    List<Usuario> listaDeUsuarios = new ArrayList();
    List<Animal> listaDeAnimais = new ArrayList();
    List<Denuncia> listaDeDenuncias = new ArrayList();
    List<PessoaFisica> listaDePF = new ArrayList();
    List<PessoaJuridica> listaDePJ = new ArrayList();
    List<Encontro> listaDeEncontros = new ArrayList();
    List<Estoque> listaDeEstoques = new ArrayList();
    List<Evento> listaDeEventos = new ArrayList();
    
    

    public PersistenciaGamb() {
        inserirUsuarios();
        inserirDenuncias();
    }

    public List<Usuario> ListarUsuarios() {
        return listaDeUsuarios;
    }
    
    public List<Encontro> ListarEncontros() {
        return listaDeEncontros;
    }
    
    public List<Estoque> ListarEstoques() {
        return listaDeEstoques;
    }

    public List<PessoaFisica> ListarPF() {
        return listaDePF;
    }

    public List<Denuncia> ListarDenuncias() {
        return listaDeDenuncias;
    }

    public List<Animal> ListarAnimais() {
        return listaDeAnimais;
    }
    
    public List<PessoaJuridica> ListarPJ() {
        return listaDePJ;
    }
    

    public List<Denuncia> listarPorTipo(String tipo) {
        List<Denuncia> lista = new ArrayList();
        for (Denuncia d : listaDeDenuncias) {
            if (d.getTipo() == tipo) {
                lista.add(d);
            }
        }
        return lista;
    }

    public List<Animal> filtragemAnimal(String especie, String idade, String raca, String sexo) {
        // Chama listagem do banco com os filtros
        return listaDeAnimais;
    }

    public void cadastrarDenuncia(Denuncia denuncia) {
        listaDeDenuncias.add(denuncia);
    }

    public void cadastrarAnimal(Animal animal) {
        listaDeAnimais.add(animal);
    }

    public void cadastrarEncontro(Encontro encontro) {
        listaDeEncontros.add(encontro);
    }
    
    public void cadastrarEvento(Evento evento) {
        listaDeEventos.add(evento);
    }

    public void cadastrarEstoque(Estoque estoque) {
        listaDeEstoques.add(estoque);
    }
    
    public void cadastrarUsuario(Usuario usuario) {
        listaDeUsuarios.add(usuario);
    }
    
       
    public void editarAnimal(Animal animal) {
        // MÉTODO PARA EDITAR ANIMAL
    }
    
    public void editarEstoque(Estoque estoque) {
        // MÉTODO PARA EDITAR ESTOQUE
    }
    
    public void editarDenuncia(Denuncia denuncia) {
        // MÉTODO PARA EDITAR DENUNCIA
    }
    
    public void excluirAnimal(Animal animal){
        // MÉTODO PARA EXCLUIR ANIMAL
    }
    
    public void excluirEncontro(Encontro encontro){
        // MÉTODO PARA EXCLUIR ENCONTRO
    }
    
    public void excluirEstoque(Estoque estoque){
        // MÉTODO PARA EXCLUIR ESTOQUE
    }
    
    public void excluirDenuncia(Denuncia denuncia){
        // MÉTODO PARA EXCLUIR DENÚNCIA
    }

    public void inserirUsuarios() {
        Usuario usuario1 = new Usuario();
        usuario1.setNome("Camila Jordana");
        usuario1.setEmail("camila@email.com");
        usuario1.setSenha("1234");
        usuario1.setNascimento("00/00/0000");

        listaDeUsuarios.add(usuario1);

        Usuario usuario2 = new Usuario();
        usuario2.setNome("Ana");
        usuario2.setEmail("ana@email.com");
        usuario2.setSenha("1234");
        usuario2.setNascimento("00/00/0000");

        listaDeUsuarios.add(usuario2);

        Usuario usuario3 = new Usuario();
        usuario3.setNome("Anne");
        usuario3.setEmail("anne@email.com");
        usuario3.setSenha("1234");
        usuario3.setNascimento("00/00/0000");

        listaDeUsuarios.add(usuario3);

        Usuario usuario4 = new Usuario();
        usuario4.setNome("Tatiana");
        usuario4.setEmail("tat@email.com");
        usuario4.setSenha("1234");
        usuario4.setNascimento("00/00/0000");

        listaDeUsuarios.add(usuario4);

    }

    private void inserirDenuncias() {
        Denuncia a = new Denuncia();
        a.setTitulo("denuncia A");
        a.setTipo("Outro");
        a.setDescricao("descrição da denuncia A");
        a.setLocalizacao("Natal, RN");
        listaDeDenuncias.add(a);
        Denuncia b = new Denuncia();
        b.setTitulo("denuncia B");
        b.setTipo("Outro");
        b.setDescricao("descrição da denuncia B");
        b.setLocalizacao("Natal, RN");
        listaDeDenuncias.add(b);
    }

}
