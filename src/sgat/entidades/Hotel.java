package sgat.entidades;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
	
	private String nome;
	private String cnpj;
	private Double valorDuploPorPessoa;
	private Double valorTriploPorPessoa;
	private Double valorQuadruploPorPessoa;
	private String endereco;
	private String cidade;
	private String telefone;
	private Integer codigo;
	private String ativo;
	private List<Arquivo> fotos = new ArrayList<Arquivo>();
	
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
	public Double getValorDuploPorPessoa() {
		return valorDuploPorPessoa;
	}
	public void setValorDuploPorPessoa(Double valorDuploPorPessoa) {
		this.valorDuploPorPessoa = valorDuploPorPessoa;
	}
	public Double getValorTriploPorPessoa() {
		return valorTriploPorPessoa;
	}
	public void setValorTriploPorPessoa(Double valorTriploPorPessoa) {
		this.valorTriploPorPessoa = valorTriploPorPessoa;
	}
	public Double getValorQuadruploPorPessoa() {
		return valorQuadruploPorPessoa;
	}
	public void setValorQuadruploPorPessoa(Double valorQuadruploPorPessoa) {
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
	public List<Arquivo> getFotos() {
		return fotos;
	}
	public void setFotos(List<Arquivo> fotos) {
		this.fotos = fotos;
	}
	
	@Override
	public String toString() {
		return "Hotel [nome=" + nome + ", cnpj=" + cnpj + ", valorDuploPorPessoa=" + valorDuploPorPessoa
				+ ", valorTriploPorPessoa=" + valorTriploPorPessoa + ", valorQuadruploPorPessoa="
				+ valorQuadruploPorPessoa + ", endereco=" + endereco + ", cidade=" + cidade + ", telefone=" + telefone
				+ ", codigo=" + codigo + ", ativo=" + ativo + ", fotos=" + fotos + "]";
	}
	
	@Override
	public boolean equals(Object object){
		
		if (object != null && object instanceof Hotel){
			if (this.codigo == ((Hotel) object).codigo){
				return true;
			}
		}
		return false;
	}

}
