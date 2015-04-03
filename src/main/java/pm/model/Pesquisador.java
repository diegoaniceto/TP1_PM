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
public abstract class Pesquisador {
   
    private int idPesquisador;
    private char tag_titulacao;
    private int horas_ic;
    private int horas_estagio_docencia;
    private int num_grad_orient;
    private int num_M_orient;
    private int num_D_orient;

    
    public Pesquisador(int idPesquisador, char tag_titulacao, int horas_ic,
			int horas_estagio_docencia, int num_grad_orient, int num_M_orient,
			int num_D_orient) {
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
    
	public int getIdPesquisador() {
		return idPesquisador;
	}


	public char getTag_titulacao() {
		return tag_titulacao;
	}


	public int getHoras_ic() {
		return horas_ic;
	}


	public int getHoras_estagio_docencia() {
		return horas_estagio_docencia;
	}


	public int getNum_grad_orient() {
		return num_grad_orient;
	}


	public int getNum_M_orient() {
		return num_M_orient;
	}


	public int getNum_D_orient() {
		return num_D_orient;
	}

    
    // Metodos a serem implementados
    public abstract void popularidadePesquisador(Pesquisador p);
    
    
}
