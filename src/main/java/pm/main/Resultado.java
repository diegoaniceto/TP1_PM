package pm.main;

import java.util.Collection;

import pm.controller.ArtigoMainControle;
import pm.controller.PesquisadorControle;
import pm.controller.VeiculoComunicacaoControle;
import pm.model.Pesquisador;

public class Resultado {
	
	private String arquivoEntradaPesquisador ="";
	private String arquivoEntradaVeiculoComunicacao="";
	private String arquivoEntradaArtigoCitacoes="";
	private String arquivoEntradaArtigoPesquisador="";
	private String arquivoEntradaArtigoVeiculoComunicacao="";
	
		
	public Resultado(String arquivoEntradaPesquisador,
			String arquivoEntradaVeiculoComunicacao,
			String arquivoEntradaArtigoCitacoes,
			String arquivoEntradaArtigoPesquisador,
			String arquivoEntradaArtigoVeiculoComunicacao) {
		this.arquivoEntradaPesquisador = arquivoEntradaPesquisador;
		this.arquivoEntradaVeiculoComunicacao = arquivoEntradaVeiculoComunicacao;
		this.arquivoEntradaArtigoCitacoes = arquivoEntradaArtigoCitacoes;
		this.arquivoEntradaArtigoPesquisador = arquivoEntradaArtigoPesquisador;
		this.arquivoEntradaArtigoVeiculoComunicacao = arquivoEntradaArtigoVeiculoComunicacao;
	}
	
	private PesquisadorControle pesquisador = new PesquisadorControle(arquivoEntradaPesquisador);
	private VeiculoComunicacaoControle veiculo = new VeiculoComunicacaoControle(arquivoEntradaVeiculoComunicacao);
	private ArtigoMainControle artigo = new ArtigoMainControle(arquivoEntradaArtigoCitacoes, 
			arquivoEntradaArtigoPesquisador, arquivoEntradaArtigoVeiculoComunicacao);
	
	Collection<Pesquisador> listaPesquisadores =  pesquisador.getListaPesquisadores();

	
	private void calculaPopularidadePesquisador(){
		//Para cada pesquisador
		for (Pesquisador pesquisador : listaPesquisadores) {
			
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
				int posicao = artigo.ordemAutoriaPorPesquisador(
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
				//teste
				System.out.println(popularidadePesquisador);
			}
		}
	}
	private void calculaFatorImpacto(){
		
	}
	public void tatuDoOk(){
		System.out.println("Keep calm funcionou!!!!!!!!!!11!");
	}
}
