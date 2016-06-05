package br.edu.ifrn.conta.servico;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.dominio.Categoria;
import br.edu.ifrn.conta.persistencia.FabricaDominio;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

@SpringApplicationConfiguration(classes = ContaApplication.class)
@WebAppConfiguration
@Test
public class CategoriaServicoIT extends AbstractTestNGSpringContextTests {

    @Inject
    private CategoriaServico categoriaServico;
    
    @BeforeMethod
    void deletarTodos()
    {
        categoriaServico.deleteAll();
        assertThat(categoriaServico.findAll()).isEmpty();
    }
    
    public void repositorioNaoEhNulo () {
        assertThat(categoriaServico).isNotNull();
    }
    
    public void salvarUm () {
        // cria o ambiente de teste
        Categoria categoria = Categoria.builder().descricao(FabricaDominio.TRANSPORTE).build();
        
        // executa a operacao a ser testada
        categoriaServico.save(categoria);
        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(categoriaServico.findAll().iterator().next()).isEqualTo(categoria);
    }
    
    public void deletarUm () {
        // cria o ambiente de teste
        Categoria categoria = Categoria.builder().descricao(FabricaDominio.TRANSPORTE).build();
        categoriaServico.save(categoria);
        
        // executa a operacao a ser testada
        categoriaServico.delete(categoria);
        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(categoriaServico.findAll().iterator().hasNext()).isFalse();
    }
    
}
