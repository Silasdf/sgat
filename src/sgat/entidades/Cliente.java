package sgat.entidades;

import java.time.LocalDate;

public class Cliente {
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	private String rg;
	private String endereco;
	private String cidade;
	private Integer viagemEmpresa;
	private Integer codigo;
	private String ativo;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public Integer getViagemEmpresa() {
		return viagemEmpresa;
	}
	public void setViagemEmpresa(Integer viagemEmpresa) {
		this.viagemEmpresa = viagemEmpresa;
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
		return "Cliente [nome=" + nome + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento + ", rg=" + rg
				+ ", endereco=" + endereco + ", cidade=" + cidade + ", viagemEmpresa=" + viagemEmpresa + " codigo=" + codigo + " ativo=" + ativo + "]";
	}

}
