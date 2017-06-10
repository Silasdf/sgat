package application.viagens;

import java.util.List;

import sgat.entidades.Viagem;

public interface ViagensService {
	
	public Viagem getViagem();
	public void setViagem (Viagem viagem);
	
	// INSERT
	public void salvar(Viagem viagem);
	
	// RETRIEVE
	public List<Viagem> buscarViagens(Viagem viagem);
	
	public Viagem buscaPorCodigo(int condigo);
	
	// UPDATE
	public void atualizar(Viagem viagem);
	
	// DELETE
	public void apagar(Viagem viagem);
	
	// ACERTO
	public List<AcertoGrupoDto> carregarAcerto(int codigoViagem);

}
