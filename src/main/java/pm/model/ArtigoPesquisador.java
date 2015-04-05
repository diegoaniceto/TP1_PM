package pm.model;

public class ArtigoPesquisador {
	private String idArtigo;
	private String idPesquisador;
	private String ordemAutoria;
	
	public ArtigoPesquisador(String idArtigo, String idPesquisador,
			String ordemAutoria) {
		this.idArtigo = idArtigo;
		this.idPesquisador = idPesquisador;
		this.ordemAutoria = ordemAutoria;
	}

	public String getIdArtigo() {
		return idArtigo;
	}

	public String getIdPesquisador() {
		return idPesquisador;
	}

	public String getOrdemAutoria() {
		return ordemAutoria;
	}
	
	
	
}
