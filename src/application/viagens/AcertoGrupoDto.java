package application.viagens;

import sgat.entidades.Hotel;

public class AcertoGrupoDto {
	
	private int grupo;
	
	private double valorVenda;
	
	private double valorPoltrona;
	
	private int quantidadePassageirosPagantes;
	
	private Hotel hotel;

	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}

	public double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public double getValorPoltrona() {
		return valorPoltrona;
	}

	public void setValorPoltrona(double valorPoltrona) {
		this.valorPoltrona = valorPoltrona;
	}
	
	public int getQuantidadePassageirosPagantes() {
		return quantidadePassageirosPagantes;
	}

	public void setQuantidadePassageirosPagantes(int quantidadePassageirosPagantes) {
		this.quantidadePassageirosPagantes = quantidadePassageirosPagantes;
	}
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	@Override
	public String toString() {
		return "AcertoGrupoDto [grupo=" + grupo + ", valorVenda=" + valorVenda + ", valorPoltrona=" + valorPoltrona
				+ ", quantidadePassageirosPagantes=" + quantidadePassageirosPagantes + ", hotel=" + hotel + "]";
	}

}
