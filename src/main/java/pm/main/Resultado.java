package pm.main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;

import pm.controller.ArtigoMainControle;
import pm.controller.PesquisadorControle;
import pm.controller.VeiculoComunicacaoControle;
import pm.model.ArtigoVeiculoComunicacao;
import pm.model.Pesquisador;
import pm.model.VeiculoComunicacao;

public class Resultado {
	
	private PesquisadorControle pesquisadores;
	private VeiculoComunicacaoControle veiculo;
	private ArtigoMainControle artigo;
	
	public Resultado(String arquivoEntradaPesquisador,
			String arquivoEntradaVeiculoComunicacao,
			String arquivoEntradaArtigoCitacoes,
			String arquivoEntradaArtigoPesquisador,
			String arquivoEntradaArtigoVeiculoComunicacao) {
		
		pesquisadores = new PesquisadorControle(arquivoEntradaPesquisador);
	    veiculo = new VeiculoComunicacaoControle(arquivoEntradaVeiculoComunicacao);
		artigo = new ArtigoMainControle(arquivoEntradaArtigoCitacoes, 
				arquivoEntradaArtigoPesquisador, arquivoEntradaArtigoVeiculoComunicacao);
	}
	
	private Collection<String> listaPopularidadePesquisador = new ArrayList<String>();
	private Collection<String> listaFatorImpactoBigDecimal = new ArrayList<String>();
	private Collection<String> listaFatorImpacto = new ArrayList<String>();
	private Collection<String> listaQualidadeArtigo = new ArrayList<String>();
	
