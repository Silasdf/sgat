package application.hoteis;

import java.util.List;

import sgat.entidades.Hotel;

public interface HoteisService {
	
	public Hotel getHotel();
	public void setHotel (Hotel hotel);
	
	// INSERT
		public void salvar(Hotel hotel);
		
	// RETRIEVE
	public List<Hotel> buscarHoteis(Hotel hotel);
		
	public Hotel buscaPorCodigo(int condigo);
		
	// UPDATE
	public void atualizar(Hotel hotel);
		
	// DELETE
	public void apagar(Hotel hotel);

}
