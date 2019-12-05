package ifpr.services;

import ifpr.exceptions.NaoPodeTerEstoqueVazioException;
import ifpr.models.Filme;
import ifpr.models.Locacao;
import ifpr.models.Usuario;
import ifpr.utils.DataUtils;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

public class LocacaoService {
	Double total = 0.0;
	Double desconto = 0.0;
	Filme f1 = new Filme("John Wick", 10.00,1000);
	Filme f2 = new Filme("Rambo 3", 15.00,1000);
	Filme f3 = new Filme("Django Livre", 10.00,1000);
	Filme f4 = new Filme("O Irlandes",20.00, 1000);
	int escolha = 6;
	Locacao locacao = new Locacao();
    public void alugarFilme(Usuario usuario, List<Filme> filme) throws Exception {
    	System.out.println("1 para John Wick");
		System.out.println("2 para Rambo 3");
		System.out.println("3 para Django Livre");
		System.out.println("4 para O Irlandes");
		System.out.println("5 para sair e finalizar o pedido");
		Scanner leia = new Scanner(System.in);
		escolha = leia.nextInt();
		switch (escolha) {
		case 1:
			locacao.getFilmes().add(f1);
			System.out.println("John Wick adicionado a sua lista de alugados");
			break;
		case 2:
			locacao.getFilmes().add(f2);
			System.out.println("Rambo 3 adicionado a sua lista de alugados");
			break;
		case 3:
			locacao.getFilmes().add(f3);
			System.out.println("Django Livre adicionado a sua lista de alugados");
			break;
		case 4:
			System.out.println("O Irlandes adicionado a sua lista de alugados");
			locacao.getFilmes().add(f4);
			break;
		case 5:
			calcularAluguel();
			break;
		}
    }
    
    public void calcularAluguel() {
    	for (int i = 0; i < locacao.getFilmes().size(); i++ ) {
            total = total + locacao.getFilmes().get(i).getPreco();
            if(i == 2){
                desconto = desconto + locacao.getFilmes().get(i).getPreco() * 0.25;
            }
            else if(i == 3){
                desconto = desconto + locacao.getFilmes().get(i).getPreco() * 0.50;
            }
            else if(i == 4){
                desconto = desconto + locacao.getFilmes().get(i).getPreco() * 0.75;
            }
            else if(i == 5){
                desconto = desconto + locacao.getFilmes().get(i).getPreco() * 0;
            }
        }
        System.out.println("Total sem desconto : " + total);
        System.out.println("Desconto : " + desconto);
        System.out.println("Total com desconto : " + (total - desconto));
        System.out.println("Quantidade de filmes : " + locacao.getFilmes().size());
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(locacao.getDataDevolucao());
        int diaDaSemana = gc.get(GregorianCalendar.DAY_OF_WEEK);
        if(diaDaSemana == 6) {
        	DataUtils.adicionarDias(locacao.getDataDevolucao(), 1);
        	System.out.println("Possui um dia bônus pois a devolução cai no domingo");
        }
    }

}
