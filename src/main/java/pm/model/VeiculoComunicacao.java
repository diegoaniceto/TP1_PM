/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.model;

/**
 *
 * @author dhca
 */
public class VeiculoComunicacao {
  
    
    private int idVeiculo;
    private char tipoVeiculo;
    
	public VeiculoComunicacao(int idVeiculo, char tipoVeiculo) {
		this.idVeiculo = idVeiculo;
		this.tipoVeiculo = tipoVeiculo;
	}

   /*********************
     * Metodos de Acesso
     * 
     *********************/
   
	public int getIdVeiculo() {
		return idVeiculo;
	}

	public char getTipoVeiculo() {
		return tipoVeiculo;
	}

}
