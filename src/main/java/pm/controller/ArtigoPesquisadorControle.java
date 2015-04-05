package pm.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import pm.model.ArtigoPesquisador;

public class ArtigoPesquisadorControle {
	private Collection<ArtigoPesquisador> listaArtigos;
	private String caminhoArquivoEntrada = "";
	
	/*
	 * Construtor da classe recebe um caminho para arquivo
	 * como parametro e é criada uma lista de artigos
	 */
	public ArtigoPesquisadorControle(String caminhoArquivoEntrada){
		//Passar o arquivo artigo_pesquisador.txt
		this.caminhoArquivoEntrada = caminhoArquivoEntrada;
		criarListaArtigos();
	}
	
	private void criarListaArtigos(){
		Collection<String> dadosArquivo = new ArrayList<String>();
		try{
			FileReader arq = new FileReader(caminhoArquivoEntrada);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = lerArq.readLine();
			while(linha != null){
				dadosArquivo.add(linha);
				linha = lerArq.readLine();
			}
			
			arq.close();
			
		}catch(IOException e){
			System.out.println("Erro ao tentar abrir o arquivo\n");
			e.getMessage();
		}
		
		String[] tk;
		
		for (String elemento : dadosArquivo) {
			tk = elemento.split(";");
			ArtigoPesquisador p = new ArtigoPesquisador(tk[0],tk[1],tk[2]);
			listaArtigos.add(p);
		}
	}
	
	
	/*
	 * retorna lista de artigos publicados por 
	 * determinado pesquisador
	 */
	public Collection<String> getListaArtigosByPesquisador(String idPesquisador){
		Collection<String> listaArtigosPorPesquisadores = new ArrayList<String>();
		for (ArtigoPesquisador artigo : listaArtigos) {
			if(artigo.getIdPesquisador().contentEquals(idPesquisador)){
				listaArtigosPorPesquisadores.add(artigo.getIdArtigo());
			}
		}
		return listaArtigosPorPesquisadores;
	}
	
	/*
	 * retorna lista de artigos
	 */
	public Collection<ArtigoPesquisador> getListaArtigos(){
		return listaArtigos;
	}
	
	public Collection<String> getListaPesquisadoresAutores(String idArtigo){
		Collection<String> listaPesquisadores = new ArrayList<String>();
		for (ArtigoPesquisador artigo : listaArtigos) {
			if(artigo.getIdArtigo().contentEquals(idArtigo)){
				listaPesquisadores.add(artigo.getIdPesquisador());
			}
		}
		return listaPesquisadores;
	}
	
	public String getOrdemAutoriaByPesquisador(String idArtigo, String idPesquisador){
		for (ArtigoPesquisador artigo : listaArtigos) {
			//Se encontrar o artigo
			if(artigo.getIdArtigo().contentEquals(idArtigo)){
				//verifica se o pesquisador participou daquele artigo
				if(artigo.getIdPesquisador().contentEquals(idPesquisador)){
					return artigo.getOrdemAutoria();
				}
			}
		}
		return null;
	}
	
	/*
	 * retorna artigoPesquisador por ID
	 */
	public ArtigoPesquisador getArtigorById(String id){
		for (ArtigoPesquisador artigo : listaArtigos) {
			if(artigo.getIdArtigo().contentEquals(id)){
				return artigo;
			}
		}
		return null;
	}
}
