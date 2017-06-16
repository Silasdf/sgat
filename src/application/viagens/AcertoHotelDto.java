package application.viagens;

import sgat.entidades.Hotel;

public class AcertoHotelDto {
	
	private Hotel hotel;
	
	private int quantidadePacotesVendidos;
	
	private double valorTotalDosPacotes;

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public int getQuantidadePacotesVendidos() {
		return quantidadePacotesVendidos;
	}

	public void setQuantidadePacotesVendidos(int quantidadePacotesVendidos) {
		this.quantidadePacotesVendidos = quantidadePacotesVendidos;
	}

	public double getValorTotalDosPacotes() {
		return valorTotalDosPacotes;
	}

	public void setValorTotalDosPacotes(double valorTotalDosPacotes) {
		this.valorTotalDosPacotes = valorTotalDosPacotes;
	}

	@Override
	public String toString() {
		return "AcertoHotelDto [hotel=" + hotel + ", quantidadePacotesVendidos=" + quantidadePacotesVendidos
				+ ", valorTotalDosPacotes=" + valorTotalDosPacotes + "]";
	}

	public AcertoHotelDto(Hotel hotel, int quantidadePacotesVendidos, double valorTotalDosPacotes) {
		super();
		this.hotel = hotel;
		this.quantidadePacotesVendidos = quantidadePacotesVendidos;
		this.valorTotalDosPacotes = valorTotalDosPacotes;
	}

}
