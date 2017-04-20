package sgat.entidades;

public class Onibus {
	
	private String nome;
	private String valorPorPoltrona;
	private String placaOnibus;
	private String onibusComMultas;
	private Integer anoOnibus;
	private Integer viagensRealizadas;
	private Integer codigo;
	private String ativo;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getValorPorPoltrona() {
		return valorPorPoltrona;
	}
	public void setValorPorPoltrona(String valorPorPoltrona) {
		this.valorPorPoltrona = valorPorPoltrona;
	}
	public String getPlacaOnibus() {
		return placaOnibus;
	}
	public void setPlacaOnibus(String placaOnibus) {
		this.placaOnibus = placaOnibus;
	}
	public String getOnibusComMultas() {
		return onibusComMultas;
	}
	public void setOnibusComMultas(String onibusComMultas) {
		this.onibusComMultas = onibusComMultas;
	}
	public Integer getAnoOnibus() {
		return anoOnibus;
	}
	public void setAnoOnibus(Integer anoOnibus) {
		this.anoOnibus = anoOnibus;
	}
	public Integer getViagensRealizadas() {
		return viagensRealizadas;
	}
	public void setViagensRealizadas(Integer viagensRealizadas) {
		this.viagensRealizadas = viagensRealizadas;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	
	@Override
	public String toString() {
		return "Onibus [nome=" + nome + ", valorPorPoltrona=" + valorPorPoltrona + ", onibusComMultas=" + onibusComMultas 
				+ ", anoOnibus=" + anoOnibus + ", viagensRealizadas=" + viagensRealizadas + ", codigo=" + codigo 
				+ " ativo=" + ativo + "]";
	}

}
