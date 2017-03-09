package sgat.entidades;

import java.util.Date;

public class Cliente {
	private String nome;
	private String cpf;
	private Date dataNascimento;
	private String rg;
	private String endereco;
	private String cidade;
	private String viagemEmpresa;
	
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
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
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
	public String getViagemEmpresa() {
		return viagemEmpresa;
	}
	public void setViagemEmpresa(String viagemEmpresa) {
		this.viagemEmpresa = viagemEmpresa;
	}
	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento + ", rg=" + rg
				+ ", endereco=" + endereco + ", cidade=" + cidade + ", viagemEmpresa=" + viagemEmpresa + "]";
	}
	
}