	private void calculaPopularidadePesquisador(){
		//Para cada pesquisador
		for (Pesquisador pesquisador : pesquisadores.getListaPesquisadores()) {
			
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
				double posicao = artigo.ordemAutoriaPorPesquisador(
						artigoId,pesquisador.getIdPesquisador());
				int numVezesArtigoFoiCitado = 0;
				
				//verifica se artigo foi citado
				if(!artigo.getArtigosCitadores(artigoId).isEmpty()){
					numVezesArtigoFoiCitado = artigo.getArtigosCitadores(artigoId).size();
				}
	
				popularidadePesquisador += ((1/posicao) + numVezesArtigoFoiCitado);
			}
			/*
			 * somado com a quantidade de artigos publicados
			 */
			int numArtigosPublicados = 0;
			
			//Se ele tem artigos publicados
			if(!artigo.getArtigosPesquisador(pesquisador.getIdPesquisador()).isEmpty()){
				numArtigosPublicados = artigo.getArtigosPesquisador(
						pesquisador.getIdPesquisador()).size();
			}
			
			popularidadePesquisador += numArtigosPublicados;
			
			if(pesquisador.getTag_titulacao().equals("G")){
				popularidadePesquisador += pesquisador.getHoras_ic() 
						+ pesquisador.getHoras_estagio_docencia();
			}else if(pesquisador.getTag_titulacao().equals("M")){
				popularidadePesquisador += pesquisador.getHoras_ic() 
						+ pesquisador.getHoras_estagio_docencia() + (10 * pesquisador.getNum_grad_orient());
			}else if(pesquisador.getTag_titulacao().equals("D")){
				popularidadePesquisador += pesquisador.getHoras_ic() 
						+ pesquisador.getHoras_estagio_docencia() + (10 * pesquisador.getNum_grad_orient()
						+(20 * pesquisador.getNum_M_orient()) + (30 * pesquisador.getNum_D_orient()));
			}
			BigDecimal popuBigDecimal = new BigDecimal(popularidadePesquisador).setScale(4,RoundingMode.HALF_EVEN);
			listaPopularidadePesquisador.add(pesquisador.getIdPesquisador()+";"+ popuBigDecimal);
		}
	}
	
	private void calculaFatorImpacto(){
		
		for (VeiculoComunicacao veiculoComunicacao : veiculo.getListaVeiculoComunicacao()) {
			double fatorImpacto = 0;
			
			Collection<ArtigoVeiculoComunicacao> artigoVeiculo = artigo.getListaArtigoByIdVeiculo(
					veiculoComunicacao.getIdVeiculo());
			
			double numVezesArtigoCitado = 0; 
			
			for (ArtigoVeiculoComunicacao artigoVeiculoComunicacao : artigoVeiculo) {
				numVezesArtigoCitado += artigo.getArtigosCitadores(
						artigoVeiculoComunicacao.getIdArtigo()).size();
			}
			
			double numArtigosPubVeiculo = artigoVeiculo.size(); 
			
			if(veiculoComunicacao.getTipoVeiculo().equals("C")){
				fatorImpacto = (numVezesArtigoCitado/numArtigosPubVeiculo) + 1;
			}else if(veiculoComunicacao.getTipoVeiculo().equals("R")){
				fatorImpacto = (numVezesArtigoCitado/numArtigosPubVeiculo) + 2;
			}
			BigDecimal fatorBigDecimal = new BigDecimal(fatorImpacto).setScale(4,RoundingMode.HALF_EVEN);
			//aux para calculo de qualidade artigo
			listaFatorImpacto.add(veiculoComunicacao.getIdVeiculo()+";"+fatorImpacto);
			//arredonda o valor para saida no txt
			listaFatorImpactoBigDecimal.add(veiculoComunicacao.getIdVeiculo()+";"+fatorBigDecimal);
			artigoVeiculo.clear();
		}
	}
	
	
	private void calculaQualidadeArtigo(){
		
		if(listaFatorImpacto.size() < 1){
			calculaFatorImpacto();
		}
		
		for (ArtigoVeiculoComunicacao art : artigo.getListaArtigos()) {
			double fatorImpacto = 0;
			double qualidadeArtigo = 0;
			VeiculoComunicacao veiculoComunicacaoArt = veiculo.
					getVeiculoComunicacaoById(art.getVeiculoComunicacaoId());
			
			double numVezesArtigoCitado = 0; 
			
			numVezesArtigoCitado += artigo.getArtigosCitadores(
					art.getIdArtigo()).size();
			
			for(String fator : listaFatorImpacto){
				String[] tk = fator.split(";");
				if(tk[0].equals(veiculoComunicacaoArt.getIdVeiculo())){
					fatorImpacto = Double.parseDouble(tk[1]);
				}
			}
			qualidadeArtigo = fatorImpacto * numVezesArtigoCitado;
			BigDecimal qualidadeBigDecimal = new BigDecimal(qualidadeArtigo).setScale(4,RoundingMode.HALF_EVEN);
			listaQualidadeArtigo.add(art.getIdArtigo()+";"+qualidadeBigDecimal);
		}
			
	}
	
	public void geraSaidaPopularidadePesquisador() throws IOException{
		
		if(listaPopularidadePesquisador.size() < 1){
			calculaPopularidadePesquisador();
		}
		
		FileWriter arq = new FileWriter("popularidade_pesquisador.txt");
		PrintWriter gravarArq = new PrintWriter(arq);
		for (String linha : listaPopularidadePesquisador){
			gravarArq.printf(linha+'\n');
		}
		arq.close();
		
		System.out.println("Arquivo popularidade_pesquisador.txt gerado com sucesso");
		
	}
	
	public void geraSaidaFatorImpacto() throws IOException{
		
		if(listaFatorImpactoBigDecimal.size() < 1){
			calculaFatorImpacto();
		}
		FileWriter arq = new FileWriter("fatorImpacto_veiculo.txt");
		PrintWriter gravarArq = new PrintWriter(arq);
		for (String linha : listaFatorImpactoBigDecimal){
			gravarArq.printf(linha+'\n');
		}
		arq.close();
		System.out.println("Arquivo fatorImpacto_veiculo.txt gerado com sucesso");
	}
	
	public void geraSaidaQualidadeArtigo() throws IOException{
		if(listaQualidadeArtigo.size() < 1){
			calculaQualidadeArtigo();
		}
		FileWriter arq = new FileWriter("pontuacao_artigo.txt");
		PrintWriter gravarArq = new PrintWriter(arq);
		for (String linha : listaQualidadeArtigo){
			gravarArq.printf(linha+'\n');
		}
		arq.close();
		System.out.println("Arquivo pontuacao_artigo.txt gerado com sucesso");
	}
}
