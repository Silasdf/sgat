package sgat.entidades;

import java.io.File;

public class Arquivo {
	
	private File conteudo;
	private String nome;
	private String tipo;
	private Integer codigo;
	private String ativo;
	private Hotel hotel;
	private Onibus onibus;
		
	public File getConteudo() {
		return conteudo;
	}
	public void setConteudo(File conteudo) {
		this.conteudo = conteudo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public Onibus getOnibus() {
		return onibus;
	}
	public void setOnibus(Onibus onibus) {
		this.onibus = onibus;
	}
	
	@Override
	public String toString() {
		return "Arquivo [conteudo=" + conteudo + ", nome=" + nome + ", tipo=" + tipo + ", codigo=" + codigo + ", ativo="
				+ ativo + ", hotel=" + hotel + ", onibus=" + onibus + "]";
	}
	
}
