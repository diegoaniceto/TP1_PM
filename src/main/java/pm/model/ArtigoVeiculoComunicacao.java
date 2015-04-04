/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.model;

public class ArtigoVeiculoComunicacao {
    private String idArtigo;
    private String veiculoComunicacaoId;
    
	public ArtigoVeiculoComunicacao(String idArtigo, String veiculoComunicacaoId) {
		this.idArtigo = idArtigo;
		this.veiculoComunicacaoId = veiculoComunicacaoId;
	}

	public String getIdArtigo() {
		return idArtigo;
	}

	public String getVeiculoComunicacaoId() {
		return veiculoComunicacaoId;
	}
    
    
}
