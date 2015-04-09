package pm.controller;

import java.util.Collection;

import pm.model.ArtigoVeiculoComunicacao;

/*
 * Classe que engloba os metodos das classes
 * que modelam um artigo:
 *  
 * ArtigoCitacoesControle,
 * ArtigoPesquisadorControle,
 * ArtgioVeiculoComunicacaoControle
 * 
 */
public class ArtigoMainControle {
	private String arquivoEntradaArtigoCitacoes;
	private String arquivoEntradaArtigoPesquisador;
	private String arquivoEntradaArtgioVeiculoComunicacao;
	
	private ArtigoCitacoesControle artCitacoes;
	private ArtigoPesquisadorControle artPesquisador;
	private ArtigoVeiculoComunicacaoControle artVeiculoComunicacao; 
	/*
	 * Construto da classe � necessario passar o caminho para os
	 * tr�s arquivos
	 */
	public ArtigoMainControle(String arquivoEntradaArtigoCitacoes,
			String arquivoEntradaArtigoPesquisador,
			String arquivoEntradaArtgioVeiculoComunicacao) {
		artCitacoes = new ArtigoCitacoesControle(arquivoEntradaArtigoCitacoes);
		artPesquisador = new ArtigoPesquisadorControle(arquivoEntradaArtigoPesquisador);
		artVeiculoComunicacao = new ArtigoVeiculoComunicacaoControle(arquivoEntradaArtgioVeiculoComunicacao);
	}
	/*
	 * Retorna lista de artigos por veiculo 
	 */
	public Collection<ArtigoVeiculoComunicacao> getListaArtigoByIdVeiculo(String id){
		return artVeiculoComunicacao.getListaArtigoByIdVeiculo(id);
	}
	/*
	 * Retorna lista de artigos e veiculos de comunicacao
	 */
	public Collection<ArtigoVeiculoComunicacao> getListaArtigos(){
		return artVeiculoComunicacao.getListaArtigos();
	}
	
	/*
	 * Retorna artigo por id
	 */
	public ArtigoVeiculoComunicacao getArtigoById(String id){
		return artVeiculoComunicacao.getArtigoById(id);
	}
	
	/*
	 * Retorna o valor inteiro da ordem de autoria do pesquisador no artigo
	 */
	public Integer ordemAutoriaPorPesquisador(String idArtigo,String idPesquisador){
		return Integer.parseInt(artPesquisador.
				getOrdemAutoriaByPesquisador(idArtigo, idPesquisador));
	}
	
	/*
	 * Retorna lista de artigos de determinado pesquisador
	 */
	public Collection<String> getArtigosPesquisador(String idPesquisador){
		return artPesquisador.getListaArtigosByPesquisador(idPesquisador);
	}
	
	/*
	 * Retorna lista de autores do artigo
	 */
	public Collection<String> getAutoresArtigo(String idArtigo){
		return artPesquisador.getListaPesquisadoresAutores(idArtigo);
	}
	
	/*
	 * retorna lista de artigos que citaram o artigo
	 */
	public Collection<String> getArtigosCitadores(String idArtigo){
		return artCitacoes.getListaArtigosCitadores(idArtigo);
	}
	
	public String getArquivoEntradaArtigoCitacoes() {
		return arquivoEntradaArtigoCitacoes;
	}

	public String getArquivoEntradaArtigoPesquisador() {
		return arquivoEntradaArtigoPesquisador;
	}

	public String getArquivoEntradaArtgioVeiculoComunicacao() {
		return arquivoEntradaArtgioVeiculoComunicacao;
	}
	
}
