package pm.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import pm.model.ArtigoVeiculoComunicacao;

public class ArtigoVeiculoComunicacaoControle {
	private Collection<ArtigoVeiculoComunicacao> listaArtigos = new ArrayList<ArtigoVeiculoComunicacao>();
	private Collection<ArtigoVeiculoComunicacao> listaArtigosPorVeiculo = new ArrayList<ArtigoVeiculoComunicacao>();
	private String caminhoArquivoEntrada = "";
	
	/*
	 * Construtor da classe recebe um caminho para arquivo
	 * como parametro e � criada uma lista de artigos
	 */
	public ArtigoVeiculoComunicacaoControle(String caminhoArquivoEntrada){
		//Passar o arquivo artigo_veiculos.txt
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
			ArtigoVeiculoComunicacao p = new ArtigoVeiculoComunicacao(tk[0],tk[1]);
			listaArtigos.add(p);
		}
	}
	
	/*
	 * retorna lista de artigos
	 */
	public Collection<ArtigoVeiculoComunicacao> getListaArtigos(){
		return listaArtigos;
	}
	
	/*
	 * Retorna lista de artigos por veiculo 
	 */
	public Collection<ArtigoVeiculoComunicacao> getListaArtigoByIdVeiculo(String id){
		for (ArtigoVeiculoComunicacao artigo : listaArtigos) {
			if(artigo.getVeiculoComunicacaoId().contentEquals(id)){
				 listaArtigosPorVeiculo.add(artigo);
			}
		}
		return listaArtigosPorVeiculo;
	}
	/*
	 * retorna artigo por ID
	 */
	public ArtigoVeiculoComunicacao getArtigoById(String id){
		for (ArtigoVeiculoComunicacao artigo : listaArtigos) {
			if(artigo.getIdArtigo().contentEquals(id)){
				return artigo;
			}
		}
		return null;
	}
}
