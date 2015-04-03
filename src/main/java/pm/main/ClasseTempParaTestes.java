package pm.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class ClasseTempParaTestes {
	
	private static String PATH = "testes-alunos/testes_15pesquisadores/entrada/";
	public static void main(String[] args) {
		Collection<String> list = new ArrayList<String>();
		
		try{
			FileReader arq = new FileReader(PATH + "pesquisadores.txt");
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = lerArq.readLine();
			while(linha != null){
				list.add(linha);
				linha = lerArq.readLine();
			}
			
			arq.close();
			
		}catch(IOException e){
			System.out.println("Erro ao tentar abrir o arquivo: %s \n");
			e.getMessage();
		}
		
		System.out.println("Lendo a lista");
		String[] c;
		
		for (String elemento : list) {
			c = elemento.split(";");
			for (int i = 0; i < c.length; i++) {
				String string = c[i];
				System.out.print(string + " ");
			}
			System.out.println("");
		}
	}

}
