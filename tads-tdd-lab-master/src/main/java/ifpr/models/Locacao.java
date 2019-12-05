package ifpr.models;

import java.util.Date;
import java.util.List;

import ifpr.utils.DataUtils;

public class Locacao {

    private List<Filme> filmes;
    private Usuario usuario;
    private Date dataLocacao = new Date();
    private Date dataDevolucao = DataUtils.adicionarDias(dataLocacao, 7);
    private Double valor;

    public Locacao(){}

    public Locacao(List<Filme> filmes, Usuario usuario, Date dataLocacao, Date dataDevolucao, Double valor) {
        this.filmes = filmes;
        this.usuario = usuario;
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataDevolucao;
        this.valor = valor;
    }

	public List<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(Date dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

   
}
