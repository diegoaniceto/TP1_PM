package pm.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import pm.model.Pesquisador;

public class PesquisadorControle {
	private Collection<Pesquisador> listaPesquisadores = new ArrayList<Pesquisador>();
	private String caminhoArquivoEntrada = "";
	
	/*
	 * Construtor da classe recebe um caminho para arquivo
	 * como parametro e eh criada uma lista de pesquisadores
	 */
	public PesquisadorControle(String caminhoArquivoEntrada){
		this.caminhoArquivoEntrada = caminhoArquivoEntrada;
		criarListaPesquisadores();
	}
	
	private void criarListaPesquisadores(){
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
			Pesquisador p = new Pesquisador(tk[0], tk[1], tk[2], tk[3], 
					tk[4], tk[5], tk[6]);
			listaPesquisadores.add(p);
		}
	}
	
	/*
	 * retorna lista de pesquisadores
	 */
	public Collection<Pesquisador> getListaPesquisadores(){
		return listaPesquisadores;
	}
	
	/*
	 * retorna pesquisador por ID
	 */
	public Pesquisador getPesquisadorById(String id){
		for (Pesquisador pesquisador : listaPesquisadores) {
			if(pesquisador.getIdPesquisador().contentEquals(id)){
				return pesquisador;
			}
		}
		return null;
	}
}
