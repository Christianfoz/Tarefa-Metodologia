package ifpr.services;

import static ifpr.utils.DataUtils.adicionarDias;
import static ifpr.utils.DataUtils.isMesmaData;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import ifpr.models.Filme;
import ifpr.models.Locacao;
import ifpr.models.Usuario;

public class LocacaoServiceTest {
    Usuario usuario;
    List<Filme> filmes = new ArrayList<>();
    LocacaoService service;

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException expected;

    @Before
    public void setup(){
        /*é executado antes de cada método de teste*/
        System.out.println("before");
        usuario = new Usuario("Usuario1");
        service = new LocacaoService();
    }

    @After
    public void tearDown(){
        /*é executado após de cada método de teste*/
        System.out.println("after");
    }

    @Test
    public void testeLocacao(){
        //cenario
        Filme filme = new Filme("Filme 1", 5.0, 2);

        //acao
        Locacao locacao = null;
        try {
           service.alugarFilme(usuario,filmes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //verificação
        error.checkThat(locacao.getValor(), is(5.0));
        error.checkThat(locacao.getValor(), is(not(99.0)));
        error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        error.checkThat(isMesmaData(locacao.getDataDevolucao(), adicionarDias(new Date(), 1)), is(true));
    }

    @Test
    public void naoPodeAlugarFilmeSemEstoque(){

        //cenario
        Usuario usuario = new Usuario("usuario 1");
        Filme filme = new Filme("Filme 1", 5.0, 0);

        //acao
        try {
            service.alugarFilme(usuario, filmes);
            fail("deveria ter lançado uma excecao");
        } catch (Exception e) {
            assertTrue(e.getMessage().equals("filme sem estoque"));
        }
    }

    @Test(expected = Exception.class)
    public void naoPodeAlugarFilmeSemEstoque2() throws Exception {

        //cenario
        Usuario usuario = new Usuario("usuario 1");
        Filme filme = new Filme("Filme 1", 5.0, 0);

        //acao
        service.alugarFilme(usuario, filmes);
    }

    @Test
    public void naoPodeAlugarFilmeSemEstoque3() throws Exception {

        //cenario
        Usuario usuario = new Usuario("usuario 1");
        Filme filme = new Filme("Filme 1", 5.0, 0);

        expected.expect(Exception.class);
        expected.expectMessage("filme sem estoque");

        //acao
        service.alugarFilme(usuario, filmes);
    }
}
