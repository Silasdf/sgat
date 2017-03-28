package framework;

import java.util.List;

import sgat.entidades.Cliente;

public interface ClientesService {
	
	// INSERT
	public void salvar(Cliente cliente);
	
	// RETRIEVE
	public List<Cliente> buscarTodos();
	
	public Cliente buscaPorCodigo(int condigo);
	
	// UPDATE
	public void atualizar(Cliente cliente);
	
	// DELETE
	public void apagar(int codigo);

	public static ClientesService getNewInstance() {
		// TODO Auto-generated method stub
		return new ClientesDBService();
	}

}