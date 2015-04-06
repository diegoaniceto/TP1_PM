package pm.main;

import java.util.Collection;

import pm.controller.ArtigoMainControle;
import pm.controller.PesquisadorControle;
import pm.controller.VeiculoComunicacaoControle;
import pm.model.Pesquisador;

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
	
	 
	private void calculaPopularidadePesquisador(){
		//Para cada pesquisador
		for (Pesquisador pesquisador : pesquisador.getListaPesquisadores()) {
			
			Collection<String> artigosPesquisador = artigo.
					getArtigosPesquisador(pesquisador.getIdPesquisador());
			
			double popularidadePesquisador = 0;
			
			//Pega os artigos
			for (String artigoId : artigosPesquisador) {
				/*
				 * Popularidade de artigos publicas
				 * = 1/(posicao relativa pub artigo) + (numero de vezes que o
				 * artigo foi citado)
				 */
				int posicao = artigo.ordemAutoriaPorPesquisador(
						artigoId,pesquisador.getIdPesquisador());
				int numVezesArtigoFoiCitado = artigo.getArtigosCitadores(artigoId).size();
				
				popularidadePesquisador += ((1/posicao) + numVezesArtigoFoiCitado);
			}
			/*
			 * somado com a quantidade de artigos publicados
			 */
			int numArtigosPublicados = artigo.getArtigosPesquisador(
					pesquisador.getIdPesquisador()).size();
			
			popularidadePesquisador += numArtigosPublicados;
			
			//Falta implementar a parte das graduações
		}
	}

}
