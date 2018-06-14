package sgat.entidades;

public class Onibus {
	
	private String nome;
	private Double valorPorPoltrona;
	private String placaOnibus;
	private String onibusComMultas;
	private Integer anoOnibus;
	private Integer viagensRealizadas;
	private String telefone;
	private Integer codigo;
	private String ativo;
	private Double valorFreteOnibus;
	private String nomeEmpresa;
//	private List<Arquivo> fotos = new ArrayList<Arquivo>();
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getValorPorPoltrona() {
		return valorPorPoltrona;
	}
	public void setValorPorPoltrona(Double valorPorPoltrona) {
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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
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

	public Double getValorFreteOnibus() {
		return valorFreteOnibus;
	}
	public void setValorFreteOnibus(Double valorFreteOnibus) {
		this.valorFreteOnibus = valorFreteOnibus;
	}
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}
//	public List<Arquivo> getFotos() {
//		return fotos;
//	}
//	public void setFotos(List<Arquivo> fotos) {
//		this.fotos = fotos;
//	}
	
	@Override
	public String toString() {
		return "Onibus [nome=" + nome + ", valorPorPoltrona=" + valorPorPoltrona + ", placaOnibus=" + placaOnibus
				+ ", onibusComMultas=" + onibusComMultas + ", anoOnibus=" + anoOnibus + ", viagensRealizadas="
				+ viagensRealizadas + ", telefone=" + telefone + ", codigo=" + codigo + ", ativo=" + ativo
				+ ", valorFreteOnibus=" + valorFreteOnibus + ", nomeEmpresa=" + nomeEmpresa + "]";
	}
	
}
