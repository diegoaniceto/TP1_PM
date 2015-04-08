package pm.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import pm.model.ArtigoCitacoes;

public class ArtigoCitacoesControle {
	private Collection<ArtigoCitacoes> listaArtigos = new ArrayList<ArtigoCitacoes>();
	private String caminhoArquivoEntrada = "";
	
	/*
	 * Construtor da classe recebe um caminho para arquivo
	 * como parametro e eh criada uma lista de artigos
	 */
	public ArtigoCitacoesControle(String caminhoArquivoEntrada){
		//Passar o arquivo grafo_citacoes.txt
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
			ArtigoCitacoes p = new ArtigoCitacoes(tk[0],tk[1]);
			listaArtigos.add(p);
		}
	}
	
	/*
	 * retorna lista de artigos
	 */
	public Collection<ArtigoCitacoes> getListaArtigos(){
		return listaArtigos;
	}
	
	public Collection<String> getListaArtigosCitadores(String idArtigo){
		Collection<String> listaArtigosCitadores = new ArrayList<String>();
		for (ArtigoCitacoes artigo : listaArtigos) {
			if(artigo.getArtigoCitado().contentEquals(idArtigo)){
				listaArtigosCitadores.add(artigo.getArtigoCitador());
			}
		}
		return listaArtigosCitadores;
	}
	
}
