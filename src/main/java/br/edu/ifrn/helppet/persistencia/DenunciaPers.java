package br.edu.ifrn.helppet.persistencia;

import br.edu.ifrn.helppet.dominio.Denuncia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anne
 */
public class DenunciaPers {
    
    List<Denuncia> lista = new ArrayList();

    public DenunciaPers() {
	GambiInserirDenuncias();
    }
    
    public List<Denuncia> Listar(){
        
	return lista;
    }
    
    public void Cadastrar(Denuncia denuncia){
	lista.add(denuncia);
    }
    
    private void GambiInserirDenuncias(){
	Denuncia a = new Denuncia();
	a.setTitulo("denuncia A");
	a.setTipo("Outro");
	a.setDescricao("descrição da denuncia A");
	a.setLocalizacao("Natal, RN");
	lista.add(a);
	Denuncia b = new Denuncia();
	b.setTitulo("denuncia B");
	b.setTipo("Outro");
	b.setDescricao("descrição da denuncia B");
	b.setLocalizacao("Natal, RN");
	lista.add(b);
    }
    
}
