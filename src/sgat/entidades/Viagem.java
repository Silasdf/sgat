package sgat.entidades;

import java.time.LocalDate;

public class Viagem {
	
	private String nome;
	private LocalDate dataIda;
	private LocalDate dataVolta;
	private String embarque;
	private String hospedagem;
	private Integer codigo;
	private String ativo;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getDataIda() {
		return dataIda;
	}
	public void setDataIda(LocalDate dataIda) {
		this.dataIda = dataIda;
	}
	public LocalDate getDataVolta() {
		return dataVolta;
	}
	public void setDataVolta(LocalDate dataVolta) {
		this.dataVolta = dataVolta;
	}
	public String getEmbarque() {
		return embarque;
	}
	public void setEmbarque(String embarque) {
		this.embarque = embarque;
	}
	public String getHospedagem() {
		return hospedagem;
	}
	public void setHospedagem(String hospedagem) {
		this.hospedagem = hospedagem;
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
		return "Viagem [nome=" + nome + ", dataIda=" + dataIda + ", dataVolta=" + dataVolta 
				+ ", embarque=" + embarque + ", hospedagem=" + hospedagem + ", codigo=" + codigo 
				+ " ativo=" + ativo + "]";
	}

}
