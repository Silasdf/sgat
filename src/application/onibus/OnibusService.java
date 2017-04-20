package application.onibus;

import java.util.List;
import sgat.entidades.Onibus;

public interface OnibusService {
	
	public Onibus getOnibus();
	public void setOnibus (Onibus onibus);
	
	// INSERT
	public void salvar(Onibus onibus);
			
	// RETRIEVE
	public List<Onibus> buscarOnibus(Onibus onibus);
			
	public Onibus buscaPorCodigo(int condigo);
			
	// UPDATE
	public void atualizar(Onibus onibus);
			
	// DELETE
	public void apagar(Onibus onibus);

}
