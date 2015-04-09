/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.model;

public class Pesquisador {
   
    private String idPesquisador;
    private String tag_titulacao;
    private String horas_ic;
    private String horas_estagio_docencia;
    private String num_grad_orient;
    private String num_M_orient;
    private String num_D_orient;

    
    public Pesquisador(String idPesquisador, String tag_titulacao, String horas_ic,
    		String horas_estagio_docencia, String num_grad_orient, String num_M_orient,
			String num_D_orient) {
		this.idPesquisador = idPesquisador;
		this.tag_titulacao = tag_titulacao;
		this.horas_ic = horas_ic;
		this.horas_estagio_docencia = horas_estagio_docencia;
		this.num_grad_orient = num_grad_orient;
		this.num_M_orient = num_M_orient;
		this.num_D_orient = num_D_orient;
	}


	/*********************
     * Metodos de Acesso
     * 
     *********************/
    
	public String getIdPesquisador() {
		return idPesquisador;
	}


	public String getTag_titulacao() {
		return tag_titulacao;
	}


	public Double getHoras_ic() {
		return Double.parseDouble(horas_ic);
	}


	public Double getHoras_estagio_docencia() {
		return Double.parseDouble(horas_estagio_docencia);
	}


	public Double getNum_grad_orient() {
		return Double.parseDouble(num_grad_orient);
	}


	public Double getNum_M_orient() {
		return Double.parseDouble(num_M_orient);
	}


	public Double getNum_D_orient() {
		return Double.parseDouble(num_D_orient);
	}
    
}
