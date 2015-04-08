package pm.main;


public class MainClass {

	public static void main(String args[]) {
		
		 String arquivoEntradaPesquisador = "testes-alunos/testes_15pesquisadores/entrada/pesquisadores.txt";
		 String arquivoEntradaVeiculoComunicacao = "testes-alunos/testes_15pesquisadores/entrada/veiculos.txt";
		 String arquivoEntradaArtigoCitacoes = "testes-alunos/testes_15pesquisadores/entrada/grafo_citacoes.txt";
		 String arquivoEntradaArtigoPesquisador = "testes-alunos/testes_15pesquisadores/entrada/grafo_artigos_pesquisadores.txt";
		 String arquivoEntradaArtigoVeiculoComunicacao = "testes-alunos/testes_15pesquisadores/entrada/artigos_veiculos.txt";
		 
		 Resultado resultado = new Resultado(arquivoEntradaPesquisador,
				 arquivoEntradaVeiculoComunicacao, arquivoEntradaArtigoCitacoes, 
				 arquivoEntradaArtigoPesquisador, arquivoEntradaArtigoVeiculoComunicacao
				 );
		 
		 resultado.calculaPopularidadePesquisador();
		 
    }
}
