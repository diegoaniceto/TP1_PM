package pm.main;

import java.io.IOException;


public class MainClass {

	public static void main(String args[]) throws IOException {
		 String arquivoEntradaPesquisador = args[0];
		 String arquivoEntradaVeiculoComunicacao = args[1];
		 String arquivoEntradaArtigoCitacoes = args[2];
		 String arquivoEntradaArtigoPesquisador = args[3];
		 String arquivoEntradaArtigoVeiculoComunicacao = args[4];
		 
		 Resultado resultado = new Resultado(arquivoEntradaPesquisador,
				 arquivoEntradaVeiculoComunicacao, arquivoEntradaArtigoCitacoes, 
				 arquivoEntradaArtigoPesquisador, arquivoEntradaArtigoVeiculoComunicacao
				 );
		 
		 resultado.geraSaidaPopularidadePesquisador();
		 resultado.geraSaidaFatorImpacto();
		 resultado.geraSaidaQualidadeArtigo();
    }
}
