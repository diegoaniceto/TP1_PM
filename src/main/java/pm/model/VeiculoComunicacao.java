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
  
    
    private String idVeiculo;
    private String tipoVeiculo;
    
	public VeiculoComunicacao(String idVeiculo, String tipoVeiculo) {
		this.idVeiculo = idVeiculo;
		this.tipoVeiculo = tipoVeiculo;
	}

   /*********************
     * Metodos de Acesso
     * 
     *********************/
   
	public String getIdVeiculo() {
		return idVeiculo;
	}

	public String getTipoVeiculo() {
		return tipoVeiculo;
	}

}
