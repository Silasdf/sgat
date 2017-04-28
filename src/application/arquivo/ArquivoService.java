package application.arquivo;

import java.util.List;

import sgat.entidades.Arquivo;

public interface ArquivoService {
	
	public Arquivo getArquivo();
	public void setArquivo (Arquivo arquivo);
	
	// INSERT
	public void salvar(Arquivo arquivo);
			
//	 RETRIEVE
	public List<Arquivo> buscarArquivo(Arquivo arquivo);
			
	public Arquivo buscaPorCodigo(int condigo);
			
	// UPDATE
	public void atualizar(Arquivo arquivo);
			
	// DELETE
	public void apagar(Arquivo arquivo);

}
