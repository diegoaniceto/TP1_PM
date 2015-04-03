package pm.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import pm.model.VeiculoComunicacao;

public class VeiculoComunicacaoControle {
	private Collection<VeiculoComunicacao> listaVeiculoComunicacao;
	private String caminhoArquivoEntrada = "";
	
	/*
	 * Construtor da classe recebe um caminho para arquivo
	 * como parametro e é criada uma lista de VeiculoComunicacao
	 */
	public VeiculoComunicacaoControle(String caminhoArquivoEntrada){
		this.caminhoArquivoEntrada = caminhoArquivoEntrada;
		criarListaVeiculoComunicacao();
	}
	
	private void criarListaVeiculoComunicacao(){
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
			VeiculoComunicacao p = new VeiculoComunicacao(tk[0], tk[1]);
			listaVeiculoComunicacao.add(p);
		}
	}
	
	/*
	 * retorna lista de pesquisadores
	 */
	public Collection<VeiculoComunicacao> getListaPesquisadores(){
		return listaVeiculoComunicacao;
	}
	
	/*
	 * retorna VeiculoComunicacao por ID
	 */
	public VeiculoComunicacao getVeiculoComunicacaoById(String id){
		for (VeiculoComunicacao veiculoComunicacao : listaVeiculoComunicacao) {
			if(veiculoComunicacao.getIdVeiculo().contentEquals(id)){
				return veiculoComunicacao;
			}
		}
		return null;
	}
}
