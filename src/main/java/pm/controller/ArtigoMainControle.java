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
	
	private ArtigoCitacoesControle artCitacoes = 
			new ArtigoCitacoesControle(arquivoEntradaArtigoCitacoes);
	private ArtigoPesquisadorControle artPesquisador = 
			new ArtigoPesquisadorControle(arquivoEntradaArtigoPesquisador);
	private ArtigoVeiculoComunicacaoControle artVeiculoComunicacao = 
			new ArtigoVeiculoComunicacaoControle(arquivoEntradaArtgioVeiculoComunicacao); 
	/*
	 * Construto da classe é necessario passar o caminho para os
	 * três arquivos
	 */
	public ArtigoMainControle(String arquivoEntradaArtigoCitacoes,
			String arquivoEntradaArtigoPesquisador,
			String arquivoEntradaArtgioVeiculoComunicacao) {
		this.arquivoEntradaArtigoCitacoes = arquivoEntradaArtigoCitacoes;
		this.arquivoEntradaArtigoPesquisador = arquivoEntradaArtigoPesquisador;
		this.arquivoEntradaArtgioVeiculoComunicacao = arquivoEntradaArtgioVeiculoComunicacao;
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
	 * Retorna lista de autores do artigo
	 */
	public Collection<String> getAutoresArtigo(String idArtigo){
		return artPesquisador.getListaIdPesquisadorAutor(idArtigo);
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
