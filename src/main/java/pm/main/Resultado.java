package pm.main;

import pm.controller.ArtigoMainControle;
import pm.controller.PesquisadorControle;
import pm.controller.VeiculoComunicacaoControle;

public class Resultado {
	
	private String arquivoEntradaPesquisador;
	private String arquivoEntradaVeiculoComunicacao;
	private String arquivoEntradaArtigoCitacoes;
	private String arquivoEntradaArtigoPesquisador;
	private String arquivoEntradaArtigoVeiculoComunicacao;
	
	private PesquisadorControle pesquisador = new PesquisadorControle(arquivoEntradaPesquisador);
	private VeiculoComunicacaoControle veiculo = new VeiculoComunicacaoControle(arquivoEntradaVeiculoComunicacao);
	private ArtigoMainControle artigo = new ArtigoMainControle(arquivoEntradaArtigoCitacoes, 
			arquivoEntradaArtigoPesquisador, arquivoEntradaArtigoVeiculoComunicacao);
	
	public Resultado(String arquivoEntradaPesquisador,
			String arquivoEntradaVeiculoComunicacao,
			String arquivoEntradaArtigoCitacoes,
			String arquivoEntradaArtigoPesquisador,
			String arquivoEntradaArtigoVeiculoComunicacao) {
		this.arquivoEntradaPesquisador = arquivoEntradaPesquisador;
		this.arquivoEntradaVeiculoComunicacao = arquivoEntradaVeiculoComunicacao;
		this.arquivoEntradaArtigoCitacoes = arquivoEntradaArtigoCitacoes;
		this.arquivoEntradaArtigoPesquisador = arquivoEntradaArtigoPesquisador;
		this.arquivoEntradaArtigoVeiculoComunicacao = arquivoEntradaArtigoVeiculoComunicacao;
	}
	
	private void equacaoPopularidade(){
		int numeroArtigosPublicados = artigo.getArtigosPesquisador("").size();
		int posicao = artigo.ordemAutoriaPorPesquisador("", "");
		for (int i = 0; i < numeroArtigosPublicados; i++) {
			
		}
	}
	 
	private void calculaPopularidadePesquisador(){
		pesquisador.getListaPesquisadores();
	}

}
