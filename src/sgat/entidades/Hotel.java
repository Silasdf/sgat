package sgat.entidades;

public class Hotel {
	
	private String nome;
	private String cnpj;
	private String valorDuploPorPessoa;
	private String valorTriploPorPessoa;
	private String valorQuadruploPorPessoa;
	private String endereco;
	private String cidade;
	private String telefone;
	private Integer codigo;
	private String ativo;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nomeHotel) {
		this.nome = nomeHotel;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getValorDuploPorPessoa() {
		return valorDuploPorPessoa;
	}
	public void setValorDuploPorPessoa(String valorDuploPorPessoa) {
		this.valorDuploPorPessoa = valorDuploPorPessoa;
	}
	public String getValorTriploPorPessoa() {
		return valorTriploPorPessoa;
	}
	public void setValorTriploPorPessoa(String valorTriploPorPessoa) {
		this.valorTriploPorPessoa = valorTriploPorPessoa;
	}
	public String getValorQuadruploPorPessoa() {
		return valorQuadruploPorPessoa;
	}
	public void setValorQuadruploPorPessoa(String valorQuadruploPorPessoa) {
		this.valorQuadruploPorPessoa = valorQuadruploPorPessoa;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String enderecoHotel) {
		this.endereco = enderecoHotel;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidadeHotel) {
		this.cidade = cidadeHotel;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefoneHotel) {
		this.telefone = telefoneHotel;
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
		return "Hotel [nomeHotel=" + nome + ", cnpj=" + cnpj + ", valorDuploPorPessoa=" + valorDuploPorPessoa + ", valorTriploPorPessoa=" + valorTriploPorPessoa
				+ ", valorQuadruploPorPessoa=" + valorQuadruploPorPessoa + ", enderecoHotel=" + endereco + ", cidadeHotel=" + cidade + ", telefeneHotel=" +
				telefone + ", codigo=" + codigo + ", ativo=" + ativo + "]";
	}

}
