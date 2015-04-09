package pm.main;

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
	
	private String arquivoEntradaPesquisador ="";
	private String arquivoEntradaVeiculoComunicacao="";
	private String arquivoEntradaArtigoCitacoes="";
	private String arquivoEntradaArtigoPesquisador="";
	private String arquivoEntradaArtigoVeiculoComunicacao="";
	
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
	
	public void calculaPopularidadePesquisador(){
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
			listaPopularidadePesquisador.add(pesquisador.getIdPesquisador()+","+ popuBigDecimal);
		}
	}
	public void calculaFatorImpacto(){
		double fatorImpacto = 0;
		for (VeiculoComunicacao veiculoComunicacao : veiculo.getListaVeiculoComunicacao()) {
			
			Collection<ArtigoVeiculoComunicacao> artigoVeiculo = artigo.getListaArtigoByIdVeiculo(
					veiculoComunicacao.getIdVeiculo());
			
			double numVezesArtigoCitado = 0; 
			
			for (ArtigoVeiculoComunicacao artigoVeiculoComunicacao : artigoVeiculo) {
				numVezesArtigoCitado += artigo.getArtigosCitadores(
						artigoVeiculoComunicacao.getIdArtigo()).size();
			}
			
			double numArtigosPubVeiculo = artigo.getListaArtigoByIdVeiculo(
					veiculoComunicacao.getIdVeiculo()).size(); 
			
			if(veiculoComunicacao.getTipoVeiculo().equals("C")){
				fatorImpacto = (numVezesArtigoCitado/numArtigosPubVeiculo) + 1;
			}else if(veiculoComunicacao.getTipoVeiculo().equals("R")){
				fatorImpacto = (numVezesArtigoCitado/numArtigosPubVeiculo) + 2;
			}
			BigDecimal fatorBigDecimal = new BigDecimal(fatorImpacto).setScale(4,RoundingMode.HALF_EVEN);
			System.out.println(veiculoComunicacao.getIdVeiculo()+","+fatorBigDecimal);
		}
	}
}
