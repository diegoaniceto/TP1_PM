package pm.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import pm.model.Artigo;

public class ArtigoControle {
	private Collection<Artigo> listaArtigos;
	private String caminhoArquivoEntrada = "";
	
	/*
	 * Construtor da classe recebe um caminho para arquivo
	 * como parametro e é criada uma lista de artigos
	 */
	public ArtigoControle(String caminhoArquivoEntrada){
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
			Artigo p = new Artigo(/*A implementar*/);
			listaArtigos.add(p);
		}
	}
	
	/*
	 * retorna lista de artigos
	 */
	public Collection<Artigo> getListaArtigos(){
		return listaArtigos;
	}
	
	/*
	 * retorna artigo por ID
	 */
	public Artigo getArtigoById(String id){
		/*A Implementar*/
//		for (Artigo artigo : listaArtigos) {
//			if(artigo.getIdArtigo().contentEquals(id)){
//				return artigo;
//			}
//		}
		return null;
	}
}
